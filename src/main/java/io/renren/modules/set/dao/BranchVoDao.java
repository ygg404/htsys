package io.renren.modules.set.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.set.vo.BranchVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 职工档案复合表（正式）
 *
 * @author ygg
 * @date 2020-02-11 11:50:15
 */
@Mapper
public interface BranchVoDao extends BaseMapper<BranchVoEntity> {

    List<BranchVoEntity> getBranchVoList();

    Long insertBranchVo(BranchVoEntity branchVoEntity);

    BranchVoEntity getEntityById(Long id);
}
