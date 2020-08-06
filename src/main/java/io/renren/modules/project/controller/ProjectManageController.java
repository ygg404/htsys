package io.renren.modules.project.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.project.service.ProjectManageService;
import io.renren.modules.project.vo.ProjectVoEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 项目管理
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@RestController
@RequestMapping("project/manage")
public class ProjectManageController {
    @Autowired
    public ProjectManageService projectManageService;

    /**
     * 列表
     */
//    @SysLog("查看项目管理")
    @RequestMapping("/page")
    @RequiresPermissions("project:manage:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = projectManageService.getProjectManagPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 导出项目列表
     */
    @SysLog("导出项目管理列表")
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, @RequestParam Map<String, Object> params) {
//        PageUtils page = projectManageService.getProjectManagPage(params);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat mdateFormat = new SimpleDateFormat("yyyy-MM");
            List<ProjectVoEntity> list = projectManageService.getProjectManagList(params);

            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            int rowIndex = 0;

            //生成一个表格，设置表格名称
            HSSFSheet sheet = workbook.createSheet("sheet1");
            //设置表格列宽度
            sheet.setColumnWidth(0, 40 * 100);
            sheet.setColumnWidth(1, 130 * 100);
            sheet.setColumnWidth(2, 90 * 100);
            sheet.setColumnWidth(3, 40 * 100);
            sheet.setColumnWidth(4, 40 * 100);
            sheet.setColumnWidth(5, 40 * 100);
            sheet.setColumnWidth(6, 40 * 100);
            sheet.setColumnWidth(7, 40 * 100);
            sheet.setColumnWidth(8, 40 * 100);
            sheet.setColumnWidth(9, 40 * 100);
            sheet.setColumnWidth(10, 40 * 100);
            sheet.setColumnWidth(11, 40 * 100);
            sheet.setColumnWidth(12, 40 * 100);
            sheet.setColumnWidth(13, 40 * 100);
            sheet.setColumnWidth(14, 40 * 100);
            sheet.setColumnWidth(15, 40 * 100);
            sheet.setColumnWidth(16, 40 * 100);
            // 字段字体设置
            HSSFFont tfont = workbook.createFont();
            tfont.setFontName("黑体");
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
            tcell.setCellValue(new HSSFRichTextString("项目编号"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(1);
            tcell.setCellValue(new HSSFRichTextString("项目名称"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(2);
            tcell.setCellValue(new HSSFRichTextString("项目类型"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(3);
            tcell.setCellValue(new HSSFRichTextString("委托单位"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(4);
            tcell.setCellValue(new HSSFRichTextString("生产负责人"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(5);
            tcell.setCellValue(new HSSFRichTextString("项目负责人"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(6);
            tcell.setCellValue(new HSSFRichTextString("项目启动时间"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(7);
            tcell.setCellValue(new HSSFRichTextString("项目开工时间"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(8);
            tcell.setCellValue(new HSSFRichTextString("作业完成时间"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(9);
            tcell.setCellValue(new HSSFRichTextString("质检完成时间"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(10);
            tcell.setCellValue(new HSSFRichTextString("结算时间"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(11);
            tcell.setCellValue(new HSSFRichTextString("作业工期"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(12);
            tcell.setCellValue(new HSSFRichTextString("质检工期"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(13);
            tcell.setCellValue(new HSSFRichTextString("返修天数"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(14);
            tcell.setCellValue(new HSSFRichTextString("暂停天数"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(15);
            tcell.setCellValue(new HSSFRichTextString("作业超时天数"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(16);
            tcell.setCellValue(new HSSFRichTextString("质检超时天数"));
            tcell.setCellStyle(tstyle);

            // 各组各项目评分明细
            for (ProjectVoEntity entity : list) {
                HSSFRow cRow = sheet.createRow(rowIndex++);
                cRow.setHeight((short) 280);
                HSSFCell cell;
                cell = cRow.createCell(0);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectNo()));
                cell = cRow.createCell(1);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectName()));
                cell = cRow.createCell(2);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectType()));
                cell = cRow.createCell(3);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectAuthorize()));
                cell = cRow.createCell(4);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectProduce()));
                cell = cRow.createCell(5);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectCharge()));
                cell = cRow.createCell(6);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectStartDateTime() == null ? "" :dateFormat.format(entity.getprojectStartDateTime())));
                cell = cRow.createCell(7);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectBegunDateTime() == null ? "" :dateFormat.format(entity.getprojectBegunDateTime())));
                cell = cRow.createCell(8);
                cell.setCellValue(new HSSFRichTextString(entity.getwFinishDateTime() == null ? "" :dateFormat.format(entity.getwFinishDateTime())));
                cell = cRow.createCell(9);
                cell.setCellValue(new HSSFRichTextString(entity.getqFinishDateTime() == null ? "" :dateFormat.format(entity.getqFinishDateTime())));
                cell = cRow.createCell(10);
                cell.setCellValue(new HSSFRichTextString(entity.getCutOffTime() == null ? "" :mdateFormat.format(entity.getCutOffTime())));
                cell = cRow.createCell(11);
                cell.setCellValue(new HSSFRichTextString(entity.getprojectWorkDate() == null ? "" : entity.getprojectWorkDate().toString()));
                cell = cRow.createCell(12);
                cell.setCellValue(new HSSFRichTextString( entity.getprojectQualityDate() == null ? "" : entity.getprojectQualityDate().toString()));
                cell = cRow.createCell(13);
                cell.setCellValue(new HSSFRichTextString( entity.getbackDateNum() == null ? "0" : entity.getbackDateNum().toString()));
                cell = cRow.createCell(14);
                cell.setCellValue(new HSSFRichTextString( entity.getsuspendDay() == null ? "0" : entity.getsuspendDay().toString()));
                // 作业超时天数
                String workoutDay = "-";
                if( !(entity.getprojectBegunDateTime() == null || entity.getwFinishDateTime() == null) ){
                    Long day = (entity.getwFinishDateTime().getTime() - entity.getprojectBegunDateTime().getTime()) / (1000*3600*24)
                            - (entity.getsuspendDay() == null ? 0 :entity.getsuspendDay() )  + (entity.getbackDateNum() == null ? 0 :entity.getbackDateNum() );
                    workoutDay = String.valueOf( day < 0 ? 0: day);
                }
                cell = cRow.createCell(15);
                cell.setCellValue(new HSSFRichTextString( workoutDay));
                // 质检超时时间
                String checkoutDay = "-";
                if( !(entity.getqFinishDateTime() == null || entity.getwFinishDateTime() == null) ){
                    Long day =  (entity.getqFinishDateTime().getTime() - entity.getwFinishDateTime().getTime()) / (1000*3600*24) - (entity.getsuspendDay() == null ? 0 :entity.getsuspendDay() );
                    checkoutDay = String.valueOf( day < 0 ? 0: day);
                }
                cell = cRow.createCell(16);
                cell.setCellValue(new HSSFRichTextString( checkoutDay));
            }

            //准备将Excel的输出流通过response输出到页面下载
            response.setContentType("application/vnd.ms-excel");
            //设置导出Excel的名称
            response.setHeader("Content-disposition", "attachment;filename= 1.xls");
            //刷新缓冲
            response.flushBuffer();
            //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
            workbook.write(response.getOutputStream());
            //关闭workbook
            workbook.close();
        } catch (Exception ex){
            ;
        }
    }
}
