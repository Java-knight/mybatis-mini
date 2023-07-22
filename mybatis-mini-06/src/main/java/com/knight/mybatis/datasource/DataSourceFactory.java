package com.knight.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 * @desc
 * @author knight
 * @date 2023/7/1
 */
public interface DataSourceFactory {

    /**
     * 设置属性
     * @param props
     */
    void setProperties(Properties props);

    /**
     * 获取数据源
     * @return
     */
    DataSource getDataSource();
}
