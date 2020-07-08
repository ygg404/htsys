package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.project.vo.ProjectVoEntity;
import io.renren.modules.sys.vo.SysTableVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据库接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2018-07-24
 */
public interface SysGeneratorDao extends BaseMapper<SysTableVoEntity> {
    /**
     * 分页查询
     */
    List<SysTableVoEntity> queryList(Page<SysTableVoEntity> pagination, Map<String, Object> params);
//    List<Map<String, Object>> queryList(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
