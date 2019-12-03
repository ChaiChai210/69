package com.colin.playerdemo.bean;

import java.io.Serializable;


public class TagTypeListBean implements Serializable {

    /**
     * id : 1
     * tag_id : 1
     * name : 历史剧
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

    @Override
    public String toString() {
        return "TagTypeListBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
