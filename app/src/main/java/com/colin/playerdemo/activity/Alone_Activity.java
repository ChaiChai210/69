package com.colin.playerdemo.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.AloneAdapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.AloneBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.BaseResponseBean;
import com.colin.playerdemo.net.CommonParser;
import com.colin.playerdemo.net.RxHttpUtils;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;


public class Alone_Activity extends BaseActivity {
    @BindView(R.id.head_iv)
    ImageView headIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.introduce_tv)
    TextView introduceTv;
    @BindView(R.id.alone_rv)
    RecyclerView aloneRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    @BindView(R.id.activity_title_include_left_iv)
    ImageView activityTitleIncludeLeftIv;
    AloneAdapter aloneAdapter;
    private String id;
    List<AloneBean.ListBean> aloneBeans = new ArrayList<>();
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_alone;
    }

    @Override
    protected void initView() {
        id  =getIntent().getStringExtra("id");
        aloneRv.setLayoutManager(new GridLayoutManager(this, 3));
        aloneAdapter = new AloneAdapter(aloneBeans);
        aloneRv.setNestedScrollingEnabled(false);
        aloneRv.setAdapter(aloneAdapter);
        getTop();
    }


    @OnClick({R.id.activity_title_include_left_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_title_include_left_iv:
                finish();
                break;
        }
    }
    /**
     * 获取专题详情
     */

    private void getTop( ) {
        RxHttp.setDebug(true);
        RxHttpUtils.getWithToken(Api.special_topic)
                .add("id",id)
                .asParser(new CommonParser<AloneBean>(new TypeToken<BaseResponseBean<AloneBean>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (s.getCode() == 0) {
                        setView(s.getData());

                    } else {
//                        UIHelper.errorToastString(s.getInfo());
                    }

                }, throwable -> {
                });
    }

    private void setView(AloneBean data) {
        nameTv.setText(data.getName());
        aloneBeans.clear();
        aloneBeans.addAll(data.getList());
        aloneAdapter.notifyDataSetChanged();
        Glide.with(Alone_Activity.this).load(data.getPic()).into(headIv);
        introduceTv.setText(data.getIntroduce());
    }
}
