package com.colin.playerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.colin.playerdemo.MainActivity;
import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.customeview.guide.CallBack;
import com.colin.playerdemo.customeview.guide.GuideCustomViews;
import com.colin.playerdemo.utils.Constant;
import com.colin.playerdemo.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity implements CallBack {

    private final int[] mPageImages = {
            R.mipmap.luancher_1,
            R.mipmap.luancher_2,
            R.mipmap.luancher_3,
            R.mipmap.luancher_4
    };

    private final int[] mGuidePoint = {
            R.mipmap.icon_guide_point_select,
            R.mipmap.icon_guide_point_unselect
    };
    @BindView(R.id.guide_CustomView)
    GuideCustomViews guideCustomView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        guideCustomView.setData(mPageImages, mGuidePoint, this);
    }


    @Override
    public void callSlidingPosition(int position) {
        Log.e("callSlidingPosition", "滑动位置 callSlidingPosition " + position);
    }

    @Override
    public void callSlidingLast() {
        Log.e("callSlidingLast", "滑动到最后一个callSlidingLast");
    }

    @Override
    public void onClickLastListener() {
        SPUtils.put(Constant.SP_FIRST_INSTALL, false);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        guideCustomView.clear();
    }


}
