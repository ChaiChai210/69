package com.colin.tomvod.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.colin.tomvod.R;
import com.colin.tomvod.base.BaseFragment;
import com.colin.tomvod.customeview.CustomViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class ChannelFragment extends BaseFragment {
    @BindView(R.id.channel_tl)
    SlidingTabLayout channelTl;
    @BindView(R.id.channel_vp)
    CustomViewPager channelVp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    public int getContentViewId() {
        return R.layout.fragment_channel;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        initTab();
    }

    private void initTab() {
        String[] tabs = {"专栏推荐","标签筛选"};
        mFragments.add(new ColumnFragment());
        mFragments.add(new LabelFragment());
        // 定义 SlidingTabLayout
        channelTl.setViewPager(channelVp, tabs, getActivity(), mFragments);
        channelTl.setIndicatorHeight(2);
        channelTl.setIndicatorColor(Color.parseColor("#C39B69"));
        channelTl.setIndicatorWidth(20);
        channelVp.setPagingEnabled(false);
    }


}
