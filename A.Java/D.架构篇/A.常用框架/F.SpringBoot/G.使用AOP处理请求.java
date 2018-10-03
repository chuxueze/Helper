1.AOP统1处理请求日志







------------------------------------------------------------------------------------------------------------------------------------------------
1.AOP统1处理请求日志
步骤:
1.1 添加依赖

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
---------------------
1.2 创建切面类
package com.imooc.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created By Administrator
 * on 2018/10/1/001
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.imooc.controller.GirlController.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("请求进来了");
        logger.info("请求接口为：{}",request.getRequestURL().toString());
        logger.info("请求方法为：{}",request.getMethod());
        logger.info("IP:{}",request.getRemoteAddr());
        logger.info("类方法:{}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("参数:{}",joinPoint.getArgs().toString());
    }

    @After("log()")
    public void doAfter(){
        logger.info("请求已结束");
    }

    //获取返回参数
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturnig(Object object){
        if(object != null){
            if(object instanceof List){
                List list = (List) object;
                for(Object o:list){
                    logger.info("返回参数为:{}",o.toString());
                }
                return;
            }
            logger.info("返回参数为:{}",object.toString());  
        }
    }
}


