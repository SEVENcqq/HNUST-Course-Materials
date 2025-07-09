package com.hnust.spring.service.impl;

import com.hnust.spring.dao.BookDao;
import com.hnust.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: BookServiceImpl
 * Package: com.hnust.spring.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/2 - 12:43
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Override
//    @Transactional(
//            //readOnly = true
//            /**
//             * 有一个属性readOnly = True
//             * 对一个查询操作来说，如果我们把它设置成只读，就能够明确告诉数据库，这个操作不涉及写操作。这样数据库就能够针对查询操作来进行优化。
//             * 注意：对增删改操作设置只读会抛出下面异常：Caused by:
//             * java.sql.SQLException: Connection is read-only. Queries leading to data modificationare not allowed
//            */
//            //timeout = 3
//            /**
//             * 表示如果某个事务在规定时间内没有完成即超时，则事务进行强制回滚
//             */
//            /*noRollbackFor = ArithmeticException.class
//            noRollbackForClassName = "java.lang.ArithmeticException"*/
//            /**
//             * 声明式事务默认只针对运行时异常回滚，编译时异常不回滚。
//             * 可以通过@Transactional中相关属性设置回滚策略
//             * - rollbackFor属性：需要设置一个Class类型的对象
//             * - rollbackForClassName属性：需要设置一个字符串类型的全类名
//             * - noRollbackFor属性：需要设置一个Class类型的对象
//             * - rollbackFor属性：需要设置一个字符串类型的全类名
//             *
//             * 表示只有什么情况下进行回滚，或只有什么情况下不进行回滚
//            */
//            //isolation = Isolation.REPEATABLE_READ // 可重复读
//            /**
//             * 隔离级别一共有四种：
//             * - 读未提交：READ UNCOMMITTED
//             * 允许Transaction01读取Transaction02未提交的修改。
//             * - 读已提交：READ COMMITTED、
//             * 要求Transaction01只能读取Transaction02已提交的修改。
//             * - 可重复读：REPEATABLE READ
//             * 确保Transaction01可以多次从一个字段中读取到相同的值，即Transaction01执行期间禁止其它事务对这个字段进行更新。
//             * - 串行化：SERIALIZABLE
//             * 确保Transaction01可以多次从一个表中读取到相同的行，在Transaction01执行期间，禁止其它
//             * 事务对这个表进行添加、更新、删除操作。可以避免任何并发问题，但性能十分低下。
//             */
//            /*propagation = Propagation.REQUIRES_NEW
//            propagation = Propagation.REQUIRES*/
//            /**
//             * 事务的传播行为：
//             * REQUIRES_NEW 使用的是被调用者的事务，也就是说能买一本是一本，但是这里出现了一个问题
//             *              就是余额第一本买不了，但是第二本可以买，但是由于bookId的顺序的原因，最终
//             *              呈现的效果是第二本也没买成功
//             * REQUIRES 使用的是调用者的事务，买不了就都不买
//             */
//    )
    public void buyBook(Integer userId, Integer bookId) {
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        // 更新图书的库存
        bookDao.updateStock(bookId);
        // 更新用户的余额
        bookDao.updateBalance(userId,price);
        //System.out.println(1/0);
    }
}
