package com.colin.playerdemo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.banner.BannerLayout;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.PopularTopicsActivity;
import com.colin.playerdemo.adapter.ColumnHotTopicAdapter;
import com.colin.playerdemo.adapter.ColumnPopularStarAdapter;
import com.colin.playerdemo.adapter.ColumnRecommentAdapter;
import com.colin.playerdemo.adapter.Fragment_Column_Adapter;
import com.colin.playerdemo.adapter.HomeBannerAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.Column_Bean;
import com.colin.playerdemo.bean.StarBean;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


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
//    ColumnPopularStarAdapter popularStarAdapter;
//        Column_Class_Adapter column_class_adapter;
    Fragment_Column_Adapter column_adapter;
    HomeBannerAdapter webBannerAdapter;
    BaseBean<Column_Bean> baseBean;

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
        column_adapter = new Fragment_Column_Adapter();
        popularityRv.setNestedScrollingEnabled(false);
        popularityRv.setAdapter(column_adapter);
        getChannel();
    }

    private void getChannel() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.CHANNEL).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<Column_Bean>>() {
                }.getType();
                baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();

                //返回码为成功时的处理
                if (baseBean.getCode() == 200) {
                    setView(baseBean.getData());
                } else {
                    FancyToast.makeText(activity, baseBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    @OnClick({R.id.hot_relayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hot_relayout:
                activity.startActivity(new Intent(activity, PopularTopicsActivity.class));
                break;

        }
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

        List<StarBean> list = new ArrayList<>();
        for (String s1 : baseBean.getData().getStar().keySet()) {//遍历map的键
            list.add(baseBean.getData().getStar().get(s1));
        }
        column_adapter.setList(list);
//        starBeans.clear();
//        starBeans.addAll(data.getStar());


    }

    @Override
    public void onItemClick(int position) {
        if (!StringUtils.isEmpty(baseBean.getData().getAdvertise().get(position).getUrl())) {
            UIhelper.addClickAdRecord(activity, baseBean.getData().getAdvertise().get(position).getId());
            Uri uri = Uri.parse(baseBean.getData().getAdvertise().get(position).getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }

}
