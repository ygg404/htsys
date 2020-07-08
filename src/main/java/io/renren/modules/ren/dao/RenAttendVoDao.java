package io.renren.modules.ren.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.ren.vo.RenAttendVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RenAttendVoDao extends BaseMapper<RenAttendVoEntity> {
    /**
     * 查询
     */
    List<RenAttendVoEntity> getRenAttendVoList(Map<String, Object> params);

    /**
     * 查询参加考勤的工作组级员工
     */
    List<RenAttendVoEntity> getBranchUserList();
}
