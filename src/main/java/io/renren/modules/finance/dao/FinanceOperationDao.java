package io.renren.modules.finance.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.finance.entity.ProjectAccountEntity;
import io.renren.modules.finance.vo.FinanceOperationVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FinanceOperationDao extends BaseMapper<FinanceOperationVoEntity> {

    /**
     * 财务分页查询
     * @param pagination
     * @param params
     * @return
     */
    List<FinanceOperationVoEntity> getFinanceOperationList(Page<FinanceOperationVoEntity> pagination,Map<String, Object> params);

    /**
     * 财务列表查询
     * @param params
     * @return
     */
    List<FinanceOperationVoEntity> getFinanceOperationList(Map<String, Object> params);

//    List<FinanceOperationVoEntity> getFinanceOperationPage(Page<FinanceOperationVoEntity> pagination, Map<String, Object> params);
    Long getContractCount();
}
