package com.colin.tomvod.bean;

import java.io.Serializable;
import java.util.List;


public class DisconverBean implements Serializable {

    /**
     * code : 0
     * info : 获取成功
     * data : [{"id":77,"cover":"http://www.cqwjit.com:8082","name":null,"play_count":0,"video_url":"http://video.imgapi.net:3389/videos/5c528c934859a03f8607ea41/index.m3u8","stor_id":"5c528c934859a03f8607ea41","stor_orig_name":"test4.mp4","is_collect":0,"video_download_url":"http://video.imgapi.net:3389/5c528c934859a03f8607ea41/test4.mp4"},{"id":94,"cover":"http://www.cqwjit.com:8082/uploads/video/pic/8b/19834e4070400ca380f7771a5238ef.jpg","name":"无码狗","play_count":0,"video_url":"http://video.imgapi.net:3389/videos/5c52c46612fcd87aed32223d/index.m3u8","stor_id":"5c52c46612fcd87aed32223d","stor_orig_name":"test20.mp4","is_collect":0,"video_download_url":"http://video.imgapi.net:3389/5c52c46612fcd87aed32223d/test20.mp4"}]
     */

    private int code;
    private String info;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 77
         * cover : http://www.cqwjit.com:8082
         * name : null
         * play_count : 0
         * video_url : http://video.imgapi.net:3389/videos/5c528c934859a03f8607ea41/index.m3u8
         * stor_id : 5c528c934859a03f8607ea41
         * stor_orig_name : test4.mp4
         * is_collect : 0
         * video_download_url : http://video.imgapi.net:3389/5c528c934859a03f8607ea41/test4.mp4
         */

        private int id;
        private String cover;
        private String name;
        private int play_count;
        private String video_url;
        private String stor_id;
        private String stor_orig_name;
        private int is_collect;
        private String video_download_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getStor_id() {
            return stor_id;
        }

        public void setStor_id(String stor_id) {
            this.stor_id = stor_id;
        }

        public String getStor_orig_name() {
            return stor_orig_name;
        }

        public void setStor_orig_name(String stor_orig_name) {
            this.stor_orig_name = stor_orig_name;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public String getVideo_download_url() {
            return video_download_url;
        }

        public void setVideo_download_url(String video_download_url) {
            this.video_download_url = video_download_url;
        }
    }
}
