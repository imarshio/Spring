package com.marshio.druid.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

/**
 * @author masuo
 * @data 7/2/2022 下午3:24
 * @Description druid数据源工厂
 * 想要使用第三方数据库连接池，第三方必须继承PooledDataSourceFactory,并且需要给dataSource重新赋值
 */

public class DruidDataSourceFactory extends PooledDataSourceFactory {
    public DruidDataSourceFactory(){
        System.out.println("启用德鲁伊。。");
        this.dataSource = new DruidDataSource();
    }
}
