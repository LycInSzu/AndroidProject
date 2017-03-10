package com.example.lyc.bootymusic.utils;


import java.util.Comparator;

/**
 * 作者：abc on 2016/12/9 12:15
 * 邮箱：liyuchong@kocla.com
 */

public class DeviceContactPinyinComparator implements Comparator<DeviceContact> {

    public int compare(DeviceContact o1, DeviceContact o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}
