package com.colin.playerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.base.CommonImmerseActivity;
import com.colin.playerdemo.bean.Category_Bean;
import com.colin.playerdemo.customeview.CustomViewPager;
import com.colin.playerdemo.customeview.MySlidingTabLayout;
import com.colin.playerdemo.fragment.Fragment_Class;
import com.colin.playerdemo.net.BaseListBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.StringUtils;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
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

public class Class_activity extends CommonImmerseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.tv_center)
    TextView activityTitleIncludeCenterTv;
    @BindView(R.id.tv_right)
    TextView activityTitleIncludeRightTv;
    @BindView(R.id.iv_right)
    ImageView activityTitleIncludeRightIv;
    @BindView(R.id.synthesis_tv)
    TextView synthesisTv;
    @BindView(R.id.more_play_tv)
    TextView morePlayTv;
    @BindView(R.id.more_new_tv)
    TextView moreNewTv;
    @BindView(R.id.more_like_tv)
    TextView moreLikeTv;
    @BindView(R.id.class_slide_tl)
    MySlidingTabLayout classSlideTl;
    @BindView(R.id.class_vp)
    CustomViewPager classVp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int tabItem = 0;
    private String id, name;
    private String ranking = "0";//(0 表示全部(默认) ,1 最多播放,2 最近更新,3 最多喜欢)
    /**
     * 查询分类
     */
    private List<Category_Bean> list;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_class;
    }

    @Override
    protected void initView() {

    }


    @Override
    public void initData() {
//        darkImmerseFontColor();
        activityTitleIncludeCenterTv.setText("全部视频");
        activityTitleIncludeRightIv.setImageResource(R.mipmap.icon_search);
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
        }
        getCategory();
    }


    @OnClick({R.id.iv_left, R.id.iv_right, R.id.more_play_tv, R.id.more_new_tv, R.id.more_like_tv, R.id.synthesis_tv})
    public void onViewClicked(View view) {
        Fragment_Class fragment_class;
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_right:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.more_play_tv:
                morePlayTv.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                synthesisTv.setBackground(null);
                moreNewTv.setBackground(null);
                moreLikeTv.setBackground(null);
                ranking = "1";
                fragment_class = (Fragment_Class) mFragments.get(tabItem);
                fragment_class.setRanking(ranking, list.get(tabItem).getId() + "");
                break;
            case R.id.more_new_tv:
                moreNewTv.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                synthesisTv.setBackground(null);
                morePlayTv.setBackground(null);
                moreLikeTv.setBackground(null);
                ranking = "2";
                fragment_class = (Fragment_Class) mFragments.get(tabItem);
                fragment_class.setRanking(ranking, list.get(tabItem).getId() + "");
                break;
            case R.id.more_like_tv:
                moreLikeTv.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                synthesisTv.setBackground(null);
                morePlayTv.setBackground(null);
                moreNewTv.setBackground(null);
                ranking = "3";
                fragment_class = (Fragment_Class) mFragments.get(tabItem);
                fragment_class.setRanking(ranking, list.get(tabItem).getId() + "");
                break;
            case R.id.synthesis_tv:
                synthesisTv.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                moreLikeTv.setBackground(null);
                morePlayTv.setBackground(null);
                moreNewTv.setBackground(null);
                ranking = "0";
                fragment_class = (Fragment_Class) mFragments.get(tabItem);
                fragment_class.setRanking(ranking, list.get(tabItem).getId() + "");
                break;

        }
    }


    private void initTab() {
        classSlideTl.setSnapOnTabClick(false);
        List<String> tabList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            tabList.add(list.get(i).getName());
        }

        String[] tabs = getArr(tabList);

        //添加其他分类fragment
        for (int a = 0; a < list.size(); a++) {
            mFragments.add(Fragment_Class.newInstance(list.get(a).getId(), ranking));
        }
        classSlideTl.setViewPager(classVp, tabs, this, mFragments);
        classSlideTl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tabItem = position;
                Fragment_Class fragment_class = (Fragment_Class) mFragments.get(position);
                fragment_class.setRanking(ranking, list.get(position).getId() + "");
                for (int i = 0; i < list.size(); i++) {
                    TextView textView = classSlideTl.getTitleView(i);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                    layoutParams.setMargins(18, 0, 18, 0);
                    textView.setLayoutParams(layoutParams);
                    if (i == position) {
                        textView.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                    } else {
                        textView.setBackground(null);
                    }
                    textView.setPadding(10, 10, 10, 10);
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        classVp.setPagingEnabled(false);
        classSlideTl.setCurrentTab(0);
//        TextView textView = classSlideTl.getTitleView(0);
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
//        layoutParams.setMargins(18, 0, 18, 0);
//        textView.setLayoutParams(layoutParams);
//        textView.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
//        textView.setPadding(10, 10, 10, 10);
        if (!StringUtils.isEmpty(id)) {
            for (int i = 0; i < list.size(); i++) {
                if (id.equals(list.get(i).getId() + "")) {
                    classSlideTl.setCurrentTab(i);
                    for (int k = 0; k < list.size(); k++) {
                        TextView textViewt = classSlideTl.getTitleView(k);
                        RelativeLayout.LayoutParams layoutParam = (RelativeLayout.LayoutParams) textViewt.getLayoutParams();
                        layoutParam.setMargins(18, 0, 18, 0);
                        textViewt.setLayoutParams(layoutParam);
                        if (k == i) {
                            textViewt.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                        } else {
                            textViewt.setBackground(null);
                        }
                        textViewt.setPadding(10, 10, 10, 10);
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(name)) {
            for (int i = 0; i < list.size(); i++) {
                if (name.equals(list.get(i).getName() + "")) {
                    classSlideTl.setCurrentTab(i);
                    for (int k = 0; k < list.size(); k++) {
                        TextView textViewt = classSlideTl.getTitleView(k);
                        RelativeLayout.LayoutParams layoutParam = (RelativeLayout.LayoutParams) textViewt.getLayoutParams();
                        layoutParam.setMargins(18, 0, 18, 0);
                        textViewt.setLayoutParams(layoutParam);
                        if (k == i) {
                            textViewt.setBackground(getResources().getDrawable(R.drawable.boder_all_circular_12));
                        } else {
                            textViewt.setBackground(null);
                        }
                        textViewt.setPadding(10, 10, 10, 10);
                    }
                }
            }
        }
    }

    public static String[] getArr(List<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }


    private void getCategory() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.CATEGORY).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<Category_Bean>>() {
                }.getType();
                BaseListBean<Category_Bean> category_beanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);

                //返回码为成功时的处理
                if (category_beanBaseListBean.getResCode() == 0) {
                    list = new ArrayList<>();
                    Category_Bean category_bean = new Category_Bean();
                    category_bean.setId(0);
                    category_bean.setName("全部");
                    category_bean.setPic("");
                    list.add(category_bean);
                    list.addAll(category_beanBaseListBean.getData());
                    initTab();
                } else {

                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
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
