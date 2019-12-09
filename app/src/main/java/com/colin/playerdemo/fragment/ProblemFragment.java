package com.colin.playerdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.AnnouncementAdapter;
import com.colin.playerdemo.adapter.ProblemAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.ProblemBean;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ProblemFragment extends BaseFragment {

    @BindView(R.id.pro_rv)
    RecyclerView proRv;
    @BindView(R.id.no_layout)
    LinearLayout no_layout;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    private ProblemAdapter adapter;

    private List<ProblemBean> noticebeans = new ArrayList<>();

    @Override
    public int getContentViewId() {
        return R.layout.fragment_problem;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        adapter = new ProblemAdapter(noticebeans);
        proRv.setLayoutManager(new LinearLayoutManager(activity));
        proRv.setAdapter(adapter);
        getNotice();
    }

    /**
     * 查询公告
     */
    private void getNotice() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.NOTICE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<ProblemBean>>() {
                }.getType();
                BaseListBean<ProblemBean> searchHotBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();


                //返回码为成功时的处理
                if (searchHotBeanBaseListBean.getResCode() == 0) {

                    if (searchHotBeanBaseListBean.getData().isEmpty()) {
                        no_layout.setVisibility(View.VISIBLE);
                    } else {
                        no_layout.setVisibility(View.GONE);
                        noticebeans.clear();
                        noticebeans.addAll(searchHotBeanBaseListBean.getData());
                        adapter.notifyDataSetChanged();
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
