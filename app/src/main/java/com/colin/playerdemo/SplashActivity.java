package com.colin.playerdemo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.activity.GuideActivity;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.Adsbean;
import com.colin.playerdemo.bean.CodeTokenbean;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.BaseListBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.Constant;
import com.colin.playerdemo.utils.SPUtils;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.qfxl.view.RoundProgressBar;

import java.lang.reflect.Type;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_gg)
    ImageView ivGg;
    @BindView(R.id.rpb_gg)
    RoundProgressBar rpbGg;
//    @BindView(R.id.load_goto_gg)
//    RoundProgressBar loadGotoGg;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        if (TextUtils.isEmpty(SPUtils.getDefaultToken())) {
            getToken();
        } else {
            init();
        }
//        rpbGg.setOnClickListener(v -> {
//            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        });
        rpbGg.setProgressChangeListener(new RoundProgressBar.ProgressChangeListener() {
            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onProgressChanged(int progress) {
                int text = 5 - progress * 5 / 100;
                rpbGg.setCenterText(text + "");
            }
        });
    }

    private void getToken() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.GETTOKEM).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                UIhelper.stopLoadingDialog();
                Type type = new TypeToken<BaseBean<CodeTokenbean>>() {
                }.getType();
                BaseBean<CodeTokenbean> baseBean = GsonHelper.gson.fromJson(response.body(), type);
                //返回码为成功时的处理
                if (baseBean.isSuccess()) {
                    //获取解析后的的数据为getData里面的数据
                    //baseBean.getData ()
                    HttpHeaders headers = new HttpHeaders();
                    headers.put("app-token", baseBean.getData().getApptoken());
                    OkGo.getInstance().addCommonHeaders(headers);
                    SPUtils.put(Constant.SP_DEFAULT_TOKEN, baseBean.getData().getApptoken());
                    init();
                } else {
                    UIhelper.ToastMessage(baseBean.getInfo());
                }


            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(SplashActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void init() {
        // 加载页
//        AppContext.isFrist = getSharedPreferences("frist", MODE_PRIVATE)
//                .getBoolean("is", true);
        if (!SPUtils.isFirstInstall()) {
            getAds();
        } else {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        }
    }

    private void getAds() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("position_id", "7");//	1表示当日保存的第一次，0表示当日以保存，没有则默认当日以保存
        OkGo.<String>get(URLs.ADVERTISE).params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //解析data里面为数组的形式，用的baseListBean基本类
                        Type type = new TypeToken<BaseListBean<Adsbean>>() {
                        }.getType();
                        final BaseListBean<Adsbean> baseBean = GsonHelper.gson.fromJson(response.body(), type);
                        UIhelper.stopLoadingDialog();
                        //返回码为成功时的处理
                        if (baseBean.getResCode() == 0) {
                            if (baseBean.getData().size() > 0) {
                                Glide.with(SplashActivity.this).load(baseBean.getData().get(0).getPic()).placeholder(R.mipmap.start).into(ivGg);
                                ivGg.setOnClickListener(v -> {
                                    if (!StringUtils.isEmpty(baseBean.getData().get(0).getUrl())) {
                                        UIhelper.addClickAdRecord(SplashActivity.this, baseBean.getData().get(0).getId());
                                        Uri uri = Uri.parse(baseBean.getData().get(0).getUrl());
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                            rpbGg.setVisibility(View.VISIBLE);
                            rpbGg.start();

                        } else {
                            UIhelper.ToastMessage(baseBean.getInfo());
                        }
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        //显示loading框
                        UIhelper.showLoadingDialog(SplashActivity.this);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        UIhelper.stopLoadingDialog();

                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
