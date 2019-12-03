package com.colin.playerdemo;

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


import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.customeview.tabhost.TabItem;
import com.colin.playerdemo.customeview.tabhost.XFragmentTabHost;
import com.colin.playerdemo.fragment.ChannelFragment;
import com.colin.playerdemo.fragment.DiscoverFragment;
import com.colin.playerdemo.fragment.HomeFragment;
import com.colin.playerdemo.fragment.MineFragment;
import com.colin.playerdemo.fragment.RecyclerViewFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_tab_content)
    FrameLayout mainTabContent;
    @BindView(R.id.main_tabHost)
    XFragmentTabHost mTabHost;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    private String[] mTabTitle = new String[]{"首页", "频道", "发现", "我的"};
    private int[] mImageResId = new int[]{R.drawable.sel_tab_home, R.drawable.sel_tab_channel, R.drawable.sel_tab_discover, R.drawable.sel_tab_mine};
    private Class[] mFragClass = new Class[]{HomeFragment.class, ChannelFragment.class, RecyclerViewFragment.class, MineFragment.class
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
        if (AppContext.isFrist) {
            mainContent.post(new Runnable() {
                @Override
                public void run() {
//                    HelpWindow main_popwindows = new HelpWindow(MainActivity.this, mainContent);
//                    main_popwindows.setPopwindowsListener(MainActivity.this);
                }
            });

        } else {
//            config();
        }
        //todo这段逻辑不用加
//        if (!Login_Activity.isLogin(this)) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.put("app-token", Tools.getSharedPreferencesValues(MainActivity.this, "app-token"));
//            OkGo.getWithToken().addCommonHeaders(headers);
//            initTabHost();
//        } else {
//            initTabHost();
//        }
        initTabHost();
    }

//    @Override
//    protected void initImmersionBar() {
//        super.initImmersionBar();
//        darkImmerseFontColor();
//    }

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
        switch (requestCode) {
            case 0://定位
                break;
            case 1://
                break;
            case 2:
                break;
            case 3://读取和写入文件
                break;
        }
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


//    @Override
//    public void Onclick(int state) {
//        switch (state) {
//            case 1:
//            case 2:
//                mTabHost.setCurrentTab(1);
//                break;
//            case 3:
//                mTabHost.setCurrentTab(3);
//                break;
//            case 4:
////                if (null != verSionBeanBaseBean) {
////                    if (verSionBeanBaseBean.isSuccess()) {
////                        if (!Tools.getAppVersion(AppContext.applicationContext).equals(verSionBeanBaseBean.getData().getVersionName())) {
////                            Cache_Popwindows cache_popwindows =new Cache_Popwindows(MainActivity.this,mainContent);
////                        }
////                    }
////                }
//                break;
//        }
//    }
}
