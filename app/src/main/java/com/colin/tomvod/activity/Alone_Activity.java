package com.colin.tomvod.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.tomvod.R;
import com.colin.tomvod.adapter.AloneAdapter;
import com.colin.tomvod.base.BaseActivity;
import com.colin.tomvod.bean.AloneBean;
import com.colin.tomvod.net.BaseBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.utils.UIhelper;
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
        id = getIntent().getStringExtra("id");
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

    private void getTop() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        OkGo.<String>post(URLs.TOPICONFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<AloneBean>>() {
                }.getType();
                BaseBean<AloneBean> baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    setView(baseBean.getData());
                } else {
                    FancyToast.makeText(Alone_Activity.this, baseBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                }

            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                UIhelper.showLoadingDialog(Alone_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();
            }
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
