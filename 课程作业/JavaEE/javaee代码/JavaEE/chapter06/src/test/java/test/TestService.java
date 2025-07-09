package test;

import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * ClassName: TestService
 * Package: test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 19:50
 */
public class TestService {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext-Service.xml");
        UserService userService = (UserService)applicationContext.getBean("userService");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        String password = sc.next();
        boolean flag = userService.login(name,password);
        if (flag){
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败！");
        }
    }

}
