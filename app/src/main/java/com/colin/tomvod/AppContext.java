package com.colin.tomvod;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.colin.tomvod.base.AppConfig;
import com.colin.tomvod.utils.SPUtils;
import com.colin.tomvod.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;


/**
 * 描述：（请填写类描述）
 * 作者：李昌骏 on 2018\3\20 0020 16:09
 * 电话：13881771371
 */

public class AppContext extends Application {
    public static Context applicationContext;
    public static int notificationId;
    public static boolean isFrist = true, home_guide = true;//是否第一次加载 是否是首页的导航
//    public static Context sCurContext;
//    private static ImagePicker imagePicker;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决4.x运行崩溃的问题
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();

        initOkGo();
//        initLogger ();//初始化logger框架
//        initImgPicker ();
//        MobSDK.init (this);//初始化Mob SDK
    }

//    private void initImgPicker() {
//        imagePicker = ImagePicker.getWithToken ();
//        imagePicker.setImageLoader (new GlideLoader ());   //设置图片加载器
//        imagePicker.setShowCamera (true);  //显示拍照按钮
//        imagePicker.setMultiMode (false);
//        imagePicker.setCrop (false);        //允许裁剪（单选才有效）
//        imagePicker.setSaveRectangle (true); //是否按矩形区域保存
//        imagePicker.setSelectLimit (9);    //选中数量限制
//        imagePicker.setStyle (CropImageView.Style.RECTANGLE);  //裁剪框的形状
//        imagePicker.setFocusWidth (800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight (800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX (1000);//保存文件的宽度。单位像素
//        imagePicker.setOutPutY (1000);//保存文件的高度。单位像素
//    }
//
//    public static ImagePicker getImagePicker() {
//        return imagePicker;
//    }
//
//    private void initLogger() {
//        Logger.addLogAdapter (new AndroidLogAdapter ());
//    }

    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
//        headers.put ("Authorization", SPUtils.getToken ());
//        headers.put ("Authorization", SPUtils.getToken ());
        if (!StringUtils.isEmpty(SPUtils.getLoginToken())) {
            headers.put("app-token", SPUtils.getLoginToken());
        } else if (!StringUtils.isEmpty(SPUtils.getDefaultToken())) {
            headers.put("app-token", SPUtils.getDefaultToken());
        }
        HttpParams params = new HttpParams();
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
//        builder.addInterceptor (new LoginIntercepter ());
        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));
        //超时时间设置，默认60秒
        builder.readTimeout(AppConfig.NET_READ_TIME_OUT, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(AppConfig.NET_WRITE_TIME_OUT, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(AppConfig.NET_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失


        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();


        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                          //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)    //全局公共头
                .addCommonParams(params);
    }

}
