package com.ly.disconf.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Date 2018-08-01 12:54
 * @Author ly
 */
@Service
@Scope(value = "singleton")
@DisconfFile(filename = "a.properties")
@DisconfUpdateService(classes = {AConfig.class}, itemKeys = "address")
public class AConfig {

    private String a;
    private String b;
    private String address;

    @DisconfFileItem(name = "address", associateField = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DisconfFileItem(name = "a", associateField = "a")
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @DisconfFileItem(name = "b", associateField = "b")
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
