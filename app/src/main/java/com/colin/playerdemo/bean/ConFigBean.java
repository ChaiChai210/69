package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class ConFigBean implements Serializable {

    /**
     * code : 0
     * info : 查询成功！
     * data : [{"id":1,"name":"version","content":"false","introduce":"是否强制更新","type":"force_update"},{"id":2,"name":"domain","content":"http://www.cqwjit.com:8082","introduce":"henhaokan ","type":"domain"},{"id":18,"name":"hop_group","content":"112300","introduce":"其他 - 火爆交流群","type":"hop_group"},{"id":20,"name":"storage","content":"http://video.imgapi.net:3389","introduce":"666VIDEO","type":"storage"},{"id":25,"name":"version","content":"1.0","introduce":"版本号","type":"version"},{"id":26,"name":"version","content":"http://www.cqwjit.com:8082/ios.apk","introduce":"ios下载地址","type":"ios_update_url"},{"id":27,"name":"version","content":"http://www.cqwjit.com:8082/android.apk","introduce":"安卓下载地址","type":"android_update_url"},{"id":28,"name":"version","content":"更新内容说明：更新了XXXXX","introduce":"更新内容说明","type":"content"}]
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
         * id : 1
         * name : version
         * content : false
         * introduce : 是否强制更新
         * type : force_update
         */

        private int id;
        private String name;
        private String content;
        private String introduce;
        private String type;
        private String tg_domain;

        public String getTg_domain() {
            return tg_domain;
        }

        public void setTg_domain(String tg_domain) {
            this.tg_domain = tg_domain;
        }

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
