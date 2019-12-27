package com.colin.tomvod.bean;

import java.io.Serializable;


public class FeedBackBean implements Serializable {

    /**
     * id : 1
     * name : 视频不全
     */

    private int id;
    private String name;

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
}
