package com.example.lyc.bootymusic.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyBitmap {
	public static Bitmap getImage(String urlpath) throws Exception {
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		Bitmap bitmap = null;
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(inputStream);
		}
		return bitmap;
	}
}
