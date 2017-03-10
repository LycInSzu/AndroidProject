package com.example.lyc.bootymusic.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 该类用于显示提示信息
 */
public class ToastUtil {
	public static Toast toast = null;//一个用于整个工程的Toast，避免多个toast排队show的情况

	//-------------------------------------Toast part-----------------------------

	public static void showToast(Context context, String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
//            toast.cancel();
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setText(message);
		}
		toast.show();
	}

	public static void showToastForLongTime(Context context, String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
//            toast.cancel();
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setText(message);
		}
		toast.show();
	}

	public static void showToast(Context context, int message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
//            toast.cancel();
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setText(message);
		}
		toast.show();
	}

//-------------------------------------Toast part   end-----------------------------


}
