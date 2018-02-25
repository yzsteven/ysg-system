package com.system.util;

import org.nutz.ioc.impl.PropertiesProxy;

/**
 * Created by zhou_yanga on 2018/2/9.
 */
public class CommonUtils {

    /**
     * 读取配置文件
     * @param key
     * @return
     */
    public static String getProperties(String key){
        PropertiesProxy property = new PropertiesProxy("/config.properties");
        return property.get(key);
    }
}
