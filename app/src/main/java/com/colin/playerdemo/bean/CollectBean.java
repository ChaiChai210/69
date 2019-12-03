package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class CollectBean implements Serializable {


    /**
     * count : 1
     * list : [{"vid":81,"video_url":"http://www.cqwjit.com:8082/videos/5c529b5e28cfbd696339d332/index.m3u8","cover":"http://www.cqwjit.com:8082","name":null,"id":30,"uid":191,"create_date":"2019-02-01 11:53:42","nickname":"69video15489146"}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * vid : 81
         * video_url : http://www.cqwjit.com:8082/videos/5c529b5e28cfbd696339d332/index.m3u8
         * cover : http://www.cqwjit.com:8082
         * name : null
         * id : 30
         * uid : 191
         * create_date : 2019-02-01 11:53:42
         * nickname : 69video15489146
         */

        private int vid;
        private String video_url;
        private String cover;
        private String name;
        private int id;
        private long uid;
        private String create_date;
        private String nickname;

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
