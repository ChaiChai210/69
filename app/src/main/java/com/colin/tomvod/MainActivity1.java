package com.colin.tomvod;

import android.os.Bundle;

import com.colin.tomvod.base.BaseActivity;
import com.colin.tomvod.base.BaseFragment;
import com.colin.tomvod.base.FragmentAdapter;
import com.colin.tomvod.fragment.HomeFragment;
import com.yinglan.alphatabs.AlphaTabView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity1 extends BaseActivity {
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    @BindView(R.id.vp_container)
    NoScrollViewPager vpContainer;
    @BindView(R.id.tab1)
    AlphaTabView tab1;


//    @BindView(R.id.recycler)
//    BannerLayout recycler;
private List<BaseFragment> list = new ArrayList<>();
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main1;
    }

    @Override
    protected void initView() {

        list.add(new HomeFragment());
        list.add(new HomeFragment());
        vpContainer.setAdapter(new FragmentAdapter<>(getSupportFragmentManager(), list));
//        vpContainer.setOffscreenPageLimit(5);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.vp_container)
    public void onViewClicked() {
    }
}
