package com.colin.playerdemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.SearchResultAdapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.DisconverBean;
import com.colin.playerdemo.bean.SearchBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.utils.StringUtils;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;


public class SearchResultActivity extends BaseActivity {

    @BindView(R.id.search_edt)
    EditText searchEdt;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.cancel_tv)
    TextView cancelTv;
    @BindView(R.id.empty_iv)
    ImageView emptyIv;
    @BindView(R.id.no_layout)
    RelativeLayout noLayout;
    @BindView(R.id.result_rv)
    RecyclerView resultRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    @BindView(R.id.tell_me_layout)
    LinearLayout tell_me_layout;
    @BindView(R.id.ser_tv)
    TextView ser_tv;
    private String search;
    private SearchResultAdapter searchResultAdapter;
    private int page = 1;
    private static final Object PAGE_SIZE = 10;
    private List<SearchBean> searchList = new ArrayList<>();
    private boolean hasMore = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        darkImmerseFontColor();
        if (getIntent().hasExtra("search")) {
            search = getIntent().getStringExtra("search");
        }
        refreshFind.setOnRefreshListener(refreshlayout -> {
            page = 1;
            getSeach();
            refreshFind.finishRefresh(1000);
        });
        refreshFind.setOnLoadMoreListener(refreshLayout -> {
            page++;
            getSeach();
            refreshFind.finishLoadMore();
        });
        searchResultAdapter = new SearchResultAdapter(searchList);
        resultRv.setLayoutManager(new LinearLayoutManager(this));
        resultRv.setAdapter(searchResultAdapter);
        getSeach();
    }


    @OnClick({R.id.tell_me_layout, R.id.cancel_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tell_me_layout:
//                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.cancel_tv:
                search = searchEdt.getText().toString();
                if (StringUtils.isEmpty(search)) {
                    FancyToast.makeText(this, "请输入搜索内容", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                    return;
                }
                getSeach();
                break;
        }

    }


    private void getSeach() {
        RxHttp.setDebug(true);
        RxHttpUtils.getWithToken(Api.searchResult)
                .add("keyword", search)
                .add("page", page)
                .add("page_size", PAGE_SIZE)
                .asDataListParser(SearchBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (s.isEmpty()) {
                        hasMore = false;
                        if (page == 1) {
                            noLayout.setVisibility(View.VISIBLE);
                            ser_tv.setText("没有找到与“" + search + "”相关的结果");
                        }
                    } else {
                        noLayout.setVisibility(View.GONE);
                        if (page == 1) {
//                            discoverList.clear();
//                            discoverList.addAll(s);
                            searchResultAdapter.replaceData(s);
                        } else {
//                            discoverList.addAll(s);
                            searchResultAdapter.addData(s);
                        }
                    }


                }, throwable -> {
                    FancyToast.makeText(this, throwable.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                });

    }
}
