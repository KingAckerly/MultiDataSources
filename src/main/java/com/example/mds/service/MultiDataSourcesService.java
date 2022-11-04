package com.example.mds.service;

import com.example.mds.config.DataSourceType;
import com.example.mds.config.DynamicDataSourceContextHolder;
import com.example.mds.mapper.DB0Mapper;
import com.example.mds.mapper.DB1Mapper;
import com.example.mds.mapper.DB2Mapper;
import com.example.mds.mapper.DBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MultiDataSourcesService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DB0Mapper db0Mapper;

    @Resource
    private DB1Mapper db1Mapper;

    @Resource
    private DB2Mapper db2Mapper;

    @Resource
    private DBMapper dbMapper;

    /*public List test(String sign) {
        switch (sign) {
            case "0":
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB0.name());
                return db0Mapper.selectList(null);
            case "1":
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB1.name());
                return db1Mapper.selectList(null);
            case "2":
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
                return db2Mapper.selectList(null);
        }
        return null;
    }*/

    public List test() {
        System.out.println("service执行mapper数据库操作-->当前请求线程ID:" + Thread.currentThread().getId());
        logger.info("service执行mapper数据库操作-->当前请求线程ID:" + Thread.currentThread().getId());
        List list = dbMapper.selectList(null);
        logger.info("list:" + list);
        return list;
    }

    public static void main(String[] args) throws Exception {
        String url = "D:\\param.txt";
        String line = System.getProperty("line.separator");
        File fout = new File(url);
        FileWriter out = new FileWriter(fout);
        Random r;
        int num;
        for (int i = 1; i <= 1000; i++) {
            r = new Random();
            num = r.nextInt(10) + 1;
            if (num < 4)
                out.write("DB0");
            if (num >= 4 && num < 7)
                out.write("DB1");
            if (num >= 7 && num <= 10)
                out.write("DB2");
            if (i < 1000)
                out.write(line);
        }
        out.flush();
        out.close();
    }
}