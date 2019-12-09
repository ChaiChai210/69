package com.colin.playerdemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.Fragment_Class_Adapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.SearchBean;
import com.colin.playerdemo.net.BaseListBean;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;


public class Fragment_Class extends BaseFragment {
    Activity activity;
    @BindView(R.id.history_rv)
    RecyclerView historyRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    @BindView(R.id.no_layout)
    RelativeLayout noLayout;
    Fragment_Class_Adapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public static Fragment_Class newInstance(int from, String ranking) {
        Fragment_Class fragment = new Fragment_Class();
        Bundle bundle = new Bundle();
        bundle.putInt("from", from);
        bundle.putString("ranking", ranking);
        fragment.setArguments(bundle);
        return fragment;
    }

    private boolean isViewInitFinished = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitFinished = true;
        requestData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        requestData();
    }

    public void requestData() {
        if (isViewInitFinished && isAdded()) {
            adapter = new Fragment_Class_Adapter();
            historyRv.setLayoutManager(new GridLayoutManager(activity, 2));
            historyRv.setAdapter(adapter);
            refreshFind.autoRefresh();
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_class;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        refreshFind.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshFind.setVisibility(View.VISIBLE);
                page = 1;
                getSeach();
                refreshFind.finishRefresh(1000);
            }

        });
        refreshFind.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (Hasmore) {
                    page++;
                    getSeach();
                    refreshFind.finishLoadMore();
                }

            }
        });

    }

    private String ranking, classify;

    public void setRanking(String ranking, String classify) {
        this.ranking = ranking;
        this.classify = classify;
        page = 1;
        getSeach();
    }

    /**
     * 全局搜索
     */
    BaseListBean<SearchBean> searchBeanBaseListBean;
    private int page = 1;
    private boolean Hasmore = true;
    private List<SearchBean> list;

    private void getSeach() {
        HttpParams httpParams = new HttpParams();
        if (StringUtils.isEmpty(ranking)) {
            httpParams.put("ranking", getArguments().getString("ranking"));
        } else {
            httpParams.put("ranking", ranking);
        }
        if (StringUtils.isEmpty(classify)) {
            httpParams.put("classify", getArguments().getInt("from"));
        } else {
            httpParams.put("classify", classify);
        }
        httpParams.put("page_size", "20");
        httpParams.put("page", page);
        OkGo.<String>get(URLs.SEARCH).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<SearchBean>>() {
                }.getType();
                searchBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                //返回码为成功时的处理
                if (searchBeanBaseListBean.getResCode() == 0) {
                    Hasmore = searchBeanBaseListBean.getData().size() >= 20;
                    if (page == 1) {
                        list = searchBeanBaseListBean.getData();
                    } else {
                        list.addAll(searchBeanBaseListBean.getData());
                    }
                    adapter.setList(list);
                } else {
                    UIhelper.ToastMessage(searchBeanBaseListBean.getInfo());
                    noLayout.setVisibility(View.VISIBLE);
                    refreshFind.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                noLayout.setVisibility(View.VISIBLE);
                refreshFind.setVisibility(View.INVISIBLE);
            }
        });
    }



}
