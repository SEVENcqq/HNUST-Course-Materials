package com.hnust.spring.dao;

/**
 * ClassName: BookDao
 * Package: com.hnust.spring.dao
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/2 - 12:43
 */
public interface BookDao {
    Integer getPriceByBookId(Integer bookId);

    void updateStock(Integer bookId);

    void updateBalance(Integer userId, Integer price);
}
