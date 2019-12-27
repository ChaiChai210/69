package com.colin.tomvod.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.colin.tomvod.R;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends ImmersionFragment  implements LifecycleOwner {
    public abstract int getContentViewId();

    /**
     * 所在activity
     */
    protected Activity activity;
    protected View mRootView;

    private Unbinder unbinder;
    protected View statusBarView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getContentViewId(), container, false);
        } else {
            ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }
        }
        return mRootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        statusBarView = view.findViewById(R.id.status_bar_view);
        fitsLayoutOverlap();
        initAllMembersView(savedInstanceState);
    }


    protected abstract void initAllMembersView(Bundle savedInstanceState);


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();//解绑
    }


    /**
     * eventbus 消息传递
     *
     * @param message 消息内容
     */
    @Subscribe
    public void onEventRefresh(Message message) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //旋转屏幕为什么要重新设置布局与状态栏重叠呢？因为旋转屏幕有可能使状态栏高度不一样，如果你是使用的静态方法修复的，所以要重新调用修复
        fitsLayoutOverlap();

    }

    private void fitsLayoutOverlap() {
        if (statusBarView != null) {
            ImmersionBar.setStatusBarView(this, statusBarView);
        }
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColorInt(getResources().getColor(R.color.full_transparent)).statusBarDarkFont(false, 1f)
                .navigationBarColor(R.color.colorPrimary)
                .init();
    }

    /**
     * 子类可通过此方法直接拿到VideoViewManager
     */
    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
}
