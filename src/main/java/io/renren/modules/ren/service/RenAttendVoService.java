package io.renren.modules.ren.service;


import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.ren.vo.RenAttendVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 出勤表复合类查询
 */
public interface RenAttendVoService extends IService<RenAttendVoEntity> {
    /**
     * 查询
     * @param params
     * @return
     */
    List<RenAttendVoEntity> queryRenAttendVoList(Map<String, Object> params);

    /**
     * 查询 参加考勤的工作组以及人员
     * @return
     */
    List<RenAttendVoEntity> queryBranchUserList();
}
