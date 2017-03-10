package com.example.lyc.bootymusic.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.TypedValue;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这是一个小工具类
 * 
 * @author femtoapp
 *
 */
public class ToolLinlUtils
{
	/**
	 * 判断手机号码格式是否合法
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles)
	{
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static int dip2px(Context context, float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale +0.5f);
	}

	/**
	 * 判断邮箱是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email)
	{
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * Toast方法
	 * 
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text)
	{
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Toast方法
	 * 
	 * @param context
	 * @param loginFailureFailed
	 */
	public static void showToast(Context context, int stringResId)
	{
		Toast.makeText(context, stringResId, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 判断身份证号码是否合法
	 * @param text
	 * @return
	 */
	public static boolean personIdValidation(String text)
	{
		String regx = "[0-9]{17}x";
		String regX = "[0-9]{17}X";
		String reg1 = "[0-9]{15}";
		String regex = "[0-9]{18}";
		return text.matches(regx) || text.matches(reg1) || text.matches(regex)|| text.matches(regX);
	}

	/**
	 * 直接打开电话界面打电话
	 * 
	 * @param context
	 * @param phoneNuber
	 */
	public static void callPhone(Context context, String phoneNuber)
	{
		context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNuber)));
	}

	public static int dp2px(Context context, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}
}
