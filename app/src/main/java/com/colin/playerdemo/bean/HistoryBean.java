package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class HistoryBean implements Serializable {

    /**
     * code : 0
     * info : 查询成功
     * data : [{"id":2,"cover":"/static/video/cover/msdgjpg","name":"新年一起过","cdate":"2019-01-18 15:20:04"},{"id":3,"cover":"ssss","name":"呵呵呵","cdate":"2019-01-18 15:20:10"}]
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
         * id : 2
         * cover : /static/video/cover/msdgjpg
         * name : 新年一起过
         * cdate : 2019-01-18 15:20:04
         */

        private int id;
        private String cover;
        private String name;
        private String video_url;
        private String cdate;
        private String vid;
        private String uid;

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

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

        public String getCdate() {
            return cdate;
        }

        public void setCdate(String cdate) {
            this.cdate = cdate;
        }
    }
}
