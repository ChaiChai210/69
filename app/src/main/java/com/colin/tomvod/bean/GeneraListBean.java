package com.colin.tomvod.bean;

import java.io.Serializable;


public class GeneraListBean implements Serializable {

    /**
     * id : 4
     * create_time : 2019-01-26 14:15:20
     * bind_uid : 11
     * nickname : 北极菜000
     * phone : 17623031599
     */

    private int id;
    private String create_time;
    private int bind_uid;
    private String nickname;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getBind_uid() {
        return bind_uid;
    }

    public void setBind_uid(int bind_uid) {
        this.bind_uid = bind_uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
