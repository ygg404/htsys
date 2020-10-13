package io.renren.modules.perf.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.perf.vo.PerfVoEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

public interface PerfVoService extends IService<PerfVoEntity> {

    List<PerfVoEntity> queryList(Map<String, Object> params);

    HSSFWorkbook exportExcel(List<PerfVoEntity> pList);
}
