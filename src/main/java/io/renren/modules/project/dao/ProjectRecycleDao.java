package io.renren.modules.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.vo.RecycleVoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 回收站
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@Mapper
public interface ProjectRecycleDao extends BaseMapper<RecycleVoEntity> {
    /**
     * 分页查询
     * @param pagination：mybatisPlus 原生分页查询，查询SQL会自动拼接分页
     * @param params：QueryWrapper条件，注意，这里需要使用 @Param("params") 指定mybatis参数
     * @return , Wrapper<RecycleVoEntity> queryWrapper
     */
    List<RecycleVoEntity> getRecyclePage(Page<RecycleVoEntity> pagination,  Map<String, Object> params);
}
