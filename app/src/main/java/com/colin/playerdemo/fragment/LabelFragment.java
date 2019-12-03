package com.colin.playerdemo.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.LabelDetailAdapter;
import com.colin.playerdemo.adapter.LableTitleAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.LabelBean;
import com.colin.playerdemo.bean.SearchBean;
import com.colin.playerdemo.bean.TagTypeListBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.RxHttpUtils;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class LabelFragment extends BaseFragment {
    @BindView(R.id.lable_title_rv)
    RecyclerView lableTitleRv;
    @BindView(R.id.lable_rv)
    RecyclerView lableRv;
    @BindView(R.id.confirm_tv)
    TextView confirmTv;
    @BindView(R.id.bottom_label_layout)
    RelativeLayout bottomLabelLayout;
    @BindView(R.id.content_rv)
    RecyclerView contentRv;
    @BindView(R.id.no_layout)
    LinearLayout noLayout;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;

    private LableTitleAdapter labelTitleAdapter;
    private List<TagTypeListBean> tagTypeListBeans = new ArrayList<>();
    private LabelDetailAdapter labelDetailAdapter;
    private List<LabelBean> mLableBeans = new ArrayList<>();

    private String getMyPromotion;
    //    BaseListBean<SearchBean> searchBeanBaseListBean;
    private int page = 1;
    private List<SearchBean> list;

    public LabelFragment() {
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_label;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        refreshFind.setOnRefreshListener(refreshlayout -> {
            page = 1;
//                getSeach();
            refreshFind.finishRefresh(1000);
        });
        refreshFind.setOnLoadMoreListener(refreshLayout -> {
            page++;
//                getSeach();
            refreshFind.finishLoadMore();
        });
        labelTitleAdapter = new LableTitleAdapter();
        lableTitleRv.setLayoutManager(new GridLayoutManager(activity, 4));
        lableTitleRv.setAdapter(labelTitleAdapter);

        labelDetailAdapter = new LabelDetailAdapter(mLableBeans);
        lableRv.setLayoutManager(new GridLayoutManager(activity, 2));
        lableRv.setAdapter(labelDetailAdapter);

//        contentRv.setLayoutManager(new LinearLayoutManager(activity));
//        content_adapter = new Label_Content_Adapter();
//        contentRv.setAdapter(content_adapter);
//

//        lable_adapter.setLableListener(new Lable_Adapter.LableListener() {
//            @Override
//            public void Onclick(int position) {
//                getMyPromotion = labelBeanBaseListBean.getRows().get(position).getId() + "";
//                lable_adapter.setLableItem(position);
//            }
//        });

        getLabelTitle();
        initListener();
    }

    private void initListener() {
        labelTitleAdapter.setLableListener(position -> {
            if (position != tagTypeListBeans.size()-1) {
                getMyPromotion = "";
                labelTitleAdapter.setItem(position);
                getTagTypeList(tagTypeListBeans.get(position).getId());
                noLayout.setVisibility(View.GONE);
                bottomLabelLayout.setVisibility(View.VISIBLE);
                refreshFind.setVisibility(View.GONE);
            } else {
                labelTitleAdapter.setItem(0);
                noLayout.setVisibility(View.GONE);
                bottomLabelLayout.setVisibility(View.VISIBLE);
                refreshFind.setVisibility(View.GONE);
                getTagTypeList(tagTypeListBeans.get(0).getId());
            }

        });

    }

    private void getTagTypeList(int id) {
        RxHttp.setDebug(true);
        //订阅观察者，
        RxHttpUtils.getWithToken(Api.tagDetail)
                .add("tagtype_id", id)
                .asDataListParser(LabelBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(this::setLabelDetail, throwable -> {
                });
    }

    private void setLabelDetail(List<LabelBean> labelBeans) {
        mLableBeans.clear();
        mLableBeans.addAll(labelBeans);
        labelDetailAdapter.notifyDataSetChanged();
    }


    private void getLabelTitle() {
        RxHttp.setDebug(true);
        RxHttpUtils.getWithToken(Api.label_title)
//                .asParser(new CommonParser<List<TagTypeListBean>>(new TypeToken<BaseResponseBean<List<TagTypeListBean>>>() {
//                }))
                .asDataListParser(TagTypeListBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {//订阅观察者，
                    setUi(s);

                }, throwable -> {
                });
    }

    private void setUi(List<TagTypeListBean> data) {
//        tagTypeListBeans.clear();
//        tagTypeListBeans.addAll(data);
//        TagTypeListBean tagTypeListBean = new TagTypeListBean();
//        tagTypeListBean.setName("重置");
//        tagTypeListBean.setId(-1);
//        tagTypeListBeans.add(tagTypeListBean);
//        labelTitleAdapter.setVideoData(tagTypeListBeans);

        TagTypeListBean tagTypeListBean = new TagTypeListBean();
        tagTypeListBean.setName("重置");
        tagTypeListBean.setId(-1);
        data.add(tagTypeListBean);
        labelTitleAdapter.setStringList(data);
        tagTypeListBeans.clear();
        tagTypeListBeans.addAll(data);
        getTagTypeList(data.get(0).getId());
    }


}
