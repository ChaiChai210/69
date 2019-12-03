package com.colin.playerdemo.bean;

import java.io.Serializable;


public class Adsbean implements Serializable {

    /**
     * id : 100
     * name : 百度双12活动
     * pic : http://www.cqwjit.com:8082/uploads/advertise/2d/2a910e44ca9460d44f6932e16aaaac.jpg
     * url : http://www.cqwjit.com:8082/www.baidu.com
     * exposure : 0
     * position_id : 3
     * position_name : 首页栏目间广告
     */

    private int id;
    private String name;
    private String pic;
    private String url;
    private int exposure;
    private int position_id;
    private String position_name;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getExposure() {
        return exposure;
    }

    public void setExposure(int exposure) {
        this.exposure = exposure;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }
}
