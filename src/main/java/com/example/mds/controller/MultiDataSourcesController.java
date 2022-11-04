package com.example.mds.controller;

import com.example.mds.annotation.DS;
import com.example.mds.service.MultiDataSourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/multi/data/sources")
public class MultiDataSourcesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MultiDataSourcesService multiDataSourcesService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @DS
    public List test() {
        System.out.println("/multi/data/sources/test-->当前请求线程ID:" + Thread.currentThread().getId());
        logger.info("/multi/data/sources/test-->当前请求线程ID:" + Thread.currentThread().getId());
        return multiDataSourcesService.test();
    }
}