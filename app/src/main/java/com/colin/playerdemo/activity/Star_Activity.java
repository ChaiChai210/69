package com.colin.playerdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.Star_Aadapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.StarInfoBean;
import com.colin.playerdemo.customeview.third.RoundImageView;
import com.colin.playerdemo.net.BaseBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Star_Activity extends BaseActivity {
    @BindView(R.id.bc_iv)
    ImageView bcIv;
    @BindView(R.id.star_image)
    RoundImageView starImage;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.ohter_tv)
    TextView ohterTv;
    @BindView(R.id.introduce_tv)
    TextView introduceTv;
    @BindView(R.id.more_tv)
    TextView moreTv;
    @BindView(R.id.near_tv)
    TextView nearTv;
    @BindView(R.id.star_rv)
    RecyclerView starRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    @BindView(R.id.iv_left)
    ImageView ivLeft;

    Star_Aadapter star_aadapter;
    private String type = "1", id;
    BaseBean<StarInfoBean> baseBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.star_activity;
    }

    @Override
    protected void initView() {

        refreshFind.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshFind.finishRefresh(1000);
            }

        });
        refreshFind.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshFind.finishLoadMore();
            }
        });
        star_aadapter = new Star_Aadapter();
        starRv.setNestedScrollingEnabled(false);
        starRv.setLayoutManager(new LinearLayoutManager(this));
        starRv.setAdapter(star_aadapter);

    }

    @Override
    protected void initData() {
        super.initData();
        id = getIntent().getStringExtra("id");
        getChannel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_left, R.id.more_tv, R.id.near_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.more_tv://最多播放
                type = "1";
                getChannel();
                moreTv.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                nearTv.setBackground(null);
                break;
            case R.id.near_tv://最近播放
                type = "2";
                getChannel();
                nearTv.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                moreTv.setBackground(null);
                break;
        }
    }


    private void getChannel() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        httpParams.put("type", type);//默认为‘1’ 1按照播放量排序 / 2按照时间顺序
        OkGo.<String>post(URLs.STARINFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<StarInfoBean>>() {
                }.getType();
                baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();

                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    Glide.with(Star_Activity.this).load(baseBean.getData().getPortrait()).into(starImage);
                    numTv.setText(baseBean.getData().getVideo_count() + "部影片");
                    Glide.with(Star_Activity.this).load(baseBean.getData().getPic()).into(bcIv);
                    nameTv.setText(baseBean.getData().getName());
                    ohterTv.setText("身高:" + baseBean.getData().getHeight() + " 三围:" +
                            baseBean.getData().getBwh_bust() + "/" + baseBean.getData().getBwh_waist() + "/" +
                            baseBean.getData().getBwh_hips() + " 罩杯:" + baseBean.getData().getCup());
                    introduceTv.setText(baseBean.getData().getIntroduce());
                    star_aadapter.setVideo_list(baseBean.getData().getVideo_list());
                } else {

                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Star_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }
}
