package io.renren.modules.sys.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.GenUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.project.vo.ProjectVoEntity;
import io.renren.modules.sys.dao.SysGeneratorDao;
import io.renren.modules.sys.vo.SysTableVoEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Service
public interface SysGeneratorService extends IService<SysTableVoEntity> {

	PageUtils queryPage(Map<String, Object> params);

	Map<String, String> queryTable(String tableName);

	List<Map<String, String>> queryColumns(String tableName);

	byte[] generatorCode(String[] tableNames);
}