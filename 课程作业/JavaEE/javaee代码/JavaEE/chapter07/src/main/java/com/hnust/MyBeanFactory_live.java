package com.hnust;

/**
 * ClassName: MyBeanFactory_live
 * Package: com.hnust
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 20:32
 */
public class MyBeanFactory_live {
    public MyBeanFactory_live() {
        System.out.println("这是实例工厂实例化");
    }

    public Bean createBean(){
        return new Bean();
    }
}
