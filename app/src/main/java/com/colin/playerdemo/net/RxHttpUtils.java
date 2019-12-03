package com.colin.playerdemo.net;


import com.colin.playerdemo.utils.SPUtils;

import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.param.RxHttp$FormParam;
import rxhttp.wrapper.param.RxHttp$NoBodyParam;

/**
 * 用于需要传token的请求
 */
public final class RxHttpUtils {

    private RxHttpUtils() {
    }



    public static RxHttp$NoBodyParam getWithToken(String name) {
        return RxHttp.get(name).addHeader("app-token", SPUtils.getToken());
    }

    public static RxHttp$FormParam postWithToken(String name) {
        return RxHttp.postForm(name).addHeader("app-token", SPUtils.getToken());
    }

//    public static RxHttp$JsonParam getInstanceForLoading(Context context, String name) {
//        LoadingDialog loading = WidgetUtils.getLoadingDialog(context)
//                .setIconScale(0.4F)
//                .setLoadingSpeed(8);
//        loading.setCancelable(true);
//        MemCache.put(KeyConsts.K_LOADING, loading);
//        loading.show();
//        return RxHttp.postJson(name).addHeader("XX-token", CacheUtils.getToken());
//    }
//
//    public static RxHttp$JsonParam getInstanceForLoading(Context context, String name, String message) {
//        return getInstanceForLoading(context, name, message, true);
//    }
//
//    public static RxHttp$JsonParam getInstanceForLoading(Context context, String name, String message, boolean hasToken) {
//        LoadingDialog loading = WidgetUtils.getLoadingDialog(context, message)
//                .setIconScale(0.4F)
//                .setLoadingSpeed(8);
//        MemCache.put(KeyConsts.K_LOADING, loading);
//        loading.show();
//        if (hasToken) {
//            return RxHttp.postJson(name).addHeader("XX-token", CacheUtils.getToken());
//        } else {
//            return RxHttp.postJson(name);
//        }
//    }
//
//    public static void hideDialog() {
//        LoadingDialog loading = MemCache.get(KeyConsts.K_LOADING);
//        if (loading != null) {
//            loading.dismiss();
//            MemCache.remove(KeyConsts.K_LOADING);
//        }
//    }
}
