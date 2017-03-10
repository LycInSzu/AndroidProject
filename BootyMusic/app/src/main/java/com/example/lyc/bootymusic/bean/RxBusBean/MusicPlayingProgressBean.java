package com.example.lyc.bootymusic.bean.RxBusBean;

/**
 * 作者：abc on 2017/1/11 14:24
 * 邮箱：liyuchong@kocla.com
 */

public class MusicPlayingProgressBean {
    private int progress=0;
    private boolean isForService=false;

    public boolean isForService() {
        return isForService;
    }

    public void setForService(boolean forService) {
        isForService = forService;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public MusicPlayingProgressBean(int progress,boolean flag) {
        this.progress=progress;
        this.isForService=flag;
    }
}
