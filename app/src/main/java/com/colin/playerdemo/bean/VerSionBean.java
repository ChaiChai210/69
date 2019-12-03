package com.colin.playerdemo.bean;

import java.io.Serializable;


public class VerSionBean implements Serializable {

    /**
     * versionCode : 1
     * versionName : 1.0
     * force : 1
     * url : http://www.baidu.com
     * detail : 1.增加回放上传功能。
     2.修复个人中心闪退bug。
     */

    private int versionCode;
    private String versionName;
    private int force;
    private String url;
    private String detail;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
