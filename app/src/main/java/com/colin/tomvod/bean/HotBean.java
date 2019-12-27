package com.colin.tomvod.bean;

import java.io.Serializable;


public class HotBean implements Serializable {


    /**
     * id : 1
     * photo : /static/uploads/topic/fw.jpg
     * pic : /static/uploads/topic/m.jpg
     * name : 圣诞节
     * introduce : 圣诞节快乐
     * create_date : 2019-01-09 00:00:00
     * hot : 0
     */

    private int id;
    private String photo;
    private String pic;
    private String name;
    private String introduce;
    private String create_date;
    private String hot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }
}
