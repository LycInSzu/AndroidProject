//package com.example.lyc.bootymusic.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.koala.broker.MyApplication;
//import com.koala.broker.activity.LoginActivity;
//import com.koala.broker.framework.AppManager;
//import com.koala.shop.mobile.broker.R;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.PersistentCookieStore;
//import com.loopj.android.http.RequestParams;
//
//import org.apache.http.Header;
//import org.apache.http.cookie.Cookie;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
////
//
//public class HttpUtil {
//
//    /**
//     * 钱包添加   start
//     */
//    //钱包开发库
////	public static final String BASE_URL = "http://218.17.158.37:8147/onehour_gateway/centerKocla/";
//    // 钱包 测试库
//    public static final String BASE_URL = "http://218.17.158.37:8880/onehour_gateway/centerKocla/";
//    // 钱包 正式库
////	public static final String BASE_URL = "http://120.55.190.237:8080/onehour_gateway/";
//    /**
//     * 获取erp学生信息列表接口
//     */
//    public static final String URL_HUOQUERPXUESHENGXINXILIEBIAO = BASE_URL + "huoQuErpXueShengXinXiLieBiao";
//    /**
//     * 余额
//     **/
//    public static final String URL_WODEYUE = BASE_URL + "woDeYuE";
//    /**
//     * 支付扫一扫erp接口
//     */
//    public static final String URL_ZHIFUSAOYISAOERP = BASE_URL + "zhiFuSaoYiSaoErp";
//    /**
//     * 生成商务订单号
//     **/
//    public static final String URL_shengChengShangWuDingDan = BASE_URL + "shengChengShangWuDingDan";
//    /**
//     * 生成商务订单号
//     **/
//    public static final String URL_shangWuDingDanShiFouChengGong = BASE_URL + "shangWuDingDanShiFouChengGong";
//    /**
//     * 手机找回支付密码获取验证码
//     */
//    public static final String URL_shouJiZhaoHuiZhiFuMiMaHuoQuYanZhengMa = BASE_URL + "shouJiZhaoHuiZhiFuMiMaHuoQuYanZhengMa";
//    /**
//     * 验证手机找回支付密码验证码
//     */
//    public static final String URL_YANZHENGSHOUJIZHAOHUIZHIFUMIMAYANZHENGMA = BASE_URL + "yanZhengShouJiZhaoHuiZhiFuMiMaYanZhengMa";
//    /**
//     * 手机找回支付密码2
//     */
//    public static final String URL_SHOUJIZHAOHUIZHIFUMIMATWO = BASE_URL + "shouJiZhaoHuiZhiFuMiMaTwo";
//    /**
//     * 设置支付密码
//     */
//    public static final String URL_SHEZHIZHIFUMIMA = BASE_URL + "sheZhiZhiFuMiMa";
//    /**
//     * 修改支付密码
//     */
//    public static final String URL_XIUGAIZHIFUMIMA = BASE_URL + "xiuGaiZhiFuMiMa";
//    /**
//     * 核对支付密码
//     */
//    public static final String URL_HEDUIZHIFUMIMA = BASE_URL + "heDuiZhiFuMiMa";
//    /**
//     * 申请erp转出
//     */
//    public static final String URL_SHENQINGERPZHUANCHU = BASE_URL + "shenQingErpZhuanChu";
//    /**
//     * 转入erp
//     */
//    public static final String URL_ZHUANRUERP = BASE_URL + "zhuanRuErp";
//    /**
//     * 交易记录
//     */
//    public static final String URL_JIAOYIJILU = BASE_URL + "jiaoYiJiLu";
//    /**
//     * 提现
//     **/
//    public static final String URL_TIXIAN = BASE_URL + "tiXian";
//    /**
//     * 获取提现储蓄卡
//     **/
//    public static final String URL_huoQuTiXianChuXuKa = BASE_URL + "huoQuTiXianChuXuKa";
//    /**
//     * 添加储蓄卡
//     **/
//    public static final String URL_tianJiaYinHangKa = BASE_URL + "tianJiaYinHangKa";
//    /**
//     * 删除银行卡
//     **/
//    public static final String URL_shanChuYinHangKa = BASE_URL + "shanChuYinHangKa";
//    /**
//     * 用户银行卡列表
//     **/
//    public static final String URL_yongHuYinHangKaLieBiao = BASE_URL + "yongHuYinHangKaLieBiao";
//    /**
//     * APP立即到账转账接口
//     */
//    public static final String URL_APPZHUANZHANG = BASE_URL + "appZhuanZhang";
//    /**
//     * 转账用户列表
//     */
//    public static final String URL_ZHUANZHANGYONGHULIEBIAO = BASE_URL + "zhuanZhangYongHuLieBiao";
//    /**
//     * 获取申请erp转出进度接口
//     */
//    public static final String URL_HUOQUSHENQINGERPZHUANCHUJINGDU = BASE_URL + "huoQuShenQingErpZhuanChuJingDu";
//    /**
//     * 钱包添加   end
//     */
////	public static final String URL = "http://192.168.1.110:8880/action/";
////	public static final String ImageUrl = "http://192.168.1.110:8880";
//
////	public static final String URL = "http://218.17.158.37:8700/shop_gateway/action/";
////	public static final String ImageUrl = "http://218.17.158.37:8700/shop_gateway";
//
////	public static final String URL = "http://120.27.197.151:8080/shop_gateway/action/";
////	public static final String ImageUrl = "http://120.27.197.151:8080/shop_gateway";
//
////	public static final String URL ="http://www.yorozuya.cn/";
////	public static final String URL ="http://v.kocla.com/app";//外网
//
//
//
//
//    //	public static final String URL ="http://testwd.kocla.com/app/";//内网
//
//    public static final String URL = "http://testwd.kocla.com/ios/";//内网
//
//    public static final String ImageUrl = "http://7xjew0.com2.z0.glb.qiniucdn.com/";
//
//    public static final String get_access_token = "https://api.weixin.qq.com/sns/oauth2/access_token";
//    public static final String get_userInfo = "https://api.weixin.qq.com/sns/userinfo";
//    public static final String check_access_token = "https://api.weixin.qq.com/sns/auth";
//    public static final String refresh_token = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
//
//    private static List<Cookie> cookies;
//
//    public static List<Cookie> getCookies() {
//        return cookies != null ? cookies : new ArrayList<Cookie>();
//    }
//
//    public static void setCookies(List<Cookie> cookies) {
//        HttpUtil.cookies = cookies;
//    }
//
//    private static AsyncHttpClient client;
//
//    public static AsyncHttpClient getInstance(Context paramContext) {
//
//        if (client == null) {
//            client = new AsyncHttpClient();
//            client.setTimeout(20000);
//            PersistentCookieStore myCookieStore = new PersistentCookieStore(
//                    paramContext);
//            client.setCookieStore(myCookieStore);
//        }
//        return client;
//    }
//
//    public static void post(String urlString, com.loopj.android.http.RequestParams params,
//                            AsyncHttpResponseHandler res) // url��������
//    {
//        client.post(urlString, params, res);
//    }
//
//    /**
//     * �ж��Ƿ�������
//     */
//
//    public static boolean netState(Context context) {
//        // ��ȡϵͳ������������
//        ConnectivityManager conn = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (conn == null) {
//            return false;
//        } else {
//            // ��Ϊ������wifi��gprs����������������
//            NetworkInfo[] infos = conn.getAllNetworkInfo();
//            if (infos != null) {
//                for (NetworkInfo info : infos) {
//                    // �ж������Ƿ�������
//                    if (info.getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
////------------------------------常用的网络请求方法-----------------------------------------------------------------------start--------------------------------------
//
//
//
//
////    /**
////     * Retrofit的封装
////     * 获取服务器返回的一个JSONObject对象，并处理其中信息
////     * @param context
////     * @param url
////     * @param params
////     * @param httpCallBack
////     */
////    public static void startHttp1(final Context context, final String url,
////                                  RequestParams params, final HttpCallBack httpCallBack) {
////
////        if(!CommonUtils.isNetWorkConnected(context))
////        {
////            //网络不可用
////            ToastUtil.showToast(context, "当前网络不可用");
////            httpCallBack.onFail();
////            return;
////        }
////
////        Call<String> call= RetrofitHttpUtil.getCommonRetrofitInterface().commonConnect(url,params);
////
////        call.enqueue(new Callback<String>() {
////            @Override
////            public void onResponse(Call<String> call, Response<String> response) {
////                try {
////                    String jsonStr = response.body();
////                    Log.d("onSuccess", "----------url-------" + url);
////                    Log.d("onSuccess", "-----------data------" + jsonStr);
////                    JSONObject object = new JSONObject(jsonStr);
////                    String code = object.optString("code");
////                    if (code.equals("-999")) {
////                        Intent login = new Intent(context,LoginActivity.class);
//////                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        context.startActivity(login);
////
////                        SharedPreferences mySharedPreferences =context.getSharedPreferences("UserInfo",
////                                Activity.MODE_PRIVATE);
////                        SharedPreferences.Editor editor = mySharedPreferences
////                                .edit();
////                        editor.putString("pwd", "");
////                        editor.commit();
////                        return;
////                    }
////                    httpCallBack.onOk(object);
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                    httpCallBack.onFail();
////                    return;
////                }
////            }
////
////            @Override
////            public void onFailure(Call<String> call, Throwable t) {
////                httpCallBack.onFail();
////                Log.d("HeetUtil", "------------startHttp---------failed  " + "     url==   " + url);
////                ToastUtil.showToast(context, context.getResources()
////                        .getString(R.string.Server_busy));
////            }
////        });
////
////    }
////
////    /**
////     * Retrofit+RxJava 的封装
////     * @param context
////     * @param url
////     * @param params
////     * @param httpCallBack
////     */
////    public static void startHttp2(final Context context, final String url,
////                                  RequestParams params, final HttpCallBack httpCallBack) {
////
////        if(!CommonUtils.isNetWorkConnected(context))
////        {
////            //网络不可用
////            ToastUtil.showToast(context, "当前网络不可用");
////            httpCallBack.onFail();
////            return;
////        }
////
////        Observable<String> observable= RetrofitHttpUtil.getCommonRetrofitInterface().rxCommonConnect(url,params);
////        observable.subscribeOn(Schedulers.newThread())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Subscriber<String>() {
////                    @Override
////                    public void onCompleted() {
////                        this.unsubscribe();
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        httpCallBack.onFail();
////                        Log.d("HeetUtil", "------------startHttp---------failed  " + "     url==   " + url);
////                        ToastUtil.showToast(context, context.getResources()
////                                .getString(R.string.Server_busy));
////                        this.unsubscribe();
////                    }
////
////                    @Override
////                    public void onNext(String s) {
////                        try {
////                            Log.d("onSuccess", "----------url-------" + url);
////                            Log.d("onSuccess", "-----------data------" + s);
////                            JSONObject object = new JSONObject(s);
////                            String code = object.optString("code");
////                            if (code.equals("-999")) {
////                                Intent login = new Intent(context,
////                                        LoginActivity.class);
//////                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                context.startActivity(login);
////
////                                SharedPreferences mySharedPreferences =context.getSharedPreferences("UserInfo",
////                                        Activity.MODE_PRIVATE);
////                                SharedPreferences.Editor editor = mySharedPreferences
////                                        .edit();
////                                editor.putString("pwd", "");
////                                editor.commit();
////
////                                return;
////                            }
////                            httpCallBack.onOk(object);
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                            httpCallBack.onFail();
////                            return;
////                        }
////                    }
////                });
////
////    }
//
//
//
//
//
//
//
//
//
//    /**
//     * 获取服务器返回的一个JSONObject对象，并处理其中信息
//     * @param context
//     * @param url
//     * @param params
//     * @param httpCallBack
//     */
//    public static void startHttp(final Context context, final String url,
//                                 final com.loopj.android.http.RequestParams params, final HttpCallBack httpCallBack) {
//
//        if(!CommonUtils.isNetWorkConnected(context))
//        {
//            //网络不可用
//            ToastUtil.showToast(context, "当前网络不可用");
//            httpCallBack.onFail();
//            return;
//        }
//
//        MyApplication.getInstance().asyncHttpClient.post(url, params,
//                new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers,
//                                          byte[] responseBody) {
//                        try {
//                            String jsonStr = new String(responseBody);
//                            Log.d("onSuccess", "----------url-------" + url+"?"+params);
//                            Log.d("onSuccess", "-----------data------" + jsonStr);
//                            JSONObject object = new JSONObject(jsonStr);
//                            String code = object.optString("code");
//                            if (code.equals("-999")) {
//                                Intent login = new Intent(context,
//                                        LoginActivity.class);
////                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                context.startActivity(login);
//                                //这里设计成每次要重新登录时，都先清除APPmanager中所有的activity实例----------------------------------------------------------------------
//                                AppManager.getAppManager().finishAllActivity();
//                                SharedPreferences mySharedPreferences =context.getSharedPreferences("UserInfo",
//                                        Activity.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = mySharedPreferences
//                                        .edit();
//                                editor.putString("pwd", "");
//                                editor.commit();
//                                return;
//                            }else if(code.equals("0")){
//                                httpCallBack.onOk(object);
//                            }else if(code.equals("1")){
//                                httpCallBack.onOk(object);
//                            }else if(Integer.valueOf(code)<0){
//                                ToastUtil.showToast(context, object.optString("msg"));
//                                httpCallBack.onFail();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            httpCallBack.onFail();
//                            return;
//                        }
//                    }
//
//                    @Override
//                    public Object getTag() {
//                        return url;
//                    }
//
//                    @Override
//                    public void onProgress(long bytesWritten, long totalSize)
//                    {
//                        super.onProgress(bytesWritten, totalSize);
//                    }
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers,
//                                          byte[] responseBody, Throwable error) {
//                        Log.d("HeetUtil", "------------startHttp---------statusCode  " + statusCode + "     url==   " + url+"?"+params);
//                        httpCallBack.onFail();
//                        ToastUtil.showToast(context, context.getResources()
//                                .getString(R.string.Server_busy));
//
//                    }
//                });
////        requestHandle.setTag(ClassesFragment.cancelTag);
//    }
//    /**
//     * 自己封装的网络请求工具（带有List的接口）---仅有一项
//     *
//     * @param context
//     * @param url
//     * @param params
//     * @param httpListCallBackItem
//     */
//    public static void startHttpList(final Context context, String url,
//                                     RequestParams params,
//                                     final HttpListCallBackItem httpListCallBackItem) {
//        startHttpList(context, url, params, new HttpListCallBack() {
//
//            @Override
//            public void onOk(JSONArray arr) {
//                if (httpListCallBackItem != null) {
//                    httpListCallBackItem.onOk(arr.optJSONObject(0));
//                }
//            }
//
//            @Override
//            public void onFail() {
//                if (httpListCallBackItem != null) {
//                    httpListCallBackItem.onFail();
//                }
//            }
//        });
//    }
//    /**
//     * 获取服务器返回的一个JSONArray对象，并处理其中信息
//     * @param context
//     * @param url
//     * @param params
//     */
//    public static void startHttpList(final Context context, final String url,
//                                     final RequestParams params, final HttpListCallBack httpListCallBack) {
//
//        if(!CommonUtils.isNetWorkConnected(context))
//        {
//            //网络不可用
//            ToastUtil.showToast(context, "当前网络不可用");
//            httpListCallBack.onFail();
//            return;
//        }
//
//        MyApplication.getInstance().asyncHttpClient.post(url, params,
//                new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers,
//                                          byte[] responseBody) {
//                        Log.d("onSuccess", "-----------------" + url+"?"+params);
//                        String jsonStr = new String(responseBody);
//                        Log.d("onSuccess", "-----------------" + jsonStr);
//                        JSONObject obj = null;
//                        try {
//                            obj = new JSONObject(jsonStr);
//                        } catch (JSONException e) {
//                            httpListCallBack.onFail();
//                            return;
//                        }
//                        String code = obj.optString("code");
//                        if (code.equals("-999")) {
//                            Intent login = new Intent(context,
//                                    LoginActivity.class);
//                            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            context.startActivity(login);
//                            AppManager.getAppManager().finishAllActivity();
//                            SharedPreferences mySharedPreferences =context.getSharedPreferences("UserInfo",
//                                    Activity.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = mySharedPreferences
//                                    .edit();
//                            editor.putString("pwd", "");
//                            editor.commit();
//                            return;
//                        }
//                        if (TextUtils.equals(code, "0")) {
//                            JSONArray array = null;
//                            try {
//                                array = new JSONArray(obj.optString("data"));
//                                httpListCallBack.onOk(array);
//                            } catch (JSONException e) {
//                                httpListCallBack.onFail();
//                                return;
//                            }
//                        } else if(code.equals("1")){
//                            JSONArray array = null;
//                            try {
//                                array = new JSONArray(obj.optString("data"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            httpListCallBack.onOk(array);
//
//                        }else if(Integer.valueOf(code)<0){
//                            ToastUtil.showToast(context, obj.optString("msg"));
//                            httpListCallBack.onFail();
//                        }else {
//                            Log.d("httpUtil","---------------code==   "+code);
//                            String message = obj.optString("msg");
//                            ToastUtil.showToast(context, message);
//                            httpListCallBack.onFail();
//                        }
//                    }
//
//                    @Override
//                    public void onProgress(long bytesWritten, long totalSize)
//                    {
//                        super.onProgress(bytesWritten, totalSize);
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers,
//                                          byte[] responseBody, Throwable error) {
//                        Log.d("onFailure", "-------------statusCode  " + statusCode + "     url==   " + url+"?"+params);
//                        httpListCallBack.onFail();
//                        ToastUtil.showToast(context, context.getResources().getString(R.string.Server_busy));
////						ToolLinlUtils.showToast(context, context.getResources()
////								.getString(R.string.Server_busy));
//                    }
//                });
//    }
//
////------------------------------------------------------------------------------------------------------------------------------end----------------------
//
//    /**
//     * @param context
//     * @param url
//     * @param params
//     * @param httpCallBackCode
//     */
//    public static void startHttp(final Context context, String url,
//                                 com.loopj.android.http.RequestParams params, final HttpCallBackCode httpCallBackCode) {
//        startHttp(context, url, params, new HttpCallBack() {
//
//            @Override
//            public void onOk(JSONObject obj) {
//                if (httpCallBackCode != null) {
//                    httpCallBackCode.onOk(Integer.parseInt(obj
//                            .optString("code")));
//                }
//                ToastUtil.showToast(context, obj.optString("msg"));
////				ToolLinlUtils.showToast(context, obj.optString("msg"));
//            }
//
//            @Override
//            public void onFail() {
//                if (httpCallBackCode != null) {
//                    httpCallBackCode.onOk(-1);
//                }
//            }
//        });
//    }
//
//
//
//
////    /**
////     * 自己封装的网络请求工具（带有List的接口）
////     *
////     * @param context
////     * @param url
////     * @param params
////     * @param httpMyCallBack
////     */
////    public static void startHttpObject(final Context context, String url,
////                                       RequestParams params, final HttpMyCallBack httpMyCallBack) {
////        MyApplication.getInstance().asyncHttpClient.post(url, params,
////                new AsyncHttpResponseHandler() {
////                    @Override
////                    public void onSuccess(int statusCode, Header[] headers,
////                                          byte[] responseBody) {
////                        String jsonStr = new String(responseBody);
////                        JSONObject obj = null;
////                        try {
////                            obj = new JSONObject(jsonStr);
////                        } catch (JSONException e) {
////                            httpMyCallBack.onFail();// 如果数据请求失败或者Json解析失败的时候也调用失败回调接口
////                            return;
////                        }
////                        String code = obj.optString("code");
////                        if (TextUtils.equals(code, "0")) {
////                            String data = obj.optString("data");
////                            httpMyCallBack.onOk(data);
////
////                        } else {
////                            String message = obj.optString("msg");
////                            ToastUtil.MyToast(context, message);
////                            httpMyCallBack.onFail();// 如果数据请求失败或者Json解析失败的时候也调用失败回调接口
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(int statusCode, Header[] headers,
////                                          byte[] responseBody, Throwable error) {
////                        httpMyCallBack.onFail();// 如果数据请求失败或者Json解析失败的时候也调用失败回调接口
////                        ToastUtil.MyToast(context, context.getResources()
////                                .getString(R.string.Server_busy));
////                        DialogUtil.dismissDialog();
////                    }
////                });
////    }
//
//
//
//
//
//    /**
//     * 网络请求回调接口
//     */
//    public interface HttpCallBack {
//        void onOk(JSONObject obj);// 请求成功
//
//        void onFail();// 请求失败
//    }
//
//    /**
//     * 网络请求回调接口(只有code和message字段的)
//     */
//    public interface HttpCallBackCode {
//        void onOk(int code);// 请求成功
//    }
//
//    /**
//     * 网络请求回调接口（带有列表的接口）
//     */
//    public interface HttpListCallBack {
//        void onOk(JSONArray arr);// 请求成功
//
//        void onFail();// 请求失败
//    }
//
//    /**
//     * 网络请求回调接口（详情页面）
//     */
//    public interface HttpMyCallBack {
//        void onOk(String data);// 请求成功
//
//        void onFail();// 请求失败
//    }
//
//    /**
//     * 网络请求回调接口（带有列表的接口）---仅有一项
//     */
//    public interface HttpListCallBackItem {
//        void onOk(JSONObject json);// 请求成功
//
//        void onFail();// 请求失败
//    }
//}
