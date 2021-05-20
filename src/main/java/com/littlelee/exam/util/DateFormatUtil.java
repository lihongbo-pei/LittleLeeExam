package com.littlelee.exam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 返回指定日期指定类型的字符串
 * @author littlelee
 * @date 2020/4/11 16:04
 */
public class DateFormatUtil {

    public static String getDate(String pattern, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
