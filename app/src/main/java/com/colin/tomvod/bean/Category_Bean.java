package com.colin.tomvod.bean;

import java.io.Serializable;


public class Category_Bean implements Serializable {

    /**
     * id : 1002
     * name : 游戏改编
     * pic : dst_uin=16572513&spec=100
     */

    private int id;
    private String name;
    private String pic;

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
