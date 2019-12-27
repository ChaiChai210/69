package com.colin.tomvod.base;

import android.view.View;

import com.colin.tomvod.R;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;


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
