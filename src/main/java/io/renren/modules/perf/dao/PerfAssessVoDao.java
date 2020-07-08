package io.renren.modules.perf.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.perf.vo.PerfAssessVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 年度各岗位中各成员的评分明细
 */
@Mapper
public interface PerfAssessVoDao extends BaseMapper<PerfAssessVoEntity> {

    List<PerfAssessVoEntity> getPerfAccessVoList(Map<String, Object> params);

    /**
     * 统计提交数判断是否提交
     * @param params
     * @return
     */
    Long countPerfAccess(Map<String, Object> params);

    /**
     * 获取有岗位的用户
     * @return
     */
    List<PerfAssessVoEntity> getUserRoleList(Map<String, Object> params);

    /**
     * 获取有评分的用户
     * @param params
     * @return
     */
    List<PerfAssessVoEntity> getUserHasAssess(Map<String, Object> params);
}
