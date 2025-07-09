package com.hnust.XML;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ClassName: Xml
 * Package: com.hnust.XML
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/4 - 13:44
 */
public class XmlAdvice {
    public void before(JoinPoint joinpoint){
        System.out.print("这是前置通知！");
        System.out.print("目标类是："+joinpoint.getTarget());
        System.out.println(",被植入增强处理的目标方法为："+joinpoint.getSignature().getName());
    }
    public void afterReturning(JoinPoint joinpoint){
        System.out.print("这是返回通知(方法不出现异常时调用)！");
        System.out.println(",被植入增强处理的目标方法为："+joinpoint.getSignature().getName());
    }
    public Object around(ProceedingJoinPoint point) throws Throwable{
        System.out.println("这是环绕通知之前的部分！");
        Object object = point.proceed();
        System.out.println("这是环绕通知之后的部分！");
        return object;
    }
    public void afterException(){
        System.out.println("异常通知！");
    }
    public void after(){
        System.out.println("这是后置通知！");
    }
}
