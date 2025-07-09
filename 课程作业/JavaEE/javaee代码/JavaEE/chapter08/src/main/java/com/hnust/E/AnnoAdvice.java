package com.hnust.E;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * ClassName: AnnoAdvice
 * Package: com.hnust.E
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/4 - 22:30
 */
@Aspect
@Component
// @Order(1) 切面的优先级，通过设置其value值来指定其优先级的大小，值越小优先级越高，其默认值为Integer的最大值
public class AnnoAdvice {

    @Pointcut("execution(* com.hnust.XML.UserDaoImpl.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinpoint){
        System.out.print("这是前置通知！");
        System.out.print("目标类是："+joinpoint.getTarget());
        System.out.println(",被植入增强处理的目标方法为："+joinpoint.getSignature().getName());
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinpoint){
        System.out.print("这是返回通知(方法不出现异常时调用)！");
        System.out.println(",被植入增强处理的目标方法为："+joinpoint.getSignature().getName());
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        System.out.println("这是环绕通知之前的部分！");
        Object object = point.proceed();
        System.out.println("这是环绕通知之后的部分！");
        return object;
    }

    @AfterThrowing("pointcut()")
    public void afterException(){
        System.out.println("异常通知！");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("这是后置通知！");
    }
}
