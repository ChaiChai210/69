package com.colin.tomvod.bean;

import java.io.Serializable;
import java.util.List;


public class BannerBean implements Serializable {

    /**
     * code : 0
     * info : 获取成功
     * data : [{"id":100,"name":"百度双12活动","pic":"www.baidu.com","url":"www.baidu.com","exposure":0,"position_id":1,"position_name":"位置1"},{"id":107,"name":"跑步活动","pic":"/uploads/advertise/e0\\c078e6183bba46570bf8ffa84b27a9.png","url":"www.baidu.com","exposure":0,"position_id":1,"position_name":"位置1"}]
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
         * id : 100
         * name : 百度双12活动
         * pic : www.baidu.com
         * url : www.baidu.com
         * exposure : 0
         * position_id : 1
         * position_name : 位置1
         */

        private int id;
        private String name;
        private String pic;
        private String url;
        private int exposure;
        private int position_id;
        private String position_name;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getExposure() {
            return exposure;
        }

        public void setExposure(int exposure) {
            this.exposure = exposure;
        }

        public int getPosition_id() {
            return position_id;
        }

        public void setPosition_id(int position_id) {
            this.position_id = position_id;
        }

        public String getPosition_name() {
            return position_name;
        }

        public void setPosition_name(String position_name) {
            this.position_name = position_name;
        }
    }
}
