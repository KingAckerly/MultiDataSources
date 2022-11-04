package com.example.mds.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DynamicDataSourceConfig {

    @Bean("db0")
    @ConfigurationProperties("spring.datasource.db0")
    public DataSource db0() {
        return DataSourceBuilder.create().build();
    }

    @Bean("db1")
    @ConfigurationProperties("spring.datasource.db1")
    public DataSource db1() {
        return DataSourceBuilder.create().build();
    }

    @Bean("db2")
    @ConfigurationProperties("spring.datasource.db2")
    public DataSource db2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dataSource(@Qualifier("db0") DataSource db0, @Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2) {
        Map<Object, Object> targetDataSources = new HashMap<>(4);
        targetDataSources.put(DataSourceType.DB0.name(), db0);
        targetDataSources.put(DataSourceType.DB1.name(), db1);
        targetDataSources.put(DataSourceType.DB2.name(), db2);
        return new DynamicDataSource(db0, targetDataSources);
    }
}