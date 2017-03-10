package com.example.lyc.bootymusic.utils;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * 作者：abc on 2016/7/12 14:00
 * 邮箱：liyuchong@kocla.com
 */
public class EnableDelayUtil {
    private static Handler handler = new Handler();
    private static ArrayList<Runnable> myRunnableList = new ArrayList<>();
    private static ArrayList<View> viewList = new ArrayList<>();


    public static void setDelayed(final View v) {
//            handler=new Handler();
        final Runnable myRunnable = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                v.setEnabled(true);
                viewList.remove(v);
                myRunnableList.remove(this);
            }
        };
        myRunnableList.add(myRunnable);
        viewList.add(v);

        v.setEnabled(false);

        handler.postDelayed(myRunnable, 2000);
    }

    public static void remove() {
        Log.d("EnableDelayUtil","---------------------remove");
        if (!myRunnableList.isEmpty()) {
            for (Runnable myRunnable : myRunnableList) {
                if (myRunnable != null) {
                    handler.removeCallbacks(myRunnable);
                }
            }
            myRunnableList.clear();
        }
    }
}
