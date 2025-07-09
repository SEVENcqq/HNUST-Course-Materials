package com.hnust.spring.service.impl;

import com.hnust.spring.service.BookService;
import com.hnust.spring.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: CheckoutServiceImpl
 * Package: com.hnust.spring.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/2 - 14:24
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private BookService bookService;
    @Override
    @Transactional
    public void checkout(Integer userId, Integer[] bookIds) {
        for (Integer bookId : bookIds) {
            bookService.buyBook(userId, bookId);
        }
    }
}
