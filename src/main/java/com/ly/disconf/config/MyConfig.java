package com.ly.disconf.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Date 2018-08-01 11:47
 * @Author ly
 */
@Service
@Scope(value = "singleton")
@DisconfFile(filename = "redis.properties")
public class MyConfig {

    private String host;
    private String port;

    @DisconfFileItem(name = "redis.host", associateField = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @DisconfFileItem(name = "redis.port", associateField = "port")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
