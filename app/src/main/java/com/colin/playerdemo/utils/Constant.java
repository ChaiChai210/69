package com.colin.playerdemo.utils;

/**
 * 描述：常量
 * 作者：李昌骏 on 2018\11\07 0021 11:06
 * 电话：13881771371
 */

public interface Constant {
    /**
     * 保存在手机里面的文件名
     */

    //------------SP KEY------
    String SP_FILE_NAME = "gold_sp_data";
    String SP_LOGIN_ACCOUNT = "SP_LOGIN_ACCOUNT";
    String SP_LOGIN_PSW = "SP_LOGIN_PSW";
    String SP_LOGIN_TOKEN = "SP_LOGIN_TOKEN";
    String SP_DEFAULT_TOKEN = "SP_DEFAULT_TOKEN";
    String SP_FIRST_INSTALL = "SP_FIRST_INSTALL";
    String SP_FIRST_ENTER = "SP_FIRST_ENTER";
    String SP_HISTORY_SEARCH = "HISTORY_SEARCH";
    String SP_AVATAR = "SP_AVATAR";


    String APK_NAME = "dyd_shop.apk";
    String NET_ERROR_DESC = "网络已经断开";//网络请求失败提示语
    String DB_NAME = "db_feifei";//


    //------微信appkey------
    String WXAPP_ID = "";

    //------支付宝appkey------
    String ALIAPP_ID = "";


    //--------------final  ---------------

    int LOGIN_STATE_OUT = -1;//未登录状态
    int LOGIN_STATE_IN = 0;//登录状态
    String LGN_TKN = "lgnTkn";//
    String LOGIN_STATE = "loginState";//


    //------------------event bus code---------------------------
    int BASE_REFRESH_VIEW_CODE = 999;//


}
