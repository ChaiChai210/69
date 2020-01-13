package com.colin.tomvod.activity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.tomvod.R;
import com.colin.tomvod.base.CommonImmerseActivity;
import com.colin.tomvod.bean.VideoDownLoad;
import com.colin.tomvod.download.FileUtils;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;

import org.litepal.LitePal;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Cache_Play_Activity extends CommonImmerseActivity {

    @BindView(R.id.player)
    VideoView mVideoView;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;

    private VideoDownLoad item;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_play_local;
    }

    @Override
    protected void initView() {
        if (getIntent().hasExtra("id")) {
            long id = getIntent().getLongExtra("id", -1);
            item = LitePal.find(VideoDownLoad.class, id);
        }
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent(getString(R.string.str_local), false);
        mVideoView.setVideoController(controller);

        if (item != null) {
            File file = FileUtils.getVideo(item.getName());
            mVideoView.setUrl("file:///" + file.getPath());
        }
        mVideoView.start();
        tvCenter.setText("播放视频");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }

    }

    @Override
    public void onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
