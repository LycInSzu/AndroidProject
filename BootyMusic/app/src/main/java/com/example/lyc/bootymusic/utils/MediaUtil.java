package com.example.lyc.bootymusic.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.lyc.bootymusic.bean.ArtistBean;
import com.example.lyc.bootymusic.bean.MusicBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by LYC on 2017/1/8.
 */

public class MediaUtil {
    /**
     * 用于从数据库中查询歌曲的信息，保存在List当中
     *
     * @return
     */
    public static ArrayList<MusicBean> getMusicBeans(Context context) {
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        ArrayList<MusicBean> mMusicBeans = new ArrayList<MusicBean>();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            MusicBean bean = new MusicBean();
            String id = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));               //音乐id
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));            //音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));            //艺术家
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));          //时长
            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));              //文件大小
            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));              //文件路径
            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));          //是否为音乐
            if (isMusic != 0) {     //只把音乐添加到集合当中
                bean.setId(id);
                bean.setTitle(title);
                ArtistBean artistBean=new ArtistBean();
                artistBean.setName(artist);
                bean.setArtist(artistBean);
                bean.setDuration(formatTime(duration));
                bean.setDuration_millinseconds(duration);
                bean.setSize(size);
                bean.setPath(url);
                mMusicBeans.add(bean);
            }
        }
        return mMusicBeans;
    }

    /**
     * 往List集合中添加Map对象数据，每一个Map对象存放一首音乐的所有属性
     * @param MusicBeans
     * @return
     */
    public static ArrayList<HashMap<String, String>> getMusicMaps(
            ArrayList<MusicBean> MusicBeans) {
        ArrayList<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();
        for (Iterator iterator = MusicBeans.iterator(); iterator.hasNext();) {
            MusicBean bean = (MusicBean) iterator.next();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", bean.getTitle());
            map.put("Artist", bean.getArtist().getName());
            map.put("duration",bean.getDuration());
            map.put("size", String.valueOf(bean.getSize()));
            map.put("url", bean.getPath());
            mp3list.add(map);
        }
        return mp3list;
    }

    /**
     * 格式化时间，将毫秒转换为分:秒格式
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + time / (1000 * 60) + "";
        } else {
            min = time / (1000 * 60) + "";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
}
