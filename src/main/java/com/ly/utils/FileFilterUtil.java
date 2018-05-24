package com.ly.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileFilter;

/**
 * @author : ly.
 * @Date : 2018/5/24.
 */
public class FileFilterUtil implements FileFilter {

    private String suffix;

    private String prefix;

    public FileFilterUtil(String prefix, String suffix){
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File file) {

        String name = file.getName();

        boolean prefixFlag = false;
        boolean suffixFlag = false;

        if(file.isDirectory()){
            return false;
        }

        if(StringUtils.isNotBlank(prefix)) {
            if(name.startsWith(prefix)){
                prefixFlag = true;
            }
        }else{
            prefixFlag = true;
        }

        if(StringUtils.isNotBlank(suffix)) {
            if(name.endsWith(suffix)){
                suffixFlag = true;
            }
        }else{
            suffixFlag = true;
        }

        return prefixFlag && suffixFlag;
    }



}
