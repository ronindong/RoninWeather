package com.ronin.weather.app;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MyApplication extends Application {

	private static MyApplication app;
	/**
	 * 图片加载配置
	 */
	private ImageLoaderConfiguration config;
	/**
	 * 图片缓存目录
	 */
	private File cacheDir;
	/**
	 * 记录程序所创建的activity，退出程序时，全部finish。
	 */
	private ArrayList<Activity> activityList = new ArrayList<Activity>();

	private static Context mContext;

	public MyApplication() {
		super();
		
	}

	public synchronized static MyApplication getInstance() {
		if (app == null) {
			app = new MyApplication();
		}
		return app;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if (mContext == null) {
			mContext = getApplicationContext();
		}
		// 初始化ImageLoader配置参数
		initImgLoaderConfig();

	}

	/**
	 * @Description 获取全局上下文对象 mContext
	 * @return Context
	 */
	public static Context getContext() {
		
		return mContext;
	}

	/**
	 * @Description: 初始化ImageLoader配置参数
	 */
	private void initImgLoaderConfig() {

		cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(),
				"imageloader/cache");
		config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.memoryCacheExtraOptions(480, 800)
				.threadPoolSize(3)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// You can pass your own memory cache
				// implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100)
				// 缓存的文件数量
				.discCache(new UnlimitedDiscCache(cacheDir))
				// 自定义缓存路径
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout
																			// (5s),
				.writeDebugLogs() // Remove for release app
				.build();

		ImageLoader.getInstance().init(config);
	}

	/**
	 * @Description: 清理缓存文件
	 * @param file
	 */
	private void clearCacheFile(File file) {
		if (null != file) {
			file.delete();
		}
	}

	/**
	 * 把新创建的activity加入到list中
	 * 
	 * @param ay
	 */
	public void addActivitys(Activity ay) {
		if (null != activityList) {
			if (!activityList.contains(ay)) {
				activityList.add(ay);
			}
		}
	}

	/**
	 * 完全退出app
	 * 
	 * @param context
	 */
	public void exitApp(Context context) {
		for (Activity activity : activityList) {
			activity.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	@Override
	public void onTerminate() {

		super.onTerminate();
		this.clearCacheFile(cacheDir);
		exitApp(MyApplication.this);

	}

}
