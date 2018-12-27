package com.zuoben.sys.config.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 * 当前为单源配置，若要配置多数据库，需要修改
 *
 * @author
 */
@Configuration
@EnableCaching
@MapperScan(basePackages = "com.zuoben.sys.mapper")
public class DatasourceAutoConfiguration {
    static {
        Logger logger = LoggerFactory.getLogger(DatasourceAutoConfiguration.class);
        logger.info("数据库加载成功");
    }
}
