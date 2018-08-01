package com.ly.log;

import org.aspectj.lang.JoinPoint;

/**
 * @Description:
 * @Date 2018-07-30 9:50
 * @Author ly
 */
public interface ILogService {
    void log(JoinPoint var1);

    void logArgAndReturn(JoinPoint var1, Object var2);
}
