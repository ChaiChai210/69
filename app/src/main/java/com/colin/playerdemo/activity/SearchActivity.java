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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.HistorySearchAdapter;
import com.colin.playerdemo.adapter.HotSearchAdapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.HotSearchBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.utils.Constant;
import com.colin.playerdemo.utils.SPUtils;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.rxjava.rxlife.RxLife;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private String historySearchString;
    private String textSearch;


    HotSearchAdapter hotSearchAdapter;
    HistorySearchAdapter historySearchAdapter;
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

    /**
     * 去重
     *
     * @param array
     * @return
     */
    String[] delelte(String[] array) {
        List<String> list = new ArrayList<>();
        list.add(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (!list.toString().contains(array[i])) {
                list.add(array[i]);
            }
        }
        return list.toArray(new String[list.size()]);
    }


    private void getHotSearchData() {
        RxHttpUtils.getWithToken(Api.hotSearch)
                .asDataListParser(HotSearchBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {
                    hotSearchBeans.clear();
                    hotSearchBeans.addAll(s);
                    hotSearchAdapter.notifyDataSetChanged();
                }, throwable -> {
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
