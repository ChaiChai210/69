package com.colin.playerdemo.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.banner.BannerLayout;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Cache_Activity;
import com.colin.playerdemo.activity.Class_activity;
import com.colin.playerdemo.activity.HistoryActivity;
import com.colin.playerdemo.activity.SearchActivity;
import com.colin.playerdemo.adapter.HomeBannerAdapter;
import com.colin.playerdemo.adapter.HomeFragmentAdapter;
import com.colin.playerdemo.adapter.Home_Class_Adapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.AloneBean;
import com.colin.playerdemo.bean.MainBean;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static androidx.appcompat.app.AppCompatActivity.RESULT_OK;


public class HomeFragment extends BaseFragment implements HomeFragmentAdapter.OnAdClickListener, Home_Class_Adapter.HomeClass_Listener, EasyPermissions.PermissionCallbacks{
    @BindView(R.id.banner)
    BannerLayout banner;
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
    private MainBean mainBean;
    private static final int RC_CAMERA_PERM = 123;

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
            case R.id.scan_iv://扫描
                if (EasyPermissions.hasPermissions(activity, Manifest.permission.CAMERA)) {
                    Intent intent = new Intent(activity, CaptureActivity.class);
                    startActivityForResult(intent, 1111);
                } else {
                    EasyPermissions.requestPermissions(
                            this,
                            getString(R.string.rationale_camera),
                            RC_CAMERA_PERM,
                            Manifest.permission.CAMERA);
                }
                break;
            case R.id.cache_iv://缓存
                activity.startActivity(new Intent(activity, Cache_Activity.class));
                break;
            case R.id.record_iv://历史记录
                activity.startActivity(new Intent(activity, HistoryActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1111) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
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


    private void initHomeView() {
        //分类
        classRv.setLayoutManager(new GridLayoutManager(activity, 4));
        class_adapter = new Home_Class_Adapter();
        classRv.setAdapter(class_adapter);

        homeRv.setLayoutManager(new LinearLayoutManager(activity));
        homeRv.setNestedScrollingEnabled(false);
        class_adapter.setHomeClass_listener(this);
        home_fragment_adapter = new HomeFragmentAdapter();
        homeRv.setAdapter(home_fragment_adapter);
        home_fragment_adapter.setOnAdClickListener(this);
        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.APPHOME).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                Type type = new TypeToken<BaseBean<MainBean>>() {
                }.getType();
                BaseBean<MainBean> homeBean = GsonHelper.gson.fromJson(response.body(), type);
                mainBean = homeBean.getData();
                //返回码为成功时的处理
                if (homeBean.isSuccess()) {
                    HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(mainBean.getAd_chart());
//                    bannerAdapter.setOnItemChildClickListener(Fragment_Home.this);
                    banner.setAdapter(bannerAdapter);
                    class_adapter.setClasslist(mainBean.getType_video());
                    home_fragment_adapter.setVideoData(mainBean.getVideo());

                } else {
                    FancyToast.makeText(activity, homeBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
                UIhelper.stopLoadingDialog();
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

    @Override
    public void OnAdclick(String url, int id) {
        UIhelper.addClickAdRecord(activity, id);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void Onclick(int position) {
        if (position == -1) {
            activity.startActivity(new Intent(activity, Class_activity.class).putExtra("id", "0"));
        } else {
            activity.startActivity(new Intent(activity, Class_activity.class).putExtra("id", mainBean.getType_video().get(position).getId() + ""));
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Intent intent = new Intent(activity, CaptureActivity.class);
        startActivityForResult(intent, 1111);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


}
