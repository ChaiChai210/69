package com.colin.tomvod.activity;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhon.appupdate.utils.FileUtil;
import com.colin.tomvod.R;
import com.colin.tomvod.adapter.CacheAdapter1;
import com.colin.tomvod.adapter.Cache_Aadapter;
import com.colin.tomvod.base.CommonImmerseActivity;
import com.colin.tomvod.bean.VideoBean;
import com.colin.tomvod.bean.VideoDownLoad;
import com.colin.tomvod.download.FileUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Cache_Activity extends CommonImmerseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.tv_center)
    TextView activityTitleIncludeCenterTv;
    @BindView(R.id.tv_right)
    TextView activityTitleIncludeRightTv;
    @BindView(R.id.iv_right)
    ImageView activityTitleIncludeRightIv;
    @BindView(R.id.cache_rv)
    RecyclerView cacheRv;
    @BindView(R.id.refresh_find)
    SmartRefreshLayout refreshFind;
    Cache_Aadapter adapter;
    public static List<Boolean> booleanslsit = new ArrayList<>();
    @BindView(R.id.all_change_tv)
    TextView allChangeTv;
    @BindView(R.id.delelte_tv)
    TextView delelteTv;
    @BindView(R.id.cache_bottom_layout)
    LinearLayout cacheBottomLayout;
    @BindView(R.id.cache_layout)
    LinearLayout cache_layout;
    @BindView(R.id.no_layout)
    LinearLayout no_layout;//空白
    @BindView(R.id.text_tv)
    TextView text_tv;
    @BindView(R.id.live_tv)
    TextView liveTv;
    @BindView(R.id.like_content_tv)
    TextView likeContentTv;

    private File filestes;

    private List<String> newList;
    private List<String> vodeList;
    private List<VideoBean> videoBeans;

    private Boolean show = false;
    private Boolean allshow = false;
    CacheAdapter1 adapter1;
    private boolean edit;
    List<VideoDownLoad> videos = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cache;
    }


    @Override
    protected void initView() {
        activityTitleIncludeCenterTv.setText("离线缓存");
        activityTitleIncludeRightTv.setText("编辑");
        darkImmerseFontColor();
        cacheRv.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new Cache_Aadapter();

//        adapter.setItemOnclickListener(position -> startActivity(new Intent(Cache_Activity.this, Cache_Play_Activity.class).putExtra("bean", videoBeans.get(position))));
    }

    @Override
    public void initData() {
//        getFilePath();
        videos = LitePal.findAll(VideoDownLoad.class);
        adapter1 = new CacheAdapter1(videos);
        cacheRv.setAdapter(adapter1);
    }


    @OnClick({R.id.iv_left, R.id.tv_right, R.id.all_change_tv, R.id.delelte_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                show = !show;
                if (show) {
                    cacheBottomLayout.setVisibility(View.VISIBLE);
                    activityTitleIncludeRightTv.setText("取消");
                    adapter1.setEdit(true);
                } else {
                    cacheBottomLayout.setVisibility(View.GONE);
                    activityTitleIncludeRightTv.setText("编辑");
                    adapter1.setEdit(false);
                }
//                adapter.setBooleanslsit(show);

                break;
            case R.id.all_change_tv:
                allshow = !allshow;
//                for (int i = 0; i < booleanslsit.size(); i++) {
//                    booleanslsit.set(i, allshow);
//                }
//                adapter.setBooleanslsit(show);
                for (VideoDownLoad item : videos) {
                    item.setSelected(allshow);
                }
                adapter1.notifyDataSetChanged();
                break;
            case R.id.delelte_tv://删除
//                String dirName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath();
//                String fileName = "/vod.txt";
//                filestes = new File(dirName + fileName);
//                filestes.delete();
//                for (int i = 0; i < booleanslsit.size(); i++) {
//                    if (booleanslsit.get(i)) {
//                        deleteFileVideo(videoBeans.get(i).getPlayUrl());
//                        deleteText(videoBeans.get(i).getNewList());
//                    }
//                }
//                getFilePath();
                Iterator<VideoDownLoad> iterator = videos.iterator();
                while (iterator.hasNext()) {
                    VideoDownLoad next = iterator.next();
                    if (next.isSelected()) {
                        File file = FileUtils.getVideo(next.getName());
                        file.delete();
                        LitePal.delete(VideoDownLoad.class, next.getId());
                        iterator.remove();
                    }
                }
                adapter1.notifyDataSetChanged();
//                show = false;
//                activityTitleIncludeRightTv.setText("编辑");
//                cacheBottomLayout.setVisibility(View.GONE);
                break;

        }
    }

    void deleteFileVideo(String f) {
        File files = new File(f);
        files.delete();
    }

    void deleteText(String strcontent) {
        try {
            String strContent = strcontent + "\r\n";
            filestes.createNewFile();
            RandomAccessFile raf = new RandomAccessFile(filestes, "rwd");
            raf.seek(filestes.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getFilePath() {
        videoBeans = new ArrayList<>();
        vodeList = new ArrayList<>();
        booleanslsit.clear();
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath() + "/vod";
        File[] files = new File(directory).listFiles(pathname -> pathname.getName().endsWith(".mp4"));
        if (files == null) {
            return;
        }
        for (File file : files) {
            vodeList.add(file.getPath());
            Log.e("this", "file==" + file);
            VideoBean videoBean = new VideoBean();
            videoBean.setName(file.getName());
            videoBean.setLength(getVideoDuration(file.getPath()));
            videoBean.setUrl("22");
            videoBean.setNewList("22");
            videoBean.setPlayUrl("22");
            videoBeans.add(videoBean);
            booleanslsit.add(false);
        }
//        newList = FileUtils.ReadTxtFile(directory + "/vod.txt");
//
//        for (int i = 0; i < newList.size(); i++) {
//            String[] s = newList.get(i).split("_");
//            try {
//                for (int j = 0; j < vodeList.size(); j++) {
//                    String[] v = vodeList.get(j).split("/");
//                    String[] k = v[v.length - 1].split("_");
//                    if (s.length > 0 && s[s.length - 1].contains(k[0])) {
//                        VideoBean videoBean = new VideoBean();
//                        videoBean.setName(s[1]);
//                        videoBean.setLength(getVideoDuration(vodeList.get(j)));
//                        videoBean.setUrl(s[0]);
//                        videoBean.setNewList(newList.get(i));
//                        videoBean.setPlayUrl(vodeList.get(j));
//                        videoBeans.add(videoBean);
//                        booleanslsit.add(false);
//
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        adapter.setVideoBeans(videoBeans);
        if (videoBeans.size() > 0) {
            no_layout.setVisibility(View.GONE);
            text_tv.setVisibility(View.GONE);
        } else {
            no_layout.setVisibility(View.VISIBLE);
            text_tv.setVisibility(View.VISIBLE);
        }
        Log.v("this", "videoBeans==" + videoBeans.size());
    }

    //获取视频总时长
    public static String getVideoDuration(String path) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path);
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION); //
        return duration;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
