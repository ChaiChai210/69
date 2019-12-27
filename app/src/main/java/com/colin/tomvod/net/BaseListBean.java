package com.colin.tomvod.net;

import java.util.List;

public class BaseListBean<T> {

    private int code;
    private String info;
    private List<T> data;


    public boolean isSuccess() {
        return code == 200;
    }

    public int getResCode() {
        return code;
    }

    public void setResCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<T> getData() {
        return data;
    }

}
