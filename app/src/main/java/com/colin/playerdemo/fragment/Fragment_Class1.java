package com.colin.playerdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.VideoClassifyAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.SearchBean;
import com.colin.playerdemo.net.BaseListBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Fragment_Class1 extends BaseFragment {

    @BindView(R.id.history_rv)
    RecyclerView historyRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    @BindView(R.id.empty_iv)
    ImageView emptyIv;
    @BindView(R.id.tell_me_layout)
    LinearLayout tellMeLayout;
    @BindView(R.id.no_layout)
    RelativeLayout noLayout;

    VideoClassifyAdapter videoClassifyAdapter;
    private String ranking, classify;
    BaseListBean<SearchBean> searchBeanBaseListBean;
    private int page = 1;
    private boolean Hasmore = true;
    private List<SearchBean> searchBeans = new ArrayList<>();

    public static Fragment_Class1 newInstance(int from, String ranking) {
        Fragment_Class1 fragment = new Fragment_Class1();
        Bundle bundle = new Bundle();
        bundle.putInt("from", from);
        bundle.putString("ranking", ranking);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_class;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        refreshFind.setOnRefreshListener(refreshlayout -> {
            refreshFind.setVisibility(View.VISIBLE);
            page = 1;
            getSeach();
            refreshFind.finishRefresh(1000);
        });
        refreshFind.setOnLoadMoreListener(refreshLayout -> {
            if (Hasmore) {
                page++;
                getSeach();
                refreshFind.finishLoadMore();
            } else {
                refreshFind.finishLoadMoreWithNoMoreData();
            }

        });
        videoClassifyAdapter = new VideoClassifyAdapter(searchBeans);
        historyRv.setLayoutManager(new GridLayoutManager(activity, 2));
        historyRv.setAdapter(videoClassifyAdapter);
        refreshFind.autoRefresh();
    }

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
                    if (searchBeanBaseListBean.getData().size() < 20) {
                        Hasmore = false;
                    }
                    if (page == 1) {
                        searchBeans = searchBeanBaseListBean.getData();
                    } else {
                        searchBeans.addAll(searchBeanBaseListBean.getData());
                    }
                    videoClassifyAdapter.notifyDataSetChanged();
                } else {
//                    UIhelper.ToastMessage(searchBeanBaseListBean.getInfo());
//                    noLayout.setVisibility(View.VISIBLE);
//                    refreshFind.setVisibility(View.INVISIBLE);
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
//                noLayout.setVisibility(View.VISIBLE);
//                refreshFind.setVisibility(View.INVISIBLE);
            }
        });
    }
}
