package io.renren.modules.ren.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import io.renren.modules.project.vo.ChartQualityVoEntity;
import io.renren.modules.project.vo.ProjectVoEntity;
import io.renren.modules.ren.service.RenRecordVoService;
import io.renren.modules.ren.service.RenSalaryVoService;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.ren.vo.RenSalaryVoEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import io.renren.modules.ren.entity.RenSalaryBaseEntity;
import io.renren.modules.ren.service.RenSalaryBaseService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 基本工资表
 *
 * @author ygg
 * @date 2020-03-07 11:22:51
 */
@RestController
@RequestMapping("ren/salarybase")
public class RenSalaryBaseController {
    @Autowired
    private RenSalaryBaseService renSalaryBaseService;

    @Autowired
    private RenSalaryVoService renSalaryVoService;

    @Autowired
    private RenRecordVoService renRecordVoService;
    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("ren:salarybase:list")
    public R list(@RequestParam Map<String, Object> params){
//        List<RenRecordVoEntity> list =
        List<RenRecordVoEntity> recordList = renRecordVoService.getRecordScoreVoList(params);
        List<RenSalaryBaseEntity> salaryBaseList = renSalaryBaseService.queryList(params);

        List<RenSalaryBaseEntity> list = renSalaryBaseService.setSalaryByRecord(recordList,salaryBaseList,params);
        return R.ok().put("list", list);
    }

    /**
     *  待写
     * @param params
     * @return
     */
    @RequestMapping("/exportExcel")
    public R exportExcel(HttpServletResponse response, @RequestParam Map<String, Object> params) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            String title = "";
            if (StringUtils.isNotBlank(params.get("salaryYear").toString())) {
                title += params.get("salaryYear").toString() + "年";
            }
            if (StringUtils.isNotBlank(params.get("salaryMonth").toString())) {
                title += params.get("salaryMonth").toString() + "月";
            }
            title += "工资表";
            List<RenRecordVoEntity> recordList = renRecordVoService.getRecordScoreVoList(params);
            List<RenSalaryBaseEntity> salaryBaseList = renSalaryBaseService.queryList(params);
            List<RenSalaryBaseEntity> list = renSalaryBaseService.setSalaryByRecord(recordList,salaryBaseList,params);

            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            int rowIndex = 0;

            //生成一个表格，设置表格名称
            HSSFSheet sheet = workbook.createSheet("sheet1");
            //设置表格列宽度
            sheet.setColumnWidth(0, 50 * 100);
            sheet.setColumnWidth(1, 50 * 100);
            sheet.setColumnWidth(2, 50 * 100);
            sheet.setColumnWidth(3, 50 * 100);
            sheet.setColumnWidth(4, 50 * 100);

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
            hRow.setHeight((short) 540);
            HSSFCell hcell = hRow.createCell(0);
            hcell.setCellValue(new HSSFRichTextString(title));
            hcell.setCellStyle(hstyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

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
            tcell.setCellValue(new HSSFRichTextString("姓名"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(1);
            tcell.setCellValue(new HSSFRichTextString("固定工资"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(2);
            tcell.setCellValue(new HSSFRichTextString("住房补贴"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(3);
            tcell.setCellValue(new HSSFRichTextString("效能工资"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(4);
            tcell.setCellValue(new HSSFRichTextString("总基本工资"));
            tcell.setCellStyle(tstyle);

            //
            for (RenSalaryBaseEntity entity : list) {
                float partA = 0.00f;
                float partB = 0.00f;
                float partC = 0.00f;
                HSSFRow cRow = sheet.createRow(rowIndex++);
                cRow.setHeight((short) 280);
                HSSFCell cell;
                cell = cRow.createCell(0);
                cell.setCellValue(new HSSFRichTextString(entity.getUsername()));
                cell = cRow.createCell(1);
                if(entity.getFixedSalary() == null || entity.getFixedSalary().toString().equals("0.0")){
                    cell.setCellValue(new HSSFRichTextString(""));
                }else{
                    partA = entity.getFixedSalary().floatValue();
                    cell.setCellValue(new HSSFRichTextString(decimalFormat.format(entity.getFixedSalary())));
                }
                cell = cRow.createCell(2);
                if(entity.getHousingSalary() == null || entity.getHousingSalary().toString().equals("0.0")){
                    cell.setCellValue(new HSSFRichTextString(""));
                }else{
                    partB = entity.getHousingSalary().floatValue();
                    cell.setCellValue(new HSSFRichTextString(decimalFormat.format(entity.getHousingSalary())));
                }
                cell = cRow.createCell(3);
                if(entity.getKbiSalary() == null || entity.getKbiSalary().toString().equals("0.0")){
                    cell.setCellValue(new HSSFRichTextString(""));
                }else{
                    partC = entity.getKbiSalary().floatValue();
                    cell.setCellValue(new HSSFRichTextString(decimalFormat.format(entity.getKbiSalary())));
                }
                cell = cRow.createCell(4);
                float allSalary = partA + partB + partC;
                if(Float.toString(allSalary).equals("0.0")){
                    cell.setCellValue(new HSSFRichTextString(""));
                }else{
                    cell.setCellValue(new HSSFRichTextString(decimalFormat.format(allSalary)));
                }
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
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ren:salarybase:info")
    public R info(@PathVariable("id") Long id){
        RenSalaryBaseEntity renSalaryBase = renSalaryBaseService.selectById(id);

        return R.ok().put("renSalaryBase", renSalaryBase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ren:salarybase:save")
    public R save(@RequestBody RenSalaryBaseEntity renSalaryBase){
        renSalaryBaseService.save(renSalaryBase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:salarybase:update")
    public R update(@RequestBody RenSalaryBaseEntity renSalaryBase){
        renSalaryBaseService.updateById(renSalaryBase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:salarybase:delete")
    public R delete(@RequestBody Long[] ids){
        renSalaryBaseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     *  上传基本工资Excel
     */
    @RequestMapping("/upBaseExcel")
    public R upBaseExcel(@RequestParam("payDate") String payDate, HttpServletResponse response) {
        return R.ok();
    }
}
