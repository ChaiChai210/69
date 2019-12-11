package com.colin.playerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colin.playerdemo.AppContext;
import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.base.CommonImmerseActivity;
import com.colin.playerdemo.bean.UVersionBean;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.popwindows.Clean_Cache_Popwindows;
import com.colin.playerdemo.utils.AppUtils;
import com.colin.playerdemo.utils.DataCleanManager;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class U_Set_activity extends CommonImmerseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.id_manage_layout)
    RelativeLayout idManageLayout;
    @BindView(R.id.cash_tv)
    TextView cashTv;
    @BindView(R.id.cache_layout)
    RelativeLayout cacheLayout;
    @BindView(R.id.version_tv)
    TextView versionTv;
    @BindView(R.id.now_version_tv)
    TextView nowVersionTv;
    @BindView(R.id.versions_layout)
    RelativeLayout versionsLayout;
    @BindView(R.id.protocol_layout)
    RelativeLayout protocolLayout;
    @BindView(R.id.set_activity)
    LinearLayout setActivity;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }
    @Override
    protected void initView() {
        darkImmerseFontColor();
        cashTv.setText(DataCleanManager.getTotalCacheSize(U_Set_activity.this));
        tvCenter.setText("设置");
//        if (!Login_Activity.checkLogin(this)) {
//            finish();
//        }
        versionTv.setText(String.format("当前版本 V%s", AppUtils.getAppVersion(AppContext.applicationContext)));
    }

    @OnClick({R.id.iv_left, R.id.id_manage_layout, R.id.cache_layout, R.id.protocol_layout, R.id.versions_layout
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.id_manage_layout://账号管理
                startActivity(new Intent(this, Manage_Setting_Activity.class));
                break;
            case R.id.cache_layout://清理缓存
                Clean_Cache_Popwindows clean_cache_popwindows = new Clean_Cache_Popwindows(this, setActivity, cashTv);
                break;
            case R.id.protocol_layout://用户协议
                startActivity(new Intent(this, Protocol_Activity.class));
                break;
            case R.id.versions_layout://版本
                configversion();
                break;

        }
    }

    private void configversion() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("name", "version");
        OkGo.<String>get(URLs.CONFIGURE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<BaseBean<UVersionBean>>() {
                }.getType();
                BaseBean<UVersionBean> baseBean = GsonHelper.gson.fromJson(response.body(), type);
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    UVersionBean bean = baseBean.getData();
                    Log.e("chai", bean.toString());
//                    if (AppUtils.getAppVersion(AppContext.applicationContext).equals(bean.getVersion())) {
//                    } else {
//                        Uri uri = Uri.parse(bean.getAndroid_update_url());
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
//                    }
//
//                } else {
////                    UIhelper.ToastMessage(baseBean.getMsg());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(U_Set_activity.this);
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

    public void finishActivity() {
        finish();
    }
}
