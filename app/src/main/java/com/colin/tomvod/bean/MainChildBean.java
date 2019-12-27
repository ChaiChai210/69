package com.colin.tomvod.bean;

import java.io.Serializable;


public class MainChildBean implements Serializable {


    /**
     * id : 3
     * video_url : /videofile/123d5asd4qw5ras56dsad/
     * name : 呵呵呵
     * cover : http://www.cqwjit.com:8082//uploads/video/pic/cf/693408dfb66fc1175d488f2542469f.jpg
     * introduce : 123
     * classify_id : 5
     * run_date : 1993-06-22
     * play_count : 3
     * duration : 1200
     * score_not : 0
     * score_yes : 1
     * status : 1
     * create_time : 2019-01-13 10:52:51
     */

    private int id;
    private String video_url;
    private String name;
    private String cover;
    private String introduce;
    private int classify_id;
    private String run_date;
    private String classify_name;
    private int play_count;
    private int duration;
    private int score_not;
    private int score_yes;
    private int status;
    private String create_time;

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(int classify_id) {
        this.classify_id = classify_id;
    }

    public String getRun_date() {
        return run_date;
    }

    public void setRun_date(String run_date) {
        this.run_date = run_date;
    }

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getScore_not() {
        return score_not;
    }

    public void setScore_not(int score_not) {
        this.score_not = score_not;
    }

    public int getScore_yes() {
        return score_yes;
    }

    public void setScore_yes(int score_yes) {
        this.score_yes = score_yes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
