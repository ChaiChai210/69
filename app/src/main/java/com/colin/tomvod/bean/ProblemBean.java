package com.colin.tomvod.bean;

import java.io.Serializable;


public class ProblemBean implements Serializable {

    /**
     * id : 2
     * name : 显示网络连接失败
     * content : 滚
     */

    private int id;
    private String name;
    private String content;

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
}
