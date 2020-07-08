package io.renren.modules.perf.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.perf.vo.PerfAssessVoEntity;

import java.util.List;
import java.util.Map;

public interface PerfAssessVoService extends IService<PerfAssessVoEntity> {

    /**
     * 获取评分列表
     * @param params
     * @return
     */
    List<PerfAssessVoEntity> queryPerfAccessVoList(Map<String, Object> params);
    /**
     * 统计提交数判断是否提交
     * @param params
     * @return
     */
    Long queryCountPerfAccess(Map<String, Object> params);

    /**
     * 获取有岗位的用户
     * @return
     */
    List<PerfAssessVoEntity> queryUserRoleList(Map<String, Object> params);
    /**
     * 获取参加评分的人
     * @param params
     * @return
     */
    List<PerfAssessVoEntity> queryUserHasAssess(Map<String, Object> params);
}
