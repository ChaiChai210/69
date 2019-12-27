package com.colin.tomvod.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.colin.tomvod.R;
import com.colin.tomvod.base.CommonImmerseActivity;
import com.colin.tomvod.customeview.CustomViewPager;
import com.colin.tomvod.fragment.AnnouncementFragment;
import com.colin.tomvod.fragment.MessageFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NotificationActivity extends CommonImmerseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.tv_center)
    TextView activityTitleIncludeCenterTv;
    @BindView(R.id.tv_right)
    TextView activityTitleIncludeRightTv;
    @BindView(R.id.iv_right)
    ImageView activityTitleIncludeRightIv;
    @BindView(R.id.chat_room_start_tl)
    SlidingTabLayout chatRoomStartTl;
    @BindView(R.id.chat_room_vp)
    CustomViewPager chatRoomVp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    String[] tabs = {"公告", "消息"};

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        activityTitleIncludeCenterTv.setText("通知");
        darkImmerseFontColor();
        initTab();
    }

    @OnClick({R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }


    private void initTab() {

        //添加其他分类fragment
        mFragments.add(new AnnouncementFragment());
        mFragments.add(new MessageFragment());
        // 定义 SlidingTabLayout
        chatRoomStartTl.setViewPager(chatRoomVp, tabs, this, mFragments);
        chatRoomStartTl.setIndicatorHeight(2);
        chatRoomStartTl.setIndicatorColor(Color.parseColor("#C39B69"));
        chatRoomStartTl.setIndicatorWidth(30);
//        chatRoomStartTl.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                item = position;
//
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
