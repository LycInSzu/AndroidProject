package com.example.lyc.bootymusic.bean;

import java.util.List;

/**
 * 作者：abc on 2017/1/13 12:57
 * 邮箱：liyuchong@kocla.com
 */

public class MusicBeanOfWangYiAlbum {

    /**
     * starred : false
     * popularity : 100.0
     * starredNum : 0
     * playedNum : 0
     * dayPlays : 0
     * hearTime : 0
     * mp3Url : http://m2.music.126.net/jBouFSO2enlPxtpH_0n3Wg==/7936274930834740.mp3
     * rtUrls : null
     * status : 0
     * alias : []
     * bMusic : {"dfsId":7936274930834740,"playTime":239027,"volumeDelta":-2.65076E-4,"bitrate":96000,"sr":44100,"name":null,"id":99279176,"size":2869288,"extension":"mp3"}
     * crbt : null
     * rtUrl : null
     * audition : null
     * ringtone : 600902000006889504
     * disc :
     * no : 1
     * album : {"songs":[],"paid":false,"onSale":false,"tags":"","status":1,"alias":[],"publishTime":973526400000,"company":"阿尔发音乐","blurPicUrl":"http://p3.music.126.net/Gd-HAk9hKC85L0wNtfRs1g==/7946170535396804.jpg","companyId":0,"pic":7946170535396804,"artist":{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","musicSize":0,"name":"","id":0,"img1v1Id_str":"18686200114669622"},"picUrl":"http://p3.music.126.net/Gd-HAk9hKC85L0wNtfRs1g==/7946170535396804.jpg","commentThreadId":"R_AL_3_18918","copyrightId":1007,"picId":7946170535396804,"briefDesc":"","artists":[{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","musicSize":0,"name":"周杰伦","id":6452,"img1v1Id_str":"18686200114669622"}],"description":"","subType":"录音室版","name":"Jay 同名专辑","id":18918,"type":"专辑","size":10}
     * commentThreadId : R_SO_4_186145
     * score : 100
     * copyrightId : 1007
     * hMusic : {"dfsId":7998947093616069,"playTime":239027,"volumeDelta":-2.65076E-4,"bitrate":320000,"sr":44100,"name":null,"id":99279174,"size":9564053,"extension":"mp3"}
     * mMusic : {"dfsId":3357908511299974,"playTime":239027,"volumeDelta":-2.65076E-4,"bitrate":160000,"sr":44100,"name":null,"id":99279175,"size":4782078,"extension":"mp3"}
     * lMusic : {"dfsId":7936274930834740,"playTime":239027,"volumeDelta":-2.65076E-4,"bitrate":96000,"sr":44100,"name":null,"id":99279176,"size":2869288,"extension":"mp3"}
     * artists : [{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"picUrl":"http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","musicSize":0,"name":"周杰伦","id":6452,"img1v1Id_str":"18686200114669622"}]
     * mvid : 504991
     * ftype : 0
     * rtype : 0
     * rurl : null
     * copyFrom :
     * position : 1
     * duration : 239027
     * fee : 8
     * name : 可爱女人
     * id : 186145
     */

    private boolean starred;
    private double popularity;
    private int starredNum;
    private int playedNum;
    private int dayPlays;
    private int hearTime;
    private String mp3Url;
    private Object rtUrls;
    private int status;
    private BMusicBean bMusic;
    private Object crbt;
    private Object rtUrl;
    private Object audition;
    private String ringtone;
    private String disc;
    private int no;
    private AlbumBean album;
    private String commentThreadId;
    private int score;
    private int copyrightId;
    private HMusicBean hMusic;
    private MMusicBean mMusic;
    private LMusicBean lMusic;
    private int mvid;
    private int ftype;
    private int rtype;
    private Object rurl;
    private String copyFrom;
    private int position;
    private int duration;
    private int fee;
    private String name;
    private int id;
    private List<?> alias;
    private List<ArtistsBeanX> artists;

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getStarredNum() {
        return starredNum;
    }

    public void setStarredNum(int starredNum) {
        this.starredNum = starredNum;
    }

    public int getPlayedNum() {
        return playedNum;
    }

    public void setPlayedNum(int playedNum) {
        this.playedNum = playedNum;
    }

    public int getDayPlays() {
        return dayPlays;
    }

    public void setDayPlays(int dayPlays) {
        this.dayPlays = dayPlays;
    }

    public int getHearTime() {
        return hearTime;
    }

    public void setHearTime(int hearTime) {
        this.hearTime = hearTime;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public Object getRtUrls() {
        return rtUrls;
    }

    public void setRtUrls(Object rtUrls) {
        this.rtUrls = rtUrls;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BMusicBean getBMusic() {
        return bMusic;
    }

    public void setBMusic(BMusicBean bMusic) {
        this.bMusic = bMusic;
    }

    public Object getCrbt() {
        return crbt;
    }

    public void setCrbt(Object crbt) {
        this.crbt = crbt;
    }

    public Object getRtUrl() {
        return rtUrl;
    }

    public void setRtUrl(Object rtUrl) {
        this.rtUrl = rtUrl;
    }

    public Object getAudition() {
        return audition;
    }

    public void setAudition(Object audition) {
        this.audition = audition;
    }

    public String getRingtone() {
        return ringtone;
    }

    public void setRingtone(String ringtone) {
        this.ringtone = ringtone;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public AlbumBean getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBean album) {
        this.album = album;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(int copyrightId) {
        this.copyrightId = copyrightId;
    }

    public HMusicBean getHMusic() {
        return hMusic;
    }

    public void setHMusic(HMusicBean hMusic) {
        this.hMusic = hMusic;
    }

    public MMusicBean getMMusic() {
        return mMusic;
    }

    public void setMMusic(MMusicBean mMusic) {
        this.mMusic = mMusic;
    }

    public LMusicBean getLMusic() {
        return lMusic;
    }

    public void setLMusic(LMusicBean lMusic) {
        this.lMusic = lMusic;
    }

    public int getMvid() {
        return mvid;
    }

    public void setMvid(int mvid) {
        this.mvid = mvid;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public Object getRurl() {
        return rurl;
    }

    public void setRurl(Object rurl) {
        this.rurl = rurl;
    }

    public String getCopyFrom() {
        return copyFrom;
    }

    public void setCopyFrom(String copyFrom) {
        this.copyFrom = copyFrom;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
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

    public List<?> getAlias() {
        return alias;
    }

    public void setAlias(List<?> alias) {
        this.alias = alias;
    }

    public List<ArtistsBeanX> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistsBeanX> artists) {
        this.artists = artists;
    }

    public static class BMusicBean {
        /**
         * dfsId : 7936274930834740
         * playTime : 239027
         * volumeDelta : -2.65076E-4
         * bitrate : 96000
         * sr : 44100
         * name : null
         * id : 99279176
         * size : 2869288
         * extension : mp3
         */

        private long dfsId;
        private int playTime;
        private double volumeDelta;
        private int bitrate;
        private int sr;
        private Object name;
        private int id;
        private int size;
        private String extension;

        public long getDfsId() {
            return dfsId;
        }

        public void setDfsId(long dfsId) {
            this.dfsId = dfsId;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public double getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(double volumeDelta) {
            this.volumeDelta = volumeDelta;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }

    public static class AlbumBean {
        /**
         * songs : []
         * paid : false
         * onSale : false
         * tags :
         * status : 1
         * alias : []
         * publishTime : 973526400000
         * company : 阿尔发音乐
         * blurPicUrl : http://p3.music.126.net/Gd-HAk9hKC85L0wNtfRs1g==/7946170535396804.jpg
         * companyId : 0
         * pic : 7946170535396804
         * artist : {"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","musicSize":0,"name":"","id":0,"img1v1Id_str":"18686200114669622"}
         * picUrl : http://p3.music.126.net/Gd-HAk9hKC85L0wNtfRs1g==/7946170535396804.jpg
         * commentThreadId : R_AL_3_18918
         * copyrightId : 1007
         * picId : 7946170535396804
         * briefDesc :
         * artists : [{"img1v1Id":18686200114669622,"topicPerson":0,"alias":[],"picUrl":"http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","picId":0,"briefDesc":"","albumSize":0,"img1v1Url":"http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","musicSize":0,"name":"周杰伦","id":6452,"img1v1Id_str":"18686200114669622"}]
         * description :
         * subType : 录音室版
         * name : Jay 同名专辑
         * id : 18918
         * type : 专辑
         * size : 10
         */

        private boolean paid;
        private boolean onSale;
        private String tags;
        private int status;
        private long publishTime;
        private String company;
        private String blurPicUrl;
        private int companyId;
        private long pic;
        private ArtistBean artist;
        private String picUrl;
        private String commentThreadId;
        private int copyrightId;
        private long picId;
        private String briefDesc;
        private String description;
        private String subType;
        private String name;
        private int id;
        private String type;
        private int size;
        private List<?> songs;
        private List<?> alias;
        private List<ArtistsBean> artists;

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

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
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

        public ArtistBean getArtist() {
            return artist;
        }

        public void setArtist(ArtistBean artist) {
            this.artist = artist;
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

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
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

        public List<?> getSongs() {
            return songs;
        }

        public void setSongs(List<?> songs) {
            this.songs = songs;
        }

        public List<?> getAlias() {
            return alias;
        }

        public void setAlias(List<?> alias) {
            this.alias = alias;
        }

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
        }

        public static class ArtistBean {
            /**
             * img1v1Id : 18686200114669622
             * topicPerson : 0
             * alias : []
             * picUrl : http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
             * picId : 0
             * briefDesc :
             * albumSize : 0
             * img1v1Url : http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
             * trans :
             * musicSize : 0
             * name :
             * id : 0
             * img1v1Id_str : 18686200114669622
             */

            private long img1v1Id;
            private int topicPerson;
            private String picUrl;
            private int picId;
            private String briefDesc;
            private int albumSize;
            private String img1v1Url;
            private String trans;
            private int musicSize;
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

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
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

            public String getTrans() {
                return trans;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }

            public int getMusicSize() {
                return musicSize;
            }

            public void setMusicSize(int musicSize) {
                this.musicSize = musicSize;
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

        public static class ArtistsBean {
            /**
             * img1v1Id : 18686200114669622
             * topicPerson : 0
             * alias : []
             * picUrl : http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
             * picId : 0
             * briefDesc :
             * albumSize : 0
             * img1v1Url : http://p4.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
             * trans :
             * musicSize : 0
             * name : 周杰伦
             * id : 6452
             * img1v1Id_str : 18686200114669622
             */

            private long img1v1Id;
            private int topicPerson;
            private String picUrl;
            private int picId;
            private String briefDesc;
            private int albumSize;
            private String img1v1Url;
            private String trans;
            private int musicSize;
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

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
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

            public String getTrans() {
                return trans;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }

            public int getMusicSize() {
                return musicSize;
            }

            public void setMusicSize(int musicSize) {
                this.musicSize = musicSize;
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

    public static class HMusicBean {
        /**
         * dfsId : 7998947093616069
         * playTime : 239027
         * volumeDelta : -2.65076E-4
         * bitrate : 320000
         * sr : 44100
         * name : null
         * id : 99279174
         * size : 9564053
         * extension : mp3
         */

        private long dfsId;
        private int playTime;
        private double volumeDelta;
        private int bitrate;
        private int sr;
        private Object name;
        private int id;
        private int size;
        private String extension;

        public long getDfsId() {
            return dfsId;
        }

        public void setDfsId(long dfsId) {
            this.dfsId = dfsId;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public double getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(double volumeDelta) {
            this.volumeDelta = volumeDelta;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }

    public static class MMusicBean {
        /**
         * dfsId : 3357908511299974
         * playTime : 239027
         * volumeDelta : -2.65076E-4
         * bitrate : 160000
         * sr : 44100
         * name : null
         * id : 99279175
         * size : 4782078
         * extension : mp3
         */

        private long dfsId;
        private int playTime;
        private double volumeDelta;
        private int bitrate;
        private int sr;
        private Object name;
        private int id;
        private int size;
        private String extension;

        public long getDfsId() {
            return dfsId;
        }

        public void setDfsId(long dfsId) {
            this.dfsId = dfsId;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public double getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(double volumeDelta) {
            this.volumeDelta = volumeDelta;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }

    public static class LMusicBean {
        /**
         * dfsId : 7936274930834740
         * playTime : 239027
         * volumeDelta : -2.65076E-4
         * bitrate : 96000
         * sr : 44100
         * name : null
         * id : 99279176
         * size : 2869288
         * extension : mp3
         */

        private long dfsId;
        private int playTime;
        private double volumeDelta;
        private int bitrate;
        private int sr;
        private Object name;
        private int id;
        private int size;
        private String extension;

        public long getDfsId() {
            return dfsId;
        }

        public void setDfsId(long dfsId) {
            this.dfsId = dfsId;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public double getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(double volumeDelta) {
            this.volumeDelta = volumeDelta;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }

    public static class ArtistsBeanX {
        /**
         * img1v1Id : 18686200114669622
         * topicPerson : 0
         * alias : []
         * picUrl : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
         * picId : 0
         * briefDesc :
         * albumSize : 0
         * img1v1Url : http://p3.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg
         * trans :
         * musicSize : 0
         * name : 周杰伦
         * id : 6452
         * img1v1Id_str : 18686200114669622
         */

        private long img1v1Id;
        private int topicPerson;
        private String picUrl;
        private int picId;
        private String briefDesc;
        private int albumSize;
        private String img1v1Url;
        private String trans;
        private int musicSize;
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

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
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

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }

        public int getMusicSize() {
            return musicSize;
        }

        public void setMusicSize(int musicSize) {
            this.musicSize = musicSize;
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
