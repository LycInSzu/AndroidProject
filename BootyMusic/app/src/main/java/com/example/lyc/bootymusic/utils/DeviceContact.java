package com.example.lyc.bootymusic.utils;

/**
 * 作者：abc on 2016/12/9 11:44
 * 邮箱：liyuchong@kocla.com
 *
 * 手机联系人
 */

public class DeviceContact {

    private String  name;
    private String  phone;

    private String sortLetters;  //显示数据拼音的首字母
    private boolean select=false;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select_state) {
        this.select = select_state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public DeviceContact(String sortLetters, String xianShiMing, String shouJiHaoMa)

    {
        this.sortLetters=sortLetters;
        this.name=xianShiMing;
        this.phone=shouJiHaoMa;
    }
}
