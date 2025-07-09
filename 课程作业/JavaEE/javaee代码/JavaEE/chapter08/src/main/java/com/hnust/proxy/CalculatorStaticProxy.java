package com.hnust.proxy;

/**
 * ClassName: CalculatorStaticProxy
 * Package: com.hnust.proxy
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 15:58
 */
public class CalculatorStaticProxy implements Calculator {

    // 指明需要代理的对象，即目标对象
    /*
         理解：这一节有种引出SpringMVC的感觉。
         就是程序员最忌讳的就是大批量的修改代码，有些代码是次要的，有些代码是主要的，
         所有把主要的代码封装起来，通过代理类进行调用，再在代理类中添加额外的功能即可，
         如果那些次要的方法不需要了，在此封装起来，在新的代理类中进行调用
    */
    private CalculatorImpl target;

    public CalculatorStaticProxy(CalculatorImpl target) {
        this.target = target;
    }

    @Override
    public int add(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + i + "," + j);
        // 通过目标对象来实现核心业务逻辑
        int Result = target.add(i, j);
        System.out.println("[日志] add 方法结束了，结果是：" + Result);
        return Result;
    }
    @Override
    public int sub(int i, int j) {
        System.out.println("[日志] sub 方法开始了，参数是：" + i + "," + j);
        int Result = target.sub(i, j);
        System.out.println("[日志] sub 方法结束了，结果是：" + Result);
        return Result;
    }
    @Override
    public int mul(int i, int j) {
        System.out.println("[日志] mul 方法开始了，参数是：" + i + "," + j);
        int Result = target.mul(i, j);
        System.out.println("[日志] mul 方法结束了，结果是：" + Result);
        return Result;
    }
    @Override
    public int div(int i, int j) {
        System.out.println("[日志] div 方法开始了，参数是：" + i + "," + j);
        int Result = target.div(i, j);
        System.out.println("[日志] div 方法结束了，结果是：" + Result);
        return Result;
    }
}
