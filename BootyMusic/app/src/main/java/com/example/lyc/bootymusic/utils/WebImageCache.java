package com.example.lyc.bootymusic.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WebImageCache
{
	private static final String DISK_CACHE_PATH = "/web_image_cache/";

	private ConcurrentHashMap<String, SoftReference<Bitmap>> memoryCache;
	/**
	 * 默认的缓存根目录
	 */
	private static String diskCachePath = "/data/data/com.koala.shop.mobile.student/cache/web_image_cache/";
	private boolean diskCacheEnabled = false;
	private ExecutorService writeThread;

	public WebImageCache(Context context)
	{
		// Set up in-memory cache store
		memoryCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>();

		// Set up disk cache store
		Context appContext = context.getApplicationContext();
		diskCachePath = appContext.getCacheDir().getAbsolutePath() + DISK_CACHE_PATH;
		File outFile = new File(diskCachePath);
		outFile.mkdirs();
		diskCacheEnabled = outFile.exists();
		// Set up threadpool for image fetching tasks
		writeThread = Executors.newSingleThreadExecutor();
	}

	public Bitmap get(final String url)
	{
		Bitmap bitmap = null;
		bitmap = getBitmapFromMemory(url);
		if (bitmap == null)
		{
			bitmap = getBitmapFromDisk(url);

			if (bitmap != null)
			{
				cacheBitmapToMemory(url, bitmap);
			}
		}
		return bitmap;
	}

	public void put(String url, Bitmap bitmap)
	{
		cacheBitmapToMemory(url, bitmap);
		cacheBitmapToDisk(url, bitmap);
	}

	public void remove(String url)
	{
		if (url == null)
		{
			return;
		}

		// Remove from memory cache
		memoryCache.remove(getCacheKey(url));

		// Remove from file cache
		File f = new File(diskCachePath, getCacheKey(url));
		if (f.exists() && f.isFile())
		{
			f.delete();
		}
	}

	public void clear()
	{
		// Remove everything from memory cache
		memoryCache.clear();

		// Remove everything from file cache
		File cachedFileDir = new File(diskCachePath);
		if (cachedFileDir.exists() && cachedFileDir.isDirectory())
		{
			File[] cachedFiles = cachedFileDir.listFiles();
			for (File f : cachedFiles)
			{
				if (f.exists() && f.isFile())
				{
					f.delete();
				}
			}
		}
	}

	private void cacheBitmapToMemory(final String url, final Bitmap bitmap)
	{
		memoryCache.put(getCacheKey(url), new SoftReference<Bitmap>(bitmap));
	}

	private void cacheBitmapToDisk(final String url, final Bitmap bitmap)
	{
		writeThread.execute(new Runnable()
		{
			@Override
			public void run()
			{
				if (diskCacheEnabled)// 文件路径是否已经创建
				{
					BufferedOutputStream ostream = null;
					try
					{
						ostream = new BufferedOutputStream(new FileOutputStream(new File(diskCachePath,
								getCacheKey(url))), 2 * 1024);
						bitmap.compress(CompressFormat.JPEG, 80, ostream);

					} catch (FileNotFoundException e)
					{
						e.printStackTrace();
					} finally
					{
						try
						{
							if (ostream != null)
							{
								ostream.flush();
								ostream.close();
							}
						} catch (IOException e)
						{
						}
					}
				}
			}
		});
	}

	private Bitmap getBitmapFromMemory(String url)
	{
		Bitmap bitmap = null;
		SoftReference<Bitmap> softRef = memoryCache.get(getCacheKey(url));
		if (softRef != null)
		{
			bitmap = softRef.get();
		}

		return bitmap;
	}

	private Bitmap getBitmapFromDisk(String url)
	{
		Bitmap bitmap = null;
		if (diskCacheEnabled)
		{
			String filePath = getFilePath(url);
			File file = new File(filePath);
			if (file.exists())
			{
				/**
				 * 图片的转码
				 */
				bitmap = BitmapFactory.decodeFile(filePath);
			}
		}
		return bitmap;
	}

	public static String getFilePath(String url)
	{
		return diskCachePath + getCacheKey(url);
	}

	private static String getCacheKey(String url)
	{
		if (url == null)
		{
			throw new RuntimeException("Null url passed in");
		} else
		{
			return url.replaceAll("[.:/,%?&=_pjg]", "").replaceAll("[+]+", "");
		}
	}
}
