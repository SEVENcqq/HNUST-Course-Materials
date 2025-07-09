package com.hnust.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: DateConverter
 * Package: com.hnust.convert
 * Description: chapter12.2.4
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 21:19
 */
public class DateConverter implements Converter<String, Date> {
    private String datePattern = "yyyy-MM-dd";
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("无效的日期格式，请使用这种格式：" + datePattern);
        }
    }
}
