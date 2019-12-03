package com.colin.playerdemo.fragment;

import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.DiscoverAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.DisconverBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.utils.SPUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class DiscoverFragment extends BaseFragment implements DiscoverAdapter.CollectListener {
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.discover_rv)
    RecyclerView discoverRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;

    private DiscoverAdapter discoverAdapter;
    private List<DisconverBean.DataBean> discoverList = new ArrayList<>();

    private static final int PAGE_SIZE = 6;
    private int page = 1;
    private boolean hasMore = true;

    private int playPosition;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        refreshFind.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                hasMore = true;
                getVideos();
                refreshFind.finishRefresh(1000);
            }
        });
        refreshFind.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (hasMore) {
                    page++;
                    getVideos();
                }
                refreshFind.finishLoadMore();
            }
        });
        initData();
    }

    private void getVideos() {
        RxHttp.setDebug(true);
        RxHttpUtils.getWithToken(Api.frag_discover_data)
                .add("page", page)
                .add("page_size", PAGE_SIZE)
                .asDataListParser(DisconverBean.DataBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (s.isEmpty()) {
                        hasMore = false;
                    } else {
                        if (page == 1) {
//                            discoverList.clear();
//                            discoverList.addAll(s);
                            discoverAdapter.replaceData(s);
                        } else {
//                            discoverList.addAll(s);
                            discoverAdapter.addData(s);
                        }
                    }


                }, throwable -> {
                });
    }

    void initData() {
        discoverRv.setLayoutManager(new LinearLayoutManager(activity));
        discoverAdapter = new DiscoverAdapter(discoverList);
        discoverRv.setAdapter(discoverAdapter);
//        config();
        discoverAdapter.setCollectListener(this);
        refreshFind.autoRefresh();
    }

    @OnClick(R.id.search_iv)
    public void onViewClicked() {
    }

    @Override
    public void onEventRefresh(Message message) {
        switch (message.what) {

            //接收EventBus订阅信息
            case 1234:
                stopPlayer();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPlayer();
    }

    private void stopPlayer() {
        if (null != discoverAdapter) {
            discoverAdapter.stopPlayer();
        }
    }

    @Override
    public void onclick(int position, int type) {
        //1,记录，2收藏，3，分享
        if (type == 1) {
            saveVideo(discoverList.get(position).getId());
            playPosition = position;
        } else if (type == 2) {
            if (discoverList.get(position).getIs_collect() == 1) {
                deleteCollectlist(discoverList.get(position).getId() + "", position);
            } else {
                collect(discoverList.get(position).getId() + "", position);
            }
        } else if (type == 3) {
//            AppUtils.copyToClipboard(activity, downUrl + "");
            Toast.makeText(activity, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
        }
    }

    //todo 收藏和取消收藏后期接口需要调整。
    private void collect(String id, final int position) {
        RxHttpUtils.postWithToken(Api.discover_collect)
                .add("vid", id)
                .asResponse(Object.class)
                .doOnSubscribe(disposable -> UIhelper.showLoadingDialog(activity))
                .doOnTerminate(() -> UIhelper.stopLoadingDialog())
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    discoverList.get(position).setIs_collect(1);
                    discoverAdapter.notifyItemChanged(position);
                }, throwable -> {
                });
    }

    private void deleteCollectlist(String id, final int position) {
        RxHttp.deleteJson(Api.discover_collect + "/" + id)
                .addHeader("app-token", SPUtils.getToken())
                .asObject(Object.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    discoverList.get(position).setIs_collect(0);
                    discoverAdapter.notifyItemChanged(position);
                }, throwable -> {
                });
    }


    private void saveVideo(int id) {
        RxHttpUtils.postWithToken(Api.video_watch_record)
                .add("vid", id)
                .asResponse(Object.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                }, throwable -> {
                });
    }

}
