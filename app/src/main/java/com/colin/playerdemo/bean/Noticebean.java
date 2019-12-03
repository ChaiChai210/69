package com.colin.playerdemo.bean;

import java.io.Serializable;


public class Noticebean implements Serializable {

    /**
     * id : 1
     * name : aha
     * pic : D:\壁纸
     * content : 我是谁，我在那
     * create_date : 2019-01-11 14:11:08
     */

    private int id;
    private String name;
    private String pic;
    private String content;
    private String create_date;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
