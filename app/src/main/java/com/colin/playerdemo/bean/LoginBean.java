package com.colin.playerdemo.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LoginBean implements Serializable {

    /**
     * code : 0
     * info : 登录成功
     * data : {"app-token":"eoyaJuB0UFBfVUlEIjozN30O0O0O"}
     */

    private int code;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * app-token : eoyaJuB0UFBfVUlEIjozN30O0O0O
         */

        @SerializedName("app-token")
        private String apptoken;

        public String getApptoken() {
            return apptoken;
        }

        public void setApptoken(String apptoken) {
            this.apptoken = apptoken;
        }
    }
}
