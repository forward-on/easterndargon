package com.ly.disconf.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 * @Description: 静态 配置文件
 * @Date 2018-08-01 16:25
 * @Author ly
 */
@DisconfFile(filename = "static.properties")
public class StaticConfig {

    private static String age = "10";

    @DisconfFileItem(name = "age", associateField = "age")
    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        StaticConfig.age = age;
    }
}
