package com.ly.log;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Date 2018-07-30 9:51
 * @Author ly
 */
public class LogServiceImpl implements ILogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    public LogServiceImpl() {
    }

    @Override
    public void log(JoinPoint point) {
        LOGGER.info("-=> {}.{}({}) ", new Object[]{point.getTarget().getClass().getName(), point.getSignature().getName(), point.getArgs()});
    }

    @Override
    public void logArgAndReturn(JoinPoint point, Object returnObj) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("-=> {}.{}({}); return {}", new Object[]{point.getTarget().getClass().getName(), point.getSignature().getName(), point.getArgs(), returnObj});
        } else {
            LOGGER.info("-=> {}.{}() return.", point.getTarget().getClass().getName(), point.getSignature().getName());
        }

    }

}
