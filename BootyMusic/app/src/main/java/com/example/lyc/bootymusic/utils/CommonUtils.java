/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lyc.bootymusic.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//import com.easemob.chat.EMMessage;
//import com.easemob.chat.TextMessageBody;
//import com.easemob.util.EMLog;
//import com.koala.broker.Constant.Constant;
//import com.koala.broker.MyApplication;
//import com.koala.broker.activity.LoginActivity;
//import com.koala.broker.communication_module.activity.ChatActivity;
//import com.koala.broker.bean.CommentPraiseVo;
//import com.koala.broker.ui.dialog.ToLoginDialog;
//import com.koala.broker.ui.dialog.ZiXunDialog;
//import com.koala.shop.mobile.broker.R;
//import com.koala.wallet.widget.ICallBack;

public class CommonUtils {
    private static final String TAG = "CommonUtils";
//    private static ZiXunDialog ziXunDialog;

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
    /**
     * 分割字符串得到List<String>
     *
     * @param string
     * @return
     */

    public static ArrayList<String> getStringList(String string) {
        ArrayList<String> list = new ArrayList<>();
        String[] str = string.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;
    }
//
//    /**
//     * 根据消息内容和消息类型获取消息内容提示
//     *
//     * @param message
//     * @param context
//     * @return
//     */
//    public static String getMessageDigest(EMMessage message, Context context) {
//        String digest = "";
//        switch (message.getType()) {
//            case LOCATION: // 位置消息
//                if (message.direct == EMMessage.Direct.RECEIVE) {
//                    //从sdk中提到了ui中，使用更简单不犯错的获取string方法
////              digest = EasyUtils.getAppResourceString(context, "location_recv");
//                    digest = getString(context, R.string.location_recv);
//                    digest = String.format(digest, message.getFrom());
//                    return digest;
//                } else {
////              digest = EasyUtils.getAppResourceString(context, "location_prefix");
//                    digest = getString(context, R.string.location_prefix);
//                }
//                break;
//            case IMAGE: // 图片消息
//                digest = getString(context, R.string.picture);
//                break;
//            case VOICE:// 语音消息
//                digest = getString(context, R.string.voice);
//                break;
//            case VIDEO: // 视频消息
//                digest = getString(context, R.string.video);
//                break;
//            case TXT: // 文本消息
//                if (!message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
//                    TextMessageBody txtBody = (TextMessageBody) message.getBody();
//                    digest = txtBody.getMessage();
//                } else {
//                    TextMessageBody txtBody = (TextMessageBody) message.getBody();
//                    digest = getString(context, R.string.voice_call) + txtBody.getMessage();
//                }
//                break;
//            case FILE: //普通文件消息
//                digest = getString(context, R.string.file);
//                break;
//            default:
//                EMLog.e(TAG, "error, unknow type");
//                return "";
//        }
//
//        return digest;
//    }

    static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }


    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null)
            return runningTaskInfos.get(0).topActivity.getClassName();
        else
            return "";
    }

    /**
     * listView高度自适应 add by zj
     *
     * @param listView
     * @param maxShowItemNum listView最大不超过这个数量的item高度,少于这个数量,则有多少显示多少
     */
    public static void setListViewHeightBasedOnChildren(ListView listView, int maxShowItemNum) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int showNum = listAdapter.getCount() > maxShowItemNum ? maxShowItemNum : listAdapter.getCount();
        for (int i = 0; i < showNum; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            // listItem.measure(listItem.getParent(), heightMeasureSpec);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * showNum);
        listView.setLayoutParams(params);
    }

    /**
     * 计算listView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    /**
     * 设置横向滑动gridView
     *
     * @param gv
     * @param length            每个item里面控件的宽度
     * @param num               相当于padding
     * @param horizontalSpacing 每个item的间隙
     */
    public static void setGridView(Activity activity, GridView gv, List list, int length, int num, int horizontalSpacing) {
        int size = list.size();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + num) * density);
        int itemWidth = (int) (length * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth,
                LinearLayout.LayoutParams.FILL_PARENT);
        gv.setLayoutParams(params); // 重点
        gv.setColumnWidth(itemWidth); // 重点
        gv.setStretchMode(GridView.NO_STRETCH);
        gv.setGravity(Gravity.CENTER);
        gv.setNumColumns(size); // 重点
        gv.setHorizontalSpacing(horizontalSpacing);
        gv.requestFocus();
    }


    /**
     * scrollView中嵌套gridView解决高度问题
     *
     * @param gridView
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridView, int verticalSpacing) {
        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int rows;
        int columns = 0;
        int horizontalBorderHeight = 0;
        Class<?> clazz = gridView.getClass();
        try {
            //利用反射，取得每行显示的个数
            Field column = clazz.getDeclaredField("mRequestedNumColumns");
            column.setAccessible(true);
            columns = (Integer) column.get(gridView);
            //利用反射，取得横向分割线高度
            Field horizontalSpacing = clazz.getDeclaredField("mRequestedHorizontalSpacing");
            horizontalSpacing.setAccessible(true);
            horizontalBorderHeight = (Integer) horizontalSpacing.get(gridView);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        //判断数据总数除以每行个数是否整除。不能整除代表有多余，需要加一行
        if (listAdapter.getCount() % columns > 0) {
            rows = listAdapter.getCount() / columns + 1;
        } else {
            rows = listAdapter.getCount() / columns;
        }
        int totalHeight = 0;
        for (int i = 0; i < rows; i++) { //只计算每项高度*行数
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = (totalHeight + horizontalBorderHeight + verticalSpacing) * (rows - 1);//最后加上分割线总高度
        gridView.setLayoutParams(params);
    }

//
//    /**
//     * 获得点赞名字
//     */
//    public static String getPariseName(List<CommentPraiseVo> list) {
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < list.size(); i++) {
//            if (i != list.size() - 1) {
//                stringBuffer.append(list.get(i).getPariseUserName() + "，");
//            } else {
//                stringBuffer.append(list.get(i).getPariseUserName());
//            }
//        }
//        return stringBuffer.toString();
//    }
//
//    /**
//     * 咨询对话框
//     * @param context
//     * @param iCallBack
//     * @param phone
//     */
//    public static void showZiXuDialog(Activity context, ICallBack iCallBack , String phone){
//        ziXunDialog = new ZiXunDialog(context, R.style.auth_dialog, iCallBack, phone);
//        Window ziWindow = ziXunDialog.getWindow();
//        ziWindow.setGravity(Gravity.BOTTOM);
//        ziWindow.setWindowAnimations(R.style.mystyle);
//        ziXunDialog.show();
//        WindowManager windowManager = context.getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = ziXunDialog.getWindow().getAttributes();
//        lp.width = (int) (display.getWidth()); // 设置宽度
//        ziXunDialog.getWindow().setAttributes(lp);
//    }
//
//    public static void dismissZiXunDialog(){
//        if (ziXunDialog!=null&&ziXunDialog.isShowing()){
//            ziXunDialog.dismiss();
//        }
//    }
//
//    /**
//     * 咨询调用打电话
//     * @param context
//     * @param phone
//     */
//    public static void takePhoneCall(final Context context , String phone) {
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        if (MyApplication.getInstance().getLoginUser() != null) {
//            Uri data = Uri.parse("tel:" + phone);
//            intent.setData(data);
//            context.startActivity(intent);
//        } else {
//            ToLoginDialog.OnToLoginClickListener lis = new ToLoginDialog.OnToLoginClickListener() {
//                @Override
//                public void getText(String type,
//                                    int param) {
//                    context.startActivity(new Intent(context, LoginActivity.class));
//                }
//            };
//            Dialog dialog1 = new ToLoginDialog(
//                    context, lis,
//                    R.style.auth_dialog);
//            dialog1.setCancelable(false);
//            dialog1.show();
//        }
//    }
//
//    /**
//     * 咨询跳转到聊天
//     */
//    public static void toChat(final Context context, String id, String name, String avatar){
//        if (MyApplication.getInstance().getLoginUser() != null) {
//            Intent intent = new Intent(context, ChatActivity.class);
//            intent.putExtra("userId", id);
//            intent.putExtra("userName", name);
//            intent.putExtra("avatar", avatar);
//            context.startActivity(intent);
//        } else {
//            ToLoginDialog.OnToLoginClickListener lis = new ToLoginDialog.OnToLoginClickListener() {
//                @Override
//                public void getText(String type,
//                                    int param) {
//                    context.startActivity(new Intent(context, LoginActivity.class));
//                }
//            };
//            Dialog dialog1 = new ToLoginDialog(
//                    context, lis,
//                    R.style.auth_dialog);
//            dialog1.setCancelable(false);
//            dialog1.show();
//        }
//    }
//
//    /**
//     * 分割字符串得到图片url
//     * @param image
//     * @return
//     */
//
//    public static ArrayList<String> getImageList(String image){
//        ArrayList<String> list = new ArrayList<>();
//        String[] str = image.split(",");
//        for (int i = 0;i<str.length;i++){
//            list.add(HttpUtil.ImageUrl+str[i]);
//        }
//        return list;
//    }

}
