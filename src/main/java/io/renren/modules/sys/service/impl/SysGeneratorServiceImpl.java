package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IOUtils;
import io.renren.common.utils.GenUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysConfigDao;
import io.renren.modules.sys.dao.SysGeneratorDao;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.redis.SysConfigRedis;
import io.renren.modules.sys.service.SysConfigService;
import io.renren.modules.sys.service.SysGeneratorService;
import io.renren.modules.sys.vo.SysTableVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service("sysGeneratorService")
public class SysGeneratorServiceImpl extends ServiceImpl<SysGeneratorDao, SysTableVoEntity> implements SysGeneratorService {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

		Page<SysTableVoEntity> pagnation = new Query<SysTableVoEntity>(params).getPage();
		pagnation = pagnation.setRecords( baseMapper.queryList(pagnation , params ) );
		return new PageUtils(pagnation);
    }

    @Override
    public Map<String, String> queryTable(String tableName) {
		return baseMapper.queryTable(tableName);
	}

    @Override
	public List<Map<String, String>> queryColumns(String tableName) {
		return baseMapper.queryColumns(tableName);
	}

    @Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = baseMapper.queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = baseMapper.queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
}
