package com.hnust.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * ClassName: ProxyFactory
 * Package: com.hnust.proxy
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 16:51
 */
public class ProxyFactory {
    // 目的是生产需要的目标代理类
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        /**
         * newProxyInstance()：创建一个代理实例
         * 其中有三个参数：
         * 1、classLoader loader：加载动态生成的代理类的类加载器 (指明哪个类作为代理类)
         * 2、Class[] interfaces：目标对象实现的所有接口的class对象所组成的数组 (要实现哪个接口的方法，肯定需要知道该接口的类型)
         * 3、InvocationHandler h：设置代理对象实现目标对象方法的过程，即代理类中如何重写接口中的抽象方法 (添加哪些语句或功能)
         */
        ClassLoader classLoader = ProxyFactory.class.getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * proxy：代理对象
                 * method：代理对象需要实现的方法，即其中需要重写的方法
                 * args：method所对应方法的参数
                 */
                Object result = null;// 这里利用了反射的原理，调用某个方法，需要知道该方法名以及参数的个数
                try {
                    System.out.println("日志，方法：" + method.getName() + "，参数：" + Arrays.toString(args));
                    result = method.invoke(target, args);
                    System.out.println("日志，方法：" + method.getName() + "，结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("日志，方法：" + method.getName() + "，异常：" + e);
                } finally {
                    System.out.println("日志，方法：" + method.getName() + "，方法执行完毕");
                }
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader, interfaces, h);

    }
}
