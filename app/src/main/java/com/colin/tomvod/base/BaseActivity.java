package com.colin.tomvod.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.colin.tomvod.R;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
//    private NetworkChangeReceiver receiver;
//    protected LoadingDialog loadingDialog;
    private Unbinder mUnbinder;

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        //初始化沉浸式
        initImmersionBar();

        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusHelper.register(this);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
//        }
//        StatusBarUtil.setLightMode(this);


//        registerNetworkChangeReceiver();
        initView();
        initData();
    }



    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected void initData() {

    }


    /**
     * 注册网络监听广播
     */
//    private void registerNetworkChangeReceiver() {
//        receiver = new NetworkChangeReceiver(this);
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(receiver, filter);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (receiver != null) {
//            unregisterReceiver(receiver);
//            receiver.onDestroy();
//            receiver = null;
//        }
        // 取消注册
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusHelper.unregister(this);
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    //状态栏字体颜色为暗灰色
    public void darkImmerseFontColor() {
        if (mImmersionBar != null) {
//            if (tm) {
//                mImmersionBar.statusBarColorInt(getResources().getColor(R.color.full_transparent));
//            } else {
//                mImmersionBar.statusBarColorInt(getResources().getColor(R.color.transparent));
//            }
            mImmersionBar.statusBarColorInt(getResources().getColor(R.color.full_transparent));
            mImmersionBar.statusBarDarkFont(false, 1f).init();
        }
    }

//    public void showFragment(BaseDialogFragment fm) {
//        fm.show(getSupportFragmentManager(), fm.getClass().getName());
//    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        mImmersionBar = ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary);
        mImmersionBar.init();
    }
}
