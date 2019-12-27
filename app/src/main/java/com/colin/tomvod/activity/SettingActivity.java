package com.colin.tomvod.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.colin.tomvod.AppContext;
import com.colin.tomvod.R;
import com.colin.tomvod.base.CommonImmerseActivity;
import com.colin.tomvod.bean.UVersionBean;
import com.colin.tomvod.net.BaseBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.popwindows.Clean_Cache_Popwindows;
import com.colin.tomvod.utils.AppUtils;
import com.colin.tomvod.utils.DataCleanManager;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends CommonImmerseActivity implements OnDownloadListener,OnButtonClickListener {
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


    private UVersionBean bean;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        darkImmerseFontColor();
        cashTv.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
        tvCenter.setText("设置");
//        if (!Login_Activity.checkLogin(this)) {
//            finish();
//        }
        versionTv.setText(String.format("当前版本 V%s", AppUtils.getAppVersion(AppContext.applicationContext)));
        configversion();
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
//                configversion();
                checkUpdate();

                break;

        }
    }

    private void checkUpdate() {
        //这里简单判断
        if (AppUtils.getAppVersion(AppContext.applicationContext).equals(bean.getVersion())) {
            FancyToast.makeText(this, "已经是最新版本", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
        } else {
//                        Uri uri = Uri.parse(bean.getAndroid_update_url());
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
//            DownloadManager manager = DownloadManager.getInstance(SettingActivity.this);
//            manager.setApkName("appupdate.apk")
//                    .setApkUrl("https://raw.githubusercontent.com/azhon/AppUpdate/master/apk/appupdate.apk")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .download();
            showUpdateDialog();

        }
    }
    private DownloadManager manager;

    private void showUpdateDialog() {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                //.setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //设置对话框强制更新时进度条和文字的颜色
                //.setDialogProgressBarColor(Color.parseColor("#E743DA"))
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置是否提示后台下载toast
                .setShowBgdToast(false)
                //设置强制更新
                .setForcedUpgrade(true)
                //设置对话框按钮的点击监听
                .setButtonClickListener(this)
                //设置下载过程的监听
                .setOnDownloadListener(this);

        manager = DownloadManager.getInstance(this);
//        manager.setApkName("ESFileExplorer.apk")
//                .setApkUrl(bean.getAndroid_update_url())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setShowNewerToast(true)
//                .setConfiguration(configuration)
//                .setAuthorities(getPackageName())
//                .setApkDescription(bean.getContent())
////                .setApkMD5("DC501F04BBAA458C9DC33008EFED5E7F")
//                .download();

        manager.setApkName("69视频.apk")
                .setApkUrl(bean.getAndroid_update_url())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(true)
                .setConfiguration(configuration)
                .setApkVersionCode(bean.getAndroid_apkVersionCode())
                .setApkVersionName("1.2")
                .setAuthorities(getPackageName())
                .setApkDescription(bean.getContent())
//                .setApkMD5("DC501F04BBAA458C9DC33008EFED5E7F")
                .download();
    }
//    private String url = "https://f29addac654be01c67d351d1b4282d53.dd.cdntips.com/imtt.dd.qq.com/16891/DC501F04BBAA458C9DC33008EFED5E7F.apk?mkey=5d6d132d73c4febb&f=0c2f&fsname=com.estrongs.android.pop_4.2.0.2.1_10027.apk&csr=1bbd&cip=115.196.216.78&proto=https";
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
                    bean = baseBean.getData();
                    nowVersionTv.setText(String.format("最新版本为:%s", bean.getVersion()));
                } else {
                    FancyToast.makeText(SettingActivity.this, "已经是最新版本", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(SettingActivity.this);
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

    @Override
    public void onButtonClick(int id) {
        Log.e("chai", String.valueOf(id));
    }

    @Override
    public void start() {

    }

    @Override
    public void downloading(int max, int progress) {

    }

    @Override
    public void done(File apk) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void error(Exception e) {

    }
}
