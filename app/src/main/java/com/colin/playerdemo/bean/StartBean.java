package com.colin.playerdemo.bean;

import java.io.Serializable;


public class StartBean implements Serializable {

    /**
     * id : 1
     * name : 沈腾
     * portrait : /static/uploads/star/md5.jpg
     * nationality : 中国
     * height : 175
     * sex : 1
     * age : 38
     * cup : null
     * bwh_bust : null
     * bwh_waist : null
     * bwh_hips : null
     * pic : /static/uploads/star/bg/md5.jpg
     * introduce : 沈腾，中国喜剧演员
     */

    private int id;
    private String name;
    private String portrait;
    private String nationality;
    private String height;
    private String sex;
    private int age;
    private Object cup;
    private Object bwh_bust;
    private Object bwh_waist;
    private Object bwh_hips;
    private String pic;
    private String introduce;

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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Object getCup() {
        return cup;
    }

    public void setCup(Object cup) {
        this.cup = cup;
    }

    public Object getBwh_bust() {
        return bwh_bust;
    }

    public void setBwh_bust(Object bwh_bust) {
        this.bwh_bust = bwh_bust;
    }

    public Object getBwh_waist() {
        return bwh_waist;
    }

    public void setBwh_waist(Object bwh_waist) {
        this.bwh_waist = bwh_waist;
    }

    public Object getBwh_hips() {
        return bwh_hips;
    }

    public void setBwh_hips(Object bwh_hips) {
        this.bwh_hips = bwh_hips;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
