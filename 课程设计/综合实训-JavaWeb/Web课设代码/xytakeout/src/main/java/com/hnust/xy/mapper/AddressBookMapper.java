package com.hnust.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnust.xy.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: AddressBookMapper
 * Package: com.hnust.xy.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/26 - 8:27
 */

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
