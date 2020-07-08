package io.renren.modules.project.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.R;
import io.renren.modules.project.service.ChartOutputService;
import io.renren.modules.project.vo.ChartOutputVoEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 产值统计表
 *
 * @author ygg
 * @date 2019-11-18 15:04:00
 */
@RestController
@RequestMapping("project/chartoutput")
public class ChartOutputController {

    @Autowired
    private ChartOutputService chartOutputService;

    /**
     * 列表
     */
//    @SysLog("查看产值统计表")
    @RequestMapping("/list")
    @RequiresPermissions("project:chartoutput")
    public R list(@RequestParam Map<String, Object> params){
        List<ChartOutputVoEntity> list = chartOutputService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 导出产值统计表Excel
     */
    @RequestMapping("/exportExcel")
    public R exportExcel(HttpServletResponse response, @RequestParam Map<String, Object> params){
        try {
            List<ChartOutputVoEntity> list = chartOutputService.queryList(params);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DecimalFormat decimalFormat=new DecimalFormat(".00");

            String startDate = params.get("startDate").toString();
            String endDate = params.get("endDate").toString();
            // 标题名称
            Calendar sDate = Calendar.getInstance();
            Calendar eDate = Calendar.getInstance();
            sDate.setTime( dateFormat.parse(startDate) );
            eDate.setTime( dateFormat.parse(endDate) );
            String title = startDate.split("-")[0] + "年" + startDate.split("-")[1] + "月";
            if ( sDate.get(Calendar.YEAR) == eDate.get(Calendar.YEAR) && sDate.get(Calendar.MONTH) == ( eDate.get(Calendar.MONTH) - 1)){
                title += "";
            } else {
                eDate.add(Calendar.MONTH, -1);
                eDate.set(Calendar.DAY_OF_MONTH, 1);
                title += "至" + eDate.get(Calendar.YEAR) + "年" + String.valueOf( eDate.get(Calendar.MONTH ) + 1) + "月";
            }
            title += "产值统计表";


            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            int rowIndex = 0;

            //生成一个表格，设置表格名称
            HSSFSheet sheet = workbook.createSheet("sheet1");
            //设置表格列宽度
            sheet.setColumnWidth(0, 180 * 100);
            sheet.setColumnWidth(1, 40 * 100);
            sheet.setColumnWidth(2, 40 * 100);
            sheet.setColumnWidth(3, 40 * 100);
            sheet.setColumnWidth(4, 40 * 100);

            // 标题字体设置
            HSSFFont hfont = workbook.createFont();
            hfont.setFontName("黑体");
            hfont.setFontHeightInPoints((short) 16);
            hfont.setBold(true);   //字体加粗
            // 标题表格样式
            HSSFCellStyle hstyle = workbook.createCellStyle();
            hstyle.setFont(hfont);
            hstyle.setVerticalAlignment(VerticalAlignment.CENTER);  // 垂直居中
            hstyle.setAlignment(HorizontalAlignment.CENTER);       // 水平居中

            // 标题行
            HSSFRow hRow = sheet.createRow(rowIndex++);
            hRow.setHeight((short) 540);
            HSSFCell hcell = hRow.createCell(0);
            hcell.setCellValue(new HSSFRichTextString(title));
            hcell.setCellStyle(hstyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

            // 字段字体设置
            HSSFFont tfont = workbook.createFont();
            tfont.setFontName("宋体");
            tfont.setFontHeightInPoints((short) 12);
            tfont.setBold(true);   //字体加粗
            // 字段表格样式
            HSSFCellStyle tstyle = workbook.createCellStyle();
            tstyle.setFont(tfont);
            tstyle.setVerticalAlignment(VerticalAlignment.CENTER);  // 垂直居中

            // 字段行
            HSSFRow tRow = sheet.createRow(rowIndex++);
            tRow.setHeight((short) 410);
            HSSFCell tcell;
            tcell = tRow.createCell(0);
            tcell.setCellValue(new HSSFRichTextString("项目名称"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(1);
            tcell.setCellValue(new HSSFRichTextString("项目负责人"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(2);
            tcell.setCellValue(new HSSFRichTextString("项目启动时间"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(3);
            tcell.setCellValue(new HSSFRichTextString("作业完成时间"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(4);
            tcell.setCellValue(new HSSFRichTextString("实际产值"));
            tcell.setCellStyle(tstyle);

            // 各队明细产值
            // 工作组名字体设置
            HSSFFont gfont = workbook.createFont();
            gfont.setFontName("宋体");
            gfont.setFontHeightInPoints((short) 9);
            gfont.setBold(true);   //字体加粗
            HSSFCellStyle gstyle = workbook.createCellStyle();
            gstyle.setFont(tfont);
            gstyle.setVerticalAlignment(VerticalAlignment.CENTER);  // 垂直居中


            Long groupId = 0L; //初始工作组Id
            String groupNameTemp = ""; // 临时工作组
            int size = 0;
            float output = 0;
            for (ChartOutputVoEntity voEntity : list) {
                if (voEntity.getgroupId() != groupId) {
                    groupId = voEntity.getgroupId();
                    if (size == 0 && output == 0) {
                        // 写入工作组名
                        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 4));
                        HSSFRow gRow = sheet.createRow(rowIndex++);
                        gRow.setHeight((short) 300);
                        HSSFCell gcell = gRow.createCell(0);
                        gcell.setCellValue(new HSSFRichTextString(voEntity.getgroupName()));
                        gcell.setCellStyle(gstyle);
                        groupNameTemp = voEntity.getgroupName();
                    } else {
                        // 写入合计产值
                        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
                        HSSFRow outRow = sheet.createRow(rowIndex++);
                        outRow.setHeight((short) 300);
                        HSSFCell outcell;
                        outcell = outRow.createCell(0);outcell.setCellValue(new HSSFRichTextString( groupNameTemp + ": 合计" + String.valueOf(size) + "个项目"));outcell.setCellStyle(gstyle);
                        outcell = outRow.createCell(4);outcell.setCellValue(new HSSFRichTextString( decimalFormat.format(output) ));outcell.setCellStyle(gstyle);
                        output = 0;
                        size = 0;
                        groupId = voEntity.getgroupId();
                        groupNameTemp = voEntity.getgroupName();
                        // 写入工作组名
                        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 4));
                        HSSFRow gRow = sheet.createRow(rowIndex++);
                        gRow.setHeight((short) 300);
                        HSSFCell gcell = gRow.createCell(0);
                        gcell.setCellValue(new HSSFRichTextString(voEntity.getgroupName()));
                        gcell.setCellStyle(gstyle);

                    }
                }
                size++;
                output += voEntity.getprojectActuallyOutput();
                HSSFRow proRow = sheet.createRow(rowIndex++);
                HSSFCell procell;
                procell = proRow.createCell(0);
                procell.setCellValue(new HSSFRichTextString(voEntity.getprojectName()));
                procell = proRow.createCell(1);
                procell.setCellValue(new HSSFRichTextString(voEntity.getProjectCharge()));
                procell = proRow.createCell(2);
                procell.setCellValue(new HSSFRichTextString( voEntity.getprojectStartDateTime() == null? "":formatter.format(voEntity.getprojectStartDateTime())));
                procell = proRow.createCell(3);
                procell.setCellValue(new HSSFRichTextString( voEntity.getwFinishDateTime() == null? "":formatter.format(voEntity.getwFinishDateTime())));
                procell = proRow.createCell(4);
                procell.setCellValue(new HSSFRichTextString(voEntity.getprojectActuallyOutput().toString()));

            }
            if(output != 0 && groupNameTemp != ""){
                // 写入合计产值
                sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
                HSSFRow outRow = sheet.createRow(rowIndex++);
                outRow.setHeight((short) 300);
                HSSFCell outcell;
                outcell = outRow.createCell(0);outcell.setCellValue(new HSSFRichTextString( groupNameTemp + ": 合计" + String.valueOf(size) + "个项目"));outcell.setCellStyle(tstyle);
                outcell = outRow.createCell(4);outcell.setCellValue(new HSSFRichTextString( decimalFormat.format(output) ));outcell.setCellStyle(tstyle);
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
        } catch (Exception ex) {
            return  R.error(ex.toString());
        }
        return R.ok();
    }

}
