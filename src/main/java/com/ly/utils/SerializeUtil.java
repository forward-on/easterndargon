package com.ly.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author : ly.
 * @Date : 2018/5/24.
 */
public class SerializeUtil {

    private static Log log = LogFactory.getLog(SerializeUtil.class);
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {

            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            log.error("serialize object error",e);
        }
        return null;
    }
    public static Object unserialize(byte[] bytes) {

        ByteArrayInputStream bais = null;
        try {

            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.error("un serialize object error",e);
        }
        return null;
    }


}
