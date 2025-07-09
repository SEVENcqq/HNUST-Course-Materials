package com.hnust;

/**
 * ClassName: MyBeanFactory_static
 * Package: com.hnust
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 20:28
 */
public class MyBeanFactory_static {
    public MyBeanFactory_static() {
        System.out.println("这是静态工厂实例化");
    }

    public static Bean createBean(){
        return new Bean();
    }
}
