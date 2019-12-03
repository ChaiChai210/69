package com.colin.playerdemo.bean;

import java.io.Serializable;


public class HotSearchBean implements Serializable {

    /**
     * id : 8
     * content : 屎
     * clicks : 45
     */

    private int id;
    private String content;
    private int clicks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
