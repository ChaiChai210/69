package com.colin.playerdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.My_Feedback_Fragment_Adapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.FendBackBeanList;
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

import java.lang.reflect.Type;

import butterknife.BindView;


public class My_Feedback_Fragment extends BaseFragment {
    @BindView(R.id.my_feed_rv)
    RecyclerView myFeedRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    My_Feedback_Fragment_Adapter adapter;
    @BindView(R.id.no_layout)
    LinearLayout no_layout;

    public static My_Feedback_Fragment newInstance(String from) {
        My_Feedback_Fragment fragment = new My_Feedback_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.my_feedback_fragment;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        adapter = new My_Feedback_Fragment_Adapter();
        myFeedRv.setLayoutManager(new LinearLayoutManager(activity));
        myFeedRv.setAdapter(adapter);
        getSearchHot();
    }

    /**
     * 查询反馈信息
     */
    private void getSearchHot() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.OPINION).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<FendBackBeanList>>() {
                }.getType();
                BaseListBean<FendBackBeanList> searchHotBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();


                //返回码为成功时的处理
                if (searchHotBeanBaseListBean.getResCode() == 0) {
                    adapter.setLists(searchHotBeanBaseListBean.getData());
                    if (searchHotBeanBaseListBean.getData().size() <= 0) {
                        no_layout.setVisibility(View.VISIBLE);
                    } else {
                        no_layout.setVisibility(View.GONE);
                    }
                } else {
                    no_layout.setVisibility(View.VISIBLE);
                    UIhelper.ToastMessage(searchHotBeanBaseListBean.getInfo());
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
                no_layout.setVisibility(View.VISIBLE);
            }
        });
    }
}
