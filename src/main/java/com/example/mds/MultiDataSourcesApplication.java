package com.example.mds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.example.mds.mapper"})
public class MultiDataSourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourcesApplication.class, args);
    }

}
