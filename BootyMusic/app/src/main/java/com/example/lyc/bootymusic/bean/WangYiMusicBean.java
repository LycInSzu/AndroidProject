package com.example.lyc.bootymusic.bean;

import java.util.List;

/**
 * Created by LYC on 2017/1/8.
 *
 * 网易音乐 —— 单曲
 */

public class WangYiMusicBean {


    /**
     * id : 418603077
     * name : 告白气球
     * artists : [{"id":6452,"name":"周杰伦","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","img1v1":18686200114669622,"trans":null}]
     * album : {"id":34720827,"name":"周杰伦的床边故事","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","img1v1":18686200114669622,"trans":null},"publishTime":1466697600007,"size":10,"copyrightId":1007,"status":3,"alias":["Jay Chou's Bedtime Stories"],"picId":3265549553028224}
     * duration : 215146
     * copyrightId : 1007
     * status : 8
     * alias : []
     * rtype : 0
     * ftype : 0
     * mvid : 5382080
     * fee : 8
     * rUrl : null
     */

    private int id;
    private String name;
    private AlbumBean album;
    private int duration;
    private int copyrightId;
    private int status;
    private int rtype;
    private int ftype;
    private int mvid;
    private int fee;
    private Object rUrl;
    private List<ArtistBean> artists;
    private List<?> alias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlbumBean getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBean album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(int copyrightId) {
        this.copyrightId = copyrightId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public int getMvid() {
        return mvid;
    }

    public void setMvid(int mvid) {
        this.mvid = mvid;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Object getRUrl() {
        return rUrl;
    }

    public void setRUrl(Object rUrl) {
        this.rUrl = rUrl;
    }

    public List<ArtistBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistBean> artists) {
        this.artists = artists;
    }

    public List<?> getAlias() {
        return alias;
    }

    public void setAlias(List<?> alias) {
        this.alias = alias;
    }

}
