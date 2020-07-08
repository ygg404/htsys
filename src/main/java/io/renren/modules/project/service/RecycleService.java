package io.renren.modules.project.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.project.vo.RecycleVoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 回收站
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface RecycleService extends IService<RecycleVoEntity> {
    /**
     * 回收站分页查询
     * @param params
     * @return
     */
    PageUtils getRecyclePage(Map<String, Object> params);
}
