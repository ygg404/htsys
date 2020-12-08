package io.renren.modules.dop.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.MapUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.dop.service.DopArchivesService;
import io.renren.modules.project.entity.ProjectArchivesEntity;
import io.renren.modules.project.service.ProjectArchivesService;
import io.renren.modules.project.service.ProjectArchivesVoService;
import io.renren.modules.project.vo.ProjectArchivesVoEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Map;

/**
 * 项目档案表
 *
 * @author ygg
 * @date 2020-09-07 10:28:20
 */
@RestController
@RequestMapping("dop/archives")
public class DopArchivesController {

    @Autowired
    private DopArchivesService dopArchivesService;


    /**
     * 列表
     */
    @RequestMapping("/page")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dopArchivesService.queryArchivesPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam Map<String, Object> params){
        ProjectArchivesVoEntity archives = dopArchivesService.queryArchivesVo(params);

        return R.ok().put("archives", archives);
    }

    @RequestMapping("/update")
    public R update(@RequestBody ProjectArchivesEntity archive){
        dopArchivesService.update(archive);
        return R.ok();
    }

    /**
     * 导出 签字excel
     */
    @RequestMapping("/exportAuthExcel")
    public void operAutographExcel(HttpServletResponse response, @RequestParam Map<String, Object> params){
        InputStream inputStream = null;
        try {
            ClassPathResource resource =  new ClassPathResource("template/xls/proAuth.xlsx");
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            System.out.println("文件不存在"+  e);
        } finally {

        }
        ProjectArchivesVoEntity entity = dopArchivesService.queryArchivesVo(params);

        if(entity != null) {
            String projectName = entity.getprojectName();
            String autograph = entity.getSigImage();
            //接收 处理base64字符串
            String[] strs = autograph.split(",");
            String picStr = strs[1];
            byte[] byteArray = Base64.getDecoder().decode(picStr);
            try {
                Workbook xb = WorkbookFactory.create(inputStream);
                for(int sheetnum = 0; sheetnum < xb.getNumberOfSheets(); sheetnum++){
                    for (Row row : xb.getSheetAt(sheetnum)) {
                        for(int i = 0; i<= row.getLastCellNum(); i++){
                            if(row.getCell(i) != null ) {
                                if(row.getCell(i).getCellType() == 1){
                                    if(row.getCell(i).getStringCellValue().equals("项目名称")){
                                        row.getCell(i+1).setCellValue("");
                                        row.getCell(i+1).setCellValue(projectName);
                                    }
                                    if(row.getCell(i).getStringCellValue().equals("交付人")){
                                        row.getCell(i+1).setCellValue("");
                                        row.getCell(i+1).setCellValue(entity.getprojectAuthorize());
                                        row.getCell(i+3).setCellValue("");
                                        row.getCell(i+3).setCellValue(entity.getuserPhone());
                                    }
                                    if(row.getCell(i).getStringCellValue().equals("日期")){
                                        row.getCell(i+1).setCellValue("");
//                                        Date date = new Date();
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        String dateStr = sdf.format(entity.getCreateTime());
                                        row.getCell(i+1).setCellValue(dateStr);
                                    }
                                    if(row.getCell(i).getStringCellValue().equals("签收人")){
                                        try{
                                            //先删除原先的签名 再将签名img贴上去
                                            int pictureIdx = xb.addPicture(byteArray, XSSFWorkbook.PICTURE_TYPE_JPEG);
                                            CreationHelper helper = xb.getCreationHelper();
                                            Sheet sheet = xb.getSheetAt(sheetnum);

                                            double standardWidth = 56.5;
                                            double standardHeight = 37;
                                            //像素值
                                            float cellWidth = sheet.getColumnWidthInPixels(row.getCell(i+1).getColumnIndex());
                                            float cellHeigth = (row.getHeightInPoints() / 72) * 96;

                                            double a = standardWidth /cellWidth;
                                            double b = standardHeight / cellHeigth;

                                            row.getHeightInPoints();
                                            Drawing drawing = sheet.createDrawingPatriarch();
                                            ClientAnchor anchor = helper.createClientAnchor();

                                            anchor.setCol1(row.getCell(i+1).getColumnIndex());
                                            anchor.setRow1(row.getCell(i+1).getRowIndex());
                                            anchor.setCol2(row.getCell(i+1).getColumnIndex());
                                            anchor.setRow2(row.getCell(i+1).getRowIndex());

                                            Picture pict = drawing.createPicture(anchor, pictureIdx);
                                            pict.resize(a,b);
                                        }catch(Exception e){

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                response.setContentType("application/vnd.ms-excel");
                //设置导出Excel的名称
                response.setHeader("Content-disposition", "attachment;filename= 1.xls");
                //刷新缓冲
                response.flushBuffer();
                //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
                xb.write(response.getOutputStream());
                xb.close();

            } catch (Exception e) {
                System.out.println("excel oper Error: " + e );
            }
        }
    }
}
