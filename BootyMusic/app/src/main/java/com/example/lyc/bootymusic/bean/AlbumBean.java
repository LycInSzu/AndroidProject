package com.example.lyc.bootymusic.bean;

import java.util.List;

/**
 * 作者：abc on 2017/1/13 09:52
 * 邮箱：liyuchong@kocla.com
 */

public class AlbumBean {

    /**
     * songs : []
     * paid : false
     * onSale : false
     * status : 1
     * blurPicUrl : http://p4.music.126.net/ldsnDEBNfrdTYa2mbRogjQ==/17887954672477219.jpg
     * companyId : 0
     * pic : 17887954672477219
     * picUrl : http://p4.music.126.net/ldsnDEBNfrdTYa2mbRogjQ==/17887954672477219.jpg
     * commentThreadId : R_AL_3_34794563
     * publishTime : 1469675253793
     * company : null
     * tags :
     * copyrightId : 0
     * picId : 17887954672477219
     * artists : [{"img1v1Id":18686200114669622,"topicPerson":0,"trans":"","picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","musicSize":0,"picId":0,"briefDesc":"","alias":[],"name":"刘飞语","id":12064130,"img1v1Id_str":"18686200114669622"}]
     * description :
     * subType : Demo及其他
     * briefDesc :
     * alias : []
     * artist : {"img1v1Id":18686200114669622,"topicPerson":0,"trans":"","picUrl":"http://p3.music.126.net/2MUBepwcwFJM6VOD9eprkA==/18823639067651695.jpg","albumSize":3,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","musicSize":15,"picId":18823639067651695,"briefDesc":"","alias":[],"name":"刘飞语","id":12064130,"picId_str":"18823639067651695","img1v1Id_str":"18686200114669622"}
     * name : Jay
     * id : 34794563
     * type : 单曲&EP
     * size : 1
     * picId_str : 17887954672477219
     */

    private boolean paid;
    private boolean onSale;
    private int status;
    private String blurPicUrl;
    private int companyId;
    private long pic;
    private String picUrl;
    private String commentThreadId;
    private long publishTime;
    private Object company;
    private String tags;
    private int copyrightId;
    private long picId;
    private String description;
    private String subType;
    private String briefDesc;
    private ArtistBean artist;
    private String name;
    private int id;
    private String type;
    private int size;
    private String picId_str;
    private List<?> songs;
    private List<ArtistsBean> artists;
    private List<?> alias;

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBlurPicUrl() {
        return blurPicUrl;
    }

    public void setBlurPicUrl(String blurPicUrl) {
        this.blurPicUrl = blurPicUrl;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public long getPic() {
        return pic;
    }

    public void setPic(long pic) {
        this.pic = pic;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(int copyrightId) {
        this.copyrightId = copyrightId;
    }

    public long getPicId() {
        return picId;
    }

    public void setPicId(long picId) {
        this.picId = picId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public ArtistBean getArtist() {
        return artist;
    }

    public void setArtist(ArtistBean artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPicId_str() {
        return picId_str;
    }

    public void setPicId_str(String picId_str) {
        this.picId_str = picId_str;
    }

    public List<?> getSongs() {
        return songs;
    }

    public void setSongs(List<?> songs) {
        this.songs = songs;
    }

    public List<ArtistsBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistsBean> artists) {
        this.artists = artists;
    }

    public List<?> getAlias() {
        return alias;
    }

    public void setAlias(List<?> alias) {
        this.alias = alias;
    }

    public static class ArtistBean {
        /**
         * img1v1Id : 18686200114669622
         * topicPerson : 0
         * trans :
         * picUrl : http://p3.music.126.net/2MUBepwcwFJM6VOD9eprkA==/18823639067651695.jpg
         * albumSize : 3
         * img1v1Url : http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
         * musicSize : 15
         * picId : 18823639067651695
         * briefDesc :
         * alias : []
         * name : 刘飞语
         * id : 12064130
         * picId_str : 18823639067651695
         * img1v1Id_str : 18686200114669622
         */

        private long img1v1Id;
        private int topicPerson;
        private String trans;
        private String picUrl;
        private int albumSize;
        private String img1v1Url;
        private int musicSize;
        private long picId;
        private String briefDesc;
        private String name;
        private int id;
        private String picId_str;
        private String img1v1Id_str;
        private List<?> alias;

        public long getImg1v1Id() {
            return img1v1Id;
        }

        public void setImg1v1Id(long img1v1Id) {
            this.img1v1Id = img1v1Id;
        }

        public int getTopicPerson() {
            return topicPerson;
        }

        public void setTopicPerson(int topicPerson) {
            this.topicPerson = topicPerson;
        }

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getAlbumSize() {
            return albumSize;
        }

        public void setAlbumSize(int albumSize) {
            this.albumSize = albumSize;
        }

        public String getImg1v1Url() {
            return img1v1Url;
        }

        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }

        public int getMusicSize() {
            return musicSize;
        }

        public void setMusicSize(int musicSize) {
            this.musicSize = musicSize;
        }

        public long getPicId() {
            return picId;
        }

        public void setPicId(long picId) {
            this.picId = picId;
        }

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicId_str() {
            return picId_str;
        }

        public void setPicId_str(String picId_str) {
            this.picId_str = picId_str;
        }

        public String getImg1v1Id_str() {
            return img1v1Id_str;
        }

        public void setImg1v1Id_str(String img1v1Id_str) {
            this.img1v1Id_str = img1v1Id_str;
        }

        public List<?> getAlias() {
            return alias;
        }

        public void setAlias(List<?> alias) {
            this.alias = alias;
        }
    }

    public static class ArtistsBean {
        /**
         * img1v1Id : 18686200114669622
         * topicPerson : 0
         * trans :
         * picUrl : http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
         * albumSize : 0
         * img1v1Url : http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
         * musicSize : 0
         * picId : 0
         * briefDesc :
         * alias : []
         * name : 刘飞语
         * id : 12064130
         * img1v1Id_str : 18686200114669622
         */

        private long img1v1Id;
        private int topicPerson;
        private String trans;
        private String picUrl;
        private int albumSize;
        private String img1v1Url;
        private int musicSize;
        private int picId;
        private String briefDesc;
        private String name;
        private int id;
        private String img1v1Id_str;
        private List<?> alias;

        public long getImg1v1Id() {
            return img1v1Id;
        }

        public void setImg1v1Id(long img1v1Id) {
            this.img1v1Id = img1v1Id;
        }

        public int getTopicPerson() {
            return topicPerson;
        }

        public void setTopicPerson(int topicPerson) {
            this.topicPerson = topicPerson;
        }

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getAlbumSize() {
            return albumSize;
        }

        public void setAlbumSize(int albumSize) {
            this.albumSize = albumSize;
        }

        public String getImg1v1Url() {
            return img1v1Url;
        }

        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }

        public int getMusicSize() {
            return musicSize;
        }

        public void setMusicSize(int musicSize) {
            this.musicSize = musicSize;
        }

        public int getPicId() {
            return picId;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg1v1Id_str() {
            return img1v1Id_str;
        }

        public void setImg1v1Id_str(String img1v1Id_str) {
            this.img1v1Id_str = img1v1Id_str;
        }

        public List<?> getAlias() {
            return alias;
        }

        public void setAlias(List<?> alias) {
            this.alias = alias;
        }
    }
}
