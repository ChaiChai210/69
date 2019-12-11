package com.colin.playerdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.colin.playerdemo.R;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class CommonImmerseActivity extends BaseActivity {

    @BindView(R.id.status_bar_view)
    View statusBarView;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarView(statusBarView)
                .statusBarColorInt(getResources().getColor(R.color.full_transparent)).statusBarDarkFont(false, 1f)
                .navigationBarColor(R.color.colorPrimary)
                .keyboardEnable(true)
                .init();
    }
}
