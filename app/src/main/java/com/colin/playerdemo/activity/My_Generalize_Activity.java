package com.colin.playerdemo.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.My_Generalize_Adapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.GeneraListBean;
import com.colin.playerdemo.net.BaseListBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class My_Generalize_Activity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.tv_center)
    TextView activityTitleIncludeCenterTv;
    @BindView(R.id.tv_right)
    TextView activityTitleIncludeRightTv;
    @BindView(R.id.iv_right)
    ImageView activityTitleIncludeRightIv;
    @BindView(R.id.gener_rv)
    RecyclerView generRv;
    My_Generalize_Adapter adapter;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;

    private int page = 1;
    private boolean hasNext = true;
    private List<GeneraListBean> list = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_generalize;
    }

    @Override
    protected void initView() {
        activityTitleIncludeCenterTv.setText("我的推广");
        generRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new My_Generalize_Adapter();
        generRv.setAdapter(adapter);
        refreshFind.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                hasNext = true;
                getMyPromotion();
                refreshFind.finishRefresh(1000);
            }
        });
        refreshFind.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (hasNext) {
                    page++;
                    getMyPromotion();
                }
                refreshFind.finishLoadMore();
            }
        });
    }

    @Override
    public void initData() {

        getMyPromotion();
    }


    @OnClick({R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }


    private void getMyPromotion() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page_size", 10);
        httpParams.put("page", page);
        OkGo.<String>get(URLs.INVITE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<BaseListBean<GeneraListBean>>() {
                }.getType();
                BaseListBean<GeneraListBean> beanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);

                //返回码为成功时的处理
                if (beanBaseListBean.getResCode() == 0) {
                    if (page == 1) {
                        list.clear();
                    }
                    list.addAll(beanBaseListBean.getData());
                    if (beanBaseListBean.getData().size() < 10) {
                        hasNext = false;
                    }
                    adapter.setListBeans(list);
                } else {
                    UIhelper.ToastMessage(beanBaseListBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(My_Generalize_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }
}
