package com.example.lyc.bootymusic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2015/8/5.
 */
public class TextUtil {
    public static boolean isEmpty(String str){
        if(str ==null || str.trim().equals("")|| str.equals("null")){
            return true;
        }
        return false;
    }

    //截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
}
