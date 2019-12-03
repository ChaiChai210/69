package com.colin.playerdemo.bean;

import java.io.Serializable;


public class VideoBean implements Serializable {
    String name ;
    String url;
    String length;
    String playUrl;
    String newList;

    public String getNewList() {
        return newList;
    }

    public void setNewList(String newList) {
        this.newList = newList;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
