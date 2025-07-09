package com.hnust.xy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.xy.entity.AddressBook;
import com.hnust.xy.mapper.AddressBookMapper;
import com.hnust.xy.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * ClassName: AddressBookServiceImpl
 * Package: com.hnust.xy.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/26 - 8:28
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
