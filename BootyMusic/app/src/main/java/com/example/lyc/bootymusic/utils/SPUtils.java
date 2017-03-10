package com.example.lyc.bootymusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.HashMap;
import java.util.Map;

public class SPUtils {
	private static String PREFERENCE_NAME = "history";

	public static String SceneList2String(Map<String, String> hashmap)
			throws IOException {
		// 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 然后将得到的字符数据装载到ObjectOutputStream
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				byteArrayOutputStream);
		// writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
		objectOutputStream.writeObject(hashmap);
		// 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
		String SceneListString = new String(Base64.encode(
				byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
		// 关闭objectOutputStream
		objectOutputStream.close();
		return SceneListString;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> String2SceneList(
			String SceneListString) throws StreamCorruptedException,
			IOException, ClassNotFoundException {
		byte[] mobileBytes = Base64.decode(SceneListString.getBytes(),
				Base64.DEFAULT);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				mobileBytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				byteArrayInputStream);
		HashMap<String, String> SceneList = (HashMap<String, String>) objectInputStream
				.readObject();
		objectInputStream.close();
		return SceneList;
	}

	public static boolean putHashMap(Context context, String key,
									 Map<String, String> map) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		try {
			String liststr = SceneList2String(map);
			editor.putString(key, liststr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return editor.commit();
	}

	public static HashMap<String, String> getHashMap(Context context,
													 String key) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		String liststr = settings.getString(key, "");
		try {
			return String2SceneList(liststr);
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
