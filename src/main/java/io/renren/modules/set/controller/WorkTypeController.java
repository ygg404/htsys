package io.renren.modules.set.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import io.renren.common.annotation.SysLog;
import io.renren.modules.set.entity.WorkProjectTypeEntity;
import io.renren.modules.set.service.ProjectTypeService;
import io.renren.modules.set.service.ProjectTypeVoService;
import io.renren.modules.set.service.WorkProjectTypeService;
import io.renren.modules.set.vo.ProjectTypeVoEntity;
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

import io.renren.modules.set.entity.WorkTypeEntity;
import io.renren.modules.set.service.WorkTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 作业类型表
 *
 * @author ygg
 * @date 2019-10-29 11:52:46
 */
@RestController
@RequestMapping("set/worktype")
public class WorkTypeController {
    @Autowired
    private WorkTypeService workTypeService;
    @Autowired
    private WorkProjectTypeService workprojecttypeservice;
    @Autowired
    private ProjectTypeVoService projectTypeVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("set:worktype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workTypeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/getList")
    public R getList(){
        List<WorkTypeEntity> list = workTypeService.queryList();

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
//    @SysLog("输出指定的单条作业类型信息")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("set:worktype:info")
    public R info(@PathVariable("id") Long id){
		WorkTypeEntity workType = workTypeService.selectById(id);
		//查看作业类型ID项目类型ID表  (根据作业类型ID)
        List<WorkProjectTypeEntity> WPTypeList = workprojecttypeservice.selectByWID(id);
        List<Long> PTypeIDList = new ArrayList<Long>();
        for(WorkProjectTypeEntity item: WPTypeList){
            PTypeIDList.add(item.getPtypeid());
        }
        workType.setProjectTypeIdList(PTypeIDList);
        return R.ok().put("workType", workType);
    }

    /**
     * 保存
     */
    @SysLog("保存作业类型")
    @RequestMapping("/save")
    @RequiresPermissions("set:worktype:save")
    public R save(@RequestBody WorkTypeEntity workType){

      //保存
        workTypeService.save(workType);
      //条件查询返回ID
        Long id = workTypeService.ReturnIDAfterSave(workType);
        workType.setorderNum(id);
        workType.setId(id);
        workTypeService.update(workType);
        //批量添加
        for(Long item : workType.getProjectTypeIdList()){
            WorkProjectTypeEntity entity = new WorkProjectTypeEntity();
            entity.setWtypeid(id);
            entity.setPtypeid(item);
            //workprojecttypeservice.save(entity);
            workprojecttypeservice.insert(entity);
        }
        //批量添加还没写



        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改作业类型")
    @RequestMapping("/update")
    @RequiresPermissions("set:worktype:update")
    public R update(@RequestBody WorkTypeEntity workType){

        //1 删
        workprojecttypeservice.deleteBatchByWID(workType.getId());

        //2 增
      List<WorkProjectTypeEntity> wplist = new ArrayList<WorkProjectTypeEntity>();

        for(Long item:workType.getProjectTypeIdList()) {
            WorkProjectTypeEntity entity = new WorkProjectTypeEntity();
            entity.setWtypeid(workType.getId());
           entity.setPtypeid(item);

           wplist.add(entity);
        }
        if( wplist.size() > 0) workprojecttypeservice.insertBatch(wplist);

		//3 项目类型数据修改
        workTypeService.updateById(workType);
        return R.ok();
    }

    /**
     * 修改作业顺序
     */
    @SysLog("修改作业顺序")
    @RequestMapping("/setSort")
    @RequiresPermissions("set:worktype:update")
    public R setSort(@RequestParam Map<String, Object> params){
        workTypeService.setOrderNum(params);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除作业类型")
    @RequestMapping("/delete")
    @RequiresPermissions("set:worktype:delete")
    public R delete(@RequestBody Long[] ids){

        //作业类型ID项目类型ID表数据的批量删除 (根据的是作业类型的ID)
       workprojecttypeservice.deleteBatchByWIDs(ids);
        //作业类型的批量删除
		workTypeService.deleteBatch(ids);

        return R.ok();
    }


    /**
     * 导出作业类表Excel
     */
    @SysLog("导出作业类表")
    @RequestMapping("/exportExcel")
    public R exportExcel(HttpServletResponse response){
        try {
            List<ProjectTypeVoEntity> list = projectTypeVoService.getPtypeVoList();

            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            int rowIndex = 0;

            //生成一个表格，设置表格名称
            HSSFSheet sheet = workbook.createSheet("sheet1");
            //设置表格列宽度
            sheet.setColumnWidth(0, 100 * 100);
            sheet.setColumnWidth(1, 110 * 100);
            sheet.setColumnWidth(2, 50 * 100);
            sheet.setColumnWidth(3, 50 * 100);

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
            tcell.setCellValue(new HSSFRichTextString("项目类型名称"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(1);
            tcell.setCellValue(new HSSFRichTextString("工作类型名称"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(2);
            tcell.setCellValue(new HSSFRichTextString("单位"));
            tcell.setCellStyle(tstyle);
            tcell = tRow.createCell(3);
            tcell.setCellValue(new HSSFRichTextString("产值单价"));
            tcell.setCellStyle(tstyle);

            // 项目字体设置
            HSSFFont hfont = workbook.createFont();
            hfont.setFontName("黑体");
            hfont.setFontHeightInPoints((short) 11);
            hfont.setBold(true);   //字体加粗
            // 标题表格样式
            HSSFCellStyle hstyle = workbook.createCellStyle();
            hstyle.setFont(hfont);
            hstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
            hstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);       // 水平居中

            int size = 0;
            Long ptypeId = 0L;
            for( int i = 0; i< list.size(); i++){
                if( i == 0){
                    ptypeId = list.get(0).getPtypeId();
                }
                if(size == 1 && ptypeId != list.get(i).getPtypeId()){
                    size = 0;
                    ptypeId = list.get(i).getPtypeId();
                }
                if(size > 1 && ptypeId != list.get(i).getPtypeId()){
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex - size , rowIndex - 1, 0, 0));
                    size = 0;
                    ptypeId = list.get(i).getPtypeId();
                }
                size ++;
                HSSFRow wRow = sheet.createRow(rowIndex++);
                HSSFCell wcell;
                wcell = wRow.createCell(0);
                wcell.setCellValue(new HSSFRichTextString(list.get(i).getProjectTypeName()));
                wcell.setCellStyle(hstyle);

                wcell = wRow.createCell(1);
                wcell.setCellValue(new HSSFRichTextString(list.get(i).getworkTypeName()));
                wcell = wRow.createCell(2);
                wcell.setCellValue(new HSSFRichTextString(list.get(i).getUnit()));
                wcell = wRow.createCell(3);
                wcell.setCellValue(new HSSFRichTextString(  list.get(i).getUnitOutput()== null? "": Float.toString(list.get(i).getUnitOutput()) ));
            }
            // 获取没有项目类型的工作类型
            List<WorkTypeEntity> wlist =  workTypeService.queryListByPtypeId(0L);
            size = wlist.size();
            for(WorkTypeEntity wentity : wlist){
                HSSFRow wRow = sheet.createRow(rowIndex++);
                HSSFCell wcell;
                wcell = wRow.createCell(1);
                wcell.setCellValue(new HSSFRichTextString(wentity.getTypeName()));
                wcell = wRow.createCell(2);
                wcell.setCellValue(new HSSFRichTextString(wentity.getUnit()));
                wcell = wRow.createCell(3);
                wcell.setCellValue(new HSSFRichTextString(  wentity.getUnitOutput()== null? "": Float.toString(wentity.getUnitOutput()) ));
            }
            if(size > 1){sheet.addMergedRegion(new CellRangeAddress(rowIndex - size , rowIndex - 1, 0, 0));}

            //准备将Excel的输出流通过response输出到页面下载
            response.setContentType("application/vnd.ms-excel");
            //设置导出Excel的名称
            response.setHeader("Content-disposition", "attachment;filename="  + "1.xls");
            //刷新缓冲
            response.flushBuffer();
            //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
            workbook.write(response.getOutputStream());
            //关闭workbook
            workbook.close();

        }catch (Exception ex){
            return R.error(ex.toString());
        }
        return R.ok();
    }
}
