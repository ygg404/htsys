package io.renren.modules.ren.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.MapUtils;
import io.renren.modules.ren.entity.RenAttendDaysEntity;
import io.renren.modules.ren.service.RenAttendDaysService;
import io.renren.modules.ren.service.RenAttendVoService;
import io.renren.modules.ren.vo.RenAttendTempVoEntity;
import io.renren.modules.ren.vo.RenAttendVoEntity;
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

import io.renren.modules.ren.entity.RenAttendEntity;
import io.renren.modules.ren.service.RenAttendService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;


/**
 * 出勤表
 *
 * @author ygg
 * @date 2020-03-10 10:10:43
 */
@RestController
@RequestMapping("ren/attend")
public class RenAttendController {
    @Autowired
    private RenAttendService renAttendService;

    @Autowired
    private RenAttendVoService renAttendVoService;

    @Autowired
    private RenAttendDaysService renAttendDaysService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("ren:attend:list")
    public R list(@RequestParam Map<String, Object> params){
        List<RenAttendEntity> list = renAttendService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/attendVoList")
//    @RequiresPermissions("ren:attend:list")
    public R queryAttendVoList( @RequestParam Map<String, Object> params ){
        List<RenAttendVoEntity> list = renAttendVoService.queryRenAttendVoList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
//    @RequiresPermissions("ren:attend:info")
    public R info(@RequestParam Map<String, Object> params ){
		List<RenAttendVoEntity> list = renAttendVoService.queryRenAttendVoList(params);
		if( list.size() == 0 ){
           list = renAttendVoService.queryBranchUserList();
        }
		String date = params.get("attendTime").toString();
        RenAttendDaysEntity daysEntity = renAttendDaysService.getByDate(date);

        return R.ok().put("renAttend", list).put("daysEntity" , daysEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenAttendTempVoEntity renAttendTemp){
        List<RenAttendEntity> renAttendlist = renAttendTemp.getRenAttendlist();
        // 保存出勤列表先删除 月份的出勤表
        renAttendService.deleteByDate( renAttendlist.get(0).getattendTime() );
        renAttendService.insertBatch(renAttendlist);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:attend:delete")
    public R delete(@RequestBody Long[] ids){
		renAttendService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 导出 出勤表Excel
     */
    @RequestMapping("/exportAttendExcel")
    public R exportAttendExcel(HttpServletResponse response, @RequestParam("attendTime") String attendTime){
        try {
            // 获取某月的出勤列表
            Map<String, Object> params = new MapUtils().put("attendTime", attendTime);
            List<RenAttendVoEntity> list = renAttendVoService.queryRenAttendVoList(params);
            // 获取应出勤天数
            RenAttendDaysEntity daysEntity = renAttendDaysService.getByDate(attendTime);

            // 生成文件名称
            String fileName = attendTime.split("-")[0] + "年" + attendTime.split("-")[1] + "月出勤表.xls";
            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            int rowIndex = 0;
            // 字体设置
            HSSFFont font = workbook.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 11);
            font.setBold(true);   //字体加粗
            //表格样式
            HSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            //生成一个表格，设置表格名称
            HSSFSheet sheet = workbook.createSheet("attend");
            sheet.setDefaultRowHeight((short) 450);
            sheet.setDefaultColumnWidth(15);    //设置表格列宽度
            // 标题
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
            headerStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER);       // 水平居中
            headerStyle.setFont(font);
            String title = fileName.replace(".xls","") + "(应出勤天数：" + Float.toString(daysEntity.getattendDays()) + "天）";
            HSSFRow headerRow = sheet.createRow(rowIndex++);
            headerRow.setHeight((short) 530);
            HSSFCell headercell = headerRow.createCell(0);
            headercell.setCellValue(new HSSFRichTextString(title));
            headercell.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            //字段头部
            HSSFRow firstRow = sheet.createRow(rowIndex++);
            firstRow.setHeight((short) 420);
            HSSFCell firstcell;
            //创建一个单元格 //将内容对象的文字内容写入到单元格中
            firstcell = firstRow.createCell(0);firstcell.setCellValue(new HSSFRichTextString("部门"));firstcell.setCellStyle(style);
            firstcell = firstRow.createCell(1);firstcell.setCellValue(new HSSFRichTextString("姓名"));firstcell.setCellStyle(style);
            firstcell = firstRow.createCell(2);firstcell.setCellValue(new HSSFRichTextString("内业天数"));firstcell.setCellStyle(style);
            firstcell = firstRow.createCell(3);firstcell.setCellValue(new HSSFRichTextString("外业天数"));firstcell.setCellStyle(style);
            firstcell = firstRow.createCell(4);firstcell.setCellValue(new HSSFRichTextString("加班天数"));firstcell.setCellStyle(style);
            firstcell = firstRow.createCell(5);firstcell.setCellValue(new HSSFRichTextString("出勤天数"));firstcell.setCellStyle(style);
            firstcell = firstRow.createCell(6);firstcell.setCellValue(new HSSFRichTextString("请假天数"));firstcell.setCellStyle(style);
            firstcell = firstRow.createCell(7);firstcell.setCellValue(new HSSFRichTextString("备注"));firstcell.setCellStyle(style);

            Long brandId = 0L;
            int fRow = 1;
            int size = 0;
            List<Merge> mergeList = new ArrayList<>();
            for (RenAttendVoEntity entity : list) {
                //创建一个row行，然后自增1
                HSSFRow row = sheet.createRow(rowIndex++);
                HSSFCell cell;
                //创建一个单元格 //将内容对象的文字内容写入到单元格中
                cell = row.createCell(0);cell.setCellValue(new HSSFRichTextString(entity.getBranchName()));    //部门
                cell = row.createCell(1);cell.setCellValue(new HSSFRichTextString(entity.getuserName()));       //姓名
                cell = row.createCell(2);cell.setCellValue(new HSSFRichTextString(entity.getinDay().toString()));   //内业天数
                cell = row.createCell(3);cell.setCellValue(new HSSFRichTextString(entity.getoutDay().toString()));  //外业天数
                cell = row.createCell(4);cell.setCellValue(new HSSFRichTextString(entity.getovertime().toString()));  //加班天数
                cell = row.createCell(5);cell.setCellValue(new HSSFRichTextString(entity.getallDay().toString()));  //出勤天数
                cell = row.createCell(6);cell.setCellValue(new HSSFRichTextString(entity.getleave().toString()));   //请假天数
                cell = row.createCell(7);cell.setCellValue(new HSSFRichTextString(entity.getremark())); // 备注
                // 部门列合并单元格
                if( brandId != entity.getBranchId()){
                    brandId = entity.getBranchId();
                    Merge merge = new Merge();
                    merge.firstRow = fRow;
                    merge.lastRow = fRow + size;
                    mergeList.add(merge);
                    fRow = fRow + size + 1;
                    size = 0;
                } else {
                    size++;
                }
            }
            Merge merge = new Merge();
            merge.firstRow = fRow;
            merge.lastRow = fRow + size;
            mergeList.add(merge);
            for(Merge mentity : mergeList) {
                if ( mentity.firstRow != mentity.lastRow)
                sheet.addMergedRegion(new CellRangeAddress(mentity.firstRow, mentity.lastRow, 0, 0));
            }

            //准备将Excel的输出流通过response输出到页面下载
            response.setContentType("application/vnd.ms-excel");
            //设置导出Excel的名称
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            //刷新缓冲
            response.flushBuffer();
            //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
            workbook.write(response.getOutputStream());
            //关闭workbook
            workbook.close();
        }catch (Exception ex) {
            return  R.error(ex.toString());
        }
        return R.ok();
    }

    public class Merge{
        public int firstRow;
        public int lastRow;
    }
}
