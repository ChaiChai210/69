package com.colin.playerdemo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.banner.BannerLayout;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.HistoryActivity;
import com.colin.playerdemo.activity.SearchActivity;
import com.colin.playerdemo.adapter.HomeBannerAdapter;
import com.colin.playerdemo.adapter.Home_Class_Adapter;
import com.colin.playerdemo.adapter.HomeFragmentAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.MainBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.BaseResponseBean;
import com.colin.playerdemo.net.CommonParser;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

import static androidx.appcompat.app.AppCompatActivity.RESULT_OK;


public class HomeFragment extends BaseFragment implements HomeFragmentAdapter.OnAdClickListener {
    @BindView(R.id.banner)
    BannerLayout recycler;
    @BindView(R.id.class_rv)
    RecyclerView classRv;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    @BindView(R.id.search_layout)
    LinearLayout search_layout;
    @BindView(R.id.scan_iv)
    ImageView scan_iv;
    @BindView(R.id.cache_iv)
    ImageView cache_iv;
    @BindView(R.id.record_iv)
    ImageView record_iv;

    Home_Class_Adapter class_adapter;
    HomeFragmentAdapter home_fragment_adapter;
//
//    BaseBean<MainBean> loginBean;

    private static final int REQUEST_CODE_SCAN = 0x0000;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.search_layout, R.id.scan_iv, R.id.cache_iv, R.id.record_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_layout:
                activity.startActivity(new Intent(activity, SearchActivity.class));
                break;
//            case R.id.scan_iv://扫描
//                Intent intent = new Intent(activity,
//                        CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE_SCAN);
//                break;
//            case R.id.cache_iv://缓存
//                activity.startActivity(new Intent(activity, Cache_Activity.class));
//                break;
            case R.id.record_iv://历史记录
                activity.startActivity(new Intent(activity, HistoryActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                if (StringUtils.isUrl(content)) {
                    Uri uri = Uri.parse(content);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else {
                    FancyToast.makeText(activity, "扫码内容不正确", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                }
            }
        }
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        refreshFind.setOnRefreshListener(refreshlayout -> {
            getData();
            refreshFind.finishRefresh(1000);
        });
        refreshFind.setEnableLoadMore(false);
//        refreshFind.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
//                refreshFind.finishLoadMore();
//            }
//        });
        initHomeView();
    }

    private void getData() {
        RxHttp.setDebug(true);
        UIhelper.showLoadingDialog(activity);
        RxHttpUtils.getWithToken(Api.home_data)
                .asParser(new CommonParser<MainBean>(new TypeToken<BaseResponseBean<MainBean>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {
                    UIhelper.stopLoadingDialog();
                    if (s.getCode() == 200) {
                        int size = s.getData().getAd_chart().size();
                        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(s.getData().getAd_chart());
                        recycler.setAdapter(bannerAdapter);
                        class_adapter.setClasslist(s.getData().getType_video());
                        home_fragment_adapter.setVideoData(s.getData().getVideo());
                    } else {
                        UIhelper.ToastMessage(s.getInfo());
                    }

                }, throwable -> {
                    UIhelper.stopLoadingDialog();
                });
    }

    void initHomeView() {
        //分类
        classRv.setLayoutManager(new GridLayoutManager(activity, 4));
        class_adapter = new Home_Class_Adapter();
        classRv.setAdapter(class_adapter);

        homeRv.setLayoutManager(new LinearLayoutManager(activity));
        homeRv.setNestedScrollingEnabled(false);
//        class_adapter.setHomeClass_listener(this);
        home_fragment_adapter = new HomeFragmentAdapter();
        homeRv.setAdapter(home_fragment_adapter);
        home_fragment_adapter.setOnAdClickListener(this);
        getData();
    }

    @Override
    public void OnAdclick(String url, int id) {
        UIhelper.addClickAdRecord(id);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


//    @Override
//    public void Onclick(int position) {
//        if (position == -1) {
//            activity.startActivity(new Intent(activity, Class_activity.class).putExtra("id", "0"));
//        } else {
//            activity.startActivity(new Intent(activity, Class_activity.class).putExtra("id", loginBean.getData().getType_video().get(position).getId() + ""));
//        }
//
//    }


//    @Override
//    public void onItemClick(int position) {
//        if (!Tools.isEmpty(loginBean.getData().getAd_chart().get(position).getUrl())) {
//            ADDAds(loginBean.getData().getAd_chart().get(position).getId() + "");
//            Uri uri = Uri.parse( loginBean.getData().getAd_chart().get(position).getUrl());
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//
//        }
//    }


}
