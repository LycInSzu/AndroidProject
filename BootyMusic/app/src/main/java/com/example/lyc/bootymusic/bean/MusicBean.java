package com.example.lyc.bootymusic.bean;

import java.io.Serializable;

/**
 * Created by LYC on 2017/1/8.
 */

public class MusicBean implements Serializable{
    private String id="";//音乐的id
    private String title="";//歌曲名称
//    private String artist="";//歌手
    private ArtistBean artist;//歌手
    private String duration="";//时长对应的字符串（分:秒）
    private long duration_millinseconds;//时长，毫秒数
    private long size;//大小
    private String path="";//路径
    private boolean isMusic;//是否为音乐
    private String image="";//音乐专辑封面

    public long getDuration_millinseconds() {
        return duration_millinseconds;
    }

    public void setDuration_millinseconds(long duration_millinseconds) {
        this.duration_millinseconds = duration_millinseconds;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getArtist() {
//        return artist;
//    }
//
//    public void setArtist(String artist) {
//        this.artist = artist;
//    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isMusic() {
        return isMusic;
    }

    public void setMusic(boolean music) {
        isMusic = music;
    }


    @Override
    public String toString() {
        return "MusicBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", duration='" + duration + '\'' +
                ", duration_millinseconds=" + duration_millinseconds +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", isMusic=" + isMusic +
                ", image='" + image + '\'' +
                '}';
    }

    public ArtistBean getArtist() {
        return artist;
    }

    public void setArtist(ArtistBean artist) {
        this.artist = artist;
    }
}
