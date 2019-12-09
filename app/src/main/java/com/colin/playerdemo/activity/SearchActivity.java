package com.colin.playerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.HistorySearchAdapter;
import com.colin.playerdemo.adapter.HotSearchAdapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.HotSearchBean;
import com.colin.playerdemo.net.BaseListBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.Constant;
import com.colin.playerdemo.utils.SPUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.delete_iv)
    ImageView deleteIv;
    @BindView(R.id.rl_history)
    RelativeLayout rlHistory;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.rv_hot_search)
    RecyclerView rvHotSearch;

    private HotSearchAdapter hotSearchAdapter;
    private HistorySearchAdapter historySearchAdapter;
    private List<HotSearchBean> hotSearchBeans = new ArrayList<>();
    private List<String> historySearchList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        darkImmerseFontColor();
        String history = SPUtils.getSearchHistory();
        if (history.isEmpty()) {
            rlHistory.setVisibility(View.GONE);
        } else {
            rlHistory.setVisibility(View.VISIBLE);
            String[] split = history.split(",");

            List<String> strings = Arrays.asList(split);
            historySearchList.addAll(strings);
        }
        historySearchAdapter = new HistorySearchAdapter(historySearchList);
        historySearchAdapter.setOnItemClickListener((adapter, view, position) -> {
            doSearch(historySearchList.get(position));
        });
        rvHistory.setLayoutManager(getLayoutManager());
        rvHistory.setAdapter(historySearchAdapter);

        rvHotSearch.setLayoutManager(getLayoutManager());
        hotSearchAdapter = new HotSearchAdapter(hotSearchBeans);
        hotSearchAdapter.setOnItemClickListener((adapter, view, position) -> {
            doSearch(hotSearchBeans.get(position).getContent());
        });
        rvHotSearch.setAdapter(hotSearchAdapter);


        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            //这里写事件，返回为true，即为搜索键的事件
            doSearch(v.getText().toString());
            return true;
        });
    }

    private FlexboxLayoutManager getLayoutManager() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        return layoutManager;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void initData() {

        getHotSearchData();
    }


    @OnClick({R.id.tv_search, R.id.delete_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                doSearch(etSearch.getText().toString());
                break;
            case R.id.delete_iv:
                SPUtils.remove(Constant.SP_HISTORY_SEARCH);
                rlHistory.setVisibility(View.GONE);
                break;
        }
    }

    private void doSearch(String textSearch) {
        if (TextUtils.isEmpty(textSearch)) {
            FancyToast.makeText(this, "请输入搜索内容", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
        } else {
            rlHistory.setVisibility(View.VISIBLE);
            if (!historySearchList.contains(textSearch)) {
                historySearchList.add(textSearch);
                historySearchAdapter.notifyItemInserted(historySearchList.size());
            }
            SPUtils.put(Constant.SP_HISTORY_SEARCH, String.join(",", historySearchList));
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent.putExtra("search", textSearch);
            startActivity(intent);
        }
    }


    private void getHotSearchData() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.SEARCHGOT).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<HotSearchBean>>() {
                }.getType();
                BaseListBean<HotSearchBean> searchHotBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();


                //返回码为成功时的处理
                if (searchHotBeanBaseListBean.getResCode() == 0) {
                    hotSearchBeans.clear();
                    hotSearchBeans.addAll(searchHotBeanBaseListBean.getData());
                    hotSearchAdapter.notifyDataSetChanged();
                } else {
                    UIhelper.ToastMessage(searchHotBeanBaseListBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(SearchActivity.this);
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
}
