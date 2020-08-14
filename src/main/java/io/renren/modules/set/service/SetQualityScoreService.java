package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetQualityScoreEntity;

import java.util.Map;
import java.util.List;

/**
 * 质量评分表设置
 *
 * @author ygg
 * @date 2020-08-14 10:17:40
 */
public interface SetQualityScoreService extends IService<SetQualityScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SetQualityScoreEntity> queryList(Map<String, Object> params);

    // 获取文件编号列表
    List<String> getFileList();

    void save(SetQualityScoreEntity entity);

    void update(SetQualityScoreEntity entity);

    void deleteBatch(Long[] Ids);

    void deleteByFileNo(String fileNo);
}

