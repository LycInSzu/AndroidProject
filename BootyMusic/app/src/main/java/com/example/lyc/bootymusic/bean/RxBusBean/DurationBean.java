package com.example.lyc.bootymusic.bean.RxBusBean;

/**
 * 作者：abc on 2017/1/12 16:02
 * 邮箱：liyuchong@kocla.com
 */

public class DurationBean {
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    private int duration;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public DurationBean(int duration, int position) {
        this.duration = duration;
        this.position = position;
    }
}
