package io.renren.modules.dop.controller;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import io.renren.common.annotation.SysLog;

import io.renren.common.utils.*;
import io.renren.common.utils.map.GPSUtil;
import io.renren.modules.dop.vo.MapVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.service.DopBmapService;
import org.springframework.web.multipart.MultipartFile;

import org.dom4j.Document;

import javax.servlet.http.HttpServletResponse;


/**
 * 地图标注表
 *
 * @author ygg
 * @date 2020-09-24 10:26:01
 */
@RestController
@RequestMapping("dop/bmap")
public class DopBmapController {

    @Value("${spring.file.upBmapFolder}")
    private String upBmapFolder;

    @Autowired
    private DopBmapService dopBmapService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = dopBmapService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<DopBmapEntity> list = dopBmapService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DopBmapEntity dopBmap = dopBmapService.selectById(id);

        return R.ok().put("dopBmap", dopBmap);
    }

    /**
     * 保存
     */
    @SysLog("保存地图标注")
    @RequestMapping("/save")
    @RequiresPermissions("dop:bmap:save")
    public R save(@RequestBody MapVoEntity mapVoEntity){
		dopBmapService.save(mapVoEntity.getDopBmapEntity());

        return R.ok();
    }

    /**
     * 复制保存
     */
    @SysLog("复制地图标注")
    @RequestMapping("/copy")
    public R copy(@RequestParam Map<String, Object> params){
        try {
            dopBmapService.copyList(params);
        } catch (Exception ex) {
            return R.error(ex.getMessage());
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改地图标注")
    @RequestMapping("/update")
    @RequiresPermissions("dop:bmap:update")
    public R update(@RequestBody MapVoEntity mapVoEntity){
        try {
            dopBmapService.update(mapVoEntity.getDopBmapEntity());
        } catch (Exception ex) {
            return R.error(ex.getMessage());
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除地图标注")
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("dop:bmap:delete")
    public R delete(@PathVariable("id") Long id){
        dopBmapService.deleteByPId(id);

        return R.ok();
    }

    /**
     * 上传并保存图片
     */
    @RequestMapping("/upBmapImg")
    public R upBmapImg(@RequestBody Map<String, Object> params){
        String imgPath = dopBmapService.saveBmapImg(params);

        return R.ok().put("imgPath",imgPath);
    }

    /**
     * 导入KML文件
     * @param file
     * @return
     */
    @SysLog("导入KML文件")
    @RequestMapping("/upKmlFile")
    public R upKmlFile(@PathVariable MultipartFile file,@RequestParam Map<String, Object> params) {
        BufferedReader reader = null;
        try {
            Reader read = new InputStreamReader(file.getInputStream(), "UTF-8");
            reader = new BufferedReader(read);
            String buffer = null;
            // 存放请求内容
            StringBuffer kml = new StringBuffer();
            while ((buffer = reader.readLine()) != null) {
                // 在页面中显示读取到的请求参数
                kml.append(buffer);
            }
            String kmlContent = kml.toString();
            dopBmapService.parseKML(kmlContent,params);
        }catch (Exception ex){
            return R.error(ex.getMessage());
        }
        return R.ok();
    }

    /**
     * 导出KML文件
     */
    @RequestMapping("/exportKML/{bmapId}")
    public  void exportKML(HttpServletResponse response, @PathVariable("bmapId") Long bmapId) {
        try {
            Document document = dopBmapService.createKML(bmapId);

            // 设置响应类型为html，编码为utf-8，处理相应页面文本显示的乱码
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/xml");
            response.setHeader("Content-disposition", "attachment;filename= 1.kml");
            // 发送给客户端的数据
            OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
            out.write(document.asXML());
            out.flush();
            out.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 上传点之记Word文件
     * @param file
     * @return
     */
    @SysLog("上传点之记Word文件")
    @RequestMapping("/upBmapWord")
    public R upBmapWord(@PathVariable MultipartFile file) {
        String fileName = "";
        try {
            String filePath = FileUtil.setFilePath(upBmapFolder + "word/", file.getOriginalFilename() , true);
            File dest = new File(filePath);
            fileName = dest.getName();
            file.transferTo(dest);
        }catch (Exception ex){
            return R.error(ex.getMessage());
        }
        return R.ok().put("fileName", fileName);
    }

    /**
     * 导出控制点 Word文件
     * @param params
     * @return
     */
    @SysLog("导出Word文件")
    @RequestMapping("/exportWord")
    public R exportWord(HttpServletResponse response, @RequestParam Map<String, Object> params) {
        try {
//            ClassPathResource resource =  new ClassPathResource("ftl/bPoint.ftl");
//            InputStream inputStream = resource.getInputStream();
//            File sourceFile =  resource.getFile();
            DopBmapEntity entity = dopBmapService.selectById(Long.parseLong((String)params.get("bmapId")));
            Map<String, Object> objectMap = MapEntityUtil.entityToMap(entity);
            String transImg = StringUtil.isEmpty(entity.getTransImg()) ? "" : ImgUtils.getImgBase64( upBmapFolder + entity.getTransImg());
            String photoScene = StringUtil.isEmpty(entity.getPhotoScene()) ? "" : ImgUtils.getImgBase64( upBmapFolder + entity.getPhotoScene());
            String photoFar = StringUtil.isEmpty(entity.getPhotoFar()) ? "" :ImgUtils.getImgBase64( upBmapFolder + entity.getPhotoFar());
            objectMap.put("transImg",transImg);
            objectMap.put("photoScene",photoScene);
            objectMap.put("photoFar",photoFar);
            objectMap.put("stoneTime",entity.getStoneTime() == null ? "" : DateUtils.format(entity.getStoneTime(),DateUtils.DATE_PATTERN));
            String ftl = FreeMarkerUtil.getFreeMarkerFile("bPoint.ftl",objectMap);

//
            //添加到zip
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(outputStream);
            zip.putNextEntry(new ZipEntry(entity.getLabel() + ".doc"));
            IOUtils.write(ftl, zip, "UTF-8" );

            zip.closeEntry();
            IOUtils.closeQuietly(zip);

            response.reset();
            response.addHeader("Access-Control-Allow-Origin" , "*");
            response.setHeader("Access-Control-Allow-Credentials" ,"false");
            response.setHeader("Content-Disposition", "attachment; filename=\"map.zip\"");
            response.addHeader("Content-Length", "" );
            response.setContentType("application/octet-stream; charset=UTF-8");

            IOUtils.write(outputStream.toByteArray(), response.getOutputStream());

        } catch (Exception ex) {
            return R.error(ex.getMessage());
        }

        return R.ok();
    }
}
