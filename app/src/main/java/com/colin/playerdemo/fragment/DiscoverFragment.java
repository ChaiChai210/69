package com.colin.playerdemo.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.DiscoverAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.ConFigBean;
import com.colin.playerdemo.bean.DisconverBean;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.AppUtils;
import com.colin.playerdemo.utils.PlayUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.ProgressManager;
import com.dueeeke.videoplayer.player.VideoView;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;


/**
 * RecyclerView demo
 */
public class DiscoverFragment extends BaseFragment implements DiscoverAdapter.OnItemChildClickListener, DiscoverAdapter.CollectListener {

    protected List<DisconverBean.DataBean> mVideos = new ArrayList<>();
    protected DiscoverAdapter mAdapter;

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

    private int page = 1;
    private boolean hasMore = true;
    DisconverBean disconverBean;
    private String downUrl;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
    protected int mLastPos = mCurPos;

    private static LinkedHashMap<Integer, Long> progressMap = new LinkedHashMap<>();

    private void initVideoView() {
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
        saveVideo(mVideos.get(position).getId() + "");
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
        HttpParams httpParams = new HttpParams();
        httpParams.put("page_size", 6);
        httpParams.put("page", page);
        OkGo.<String>get(URLs.RECOMVIDEO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Type type = new TypeToken<DisconverBean>() {
                }.getType();
                disconverBean = GsonHelper.gson.fromJson(response.body(), type);

                //返回码为成功时的处理
                if (disconverBean.getCode() == 0) {
                    if (disconverBean.getData().size() < 6) {
                        hasMore = false;
                    }
                    if (page == 1) {
                        mAdapter.replaceData(disconverBean.getData());
                    } else {
                        mAdapter.addData(disconverBean.getData());
                    }
                } else {
                    UIhelper.ToastMessage(disconverBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        initVideoView();
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
                refreshFind.finishLoadMore();
            } else {
                refreshFind.finishLoadMoreWithNoMoreData();
            }

        });
        refreshFind.autoRefresh();
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        discoverRv.setLayoutManager(mLinearLayoutManager);
        mAdapter = new DiscoverAdapter(mVideos);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setCollectListener(this::onclick);
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
        config();

    }

    private void config() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.CONFIGURE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                Type type = new TypeToken<ConFigBean>() {
                }.getType();
                ConFigBean conFigBean = GsonHelper.gson.fromJson(response.body(), type);
                //返回码为成功时的处理
                if (conFigBean.getCode() == 0) {
                    for (int i = 0; i < conFigBean.getData().size(); i++) {
                        if (conFigBean.getData().get(i).getType().equals("shareText")) {
                            downUrl = conFigBean.getData().get(i).getContent();
                        }
                    }

                } else {
                    UIhelper.ToastMessage(conFigBean.getInfo());
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
    public void onclick(int position, int type) {
        if (type == 3) {
            AppUtils.copyToClipboard(activity, downUrl);
            Toast.makeText(activity, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveVideo(String id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("vid", id);
        OkGo.<String>post(URLs.RECORDS).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
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
}
