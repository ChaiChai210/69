package com.colin.playerdemo.net;

public class BaseBean<T> {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int code;
    private String info;
    private T data;
    public boolean isSuccess(){
        return code == 0 || code == 200;
    }

}
