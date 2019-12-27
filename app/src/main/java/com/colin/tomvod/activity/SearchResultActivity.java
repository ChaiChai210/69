package com.colin.tomvod.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.tomvod.R;
import com.colin.tomvod.adapter.SearchResultAdapter;
import com.colin.tomvod.base.BaseActivity;
import com.colin.tomvod.bean.SearchBean;
import com.colin.tomvod.net.BaseListBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.utils.StringUtils;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
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
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    private String search;
    private SearchResultAdapter searchResultAdapter;
    private int page = 1;
    private List<SearchBean> searchList = new ArrayList<>();
    private boolean hasMore = true;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarView(statusBarView)
                .statusBarColorInt(getResources().getColor(R.color.full_transparent)).statusBarDarkFont(false, 1f)
                .navigationBarColor(R.color.colorPrimary)
                .keyboardEnable(true)
                .init();
    }

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
            if (hasMore) {
                page++;
                getSeach();
                refreshFind.finishLoadMore();
            } else {
                refreshFind.finishLoadMoreWithNoMoreData();
            }
        });
        searchResultAdapter = new SearchResultAdapter(searchList);
        resultRv.setLayoutManager(new LinearLayoutManager(this));
        resultRv.setAdapter(searchResultAdapter);
        getSeach();
    }


    @OnClick({R.id.tell_me_layout, R.id.cancel_tv,R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tell_me_layout:
                startActivity(new Intent(this, FeedbackActivity.class));
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
        HttpParams httpParams = new HttpParams();
        httpParams.put("keyword", search);
        httpParams.put("page", page);
        httpParams.put("page_size", 10);
        OkGo.<String>get(URLs.SEARCH).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<SearchBean>>() {
                }.getType();
                BaseListBean<SearchBean> searchBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                searchList = searchBeanBaseListBean.getData();
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (searchList.isEmpty()) {
                    noLayout.setVisibility(View.VISIBLE);
                    ser_tv.setText("没有找到与“" + search + "”相关的结果");
                } else {
                    if (searchList.size() < 10) {
                        hasMore = false;
                    }
                    noLayout.setVisibility(View.GONE);
                    if (page == 1) {
//                            discoverList.clear();
//                            discoverList.addAll(s);
                        searchResultAdapter.replaceData(searchList);
                    } else {
//                            discoverList.addAll(s);
                        searchResultAdapter.addData(searchList);
                    }
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(SearchResultActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                noLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
