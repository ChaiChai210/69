package com.colin.playerdemo;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.CodeTokenbean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.BaseResponseBean;
import com.colin.playerdemo.net.CommonParser;
import com.colin.playerdemo.utils.Constant;
import com.colin.playerdemo.utils.SPUtils;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_gg)
    ImageView ivGg;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        if (TextUtils.isEmpty(SPUtils.getToken())) {
            getToken();
        } else {
            init();
        }
//        loadGotoGg.setOnClickListener(v -> {
//            Intent intent = new Intent(SplashActivity.this, MainActivity1.class);
//            startActivity(intent);
//            finish();
//        });
    }

    private void init() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void getToken() {
        Log.e("chia", "request token");
        RxHttp.setDebug(true);
        RxHttp.get(Api.token)
                .asParser(new CommonParser<CodeTokenbean>(new TypeToken<BaseResponseBean<CodeTokenbean>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    Log.e("chai",s.getData().getApptoken());
                    if (s.getCode() == 0) {
                        SPUtils.put(Constant.SP_LOGIN_TOKEN, s.getData().getApptoken());
                        init();
                    } else {
//                        UIHelper.errorToastString(s.getInfo());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }


}
