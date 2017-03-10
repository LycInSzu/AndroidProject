package com.example.lyc.bootymusic.utils.internet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：abc on 2016/12/2 11:34
 * 邮箱：liyuchong@kocla.com
 */

public class RetrofitHttpUtil {
    //网易云音乐eg：http://s.music.163.com/search/get/?type=1&s=周杰伦&limit=5
    public static final String SingleMusicURL = "http://s.music.163.com/";
    public static final String MainURL = "http://music.163.com/";
    public static final String MusicBeanURL = "http://m1.music.126.net/";//下载特点id的歌曲

    enum URLType {SingleMusicURL, MainURL,MusicBeanURL}

    private static OkHttpClient mOkHttpClient;
    private static RetrofitInterface imageRetrofitInterface;
    private static RetrofitInterface commonRetrofitInterface;
    private static Object mObject = new Object();

    public static RetrofitInterface getCommonRetrofitInterface() {
        if (commonRetrofitInterface == null) {
            synchronized (mObject) {
                if (commonRetrofitInterface == null) {
                    commonRetrofitInterface = provideRetrofitService(URLType.MainURL);
                }
            }
        }
        return commonRetrofitInterface;
    }
    public static RetrofitInterface getSingleMusicInterface() {
        if (commonRetrofitInterface == null) {
            synchronized (mObject) {
                if (commonRetrofitInterface == null) {
                    commonRetrofitInterface = provideRetrofitService(URLType.SingleMusicURL);
                }
            }
        }
        return commonRetrofitInterface;
    }

    public static RetrofitInterface getImageRetrofitInterface() {
        if (imageRetrofitInterface == null) {
            synchronized (mObject) {
                if (imageRetrofitInterface == null) {
                    imageRetrofitInterface = provideRetrofitService(URLType.MusicBeanURL);
                }
            }
        }
        return imageRetrofitInterface;
    }

    private static void initHttpClient() {
//         OkHttpClient mOkHttpClient;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (httpClientBuilder.interceptors() != null) {
            httpClientBuilder.interceptors().clear();
        }



        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("appver","2.0.2")
                        .addHeader("referer","http://music.163.com")
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };






//        new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//////                这里可以获取到请求的request所有数据
//                Request request = chain.request();
//                String path = request.url().encodedPath();
//                Log.d("AppClient", path + ">>>path");
//                String query = request.url().query();
//                Log.d("AppClient", query + ">>>query");
////                if (BuildConfig.DEBUG){
////                    Log.d("AppClient", query + ">>>query");
////                }
//                //这里设置成你的全局header
//                Request interRequest = chain.request().newBuilder()
//                        .headers(Headers.of(new HashMap<String, String>().put("appver","2.0.2")))
//                        .build();
//                return chain.proceed(interRequest);
//            }
//        }






        httpClientBuilder.addInterceptor(headerInterceptor)//设置头
                .addInterceptor(logging)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        mOkHttpClient = httpClientBuilder.build();
    }


    private static  RetrofitInterface provideRetrofitService(URLType type) {
        if (mOkHttpClient == null)
            initHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(RetrofitHttpUtil.getUrlByLocalSetting(type)))
                // for RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
        return retrofit.create(RetrofitInterface.class);
    }


    private static String getUrlByLocalSetting(URLType type) {
        String url;
        switch (type) {
            case MainURL:
                url = MainURL;
                break;
            case SingleMusicURL:
                url = SingleMusicURL;
                break;
            case MusicBeanURL:
                url = MusicBeanURL;
                break;
            default:
                url = MainURL;
                break;
        }
        return url;
    }
}
