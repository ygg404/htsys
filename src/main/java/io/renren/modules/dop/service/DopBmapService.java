package io.renren.modules.dop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.dop.entity.DopBmapEntity;
import org.dom4j.Document;

import java.util.Map;
import java.util.List;

/**
 * 地图标注表
 *
 * @author ygg
 * @date 2020-09-24 10:26:01
 */
public interface DopBmapService extends IService<DopBmapEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DopBmapEntity> queryList(Map<String, Object> params);

    List<DopBmapEntity> queryByPId(List<Long> pIdList);

    Long getMaxId();

    Document createKML(Long bmapId) throws Exception;

    void parseKML(String kmlContent,Map<String, Object> params) throws Exception;

    void copyList(Map<String, Object> params) throws Exception;

    String saveBmapImg(Map<String, Object> params);

    void save(DopBmapEntity entity);

    void update(DopBmapEntity entity) throws Exception;

    void deleteBatch(Long[] Ids);

    void deleteByPId(Long pId);
}

