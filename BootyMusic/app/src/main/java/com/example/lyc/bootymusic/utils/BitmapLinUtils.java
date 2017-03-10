package com.example.lyc.bootymusic.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.MeasureSpec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapLinUtils {

	private BitmapLinUtils() {
		return;
	}

	/**
	 * 将DP转成像素PX
	 * 
	 * @param dp
	 *            dp size to get converted
	 * @return Pixel size
	 */
	public static float fromDpToPx(float dp) {
		return dp * Resources.getSystem().getDisplayMetrics().density;
	}

	/**
	 * 将Drawable资源文件转成Bitmap图片
	 * 
	 * @param drawable
	 *            {@link android.graphics.drawable.Drawable} to be converted
	 * @return {@link android.graphics.Bitmap} object
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {

		if (drawable instanceof BitmapDrawable)
			return ((BitmapDrawable) drawable).getBitmap();

		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	private static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static Bitmap getBitmap(Context context, int res, float max) {
		return getBitmap(context,
				BitmapFactory.decodeResource(context.getResources(), res), max);
	}

	/**
	 *
	 * @param context
	 * @param srcBitmap
	 * @param min
	 * @param max
	 * @return
	 */
	@SuppressLint("NewApi")
	public static Bitmap getBitmap(Context context, Bitmap srcBitmap, float max) {
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		float imagewidth = srcBitmap.getWidth();
		float imagehight = srcBitmap.getHeight();
		float newwidth = 0;
		float newheight = 0;
		float biger = 0;
		float demax = 0;
		float demin = 0;
		int statecode = 0;
		float dem = 0;
		// "最大为:" + max
		biger = imagewidth >= imagehight ? imagewidth : imagehight;
		demax = max / biger;
		// "最小比:" + demin + "最大比" + demax
		newwidth = imagewidth * demax;
		newheight = imagehight * demax;
		// "宽度:" + imagewidth + "高度" + imagehight
		// "新的宽度:" + newwidth + "新的高度" + newheight
		// "处理过一次后的新的宽度:" + newwidth + "新的高度" + newheight
		Matrix matrix = new Matrix();
		dem = statecode == 1 ? demin : demax;
		// "压缩比是" + dem
		matrix.postScale(dem, dem);
		Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, (int) imagewidth,
				(int) imagehight, matrix, true);
		// "处理后第二次的图片宽为:" + bitmap.getWidth() + "高为:" + bitmap.getHeight()
		if (statecode == 1) {
			System.err.println("特殊处理");
			int cuth = (int) (newheight > dip2px(context, max) ? (newheight - dip2px(
					context, max)) / 2 : 0);
			int cutw = (int) (newwidth > dip2px(context, max) ? (newwidth - dip2px(
					context, max)) / 2 : 0);
			newheight = newheight < dip2px(context, max) ? newheight : dip2px(
					context, max);
			newwidth = newwidth < dip2px(context, max) ? newwidth : dip2px(
					context, max);
			System.err.println("处理后宽为" + newwidth + "高为:" + newheight);
			bitmap = Bitmap.createBitmap(bitmap, cutw, cuth, (int) newwidth,
					(int) newheight);
		}
		// "最终处理后的图片宽为:" + bitmap.getWidth() + "高为:" + bitmap.getHeight()
		// "最终处理后的图片大小为:" + bitmap.getByteCount()
		return bitmap;
	}

	/**
	 * 这是一个将大的位图加载到系统内存中。然后按照自定义宽高的比例。缩放后显示到活动
	 *
	 * @param filePath
	 * @param height
	 * @param width
	 * @return
	 */
	public static Bitmap getImage(String filePath, int height, int width) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opts);
		int imageHeight = opts.outHeight;// 拿到图片的真是高度
		int imageWidth = opts.outWidth;// 拿到图片的真实宽度
		/**
		 * 计算缩放比
		 */
		int scole = 1;// 默认缩放比例为1；
		// Math.ceil---->取最近的整数。如：2.3=>3。7.1=>8（为了让得到的比例强转）
		int scoleY = (int) Math.ceil(imageHeight / height);// 得到高度的缩放比例
		int scoleX = (int) Math.ceil(imageWidth / width);// 得到缩放的宽度比例
		if (scoleY > 1 || scoleX > 1) {
			if (scoleY > scoleX) {
				scole = scoleY;
			} else {
				scole = scoleX;
			}
		}
		// 修改完比例之后。就把JustDecodeBounds设置为false。真正去解析图片里面的数据
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scole;// 设置图片采样的比例
		return BitmapFactory.decodeFile(filePath, opts);
	}

	/**
	 * 缩小和放大一个位图
	 *
	 * @param bitmap
	 * @return
	 */
	public static Bitmap smallBitmap(Bitmap bitmap, float multiple) {
		Matrix matrix = new Matrix();
		matrix.postScale(multiple, multiple); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}

	public static Bitmap getBitmap2(Context context, String filePath, float reft) {
		Bitmap bitmap = BitmapFactory.decodeFile(filePath);
		return getBitmap2(context, bitmap, reft);
	}

	public static Bitmap getBitmap2(Context context, Bitmap srcBitmap,
									float reft) {
		float smaller = 0;
		float demin = 0;
		float newwidth = 0;
		float newheight = 0;
		float imagewidth = srcBitmap.getWidth();
		float imagehight = srcBitmap.getHeight();
		smaller = imagewidth <= imagehight ? imagewidth : imagehight;
		demin = dip2px(context, reft) / smaller;
		System.err.println("压缩比例:" + demin);
		newwidth = imagewidth * demin;
		newheight = imagehight * demin;
		System.err.println("新的宽度:" + newwidth + "新的高度" + newheight);
		Matrix matrix = new Matrix();
		matrix.postScale(demin, demin);
		Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, (int) imagewidth,
				(int) imagehight, matrix, true);
		System.err.println("第一次处理后的图片宽为:" + bitmap.getWidth() + "高为:"
				+ bitmap.getHeight());
		int cuth = (int) (newheight > dip2px(context, reft) ? (newheight - dip2px(
				context, reft)) / 2 : 0);
		int cutw = (int) (newwidth > dip2px(context, reft) ? (newwidth - dip2px(
				context, reft)) / 2 : 0);
		newheight = newheight < dip2px(context, reft) ? newheight : dip2px(
				context, reft);
		newwidth = newwidth < dip2px(context, reft) ? newwidth : dip2px(
				context, reft);
		bitmap = Bitmap.createBitmap(bitmap, cutw, cuth, (int) newwidth,
				(int) newheight);
		System.err.println("最终处理后的图片宽为:" + bitmap.getWidth() + "高为:"
				+ bitmap.getHeight());
		srcBitmap.recycle();
		srcBitmap = null;
		return bitmap;
	}

	@SuppressWarnings("static-access")
	public static Bitmap getBitmap(int width, int heigh, int textSize,
								   int bgColor, int fontColor, String text, float startX,
								   float startY, int typeface, float rotate) {
		Bitmap bitmap = Bitmap.createBitmap(width, heigh, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawColor(bgColor);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG
				| Paint.DEV_KERN_TEXT_FLAG);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.defaultFromStyle(typeface));
		paint.setColor(fontColor);
		canvas.drawText(text, startX, startY, paint);
		canvas.save(canvas.ALL_SAVE_FLAG);
		canvas.restore();
		Matrix m = new Matrix();
		m.setRotate(rotate);
		Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), m, true);
		return b2;
	}

	@SuppressWarnings("static-access")
	public static Bitmap getBitmap(int width, int heigh, int bgColor,
								   int strokeColor) {
		Bitmap bitmap = Bitmap.createBitmap(width, heigh, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawColor(bgColor);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG
				| Paint.DEV_KERN_TEXT_FLAG);
		paint.setColor(strokeColor);
		canvas.save(canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return bitmap;
	}

	public static void compressImage(String srcPath, String outFile) {
		/**
		 * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
		 */
		int degree = readPictureDegree(srcPath);

		final int DEF_MIN = 480;
		final int DEF_MAX = 1280;
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap;
		BitmapFactory.decodeFile(srcPath, newOpts);
		int picMin = Math.min(newOpts.outWidth, newOpts.outHeight);
		int picMax = Math.max(newOpts.outWidth, newOpts.outHeight);
		float picShap = 1.0f * picMin / picMax;
		float defShap = 1.0f * DEF_MIN / DEF_MAX;
		float scale = 1f;
		if (picMax < DEF_MAX) {

		} else if (picShap > defShap) {// 比较 方正的，等比压缩至最大值
			scale = 1.0f * DEF_MAX / picMax;

		} else if (picMin > DEF_MIN) {// 较窄边压缩至最小值
			scale = 1.0f * DEF_MIN / picMin;
		}
		int scaledWidth = (int) (scale * newOpts.outWidth);
		int scaledHeight = (int) (scale * newOpts.outHeight);
		newOpts.inSampleSize = (int) ((scale == 1 ? 1 : 2) / scale);
		newOpts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		if (scale != 1) {
			Matrix matrix = new Matrix();
			scale = (float) scaledWidth / bitmap.getWidth();
			matrix.setScale(scale, scale);
			Bitmap bitmapNew = Bitmap.createBitmap(bitmap, 0, 0,
					bitmap.getWidth(), bitmap.getHeight(), matrix, false);
			bitmap.recycle();
			bitmap = bitmapNew;
		}
		int quality;
		if (scaledHeight * scaledWidth > (1280 * 720)) {
			quality = 30;
		} else {
			quality = 80 - (int) ((float) scaledHeight * scaledWidth
					/ (1280 * 720) * 50);
		}
		try {
			System.err.println("file byte is:" + new File(srcPath).length());
			//
			/**
			 * 把图片旋转为正的方向
			 */
			bitmap = rotaingImageView(degree, bitmap);
			//
			bitmap.compress(Bitmap.CompressFormat.JPEG, quality,
					new FileOutputStream(new File(outFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bitmap.recycle();
		System.gc();
	}

	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 旋转图片
	 *
	 * @param angle
	 *
	 * @param bitmap
	 *
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	/**
	 * 将一个View转成Bitmap
	 *
	 * @param view
	 * @param bitmapWidth
	 * @param bitmapHeight
	 * @return
	 */
	public static Bitmap convertViewToBitmap(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}

	/**
	 * 把输入流转为byte数组
	 *
	 * @param in
	 * @return
	 */
	public static byte[] getBytes(InputStream in) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] result = null;
		byte[] buffer = new byte[1024];
		int num;
		try {
			while ((num = in.read(buffer)) != -1) {
				out.write(buffer, 0, num);
			}
			out.flush();
			result = out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// 将byte[]转换成InputStream
	public static InputStream Byte2InputStream(byte[] b) {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		return bais;
	}

	// 将InputStream转换成byte[]
	public static byte[] InputStream2Bytes(InputStream is) {
		String str = "";
		byte[] readByte = new byte[1024];
		try {
			while ((is.read(readByte, 0, 1024)) != -1) {
				str += new String(readByte).trim();
			}
			return str.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 将Bitmap转换成InputStream
	public static InputStream Bitmap2InputStream(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

	// 将Bitmap转换成InputStream
	public static InputStream Bitmap2InputStream(Bitmap bm, int quality) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

	// 将InputStream转换成Bitmap
	public static Bitmap InputStream2Bitmap(InputStream is) {
		return BitmapFactory.decodeStream(is);
	}

	// Drawable转换成InputStream
	public static InputStream Drawable2InputStream(Drawable d) {
		Bitmap bitmap = BitmapLinUtils.drawable2Bitmap(d);
		return BitmapLinUtils.Bitmap2InputStream(bitmap);
	}

	// InputStream转换成Drawable
	public static Drawable InputStream2Drawable(InputStream is) {
		Bitmap bitmap = BitmapLinUtils.InputStream2Bitmap(is);
		return BitmapLinUtils.bitmap2Drawable(bitmap);
	}

	// Drawable转换成byte[]
	public static byte[] Drawable2Bytes(Drawable d) {
		Bitmap bitmap = BitmapLinUtils.drawable2Bitmap(d);
		return BitmapLinUtils.Bitmap2Bytes(bitmap);
	}

	// byte[]转换成Drawable
	public static Drawable Bytes2Drawable(byte[] b) {
		Bitmap bitmap = BitmapLinUtils.Bytes2Bitmap(b);
		return BitmapLinUtils.bitmap2Drawable(bitmap);
	}

	/**
	 * Bitmap转换成byte[]
	 *
	 * @param bm
	 * @return
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		return baos.toByteArray();
	}

	// byte[]转换成Bitmap
	public static Bitmap Bytes2Bitmap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		}
		return null;
	}

	// Drawable转换成Bitmap
	public static Bitmap drawable2Bitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	// Bitmap转换成Drawable
	@SuppressWarnings("deprecation")
	public static Drawable bitmap2Drawable(Bitmap bitmap) {
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		Drawable d = (Drawable) bd;
		return d;
	}

	/**
	 * Android 4.4以后版本获取图片路径方法
	 */
	@SuppressLint("NewApi")
	public static String getPathUri(final Context context, final Uri uri) {
		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {

			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}
		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	private static String getDataColumn(Context context, Uri uri,
										String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	private static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	private static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	private static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	private static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}
}
