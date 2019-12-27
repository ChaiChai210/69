package com.colin.tomvod.bean;

import java.io.Serializable;


public class Change_Head_bean implements Serializable {


    /**
     * url : http://47.92.158.69:8082/uploads/user/a1/fe95225e36e09270a05590801e0d13.jpg
     * key : uploads/user/a1/fe95225e36e09270a05590801e0d13.jpg
     * domain : null
     * host : http://47.92.158.69:8082
     * file_size : 347370
     * file_type : image/jpeg
     * file_hash : a1fe95225e36e09270a05590801e0d13
     */

    private String url;
    private String key;
    private Object domain;
    private String host;
    private int file_size;
    private String file_type;
    private String file_hash;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getDomain() {
        return domain;
    }

    public void setDomain(Object domain) {
        this.domain = domain;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_hash() {
        return file_hash;
    }

    public void setFile_hash(String file_hash) {
        this.file_hash = file_hash;
    }
}
