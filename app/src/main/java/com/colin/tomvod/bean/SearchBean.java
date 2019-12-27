package com.colin.tomvod.bean;

import java.io.Serializable;
import java.util.List;


public class SearchBean implements Serializable {

    /**
     * id : 3
     * name : HHH
     * cover : http://www.cqwjit.com:8082/uploads/video/pic/c8/421b2e4d8ee27d9ba6a78f483a2e9a.png
     * score : 6.5
     * introduce : DDDDD
     * play_count : 5
     * tags : ["中文字幕"]
     */

    private int id;
    private String name;
    private String cover;
    private String score;
    private String introduce;
    private int play_count;
    private List<String> tags;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
