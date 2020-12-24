package io.renren.modules.dop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.*;
import io.renren.common.utils.map.AreaUtil;
import io.renren.common.utils.map.GPSUtil;
import io.renren.modules.dop.dao.DopBmapDao;
import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.service.DopBmapService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;


@Service("dopBmapService")
public class DopBmapServiceImpl extends ServiceImpl<DopBmapDao, DopBmapEntity> implements DopBmapService {

    @Value("${spring.file.upBmapFolder}")
    private String upBmapFolder;

    public String pointStyleId = "2707719465";
    public String pointStyleIdStr = "#" + pointStyleId;
    public String lineStyleId = "559648089";
    public String lineStyleIdStr = "#" + lineStyleId;
    public String polygonStyleId = "3411153112";
    public String polygonStyleIdStr = "#" + polygonStyleId;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Page<DopBmapEntity> pagnation = new Query<DopBmapEntity>(params).getPage();
        List<Long> pIdList = this.baseMapper.getMapPIdList(params );
        pagnation.setTotal(pIdList.size());

        int fromIndex = (pagnation.getCurrent() - 1)*pagnation.getLimit();
        int toIndex =  pagnation.getCurrent()*pagnation.getLimit() > pIdList.size() ? pIdList.size() : pagnation.getCurrent()*pagnation.getLimit();
        pIdList = pIdList.subList( fromIndex, toIndex);
        pagnation = pagnation.setRecords(  this.baseMapper.getMapChildList(pIdList) );

        return new PageUtils(pagnation);
    }

    @Override
    public List<DopBmapEntity> queryList(Map<String, Object> params){
        Long id = Long.parseLong(params.get("id").toString());
        List<Long> pIdList = new ArrayList<>();
        pIdList.add(id);
        List<DopBmapEntity> list = this.baseMapper.getMapChildList(pIdList);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DopBmapEntity> queryByPId(List<Long> pIdList){
        List<DopBmapEntity> list = this.baseMapper.getMapChildList(pIdList);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long getMaxId(){
        return this.baseMapper.getMaxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Document createKML(Long bmapId) throws Exception{
        try {
            List<Long> pIdList = new ArrayList<>();
            pIdList.add(bmapId);
            List<DopBmapEntity> list = this.baseMapper.getMapChildList(pIdList);
            DopBmapEntity entity = this.selectById(bmapId);
            Element root = DocumentHelper.createElement("kml");
            Document document = DocumentHelper.createDocument(root);
            //根节点添加属性
            root.addAttribute("xmlns", "http://www.opengis.net/kml/2.2")
                    .addAttribute("xmlns:gx", "http://www.google.com/kml/ext/2.2");
            Element documentElement = root.addElement("Document");

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

            this.addKmlNodeDocument(entity.getParentId(), documentElement , list);
            return document;
        }catch (Exception ex) {
            throw ex;
        }
    }

    //递归生成KML文件
    private void addKmlNodeDocument(Long parentId,Element parentElement, List<DopBmapEntity> list) {
        try {
            //处理子节点数据 将其加入kml文档对象
            for(DopBmapEntity item : list) {
                if ( item.getParentId().equals(parentId)) {
                    //子节点文档对象
                    Element childElement;
                    //判断 类型（0-项目;1-点；2-线；3-面）
                    switch (item.getType().intValue()) {
                        //目录
                        case 0:
                            childElement = parentElement.addElement("Folder");
                            childElement.addElement("name").addText(item.getLabel());
                            addKmlNodeDocument(item.getId(), childElement,list);
                            break;
                        //点
                        case 1:
                            childElement = parentElement.addElement("Placemark");
                            childElement.addElement("name").addText(item.getLabel());
                            childElement.addElement("styleUrl").addText(pointStyleIdStr);
                            Element pointElement = childElement.addElement("Point");
                            // 经纬度 由百度坐标 转化为 84坐标系
                            double[] gps84 = GPSUtil.bd09_To_gps84(item.getLat(), item.getLng());
                            String pointcoordinates = gps84[1] + "," + gps84[0];
                            pointElement.addElement("coordinates").addText(pointcoordinates);
                            break;
                        //线
                        case 2:
                            childElement = parentElement.addElement("Placemark");
                            childElement.addElement("name").addText(item.getLabel());
                            childElement.addElement("styleUrl").addText(lineStyleIdStr);
                            Element lineStringElement = childElement.addElement("LineString");
                            String coordinateData = "";
                            // 经纬度 由百度坐标 转化为 84坐标系
                            for (String corItem : item.getCoordinate().split(";")) {
                                // 经纬度 由百度坐标 转化为 84坐标系
                                double[] lineGps84 = GPSUtil.bd09_To_gps84(Double.parseDouble(corItem.split(",")[1])
                                        , Double.parseDouble(corItem.split(",")[0]));
                                coordinateData += String.valueOf(lineGps84[1]) + "," + String.valueOf(lineGps84[0]) + ";";
                            }
                            String coordinateText = coordinateData.replaceAll(";", "  ");
                            lineStringElement.addElement("coordinates").addText(coordinateText);
                            break;
                        //面
                        case 3:
                            childElement = parentElement.addElement("Placemark");
                            childElement.addElement("name").addText(item.getLabel());
                            childElement.addElement("styleUrl").addText(polygonStyleIdStr);
                            Element polygonElement = childElement.addElement("Polygon");
                            polygonElement.addElement("tessellate").addText("1");
                            Element outerBoundaryIsElement = polygonElement.addElement("outerBoundaryIs");
                            Element linearRingElement = outerBoundaryIsElement.addElement("LinearRing");
                            String coorData = "";
                            String coorText = "";
                            // 经纬度 由百度坐标 转化为 84坐标系
                            for (String corItem : item.getCoordinate().split(";")) {
                                // 经纬度 由百度坐标 转化为 84坐标系
                                double[] wgs84 = GPSUtil.bd09_To_gps84(Double.parseDouble(corItem.split(",")[1])
                                        , Double.parseDouble(corItem.split(",")[0]));
                                coorData += String.valueOf(wgs84[1]) + "," + String.valueOf(wgs84[0]) + ";";
                            }
                            coorData += coorData.split(";")[0];
                            coorText = coorData.replaceAll(";", "  ");
                            linearRingElement.addElement("coordinates").addText(coorText);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception ex) {

        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void parseKML(String kmlContent,Map<String, Object> params) throws Exception{
        try {
            SysUserEntity userEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            Long parentId = Long.parseLong(params.get("parentId").toString());
            Long maxId = this.baseMapper.getMaxId();
            Long rootId = maxId == null ? 0L : maxId;
            Document doc = DocumentHelper.parseText(kmlContent);
            Element docElement = doc.getRootElement();
            // 插入数据库的标志列表 及其项目
            List<DopBmapEntity> tempList = new ArrayList<>();
            parseNodeElement(docElement,rootId + 1L,parentId,tempList);

            for (DopBmapEntity dop : tempList) {
                dop.setCreateUserId(userEntity.getUserId());
                dop.setCreateUserName(userEntity.getUsername());
                dop.setCreateTime(new Date());
            }
            this.insertBatch(tempList);
        }catch (Exception ex){
            throw ex;
        }
    }

    //遍历解析节点 保存处理后的数据
    public Long parseNodeElement(Element element,Long rootId,Long parentId,List<DopBmapEntity> tempList) {
        Iterator iter = element.elementIterator("Document");
        while (iter.hasNext()) {
            Element node = (Element) iter.next();
            rootId = parseNodeElement(node,rootId,parentId,tempList);
        }

        iter = element.elementIterator("Folder");
        while (iter.hasNext()) {
            Element node = (Element) iter.next();

            DopBmapEntity entity = new DopBmapEntity();
            String nodeName = node.element("name").getTextTrim();
            entity.setLabel(nodeName);
            entity.setId(rootId);
            entity.setParentId(parentId);
            entity.setType(0L);
            tempList.add(entity);
            rootId = this.parseNodeElement(node,rootId + 1,rootId,tempList);
        }

        iter = element.elementIterator("Placemark");
        while (iter.hasNext()) {
            DopBmapEntity entity = new DopBmapEntity();
            Element node = (Element) iter.next();
            String nodeName = node.element("name").getTextTrim();
            entity.setId(rootId);
            entity.setLabel(nodeName);
            entity.setParentId(parentId);
            // 面元素
            Element polyEle = node.element("Polygon");
            if (polyEle != null) {
                String[] polyList = polyEle.element("outerBoundaryIs").element("LinearRing").element("coordinates").getTextTrim().split(" ");
                String corStr = "";
                double lng = 0f;
                double lat = 0f;
                for (int i = 0; i < polyList.length - 1 ; i ++) {
                    String[] pointStr = polyList[i].split(",");
                    double[] bd09 = GPSUtil.gps84_To_bd09(Double.parseDouble(pointStr[1]),Double.parseDouble(pointStr[0]));
                    corStr += bd09[1] + "," + bd09[0] + ";";

                    lng += bd09[1];
                    lat += bd09[0];
                }
                entity.setLng( lng/(polyList.length - 1));
                entity.setLat( lat/(polyList.length - 1));
                entity.setLabelLng( lng/(polyList.length - 1));
                entity.setLabelLat( lat/(polyList.length - 1));
                entity.setCoordinate(corStr.substring(0,corStr.length()-1));
                entity.setArea(AreaUtil.caculateArea(corStr));   // 多边面面积计算
                entity.setType(3L);
                rootId ++;
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
                entity.setArea(0.0);
                entity.setType(2L);
                rootId ++;
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
                entity.setArea(0.0);
                entity.setType(1L);
                entity.setCoordinate( String.valueOf(bd09[1]) + "," + String.valueOf(bd09[0]) );
                rootId ++;
            }

            tempList.add(entity);
        }

        return rootId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void copyList(Map<String, Object> params) throws Exception{
        // 拷贝的节点
        Long id = Long.parseLong(params.get("id").toString());
        // 要复制到的父节点
        Long mpId = Long.parseLong(params.get("pId").toString());

        Long maxId = this.baseMapper.getMaxId();
        // 获取要拷贝节点下的所有子节点（含拷贝节点）
        List<Long> pIdList = new ArrayList<>();
        pIdList.add(id);
        List<DopBmapEntity> list = this.baseMapper.getMapChildList(pIdList);
        DopBmapEntity entity = this.selectById(id);

        // 如果复制到的父节点目录 是其子节点 则抛出异常
        for (DopBmapEntity bmap : list) {
            if (bmap.getId().equals(mpId)) throw new Exception("该节点无法复制到 子节点的目录下！");
        }

        // 复制下的所有子节点列表 要修改Id 和 父id
        this.recallList(entity.getParentId(),mpId,maxId + 1,list);

        this.insertBatch(list);
    }

    /**
     * 修改子节点 所有子ID 和父ID
     * @param pId  原父ID
     * @param mpId 修改后的父ID
     * @param mId  修改后的节点ID
     * @param list
     * @return
     */
    public Long recallList(Long pId,Long mpId,Long mId,List<DopBmapEntity> list) {
        for (DopBmapEntity entity : list) {
            if (entity.getParentId().equals(pId)) {
                Long oId = entity.getId(); // 原节点的ID
                entity.setId(mId);
                entity.setParentId(mpId);
                // 图层类型需要往下遍历
                if (entity.getType() == 0) {
                    mId = this.recallList(oId,entity.getId(),mId + 1,list);
                } else {
                    mId ++;
                }
            }
        }
        return mId;
    }

    @Override
    public String saveBmapImg(Map<String, Object> params){
        String base64 = (String)params.get("imgCode");
        String imgType = (String)params.get("imgType");

        String imgName = imgType + "("  +
                DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN).replaceAll("-","").replaceAll(":","").replaceAll(" ","")
                + ").png";
        try {
            ImgUtils.convertBase64ToImage(base64, upBmapFolder + "photo/" + imgName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return imgName;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DopBmapEntity entity) {
        // 获取当前用户Id
        SysUserEntity createUser =  (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        entity.setCreateUserId(createUser.getUserId());
        entity.setCreateUserName(createUser.getUsername());
        entity.setCreateTime(new Date());
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPId(Long pId) {
        List<Long> pIdList = new ArrayList<>();
        pIdList.add(pId);
        List<DopBmapEntity> list = this.baseMapper.getMapChildList(pIdList);
        Long[] idList = new Long[list.size()];
        int index = 0;
        for (DopBmapEntity entity : list) {
            idList[index] = entity.getId();
            index ++;
        }
        this.deleteBatch(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DopBmapEntity entity) throws Exception{
        // 获取要拷贝节点下的所有子节点（含拷贝节点）
        List<Long> pIdList = new ArrayList<>();
        pIdList.add(entity.getId());
        List<DopBmapEntity> list = this.baseMapper.getMapChildList(pIdList);
        // 如果移动到的父节点目录 是其子节点 则抛出异常
        for (DopBmapEntity bmap : list) {
            if (bmap.getId().equals(entity.getParentId())) throw new Exception("该节点无法 移动到 其子节点的目录下！");
        }
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}