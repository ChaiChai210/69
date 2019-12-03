package com.colin.playerdemo.bean;

public class HistoryBean {
    private int id;
    private String cover;
    private String name;
    private String video_url;
    private String cdate;
    private String vid;
    private String uid;

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
