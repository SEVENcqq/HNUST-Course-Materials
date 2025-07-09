package com.hnust.spring.controller;

import com.hnust.spring.service.BookService;
import com.hnust.spring.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ClassName: BookController
 * Package: com.hnust.spring.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/2 - 12:42
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CheckoutService checkoutService;

    public void buyBook(Integer userId, Integer bookId){
        bookService.buyBook(userId,bookId);
    }

    public void checkout(Integer userId, Integer[] bookIds){
        checkoutService.checkout(userId,bookIds);
    }
}
