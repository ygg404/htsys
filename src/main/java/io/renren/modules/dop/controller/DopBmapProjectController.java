package io.renren.modules.dop.controller;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.common.utils.map.GPSUtil;
import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.entity.DopBmapProjectEntity;
import io.renren.modules.dop.service.DopBmapProjectService;
import io.renren.modules.dop.service.DopBmapService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 百度地图项目名称
 *
 * @author ygg
 * @date 2020-10-22 10:56:31
 */
@RestController
@RequestMapping("dop/bmapproject")
public class DopBmapProjectController {
    @Autowired
    private DopBmapProjectService dopBmapProjectService;
    @Autowired
    private DopBmapService dopBmapService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = dopBmapProjectService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DopBmapProjectEntity dopBmapProject = dopBmapProjectService.selectById(id);

        return R.ok().put("dopBmapProject", dopBmapProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dop:bmap:save")
    public R save(@RequestBody DopBmapProjectEntity dopBmapProject){
		dopBmapProjectService.save(dopBmapProject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dop:bmap:update")
    public R update(@RequestBody DopBmapProjectEntity dopBmapProject){
		dopBmapProjectService.updateById(dopBmapProject);

        return R.ok();
    }

    /**
     * 删除(项目及其标注信息)
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("dop:bmap:delete")
    public R delete(@PathVariable("id") Long id){
		dopBmapProjectService.deleteBatch(new Long[] {id});
        dopBmapService.deleteByProId(id);
        return R.ok();
    }

    /**
     * 导出项目KML
     */
    @RequestMapping("/exportKML/{projectId}")
    public  void exportKML(HttpServletResponse response, @PathVariable("projectId") Long projectId) {
        try {
            List<DopBmapEntity> list = dopBmapService.queryByProId(projectId);
            DopBmapProjectEntity project = dopBmapProjectService.selectById(projectId);
            //接收项目id
            Element root = DocumentHelper.createElement("kml");
            Document document = DocumentHelper.createDocument(root);
            //根节点添加属性
            root.addAttribute("xmlns", "http://www.opengis.net/kml/2.2")
                    .addAttribute("xmlns:gx", "http://www.google.com/kml/ext/2.2");
            Element documentElement = root.addElement("Document");

            String pointStyleId = "2707719465";
            String pointStyleIdStr = "#" + pointStyleId;
            String lineStyleId = "559648089";
            String lineStyleIdStr = "#" + lineStyleId;
            String polygonStyleId = "3411153112";
            String polygonStyleIdStr = "#" + polygonStyleId;

            //添加样式
            Element styleElement1 = documentElement.addElement("Style");
            styleElement1.addAttribute("id", lineStyleId);
            Element linestyleElement1 = styleElement1.addElement("LineStyle");
            linestyleElement1.addElement("color").addText("ff0000ff");
            linestyleElement1.addElement("width").addText("1");

            Element styleElement2 = documentElement.addElement("Style");
            styleElement2.addAttribute("id", pointStyleId);
            Element iconstyleElement = styleElement2.addElement("IconStyle");
            iconstyleElement.addElement("scale").addText("1");
            Element iconElement = iconstyleElement.addElement("icon");
            iconElement.addElement("href").addText("http://maps.google.com/mapfiles/kml/paddle/ylw-square.png");

            Element styleElement3 = documentElement.addElement("Style");
            styleElement3.addAttribute("id", polygonStyleId);
            Element linestyleElement2 = styleElement3.addElement("LineStyle");
            linestyleElement2.addElement("color").addText("ff0000ff");
            linestyleElement2.addElement("width").addText("1");
            Element polyStyleElement = styleElement3.addElement("PolyStyle");
            polyStyleElement.addElement("color").addText("40ff0000");

            //图层内容
            Element folderElement = documentElement.addElement("Folder");
            folderElement.addElement("name").addText(project.getProjectName());

            for (DopBmapEntity item : list) {
                Element placemarkElement;
                switch (item.getType().intValue()) {
                    //点
                    case 1:
                        placemarkElement = folderElement.addElement("Placemark");
                        placemarkElement.addElement("name").addText(item.getLabel());
                        placemarkElement.addElement("styleUrl").addText(pointStyleIdStr);
                        Element pointElement = placemarkElement.addElement("Point");
                        // 经纬度 由百度坐标 转化为 84坐标系
                        double[] gps84 = GPSUtil.bd09_To_gps84(  item.getLat() , item.getLng());
                        String pointcoordinates = gps84[1]  + "," + gps84[0];
                        pointElement.addElement("coordinates").addText(pointcoordinates);
                        break;
                    //线
                    case 2:
                        placemarkElement = folderElement.addElement("Placemark");
                        placemarkElement.addElement("name").addText(item.getLabel());
                        placemarkElement.addElement("styleUrl").addText(lineStyleIdStr);
                        Element lineStringElement = placemarkElement.addElement("LineString");
                        String coordinateData = "";
                        // 经纬度 由百度坐标 转化为 84坐标系
                        for (String corItem : item.getCoordinate().split(";")){
                            // 经纬度 由百度坐标 转化为 84坐标系
                            double[] lineGps84 = GPSUtil.bd09_To_gps84(  Double.parseDouble(corItem.split(",")[1])
                                                        , Double.parseDouble(corItem.split(",")[0]));
                            coordinateData += String.valueOf(lineGps84[1]) + "," + String.valueOf(lineGps84[0]) + ";";
                        }
                        String coordinateText = coordinateData.replaceAll(";","  " );
                        lineStringElement.addElement("coordinates").addText(coordinateText);
                        break;
                    //面
                    case 3:
                        placemarkElement = folderElement.addElement("Placemark");
                        placemarkElement.addElement("name").addText(item.getLabel());
                        placemarkElement.addElement("styleUrl").addText(polygonStyleIdStr);
                        Element polygonElement = placemarkElement.addElement("Polygon");
                        polygonElement.addElement("tessellate").addText("1");
                        Element outerBoundaryIsElement = polygonElement.addElement("outerBoundaryIs");
                        Element linearRingElement = outerBoundaryIsElement.addElement("LinearRing");
                        String coorData = "";
                        String coorText = "";
                        // 经纬度 由百度坐标 转化为 84坐标系
                        for (String corItem : item.getCoordinate().split(";")){
                            // 经纬度 由百度坐标 转化为 84坐标系
                            double[] wgs84 = GPSUtil.bd09_To_gps84(  Double.parseDouble(corItem.split(",")[1])
                                    , Double.parseDouble(corItem.split(",")[0]));
                            coorData += String.valueOf(wgs84[1]) + "," + String.valueOf(wgs84[0]) + ";";
                        }
                        coorData += coorData.split(";")[0];
                        coorText = coorData.replaceAll(";","  " );
                        linearRingElement.addElement("coordinates").addText(coorText);
                        break;
                    default:
                        break;
                }
            }

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

}
