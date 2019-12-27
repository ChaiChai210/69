package com.colin.tomvod.activity;

import android.content.Intent;
import android.util.Log;

import com.colin.tomvod.MainActivity;
import com.colin.tomvod.R;
import com.colin.tomvod.base.BaseActivity;
import com.colin.tomvod.customeview.guide.CallBack;
import com.colin.tomvod.customeview.guide.GuideCustomViews;
import com.colin.tomvod.utils.Constant;
import com.colin.tomvod.utils.SPUtils;

import butterknife.BindView;

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
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (guideCustomView != null) {
            guideCustomView.clear();
        }
    }


}
