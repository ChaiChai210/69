package com.colin.playerdemo.net;


import com.colin.playerdemo.bean.AloneBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;

/**
 * 描述：Gson辅助类
 * * 作者：李昌骏 on 2018\4\25 0025 10:17
 * 电话：13881771371
 */
public class GsonHelper {
    public static Gson gson = new GsonBuilder().serializeNulls().
            registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();


}
