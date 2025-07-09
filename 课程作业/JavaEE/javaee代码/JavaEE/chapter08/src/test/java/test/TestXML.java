package test;

import com.hnust.XML.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestXML
 * Package: test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/4 - 14:08
 */
public class TestXML {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext-XML.xml");
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

        userDao.delete();
        System.out.println();
        userDao.insert();
        System.out.println();
        userDao.update();
        System.out.println();
        userDao.select();

    }
}
