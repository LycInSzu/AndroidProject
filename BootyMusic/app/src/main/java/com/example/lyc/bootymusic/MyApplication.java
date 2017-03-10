package com.example.lyc.bootymusic;

import android.app.Application;

import utils.L;

/**
 * Created by LYC on 2017/1/8.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        L.isDebug=true;
    }
}
