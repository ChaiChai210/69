package com.colin.playerdemo.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.LabelDetailAdapter;
import com.colin.playerdemo.adapter.Label_Content_Adapter;
import com.colin.playerdemo.adapter.LableTitleAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.LabelBean;
import com.colin.playerdemo.bean.SearchBean;
import com.colin.playerdemo.bean.TagTypeListBean;
import com.colin.playerdemo.net.BaseListBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.net.rxjava.Api;
import com.colin.playerdemo.utils.GsonUtils;
import com.colin.playerdemo.utils.UIhelper;
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

public class LabelFragment extends BaseFragment {
    @BindView(R.id.lable_title_rv)
    RecyclerView lableTitleRv;
    @BindView(R.id.lable_rv)
    RecyclerView lableRv;
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
    private Label_Content_Adapter content_adapter;
    private String getMyPromotion;
    BaseListBean<SearchBean> searchBeanBaseListBean;
    BaseListBean<TagTypeListBean> tagTypeListBeanBaseListBean;
    BaseListBean<LabelBean> labelBeanBaseListBean;
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
            getSeach();
            refreshFind.finishRefresh(1000);
        });
        refreshFind.setOnLoadMoreListener(refreshLayout -> {
            page++;
            getSeach();
            refreshFind.finishLoadMore();
        });
        labelTitleAdapter = new LableTitleAdapter();
        lableTitleRv.setLayoutManager(new GridLayoutManager(activity, 4));
        lableTitleRv.setAdapter(labelTitleAdapter);

        labelDetailAdapter = new LabelDetailAdapter(mLableBeans);
        lableRv.setLayoutManager(new GridLayoutManager(activity, 2));
        lableRv.setAdapter(labelDetailAdapter);

        contentRv.setLayoutManager(new LinearLayoutManager(activity));
        content_adapter = new Label_Content_Adapter();
        contentRv.setAdapter(content_adapter);

        getLabelTitle();
        initListener();
    }


    private void initListener() {
        labelTitleAdapter.setLableListener(position -> {
            if (position != tagTypeListBeans.size() - 1) {
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

        labelDetailAdapter.setOnItemClickListener((adapter, view, position) -> {
            getMyPromotion = mLableBeans.get(position).getId() + "";
            page = 1;
            getSeach();
            bottomLabelLayout.setVisibility(View.GONE);
            refreshFind.setVisibility(View.VISIBLE);

        });
    }
//

    private void setLabelDetail(List<LabelBean> labelBeans) {
        mLableBeans.clear();
        mLableBeans.addAll(labelBeans);
        labelDetailAdapter.notifyDataSetChanged();
    }


    private void getLabelTitle() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.TAGTYPE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<TagTypeListBean>>() {
                }.getType();
                tagTypeListBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (tagTypeListBeanBaseListBean.getResCode() == 0) {

                    if (tagTypeListBeanBaseListBean.getData().size() > 0) {
                        setUi(tagTypeListBeanBaseListBean.getData());
                    }
                } else {

                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
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

    private void getTagTypeList(int id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("tagtype_id", id);

        OkGo.<String>get(URLs.TAGLIST).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<LabelBean>>() {
                }.getType();
                labelBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (labelBeanBaseListBean.getResCode() == 0) {

                    setLabelDetail(labelBeanBaseListBean.getData());
                } else {
                    FancyToast.makeText(activity, labelBeanBaseListBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void getSeach() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("getMyPromotion", getMyPromotion);
        httpParams.put("page", page);
        OkGo.<String>get(URLs.SEARCH).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<SearchBean>>() {
                }.getType();
                searchBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (searchBeanBaseListBean.getResCode() == 0) {
                    if (page == 1) {
                        list = searchBeanBaseListBean.getData();
                    } else {
                        list.addAll(searchBeanBaseListBean.getData());
                    }
                    if (list.size() > 0) {
                        content_adapter.setList(list);
                        noLayout.setVisibility(View.GONE);

                    } else {
                        noLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    UIhelper.ToastMessage(searchBeanBaseListBean.getInfo());
                    noLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                noLayout.setVisibility(View.VISIBLE);
            }
        });
    }

}
