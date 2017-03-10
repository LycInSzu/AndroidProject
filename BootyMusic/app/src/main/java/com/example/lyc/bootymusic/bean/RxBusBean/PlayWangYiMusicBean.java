package com.example.lyc.bootymusic.bean.RxBusBean;

/**
 * Created by LYC on 2017/1/8.
 */

public class PlayWangYiMusicBean {
    public static final int PLAY=0,PAUSE=1,NEXT=2,STOP=3,COMPLETED=4;
    private int type=-1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PlayWangYiMusicBean(int tp) {
        this.type=tp;
    }
}
