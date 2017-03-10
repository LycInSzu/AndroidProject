//package com.example.lyc.bootymusic.utils;
//
//import android.content.Context;
//import android.widget.ImageView;
//
//import com.koala.broker.MyApplication;
//import com.koala.broker.communication_module.bean.User;
//import com.koala.shop.mobile.broker.R;
//import com.squareup.picasso.Picasso;
//
//import java.util.Map;
//
//public class UserUtils {
//	/**
//	 * 根据username获取相应user，由于demo没有真实的用户数据，这里给的模拟的数据；
//	 *
//	 * @param username
//	 * @return
//	 */
//	public static User getUserInfo(String username) {
//		User user = MyApplication.getInstance().getContactList().get(username);
//		if (user == null) {
//			user = new User(username);
//		}
//
//		if (user != null) {
//			// demo没有这些数据，临时填�??
//			user.setNick(username);
//			// user.setAvatar("http://downloads.easemob.com/downloads/57.png");
//		}
//		return user;
//	}
//
//	/**
//	 * 设置用户头像
//	 *
//	 * @param username
//	 */
//	public static void setUserAvatar(Context context,
//									 Map<String, String> avatarMap, String username, ImageView imageView) {
//		User user = getUserInfo(username);
//		if (user != null) {
//			// Picasso.with(context).load(user.getAvatar()).placeholder(R.drawable.default_avatar).into(imageView);
//			// Picasso.with(context).load(user.getAvatar()).placeholder(R.drawable.default_avatar).into(imageView);
//			ImageTools.getImageLoader().displayImage(
//					HttpUtil.ImageUrl + avatarMap.get(user.getUsername()),
//					imageView,ImageTools.getFadeOptionsDefault1());
//
//		} else {
//			Picasso.with(context).load(R.drawable.default_avatar)
//					.into(imageView);
//		}
//	}
//
//	/**
//	 * 设置用户头像
//	 *
//	 * @param username
//	 */
//	public static void setUserAvatar(Context context, String username,
//									 ImageView imageView) {
//		User user = getUserInfo(username);
//		if (user != null) {
//			Picasso.with(context).load(user.getAvatar())
//					.placeholder(R.drawable.default_avatar).fit().into(imageView);
//		} else {
//			Picasso.with(context).load(R.drawable.default_avatar)
//					.into(imageView);
//		}
//	}
//
//}
