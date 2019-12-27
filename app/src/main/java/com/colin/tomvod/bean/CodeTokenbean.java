package com.colin.tomvod.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CodeTokenbean implements Serializable {

    /**
     * invite_code : SW56G1
     * app-token : asdwqejfhdjfgjksqweadasddsfjl
     */

    private String invite_code;
    @SerializedName("app-token")
    private String apptoken;

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }
}
