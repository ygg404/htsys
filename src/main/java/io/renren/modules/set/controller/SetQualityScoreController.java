package io.renren.modules.set.controller;

import java.io.InputStream;
import java.util.*;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.DateUtil;
import io.renren.common.utils.StringUtil;
import org.apache.lucene.search.FieldCache;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.SetQualityScoreEntity;
import io.renren.modules.set.service.SetQualityScoreService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 质量评分表设置
 *
 * @author ygg
 * @date 2020-08-14 10:17:40
 */
@RestController
@RequestMapping("set/qualityscore")
public class SetQualityScoreController {
    @Autowired
    private SetQualityScoreService setQualityScoreService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SetQualityScoreEntity> list = setQualityScoreService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取文件列表
     */
    @RequestMapping("/fileList")
    public R fileList(){
        List<String> list = setQualityScoreService.getFileList();

        return R.ok().put("list", list);
    }

    /**
     * 获取文件列表
     */
    @RequestMapping("/fileNoList")
    public R fileNoList(){
        List<SetQualityScoreEntity> list = setQualityScoreService.getFileNoList();

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{typeId}")
    @RequiresPermissions("set:qualityscore:info")
    public R info(@PathVariable("typeId") Long typeId){
		SetQualityScoreEntity setQualityScore = setQualityScoreService.selectById(typeId);

        return R.ok().put("setQualityScore", setQualityScore);
    }

    /**
     * 导入Excel
     */
    @SysLog("导入质量评分表")
    @RequestMapping("/importFile")
    @RequiresPermissions("set:qualityscore:import")
    public R importFile(@RequestParam("file") MultipartFile file){
        try {
            //获取workbook对象
            Workbook workbook = null;
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            //根据后缀名是否excel文件
            if(filename.endsWith("xls")){
                //2003
                workbook = new HSSFWorkbook(inputStream);
            }else if(filename.endsWith("xlsx")){
                //2007
                workbook = new XSSFWorkbook(inputStream);
            }
            if(workbook != null) {
                // 获取文件编号(年+月+日+两位数字)
                String dateNum = DateUtil.getDays();
                List<String> fileList = setQualityScoreService.getFileList();
                int maxNum = 1;
                for(String fileNo : fileList) {
                    if( fileNo.contains(dateNum)) {
                        maxNum = Integer.parseInt(fileNo.replace(dateNum,"")) + 1;
                        break;
                    }
                }
                Long fileNo = Long.parseLong(dateNum + String.format("%02d", maxNum));

                //循环sheet,现在是单sheet
                for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                    //获取第一个sheet
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    //获取当前sheet开始行和结束行
                    int firstRowNum = sheet.getFirstRowNum();
                    int lastRowNum = sheet.getLastRowNum();
                    List<SetQualityScoreEntity> list = new ArrayList<>();
                    //循环开始，除了前两行
                    for(int rowNum = firstRowNum + 2;rowNum <= lastRowNum;rowNum++){
                        //获取当前行
                        Row row = sheet.getRow(rowNum);
                        SetQualityScoreEntity entity = new SetQualityScoreEntity();
                        if (StringUtil.isEmpty(row.getCell(0)) || row.getCell(0).toString().equals("")) break;
                        entity.setFileNo(fileNo);
                        entity.setQualityCate(row.getCell(0).toString());
                        entity.setScoreRadio(Float.parseFloat(row.getCell(1).toString()));
                        entity.setTypeaName(StringUtil.isEmpty(row.getCell(2))? null:row.getCell(2).toString());
                        entity.setTypebName(StringUtil.isEmpty(row.getCell(3))? null:row.getCell(3).toString());
                        entity.setTypecName(StringUtil.isEmpty(row.getCell(4))? null:row.getCell(4).toString());
                        entity.setTypedName(StringUtil.isEmpty(row.getCell(5))? null:row.getCell(5).toString());
                        list.add(entity);

                    }
                    setQualityScoreService.insertBatch(list);
                    break;
                }
            }
        } catch (Exception ex) {
            return R.error("读取文件失败！");
        }

        return R.ok("提交文件成功！");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("set:qualityscore:update")
    public R update(@RequestBody SetQualityScoreEntity setQualityScore){
		setQualityScoreService.updateById(setQualityScore);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("set:qualityscore:delete")
    public R delete(@RequestParam String fileNo){
        if (fileNo == null) {
            return R.error("当前文件编号为空，无法删除！");
        }
		setQualityScoreService.deleteByFileNo(fileNo);

        return R.ok();
    }

}
