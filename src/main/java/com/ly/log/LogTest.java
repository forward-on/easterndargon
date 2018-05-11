package com.ly.log;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * @author : ly.
 * @Date : 2018/5/4.
 */
@Controller
public class LogTest {

    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        String path = Class.class.getClass().getResource("/").getPath();
        System.out.println(path);
        PropertyConfigurator.configure(path + "/log4j.properties");
//        Logger logger = LoggerFactory.getLogger("parkLogger");
        logger.debug("debug---");
        logger.info("info---");
        logger.error("error--");
        logger.warn("warn---");
        logger.info("zhanweifu {}" , path, path);
    }

}
