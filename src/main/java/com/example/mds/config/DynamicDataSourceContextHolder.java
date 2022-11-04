package com.example.mds.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceContextHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源变量
     *
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        CONTEXT_HOLDER.set(dataSourceType);
        System.out.println("DynamicDataSourceContextHolder.setDataSourceType-->设置当前数据源:" + dataSourceType + ",当前请求线程ID:" + Thread.currentThread().getId());
        logger.info("DynamicDataSourceContextHolder.setDataSourceType-->设置当前数据源:" + dataSourceType + ",当前请求线程ID:" + Thread.currentThread().getId());
    }

    /**
     * 获取数据源变量
     *
     * @return
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
