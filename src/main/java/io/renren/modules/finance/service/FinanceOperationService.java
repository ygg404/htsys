package io.renren.modules.finance.service;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.modules.finance.dao.FinanceOperationDao;
import io.renren.modules.finance.dao.ProjectAccountDao;
import io.renren.modules.finance.entity.ProjectAccountEntity;
import io.renren.modules.finance.vo.FinanceOperationVoEntity;
import java.util.List;
import java.util.Map;

//财务操作
public interface FinanceOperationService extends IService<FinanceOperationVoEntity>{

    PageUtils getFinanceOperationPage(Map<String, Object> params);

    List<FinanceOperationVoEntity> getFinanceList(Map<String,Object>params);

}
