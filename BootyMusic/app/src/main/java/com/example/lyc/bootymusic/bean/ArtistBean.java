package com.example.lyc.bootymusic.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：abc on 2017/1/13 09:27
 * 邮箱：liyuchong@kocla.com
 */

public class ArtistBean implements Serializable{

        /**
         * id : 6452
         * name : 周杰伦
         * picUrl : null
         * alias : []
         * albumSize : 0
         * picId : 0
         * img1v1Url : http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
         * img1v1 : 18686200114669622
         * trans : null
         */

        private int id;
        private String name;
        private Object picUrl;
        private int albumSize;
        private int picId;
        private String img1v1Url;
        private long img1v1;
        private Object trans;
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

        public Object getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(Object picUrl) {
            this.picUrl = picUrl;
        }

        public int getAlbumSize() {
            return albumSize;
        }

        public void setAlbumSize(int albumSize) {
            this.albumSize = albumSize;
        }

        public int getPicId() {
            return picId;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }

        public String getImg1v1Url() {
            return img1v1Url;
        }

        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }

        public long getImg1v1() {
            return img1v1;
        }

        public void setImg1v1(long img1v1) {
            this.img1v1 = img1v1;
        }

        public Object getTrans() {
            return trans;
        }

        public void setTrans(Object trans) {
            this.trans = trans;
        }

        public List<?> getAlias() {
            return alias;
        }

        public void setAlias(List<?> alias) {
            this.alias = alias;
        }

    @Override
    public String toString() {
        return "ArtistBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picUrl=" + picUrl +
                ", albumSize=" + albumSize +
                ", picId=" + picId +
                ", img1v1Url='" + img1v1Url + '\'' +
                ", img1v1=" + img1v1 +
                ", trans=" + trans +
                ", alias=" + alias +
                '}';
    }
}
