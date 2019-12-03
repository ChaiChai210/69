package com.colin.playerdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.banner.BannerLayout;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.ColumnHotTopicAdapter;
import com.colin.playerdemo.adapter.ColumnPopularStarAdapter;
import com.colin.playerdemo.adapter.ColumnRecommentAdapter;
import com.colin.playerdemo.adapter.HomeBannerAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.Column_Bean;
import com.colin.playerdemo.bean.StarBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.BaseResponseBean;
import com.colin.playerdemo.net.CommonParser;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;


public class ColumnFragment extends BaseFragment implements BannerLayout.OnBannerItemClickListener {
    @BindView(R.id.channel_h_rv)
    RecyclerView channelHRv;
    @BindView(R.id.banner)
    BannerLayout bannerLayout;
    @BindView(R.id.hot_relayout)
    RelativeLayout hotRelayout;
    @BindView(R.id.class_rv)
    RecyclerView classRv;
    @BindView(R.id.popularity_rv)
    RecyclerView popularityRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;

    ColumnRecommentAdapter columnRecommentAdapter;
    ColumnHotTopicAdapter hotTopicAdapter;
    ColumnPopularStarAdapter popularStarAdapter;
    //    Column_Class_Adapter column_class_adapter;
//    Fragment_Column_Adapter column_adapter;
    HomeBannerAdapter webBannerAdapter;
//    BaseBean<Column_Bean> baseBean;

    List<Column_Bean.TjtopicBean> recommendList = new ArrayList<>();
    List<Column_Bean.HottopicBean> hotTopicBeans = new ArrayList<>();
    List<StarBean> starBeans = new ArrayList<>();

    @Override
    public int getContentViewId() {
        return R.layout.fragment_column;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        refreshFind.setOnRefreshListener(refreshlayout -> {
            getChannel();
            refreshFind.finishRefresh(1000);
        });
        refreshFind.setOnLoadMoreListener(refreshLayout -> refreshFind.finishLoadMore());

        columnRecommentAdapter = new ColumnRecommentAdapter(recommendList);
        channelHRv.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        channelHRv.setNestedScrollingEnabled(false);
        channelHRv.setAdapter(columnRecommentAdapter);

        classRv.setNestedScrollingEnabled(false);
        hotTopicAdapter = new ColumnHotTopicAdapter(hotTopicBeans);
        classRv.setLayoutManager(new GridLayoutManager(activity, 4));
        classRv.setAdapter(hotTopicAdapter);

        popularityRv.setLayoutManager(new LinearLayoutManager(activity));
        popularStarAdapter = new ColumnPopularStarAdapter(starBeans);
        popularityRv.setNestedScrollingEnabled(false);
        popularityRv.setAdapter(popularStarAdapter);
        getChannel();
    }

    @OnClick({R.id.hot_relayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hot_relayout:
//                activity.startActivity(new Intent(activity, Hot_Ddissertation_Activity.class));
                break;

        }
    }


    private void getChannel() {
        RxHttp.setDebug(true);
        RxHttpUtils.getWithToken(Api.channel)
                .asParser(new CommonParser<Column_Bean>(new TypeToken<BaseResponseBean<Column_Bean>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (s.getCode() == 200) {
                        setView(s.getData());
                    } else {
                        UIhelper.ToastMessage(s.getInfo());
                    }

                }, throwable -> {
                });

    }

    private void setView(Column_Bean data) {
        recommendList.clear();
        recommendList.addAll(data.getTjtopic());
        columnRecommentAdapter.notifyDataSetChanged();
        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(data.getAdvertise());
        bannerLayout.setAdapter(bannerAdapter);
        hotTopicBeans.clear();
        hotTopicBeans.addAll(data.getHottopic());
        hotTopicAdapter.notifyDataSetChanged();
//        starBeans.clear();
//        starBeans.addAll(data.getStar());
//        hotTopicAdapter.notifyDataSetChanged();


    }

    @Override
    public void onItemClick(int position) {
//        if (!Tools.isEmpty(baseBean.getData().getAdvertise().get(position).getUrl())) {
//            ADDAds(baseBean.getData().getAdvertise().get(position).getId()+"");
//            Uri uri = Uri.parse( baseBean.getData().getAdvertise().get(position).getUrl());
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//        }

    }

}
