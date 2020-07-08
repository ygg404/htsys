
package io.renren.config;

import io.renren.common.exception.RRException;


import io.renren.modules.sys.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据库配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class DbConfig {
    @Value("${renren.database: mysql}")
    private String database;
    @Autowired
    private SysGeneratorMySQLDao mySQLGeneratorDao;
    @Autowired
    private SysGeneratorOracleDao oracleGeneratorDao;
    @Autowired
    private SysGeneratorSQLServerDao sqlServerGeneratorDao;
    @Autowired
    private SysGeneratorPostgreSQLDao postgreSQLGeneratorDao;

    @Bean
    @Primary
    public SysGeneratorDao getGeneratorDao(){
        if("mysql".equalsIgnoreCase(database)){
            return mySQLGeneratorDao;
        }else if("oracle".equalsIgnoreCase(database)){
            return oracleGeneratorDao;
        }else if("sqlserver".equalsIgnoreCase(database)){
            return sqlServerGeneratorDao;
        }else if("postgresql".equalsIgnoreCase(database)){
            return postgreSQLGeneratorDao;
        }else {
            throw new RRException("不支持当前数据库：" + database);
        }
    }
}
