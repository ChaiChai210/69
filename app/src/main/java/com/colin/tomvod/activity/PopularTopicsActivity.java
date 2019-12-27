package com.colin.tomvod.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.tomvod.R;
import com.colin.tomvod.adapter.PopularTopicsAdapter;
import com.colin.tomvod.base.CommonImmerseActivity;
import com.colin.tomvod.bean.HotBean;
import com.colin.tomvod.net.BaseListBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PopularTopicsActivity extends CommonImmerseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.hot_dis_rv)
    RecyclerView hotDisRv;

    PopularTopicsAdapter adapter;
    List<HotBean> hotBeans = new ArrayList<>();


    @OnClick({R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }


    /**
     * 获取热门
     */

    private void getHot() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.HOTSUB).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<HotBean>>() {
                }.getType();
                BaseListBean<HotBean> baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();

                //返回码为成功时的处理
                if (baseBean.getResCode() == 0) {
                    hotBeans.clear();
                    hotBeans.addAll(baseBean.getData());
                    adapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(PopularTopicsActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_popular_topics;
    }

    @Override
    protected void initView() {
        tvCenter.setText("热门专题");

        hotDisRv.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new PopularTopicsAdapter(hotBeans);
        hotDisRv.setAdapter(adapter);
        getHot();
    }
}
