package test;

import com.hnust.proxy.Calculator;
import com.hnust.proxy.CalculatorImpl;
import com.hnust.proxy.CalculatorStaticProxy;
import com.hnust.proxy.ProxyFactory;
import org.junit.Test;

/**
 * ClassName: ProxyTest
 * Package: test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 16:43
 */


public class ProxyTest {

    /**
     * 动态代理两种表现形式：
     * 1.jdk动态代理，要求必须有接口，最终生成的代理类和目标类实现相同的接口
     * 在com.sun.proxy包下，类名为$proxy2
     * 2.cglib动态代理，最终生成的代理类会继承目标类，并且和目标类在相同的包下
     */
    @Test
    public void testStaticProxy(){
        CalculatorStaticProxy calculatorStaticProxy = new CalculatorStaticProxy(new CalculatorImpl());
        System.out.println(calculatorStaticProxy.add(1,2));
    }

    @Test
    public void testDynamicProxy(){
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1,3);
        //proxy.div(1,0);
    }
}
