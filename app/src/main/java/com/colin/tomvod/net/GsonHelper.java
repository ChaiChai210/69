package com.colin.tomvod.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 描述：Gson辅助类
 * * 作者：李昌骏 on 2018\4\25 0025 10:17
 * 电话：13881771371
 */
public class GsonHelper {
    public static Gson gson = new GsonBuilder().serializeNulls().
            registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();


}
