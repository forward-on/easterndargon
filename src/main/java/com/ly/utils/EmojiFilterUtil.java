package com.ly.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ly.
 * @Date : 2018/5/24.
 */
public class EmojiFilterUtil {

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                // do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }


    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)){
            return source;
        }
        StringBuffer sb = new StringBuffer();
        char[] ach = source.toCharArray();
        List<Integer> key = null;
        for (int i = 0,cp; i < ach.length;) {
            key = new ArrayList<Integer>();
            cp = Character.codePointAt(ach, i);
            int count=Character.charCount(cp);
            if (cp<32) {
                i=i+count;
                sb.append(" ");
                continue;
            }
            if (i + count < ach.length) {
                int tp=Character.codePointAt(ach,i+count);
                key.add(cp);
                key.add(tp);
                if (EmojiUtil.isEmoji(key)) {
                    i=i+count+Character.charCount(tp);
                    sb.append("");
                    continue;
                }
            }
            key = new ArrayList<Integer>();
            key.add(cp);
            if (EmojiUtil.isEmoji(key)) {
                sb.append("");
                i=i+count;
                continue;
            }
            sb.append(ach[i]);
            i=i+count;
        }
        return sb.toString();



    }

    public static void main(String[] args) {
        String s = "a";
        System.out.println(filterEmoji(s));
    }



}
