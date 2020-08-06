package io.renren.modules.project.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.R;
import io.renren.modules.project.service.ChartOutputService;
import io.renren.modules.project.service.ChartQualityService;
import io.renren.modules.project.vo.ChartOutputVoEntity;
import io.renren.modules.project.vo.ChartQualityVoEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 质量统计表
 *
 * @author ygg
 * @date 2019-11-18 15:04:00
 */
@RestController
@RequestMapping("project/chartquality")
public class ChartQualityController {

    @Autowired
    private ChartQualityService chartQualityService;

    /**
     * 列表
     */
//    @SysLog("查看质量统计表")
    @RequestMapping("/list")
    @RequiresPermissions("project:chartquality")
    public R list(@RequestParam Map<String, Object> params){
        List<ChartQualityVoEntity> list = chartQualityService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 列表
     */
    @SysLog("导出质量统计表")
    @RequestMapping("/exportExcel")
    @RequiresPermissions("project:chartquality")
    public R exportExcel(HttpServletResponse response, @RequestParam Map<String, Object> params){
        try {
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            List<ChartQualityVoEntity> list = chartQualityService.queryList(params);

            String title = "";
            if (StringUtils.isNotBlank(params.get("startDate").toString())) {
                title += params.get("startDate").toString();
            }
            if (StringUtils.isNotBlank(params.get("endDate").toString())) {
                title += params.get("endDate").toString();
            } else {
                title += "至今";
            }
            title += "  质量统计表";

            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            int rowIndex = 0;

            //生成一个表格，设置表格名称
            HSSFSheet sheet = workbook.createSheet("sheet1");
            //设置表格列宽度
            sheet.setColumnWidth(0, 170 * 100);
            sheet.setColumnWidth(1, 40 * 100);
            sheet.setColumnWidth(2, 40 * 100);
            sheet.setColumnWidth(3, 40 * 100);
            sheet.setColumnWidth(4, 40 * 100);
            sheet.setColumnWidth(5, 40 * 100);

            // 标题字体设置
            HSSFFont hfont = workbook.createFont();
            hfont.setFontName("黑体");
            hfont.setFontHeightInPoints((short) 16);
            hfont.setBold(true);   //字体加粗
            // 标题表格样式
            HSSFCellStyle hstyle = workbook.createCellStyle();
            hstyle.setFont(hfont);
            hstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
            hstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);       // 水平居中

            // 标题行
            HSSFRow hRow = sheet.createRow(rowIndex++);
            hRow.setHeight((short) 550);
            HSSFCell hcell = hRow.createCell(0);
            hcell.setCellValue(new HSSFRichTextString(title));
            hcell.setCellStyle(hstyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

            // 字段字体设置
            HSSFFont tfont = workbook.createFont();
            tfont.setFontName("宋体");
            tfont.setFontHeightInPoints((short) 12);
            tfont.setBold(true);   //字体加粗
            // 字段表格样式
            HSSFCellStyle tstyle = workbook.createCellStyle();
            tstyle.setFont(tfont);
            tstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中

            // 字段行
            HSSFRow tRow = sheet.createRow(rowIndex++);
            tRow.setHeight((short) 420);
            HSSFCell tcell;
            tcell = tRow.createCell(0);
            tcell.setCellValue(new HSSFRichTextString("项目名称"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(1);
            tcell.setCellValue(new HSSFRichTextString("质量评分"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(2);
            tcell.setCellValue(new HSSFRichTextString("质量等级"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(3);
            tcell.setCellValue(new HSSFRichTextString("项目负责人"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(4);
            tcell.setCellValue(new HSSFRichTextString("质检员"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(5);
            tcell.setCellValue(new HSSFRichTextString("质检审核员"));
            tcell.setCellStyle(tstyle);


            String groupName = "";
            List<GroupScoreItem> scoreList = new ArrayList<>();
            for( int i=0; i<list.size(); i++){
                if(list.get(i).getgroupName() != null) {
                    GroupScoreItem item = new GroupScoreItem();
                    item.groupName = list.get(i).getgroupName();
                    groupName = list.get(i).getgroupName();
                    // 查找该工作组的所有质量评分列表
                    for (int t = i; t < list.size(); t++) {
                        if (groupName.equals(list.get(t).getgroupName())) {
                            item.qualityList.add(list.get(t));
                            item.setLevelNum(list.get(t).getqualityScore() == null ? 0 : list.get(t).getqualityScore());
                            i=t;
                        } else {
                            i = t - 1;
                            break;
                        }
                    }
                    scoreList.add(item);
                }
            }

            for(GroupScoreItem item : scoreList){
                // 各队明细产值
                // 工作组名字体设置
                HSSFFont gfont = workbook.createFont();
                gfont.setFontName("宋体");
                gfont.setFontHeightInPoints((short) 11);
                gfont.setBold(true);   //字体加粗
                HSSFCellStyle gstyle = workbook.createCellStyle();
                gstyle.setFont(gfont);
                gstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
                sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
                HSSFRow gRow = sheet.createRow(rowIndex++);
                gRow.setHeight((short) 300);
                HSSFCell gcell = gRow.createCell(0);
                gcell.setCellValue(new HSSFRichTextString(item.groupName));
                gcell.setCellStyle(gstyle);

                // 各组各项目评分明细
                for( ChartQualityVoEntity entity : item.qualityList){
                    HSSFRow cRow = sheet.createRow(rowIndex++);
                    cRow.setHeight((short) 280);
                    HSSFCell cell;
                    cell = cRow.createCell(0);cell.setCellValue(new HSSFRichTextString( entity.getprojectName() ));
                    cell = cRow.createCell(1);cell.setCellValue(new HSSFRichTextString( entity.getqualityScore()!= null? entity.getqualityScore().toString(): "" ));
                    cell = cRow.createCell(2);cell.setCellValue(new HSSFRichTextString( entity.getqualityScore()!= null? getLevel(entity.getqualityScore()): ""));
                    cell = cRow.createCell(3);cell.setCellValue(new HSSFRichTextString( entity.getprojectCharge()));
                    cell = cRow.createCell(4);cell.setCellValue(new HSSFRichTextString( entity.getqualityUserName() ));
                    cell = cRow.createCell(5);cell.setCellValue(new HSSFRichTextString( entity.getqualityConfirmName() ));
                }

                sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 5));
                gRow = sheet.createRow(rowIndex++);
                gRow.setHeight((short) 300);
                gcell = gRow.createCell(0);
                gcell.setCellValue(new HSSFRichTextString( item.groupName +  ":合计 "  + item.projectNum  + " 个项目" ));
                gcell.setCellStyle(gstyle);
                gcell = gRow.createCell(1);
                String totalAmount = "优:" + String.valueOf(item.exceNum) + "项；" +
                        "良:" + String.valueOf(item.fineNum) + "项；" +
                        "及格:" + String.valueOf(item.passNum) + "项；" +
                        "不及格:" + String.valueOf(item.badNum) + "项；" +
                        "优良:" + String.valueOf(item.exceNum + item.fineNum) + "项；" +
                        "优良率:" + decimalFormat.format((float)(item.exceNum + item.fineNum) * 100 / item.projectNum ) + "%；";
                gcell.setCellValue(new HSSFRichTextString( totalAmount));
                gcell.setCellStyle(gstyle);


            }


            //准备将Excel的输出流通过response输出到页面下载
            response.setContentType("application/vnd.ms-excel");
            //设置导出Excel的名称
            response.setHeader("Content-disposition", "attachment;filename=" + title + ".xls");
            //刷新缓冲
            response.flushBuffer();
            //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
            workbook.write(response.getOutputStream());
            //关闭workbook
            workbook.close();
        } catch (Exception ex){
            return R.error(ex.toString());
        }
        return R.ok();
    }


    //质量等级返回
    public String getLevel(float qualityScore){
        if (qualityScore < 60) {
           return  "不合格";
        } else if (qualityScore >= 60 && qualityScore <= 70) {
            return "合格";
        } else if (qualityScore > 70 && qualityScore < 90) {
             return  "良";
        } else if (qualityScore >= 90) {
            return  "优";
        }
        return "";
    }

    // 工作组质量统计类
    public class GroupScoreItem{
        public String groupName;
        public int exceNum;  // 优秀数量
        public int fineNum;  // 良好数量
        public int passNum;  // 及格数量
        public int badNum;  // 不及格数量
        public int projectNum;  // 项目数
        public List<ChartQualityVoEntity> qualityList; // 工作组项目质量列表

        public GroupScoreItem(){
            groupName = "";
            exceNum = 0;
            fineNum = 0;
            passNum = 0;
            badNum = 0;
            projectNum = 0;
            qualityList = new ArrayList<>();
        }

        // 通过分数设置 优良中差数量
        public void setLevelNum( float qualityScore){
            if (qualityScore < 60) {
                this.badNum ++;
            } else if (qualityScore >= 60 && qualityScore <= 70) {
                this.passNum ++;
            } else if (qualityScore > 70 && qualityScore < 90) {
                this.fineNum ++;
            } else if (qualityScore >= 90) {
                this.exceNum ++;
            }
            projectNum ++;
        }


    }
}
