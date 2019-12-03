package com.colin.playerdemo.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.DiscoverAdapter1;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.DisconverBean;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.utils.PlayUtils;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.ProgressManager;
import com.dueeeke.videoplayer.player.VideoView;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;


/**
 * RecyclerView demo
 */
public class RecyclerViewFragment extends BaseFragment implements DiscoverAdapter1.OnItemChildClickListener {

    protected List<DisconverBean.DataBean> mVideos = new ArrayList<>();
    protected DiscoverAdapter1 mAdapter;

    protected LinearLayoutManager mLinearLayoutManager;

    protected VideoView mVideoView;
    protected StandardVideoController mController;
    protected ErrorView mErrorView;
    protected CompleteView mCompleteView;
    protected TitleView mTitleView;

    /**
     * 当前播放的位置
     */
    protected int mCurPos = -1;
    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.discover_rv)
    RecyclerView discoverRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;

    private static final int PAGE_SIZE = 6;
    private int page = 1;
    private boolean hasMore = true;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
    protected int mLastPos = mCurPos;

    private static LinkedHashMap<Integer, Long> progressMap = new LinkedHashMap<>();
    protected void initVideoView() {
        mVideoView = new VideoView(activity);
        mVideoView.setProgressManager(new ProgressManager() {
            @Override
            public void saveProgress(String url, long progress) {
                if (TextUtils.isEmpty(url)) return;
                progressMap.put(url.hashCode(), progress);
            }

            @Override
            public long getSavedProgress(String url) {
                return TextUtils.isEmpty(url) ? 0 : progressMap.containsKey(url.hashCode()) ? progressMap.get(url.hashCode()) : 0;
            }
        });
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                //监听VideoViewManager释放，重置状态
                if (playState == VideoView.STATE_IDLE) {
                    PlayUtils.removeViewFormParent(mVideoView);
                    mLastPos = mCurPos;
                    mCurPos = -1;
                }
            }
        });
        mController = new StandardVideoController(activity);
        mErrorView = new ErrorView(activity);
        mController.addControlComponent(mErrorView);
        mCompleteView = new CompleteView(activity);
        mController.addControlComponent(mCompleteView);
        mTitleView = new TitleView(activity);
        mController.addControlComponent(mTitleView);
        mController.addControlComponent(new VodControlView(activity));
        mController.addControlComponent(new GestureView(activity));
        mController.setEnableOrientation(true);
        mVideoView.setVideoController(mController);
    }


    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            pause();
        }
    }


    protected void resume() {
        if (mLastPos == -1)
            return;
        //恢复上次播放的位置
        startPlay(mLastPos);
    }

    /**
     * 由于onPause必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onPause的逻辑
     */
    protected void pause() {
        releaseVideoView();
    }


    /**
     * PrepareView被点击
     */
    @Override
    public void onItemChildClick(int position) {
        startPlay(position);
    }

    /**
     * 开始播放
     *
     * @param position 列表位置
     */
    protected void startPlay(int position) {
        if (mCurPos == position) return;
        if (mCurPos != -1) {
            releaseVideoView();
        }
        DisconverBean.DataBean videoBean = mVideos.get(position);
        mVideoView.setUrl(videoBean.getVideo_url());
        mTitleView.setTitle(videoBean.getName());
        View itemView = mLinearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        BaseViewHolder viewHolder = (BaseViewHolder) discoverRv.findViewHolderForAdapterPosition(position);
//        VideoRecyclerViewAdapter.VideoHolder viewHolder = (VideoRecyclerViewAdapter.VideoHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isPrivate此处只能为true。
        PrepareView mPrepareView = viewHolder.getView(R.id.prepare_view);
        mController.addControlComponent(mPrepareView, true);
        PlayUtils.removeViewFormParent(mVideoView);
        FrameLayout container = viewHolder.getView(R.id.player_container);
        container.addView(mVideoView, 0);
//        //播放之前将VideoView添加到VideoViewManager以便在别的页面也能操作它
//        getVideoViewManager().add(mVideoView, Tag.LIST);
        mVideoView.start();
        mCurPos = position;
    }


    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_discover;
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
                            mAdapter.replaceData(s);
                        } else {
//                            discoverList.addAll(s);
                            mAdapter.addData(s);
                        }
                    }


                }, throwable -> {
                });
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        initVideoView();
        getVideos();
        refreshFind.setOnRefreshListener(refreshlayout -> {
            page = 1;
            hasMore = true;
            getVideos();
            refreshFind.finishRefresh(1000);
        });
        refreshFind.setOnLoadMoreListener(refreshLayout -> {
            if (hasMore) {
                page++;
                getVideos();
            }
            refreshFind.finishLoadMore();
        });
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        discoverRv.setLayoutManager(mLinearLayoutManager);
        mAdapter = new DiscoverAdapter1(mVideos);
        mAdapter.setOnItemChildClickListener(this);
        discoverRv.setAdapter(mAdapter);
        discoverRv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                FrameLayout playerContainer = view.findViewById(R.id.player_container);
                View v = playerContainer.getChildAt(0);
                if (v != null && v == mVideoView && !mVideoView.isFullScreen()) {
                    releaseVideoView();
                }
            }
        });


    }
}
