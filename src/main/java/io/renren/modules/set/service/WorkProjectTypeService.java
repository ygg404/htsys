package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.WorkProjectTypeEntity;
import io.renren.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 作业类型ID项目类型ID关联表
 *
 * @author ygg
 * @date 2019-10-31 16:56:32
 */
public interface WorkProjectTypeService extends IService<WorkProjectTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(WorkProjectTypeEntity entity);

    void update(WorkProjectTypeEntity entity);

    void deleteBatch(Long[] roleIds);

    void deleteBatchByWIDs(Long[] wids);

    //通过单个项目类型ID去删除 作业类型ID项目类型ID表
    void deleteBatchByWID(Long wid);

    List<WorkProjectTypeEntity> selectByWID(Long wids);

    List<WorkProjectTypeEntity> queryList(Map<String, Object> params);

    void DeleteBatchByWPEnitty(Map<String, Object> params);


}

