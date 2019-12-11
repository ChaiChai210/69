package com.colin.playerdemo.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.base.CommonImmerseActivity;
import com.colin.playerdemo.customeview.CustomViewPager;
import com.colin.playerdemo.fragment.AnnouncementFragment;
import com.colin.playerdemo.fragment.MessageFragment;
import com.colin.playerdemo.fragment.My_Feedback_Fragment;
import com.colin.playerdemo.fragment.Need_Feedback_Fragment;
import com.colin.playerdemo.fragment.ProblemFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class FeedbackActivity extends CommonImmerseActivity {
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

    String[] tabs = {"常见问题", "我要反馈","我的反馈"};

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        activityTitleIncludeCenterTv.setText("反馈");
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
        mFragments.add(new ProblemFragment());
        mFragments.add(new Need_Feedback_Fragment());
        mFragments.add(new My_Feedback_Fragment());
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


}
