package io.renren.modules.dop.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.FileUtil;
import io.renren.modules.dop.entity.DopBmapProjectEntity;
import io.renren.modules.dop.service.DopBmapProjectService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.service.DopBmapService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.DocumentHelper;


/**
 * 地图标注表
 *
 * @author ygg
 * @date 2020-09-24 10:26:01
 */
@RestController
@RequestMapping("dop/bmap")
public class DopBmapController {
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
    public R save(@RequestBody DopBmapEntity dopBmap){
		dopBmapService.save(dopBmap);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改地图标注")
    @RequestMapping("/update")
    @RequiresPermissions("dop:bmap:update")
    public R update(@RequestBody DopBmapEntity dopBmap){
		dopBmapService.updateById(dopBmap);

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
                    Float lng = 0f;
                    Float lat = 0f;
                    for (String poly : polyList) {
                        String[] pointStr = poly.split(",");
                        corStr += pointStr[0] + "," + pointStr[1] + ";";

                        lng += Float.parseFloat(pointStr[0]);
                        lat += Float.parseFloat(pointStr[1]);
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
                    Float lng = 0f;
                    Float lat = 0f;
                    for (String line : lineList) {
                        String[] pointStr = line.split(",");
                        corStr += pointStr[0] + "," + pointStr[1] + ";";

                        lng += Float.parseFloat(pointStr[0]);
                        lat += Float.parseFloat(pointStr[1]);
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
                    entity.setLabelLng(Float.parseFloat(pointStr[0]));
                    entity.setLabelLat(Float.parseFloat(pointStr[1]));
                    entity.setLng(Float.parseFloat(pointStr[0]));
                    entity.setLat(Float.parseFloat(pointStr[1]));
                    entity.setArea(0f);
                    entity.setType(1L);
                    entity.setCoordinate(pointStr[0] + "," + pointStr[1]);
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
}
