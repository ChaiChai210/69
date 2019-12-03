package com.colin.playerdemo.bean;

import java.io.Serializable;


public class MessageBean implements Serializable {

    /**
     * id : 29
     * comment_id : 22
     * uid : 162
     * nickname : 风萧萧兮易水寒
     * portrait : http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png
     * sex : 女
     * level_id : null
     * content : 我学医七年，研究生毕业，表弟学医五年，本科毕业。我们两个同时到一家医院应聘。考题只有一个题目：有人遗传有神经性偏头痛应该怎么治疗？我一看题目这么简单，就写上了“轻度和中度头痛可以尝试服用阿司匹林，较严重者可以尝试添加少量神经抑制类药物。”结果我被淘汰了，表弟被录取了。我瞄了一眼他的答案，上面写着：“应该让患者先做一下血常规，确认不是第三方因素引起的头痛。如确认是神经性头痛，应该补做一次CT影像，以观测头痛是否因血管和神经距离过近造成压迫神经引起，同时分析该神经分布和作用区域，考虑神经阻断的可行性和阻断神经造成面瘫的几率，让患者选择药物治疗还是神经阻断。药物治疗首推雾化喷剂类药物....”
     * create_time : 2019-01-31 11:06:39
     * reply_id : 0
     * to_uid : 172
     * to_nickname : 北极菜ASD
     * to_portrait : http://www.cqwjit.com:8082
     * to_sex : 0
     * to_level_id : null
     * zan_count : 0
     * vid : 3
     */

    private int id;
    private int comment_id;
    private long uid;
    private String nickname;
    private String portrait;
    private String sex;
    private Object level_id;
    private String content;
    private String create_time;
    private int reply_id;
    private int to_uid;
    private String to_nickname;
    private String to_portrait;
    private int to_sex;
    private Object to_level_id;
    private int zan_count;
    private int vid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Object getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Object level_id) {
        this.level_id = level_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public int getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(int to_uid) {
        this.to_uid = to_uid;
    }

    public String getTo_nickname() {
        return to_nickname;
    }

    public void setTo_nickname(String to_nickname) {
        this.to_nickname = to_nickname;
    }

    public String getTo_portrait() {
        return to_portrait;
    }

    public void setTo_portrait(String to_portrait) {
        this.to_portrait = to_portrait;
    }

    public int getTo_sex() {
        return to_sex;
    }

    public void setTo_sex(int to_sex) {
        this.to_sex = to_sex;
    }

    public Object getTo_level_id() {
        return to_level_id;
    }

    public void setTo_level_id(Object to_level_id) {
        this.to_level_id = to_level_id;
    }

    public int getZan_count() {
        return zan_count;
    }

    public void setZan_count(int zan_count) {
        this.zan_count = zan_count;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }
}
