package com.example.lyc.bootymusic.utils.internet;


import com.example.lyc.bootymusic.bean.WangYiMusicBean;

import java.util.Map;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * 作者：abc on 2016/12/2 11:36
 * 邮箱：liyuchong@kocla.com
 * <p>
 * Retrofit建议url以/结束，注解不要以/开始
 */

public interface RetrofitInterface {


    String RES_USERS_LOGIN = "{path}";

    @POST(RES_USERS_LOGIN)
    Call<WangYiMusicBean> searchWangYiMusic(@Path(value = "path",encoded=true) String path, @QueryMap(encoded=true) Map<String, Object> body);


    @POST(RES_USERS_LOGIN)
    Call<String> commonConnect(@Path(value = "path",encoded=true) String path, @QueryMap(encoded=true) Map<String, Object> body);

    @GET(RES_USERS_LOGIN)
    Call<String> getMusicOfAlbum(@Path(value = "path",encoded=true) String path);
}

