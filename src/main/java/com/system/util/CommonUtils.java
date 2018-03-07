package com.system.util;

import org.nutz.ioc.impl.PropertiesProxy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhou_yanga on 2018/2/9.
 */
public class CommonUtils {

    private static long orderNum = 0l;
    private static String date ;

    /**
     * 读取配置文件
     * @param key
     * @return
     */
    public static String getProperties(String key){
        PropertiesProxy property = new PropertiesProxy("/config.properties");
        return property.get(key);
    }

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;;
        return orderNo+"";
    }
}
