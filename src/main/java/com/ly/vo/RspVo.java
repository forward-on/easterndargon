package com.ly.vo;

import java.io.Serializable;

/**
 * @Description:
 * @Date 2018-07-30 9:40
 * @Author ly
 */
public class RspVo implements Serializable {

    private static final long serialVersionUID = 6622366679191256683L;
    private String code;
    private String msg;
    private Object data;

    public RspVo() {
    }

    public RspVo(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RspVo success(Object data) {
        return new RspVo("000", (String)null, data);
    }

    public static RspVo error(String code, String msg) {
        return new RspVo(code, msg, (Object)null);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
