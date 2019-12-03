package com.colin.playerdemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.HistoryActivity;
import com.colin.playerdemo.adapter.Fragment_History_Adapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.HistoryBean;
import com.colin.playerdemo.bean.HistoryBeanCopy;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class Fragment_History extends BaseFragment {
    @BindView(R.id.history_rv)
    RecyclerView historyRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    Fragment_History_Adapter adapter;

    @BindView(R.id.all_change_tv)
    TextView allChangeTv;
    @BindView(R.id.delelte_tv)
    TextView delelteTv;
    @BindView(R.id.cache_bottom_layout)
    LinearLayout cacheBottomLayout;
    @BindView(R.id.no_layout)
    LinearLayout noLayout;
    private Boolean show = false;
    private List<HistoryBean> historyBeans = new ArrayList<>();
    private int page = 1, page_size = 8;
    private boolean hasmore = true;

    private int historyType = 0;
    public Boolean allshow = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public static Fragment_History newInstance(int from) {
        Fragment_History fragment = new Fragment_History();
        Bundle bundle = new Bundle();
        bundle.putInt("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_history;
    }

    public void ChangeClick(Boolean show) {
        this.show = show;
        if (show) {
            cacheBottomLayout.setVisibility(View.VISIBLE);
        } else {
            cacheBottomLayout.setVisibility(View.GONE);
        }
        adapter.setBooleanslsit(show);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        refreshFind.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                hasmore = true;
                page = 1;
                getHistory(historyType);
                refreshFind.finishRefresh(1000);
            }

        });
        refreshFind.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (hasmore) {
                    page++;
                    getHistory(historyType);
                }
                refreshFind.finishLoadMore();
            }
        });
        historyType = getArguments().getInt("from");
        historyRv.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new Fragment_History_Adapter();
        adapter.setFrom(historyType);
        historyRv.setAdapter(adapter);
        getHistory(historyType);
    }


    @OnClick({R.id.all_change_tv, R.id.delelte_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_change_tv:
                allshow = !allshow;
                for (int i = 0; i < HistoryActivity.historybooleanslsit.get(historyType).size(); i++) {
                    HistoryActivity.historybooleanslsit.get(historyType).set(i, allshow);
                }
                adapter.setBooleanslsit(show);
                break;
            case R.id.delelte_tv:
                String id = "";
                for (int i = 0; i < HistoryActivity.historybooleanslsit.get(historyType).size(); i++) {
                    if (HistoryActivity.historybooleanslsit.get(historyType).get(i)) {
                        id = id + historyBeans.get(i).getId() + "_";
                    }
                }
                if (id.length() > 0) {
                    id = id.substring(0, id.length() - 1);
                }
//                deleteHistory(id);
                break;

        }
    }

    private void getHistory(int type) {
        RxHttpUtils.getWithToken(Api.video_watch_record)
                .add("type", type)
                .add("page", page)
                .add("page_size", page_size)
                .asDataListParser(HistoryBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {
                    setHistory(s);

                }, throwable -> {
                });


    }

    private void setHistory(List<HistoryBean> s) {
        if (page == 1) {
            HistoryActivity.historybooleanslsit.get(historyType).clear();
            historyBeans = s;
        } else {
            historyBeans.addAll(s);
        }
        for (int i = 0; i < historyBeans.size(); i++) {
            HistoryActivity.historybooleanslsit.get(historyType).add(false);
        }
        adapter.setData(historyBeans);
        if (historyBeans.size() == 0) {
            cacheBottomLayout.setVisibility(View.GONE);
            noLayout.setVisibility(View.VISIBLE);
        } else {
            noLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 删除观看记录
     */

//    private void deleteHistory(String id) {
//        HttpParams httpParams = new HttpParams();
//        String url = URLs.RECORDS;
//        url = url + "/" + id;
//        OkGo.<String>delete(url).params(httpParams).execute(new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//                //解析data里面为数组的形式，用的baseListBean基本类
//                Type type = new TypeToken<BaseBean>() {
//                }.getType();
//                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);
//                UIhelper.stopLoadingDialog();
//
//                //返回码为成功时的处理
//                if (baseBean.getCode() == 0) {
//                    hasmore = true;
//                    page = 1;
//                    getHistory(historyType);
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onStart(Request<String, ? extends Request> request) {
//                super.onStart(request);
//                //显示loading框
//                UIhelper.showLoadingDialog(activity);
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                UIhelper.stopLoadingDialog();
//
//            }
//        });
//    }
}
