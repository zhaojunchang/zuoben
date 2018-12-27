package com.zuoben.sys.config.datasource;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 开启druid监控统计
 *
 * @author
 * createTime:2018/3/5 0005
 */
@WebFilter(
        filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                /** 忽略资源 */
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),
                //session统计
                @WebInitParam(name = "sessionStatEnable", value = "true"),
                //监控单个url调用的sql列表
                @WebInitParam(name = "profileEnable", value = "true"),
        }
)
public class DruidStatFilter extends WebStatFilter {
}
