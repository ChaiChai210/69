package com.colin.tomvod.bean;

import java.io.Serializable;


public class FendBackBeanList implements Serializable {

    /**
     * uid : 1
     * nickname : 北极菜
     * portrait : /static/uploads/user/portrait/md5.jpg
     * id : 1
     * name : SQL_NOT_FOUND 1403 错误
     * opiniontype_id : 1
     * replay : 什么鬼哦,老是播放失败.
     * comments : 请检查你的网络是否正常
     * create_date : 2019-01-09 10:24:01
     * pic : //uploads/notice/6a/335524248af652c6f2e1cb364d404c.jpg
     */

    private long uid;
    private String nickname;
    private String portrait;
    private int id;
    private String name;
    private int opiniontype_id;
    private String replay;
    private String comments;
    private String create_date;
    private String pic;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

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

    public int getOpiniontype_id() {
        return opiniontype_id;
    }

    public void setOpiniontype_id(int opiniontype_id) {
        this.opiniontype_id = opiniontype_id;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
