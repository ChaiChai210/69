package com.colin.tomvod;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.colin.tomvod.base.BaseActivity;
import com.colin.tomvod.bean.ChildPlayCommentBean;
import com.colin.tomvod.bean.ConFigBean;
import com.colin.tomvod.bean.ConfigureBean;
import com.colin.tomvod.customeview.tabhost.TabItem;
import com.colin.tomvod.customeview.tabhost.XFragmentTabHost;
import com.colin.tomvod.fragment.ChannelFragment;
import com.colin.tomvod.fragment.DiscoverFragment;
import com.colin.tomvod.fragment.HomeFragment;
import com.colin.tomvod.fragment.MineFragment;
import com.colin.tomvod.net.BaseListBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.popwindows.HelpWindow;
import com.colin.tomvod.popwindows.MainPopWindows;
import com.colin.tomvod.utils.Constant;
import com.colin.tomvod.utils.SPUtils;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements HelpWindow.PopwindowsListener {


    @BindView(R.id.main_tab_content)
    FrameLayout mainTabContent;
    @BindView(R.id.main_tabHost)
    XFragmentTabHost mTabHost;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    private String[] mTabTitle = new String[]{"首页", "频道", "发现", "我的"};
    private int[] mImageResId = new int[]{R.drawable.sel_tab_home, R.drawable.sel_tab_channel, R.drawable.sel_tab_discover, R.drawable.sel_tab_mine};
    private Class[] mFragClass = new Class[]{HomeFragment.class, ChannelFragment.class, DiscoverFragment.class, MineFragment.class
    };
    private long mExitTime;
    /**
     * 查询当前配置
     */
    private String config = "", domain = "", introduce = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
//        darkImmerseFontColor();
        checkPermissions();
//        getChannel();
        if (SPUtils.isFirstEnter()) {
            mainContent.post(() -> {
                HelpWindow main_popwindows = new HelpWindow(MainActivity.this, mainContent);
                main_popwindows.setPopwindowsListener(MainActivity.this);
            });
            SPUtils.put(Constant.SP_FIRST_ENTER, false);
        } else {
            config();
        }
        initTabHost();
    }

    private void config() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.CONFIGURE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

//                Type type = new TypeToken<ConFigBean>() {
//                }.getType();
                Type type = new TypeToken<BaseListBean<ConfigureBean>>() {
                }.getType();
                BaseListBean<ConfigureBean> beanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
//                ConFigBean conFigBean = GsonHelper.gson.fromJson(response.body(), type);
                //返回码为成功时的处理
//                if (conFigBean.getCode() == 0) {
//                    for (int i = 0; i < conFigBean.getData().size(); i++) {
//                        //官网地址
//                        if (conFigBean.getData().get(i).getType().equals("webSite")) {
//                            domain = conFigBean.getData().get(i).getContent();
//                        }
////                        "主页弹出提示"
//                        if (conFigBean.getData().get(i).getType().equals("alertText")) {
//                            config = conFigBean.getData().get(i).getContent();
//
//                        }
//                    }
//                    mainContent.post(() -> {
//                        new MainPopWindows(MainActivity.this, mainContent, config, domain);
//                    });
//                } else {
//                    UIhelper.ToastMessage(conFigBean.getInfo());
//                }

                if (beanBaseListBean.getResCode() == 0) {
                    for (int i = 0; i < beanBaseListBean.getData().size(); i++) {
//                        //官网地址
                        if (beanBaseListBean.getData().get(i).getType().equals("webSite")) {
                            domain = beanBaseListBean.getData().get(i).getContent();
                        }
//                        "主页弹出提示"
                        if (beanBaseListBean.getData().get(i).getType().equals("alertText")) {
                            config = beanBaseListBean.getData().get(i).getContent();

                        }
                    }
                } else {
                    FancyToast.makeText(mContext, beanBaseListBean.getInfo(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(MainActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void checkPermissions() {
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO
        };
        List<String> mPermissionList = new ArrayList<>();
        mPermissionList.clear();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permission);
            }
        }

        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
        } else {//请求权限方法
            String[] newpermissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, newpermissions, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 初始化底部菜单
     */
    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_tab_content);

        for (int i = 0; i < mFragClass.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("num", i);
            mTabHost.addTabItem(new TabItem(mTabTitle[i], mImageResId[i]), mFragClass[i], bundle);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (!tabId.equals("发现")) {
                    Message message = new Message();
                    message.what = 1234;
                    EventBus.getDefault().post(message);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void Onclick(int state) {
        switch (state) {
            case 1:
            case 2:
                mTabHost.setCurrentTab(1);
                break;
            case 3:
                mTabHost.setCurrentTab(3);
                break;
            case 4:
//                if (null != verSionBeanBaseBean) {
//                    if (verSionBeanBaseBean.isSuccess()) {
//                        if (!Tools.getAppVersion(AppContext.applicationContext).equals(verSionBeanBaseBean.getData().getVersionName())) {
//                            Cache_Popwindows cache_popwindows =new Cache_Popwindows(MainActivity.this,mainContent);
//                        }
//                    }
//                }
                break;
        }
    }
}
