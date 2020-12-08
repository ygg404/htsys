package io.renren.modules.dop.controller;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import freemarker.template.Configuration;
import freemarker.template.Template;
import io.renren.common.annotation.SysLog;

import io.renren.common.utils.*;
import io.renren.common.utils.map.GPSUtil;
import io.renren.modules.dop.entity.DopBmapProjectEntity;
import io.renren.modules.dop.service.DopBmapProjectService;
import io.renren.modules.dop.vo.MapVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.service.DopBmapService;
import org.springframework.web.multipart.MultipartFile;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.DocumentHelper;

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

    @Autowired
    private DopBmapProjectService dopBmapProjectService;

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
     * 修改
     */
    @SysLog("修改地图标注")
    @RequestMapping("/update")
    @RequiresPermissions("dop:bmap:update")
    public R update(@RequestBody MapVoEntity mapVoEntity){
		dopBmapService.updateById(mapVoEntity.getDopBmapEntity());

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除地图标注")
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("dop:bmap:delete")
    public R delete(@PathVariable("id") Long id){
        dopBmapService.deleteBatch(new Long[] {id});

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
    public R upKmlFile(@PathVariable MultipartFile file) {
        SysUserEntity userEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
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
            Document doc = DocumentHelper.parseText(kmlContent);

            Element root =doc.getRootElement();
            Element document = root.element("Document");
            String  projectName = document.element("Folder").element("name").getTextTrim();
            Element folder = (Element) document.elementIterator("Folder").next();
            Iterator iter = folder.elementIterator("Placemark");
            // 插入数据库的标志列表 及其项目
            List<DopBmapEntity> bList = new ArrayList<>();
            DopBmapProjectEntity projectEntity = new DopBmapProjectEntity();
            projectEntity.setProjectName(projectName);
            // 遍历body节点
            while (iter.hasNext()) {
                DopBmapEntity entity = new DopBmapEntity();

                Element node = (Element) iter.next();
                String nodeName = node.element("name").getTextTrim();
                entity.setLabel(nodeName);
                entity.setCreateTime(new Date());
                entity.setCreateUserId(userEntity.getUserId());
                entity.setCreateUserName(userEntity.getUsername());
                // 面元素
                Element polyEle = node.element("Polygon");
                if (polyEle != null) {
                    String[] polyList = polyEle.element("outerBoundaryIs").element("LinearRing").element("coordinates").getTextTrim().split(" ");
                    String corStr = "";
                    double lng = 0f;
                    double lat = 0f;
                    for (String poly : polyList) {
                        String[] pointStr = poly.split(",");
                        double[] bd09 = GPSUtil.gps84_To_bd09(Double.parseDouble(pointStr[1]),Double.parseDouble(pointStr[0]));
                        corStr += bd09[1] + "," + bd09[0] + ";";

                        lng += bd09[1];
                        lat += bd09[0];
                    }
                    entity.setLng( lng/polyList.length);
                    entity.setLat( lat/polyList.length);
                    entity.setLabelLng( lng/polyList.length);
                    entity.setLabelLat( lat/polyList.length);
                    entity.setCoordinate(corStr.substring(0,corStr.length()-1));
                    entity.setArea(0f);   // 多边面面积计算
                    entity.setType(3L);
                }
                // 线元素
                Element lineEle = node.element("LineString");
                if (lineEle != null) {
                    String[] lineList = lineEle.element("coordinates").getTextTrim().split(" ");
                    String corStr = "";
                    double lng = 0f;
                    double lat = 0f;
                    for (String line : lineList) {
                        String[] pointStr = line.split(",");
                        double[] bd09 = GPSUtil.gps84_To_bd09(Double.parseDouble(pointStr[1]),Double.parseDouble(pointStr[0]));
                        corStr += bd09[1] + "," + bd09[0] + ";";
                        lng += bd09[1];
                        lat += bd09[0];
                    }
                    entity.setLng( lng/lineList.length);
                    entity.setLat( lat/lineList.length);
                    entity.setLabelLng( lng/lineList.length);
                    entity.setLabelLat( lat/lineList.length);
                    entity.setCoordinate(corStr.substring(0,corStr.length()-1));
                    entity.setArea(0f);
                    entity.setType(2L);
                }
                // 点元素
                Element pointEle = node.element("Point");
                if (pointEle != null) {
                    String[] pointStr = pointEle.element("coordinates").getTextTrim().split(",");
                    double[] bd09 = GPSUtil.gps84_To_bd09(Double.parseDouble(pointStr[1]),Double.parseDouble(pointStr[0]));
                    entity.setLabelLng(bd09[1]);
                    entity.setLabelLat(bd09[0]);
                    entity.setLng(bd09[1]);
                    entity.setLat(bd09[0]);
                    entity.setArea(0f);
                    entity.setType(1L);
                    entity.setCoordinate( String.valueOf(bd09[1]) + "," + String.valueOf(bd09[0]) );
                }

                bList.add(entity);
            }

            dopBmapProjectService.save(projectEntity);
            for(DopBmapEntity bEntity : bList) {
                bEntity.setProjectId(projectEntity.getId());
            }
            dopBmapService.insertOrUpdateBatch(bList);

        }catch (Exception ex){
            return R.error(ex.getMessage());
        }
        return R.ok();
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
            Map<String, Object> objectMap = MapEntityUtil.entity2Map(entity);
            String transImg = ImgUtils.getImgBase64(upBmapFolder + entity.getTransImg());
            String photoScene = ImgUtils.getImgBase64(upBmapFolder + entity.getPhotoScene());
            String photoFar = ImgUtils.getImgBase64(upBmapFolder + entity.getPhotoFar());
            objectMap.put("transImg",transImg);
            objectMap.put("photoScene",photoScene);
            objectMap.put("photoFar",photoFar);
            objectMap.put("stoneTime",entity.getStoneTime().equals("") ? "" : DateUtils.format(entity.getStoneTime(),DateUtils.DATE_PATTERN));
            String ftl = FreeMarkerUtil.getFreeMarkerFile("bPoint.ftl",objectMap);

//            // 设置响应类型为html，编码为utf-8，处理相应页面文本显示的乱码
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-disposition", "attachment;filename= 1.doc");
//            // 发送给客户端的数据
//            OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
//            out.write(ftl);
//            out.flush();
//            out.close();
//
//            //ftl模板文件
//            configuration.setClassForTemplateLoading(this.getClass(),"/");
//            //获取模板
//            Template template = configuration.getTemplate("ftl/bPoint.ftl");

//            VelocityContext context = new VelocityContext(params);
//            //渲染模板
//            StringWriter sw = new StringWriter();
//            Template tpl = Velocity.getTemplate("template/bPoint.ftl", "UTF-8" );
//            tpl.merge(context, sw);
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
