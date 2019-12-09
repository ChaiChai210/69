package com.colin.playerdemo.bean;

import java.io.Serializable;


public class UVersionBean implements Serializable {

    /**
     * force_update : false
     * version : 1.0
     * ios_update_url : http://www.cqwjit.com:8082/ios.apk
     * android_update_url : http://www.cqwjit.com:8082/api/extend
     * content : 更新内容说明：更新了XXXXX
     */

    private String force_update;
    private String version;
    private String ios_update_url;
    private String android_update_url;
    private String content;

    public String getForce_update() {
        return force_update;
    }

    public void setForce_update(String force_update) {
        this.force_update = force_update;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIos_update_url() {
        return ios_update_url;
    }

    public void setIos_update_url(String ios_update_url) {
        this.ios_update_url = ios_update_url;
    }

    public String getAndroid_update_url() {
        return android_update_url;
    }

    public void setAndroid_update_url(String android_update_url) {
        this.android_update_url = android_update_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UVersionBean{" +
                "force_update='" + force_update + '\'' +
                ", version='" + version + '\'' +
                ", ios_update_url='" + ios_update_url + '\'' +
                ", android_update_url='" + android_update_url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
