package com.example.mds.aop;

import com.example.mds.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 执行顺序
 * Around>Before>接口中的逻辑代码>After>AfterReturning
 * Around:环绕,可以在切入点前后织入代码,并且可以自由的控制何时执行切点
 * Before:在切点之前,织入相关代码
 * After:在切点之后,织入相关代码
 * AfterReturning:在切点返回内容后,织入相关代码,一般用于对返回值做些加工处理的场景
 * AfterThrowing:用来处理当织入的代码抛出异常后的逻辑处理
 */
@Aspect
@Component
public class DSAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义一个切点
     * 这里的表达式是自定义注解的全路径
     */
    @Pointcut("@annotation(com.example.mds.annotation.DS)")
    public void ds() {
    }

    /**
     * 在切点之前织入
     *
     * @throws Throwable
     */
    @Before("ds()")
    public void doBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //根据@RequestHeader里的数据源标识设置数据源
        DynamicDataSourceContextHolder.setDataSourceType(request.getHeader("sign"));
        System.out.println("当前请求线程ID:" + Thread.currentThread().getId() + ",doBefore设置当前数据源:" + request.getHeader("sign"));
        logger.info("当前请求线程ID:" + Thread.currentThread().getId() + ",doBefore设置当前数据源:" + request.getHeader("sign"));
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("ds()")
    public void doAfter() {
        //清除设置的数据源
        DynamicDataSourceContextHolder.clearDataSourceType();
        System.out.println("当前请求线程ID:" + Thread.currentThread().getId() + ",After清除设置的数据源");
        logger.info("当前请求线程ID:" + Thread.currentThread().getId() + ",After清除设置的数据源");
    }
}