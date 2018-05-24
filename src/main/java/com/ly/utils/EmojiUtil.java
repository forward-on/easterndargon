package com.ly.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : ly.
 * @Date : 2018/5/24.
 */
public class EmojiUtil {

    private static HashMap<List<Integer>, String> convertMap = new HashMap<List<Integer>, String>();

    static{
        readMap();
    }


    /**
     * 读取emoji.xml，获得converMap
     */
    public static void readMap(){
        if (convertMap == null || convertMap.size() == 0) {
            convertMap = new HashMap<List<Integer>, String>();
        }
        try {
            DocumentBuilder documentBuilder=null;
            documentBuilder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream in=EmojiUtil.class.getResourceAsStream("/emoji.xml");
            Document document=documentBuilder.parse(in);
            Element root=document.getDocumentElement();
            NodeList emojiList=root.getChildNodes();
            for (int i = 0; i < emojiList.getLength(); i++) {
                Node emoji=emojiList.item(i);
                if (emoji.getNodeName().equals("e")) {
                    String fromUnicode=emoji.getTextContent();
                    List<Integer> fromCodePoints = new ArrayList<Integer>();
                    if (fromUnicode.length()>6) {
                        String[] froms = fromUnicode.split("-");
                        for (String part : froms) {
                            fromCodePoints.add(Integer.parseInt(part, 16));
                        }
                    }else {
                        fromCodePoints.add(Integer.parseInt(fromUnicode, 16));
                    }
                    convertMap.put(fromCodePoints, fromUnicode);
                }
            }
            if(in!=null){
                in.close();
            }
        } catch (Exception e) {
        }
    }



    /**
     * 获得emoji文件
     * @param list
     * @return
     */
    public static String  getEmojiFileName(List<Integer> list){
        String  fileKey=null;
        if (list!=null&&list.size()>0) {
            fileKey=convertMap.get(list)+".png";
        }
        return fileKey;
    }




    /**
     * 是否为emoji表情
     * @param key
     * @return
     */
    public static boolean isEmoji(List<Integer> key){
        if (key==null) {
            return false;
        }
        if (convertMap.containsKey(key)) {
            return true;
        }
        return false;
    }

	/*public String parseEmoji(String input) {
		if (input == null || input.length() <= 0) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		int[] codePoints = toCodePointArray(input);
		List<Integer> key = null;
		for (int i = 0; i < codePoints.length; i++) {
			key = new ArrayList<Integer>();
			if (i + 1 < codePoints.length) {
				key.add(codePoints[i]);
				key.add(codePoints[i + 1]);
				if (convertMap.containsKey(key)) {
					String value = convertMap.get(key);
					if (value != null) {
						result.append("[e]" + value + "[/e]");
					}
					i++;
					continue;
				}
			}
			key.clear();
			key.add(codePoints[i]);
			if (convertMap.containsKey(key)) {
				String value = convertMap.get(key);
				if (value != null) {
					result.append("[e]" + value + "[/e]");
				}
				continue;
			}
			result.append(Character.toChars(codePoints[i]));
		}
		return result.toString();
	}

	private int[] toCodePointArray(String str) {
		char[] ach = str.toCharArray();
		int len = ach.length;
		int[] acp = new int[Character.codePointCount(ach, 0, len)];
		int j = 0;
		for (int i = 0, cp; i < len; i += Character.charCount(cp)) {
			cp = Character.codePointAt(ach, i);
			acp[j++] = cp;
		}
		return acp;
	}

	public String convertEmoji(String input) {
		if (input == null || input.length() <= 0) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		int[] codePoints = toCodePointArray(input);
		List<Integer> key = null;
		for (int i = 0; i < codePoints.length; i++) {
			key = new ArrayList<Integer>();
			if (i + 1 < codePoints.length) {
				key.add(codePoints[i]);
				key.add(codePoints[i + 1]);
				if (convertMap.containsKey(key)) {
					String value = convertMap.get(key);
					if (value != null) {
						result.append("[表情]");
					}
					i++;
					continue;
				}
			}
			key.clear();
			key.add(codePoints[i]);
			if (convertMap.containsKey(key)) {
				String value = convertMap.get(key);
				if (value != null) {
					result.append("[表情]");
				}
				continue;
			}
			result.append(Character.toChars(codePoints[i]));
		}
		return result.toString();
	}*/



}
