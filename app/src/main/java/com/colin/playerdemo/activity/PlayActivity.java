package com.colin.playerdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.PlayCommentAdapter;
import com.colin.playerdemo.adapter.PlayCommentDetailAdapter;
import com.colin.playerdemo.adapter.PlayGuessLikeAdapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.ChildPlayCommentBean;
import com.colin.playerdemo.bean.MineUserInfoBean;
import com.colin.playerdemo.bean.PlayCommentBean;
import com.colin.playerdemo.bean.PlayDetailBean;
import com.colin.playerdemo.customeview.third.RoundImageView;
import com.colin.playerdemo.net.Api;
import com.colin.playerdemo.net.BaseResponseBean;
import com.colin.playerdemo.net.CommonParser;
import com.colin.playerdemo.net.RxHttpUtils;
import com.colin.playerdemo.popwindows.CommentDetailPopup;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.qfxl.view.RoundProgressBar;
import com.rxjava.rxlife.RxLife;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class PlayActivity extends BaseActivity implements PlayCommentAdapter.PlayCommentListener, PlayCommentDetailAdapter.CommentListener {
    private static final String THUMB = "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg";
    private static final String VOD_URL = "https://sys-v.hgpjc.com/6597338118197911552/720p/mp4/Mds5A9GELOlv.mp4";
    @BindView(R.id.player)
    VideoView mVideoView;
    @BindView(R.id.status_bar_view)
    View view;
    @BindView(R.id.img_gg)
    ImageView imgGg;
    @BindView(R.id.rpb_gg)
    RoundProgressBar rpbGg;
    @BindView(R.id.fl_load_gg)
    FrameLayout flLoadGg;
    @BindView(R.id.ads_iv)
    ImageView adsIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tiem_tv)
    TextView tiemTv;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.unpraise_iv)
    ImageView unpraiseIv;
    @BindView(R.id.play_pBar)
    ProgressBar playPBar;
    @BindView(R.id.normal_iv)
    ImageView normalIv;
    @BindView(R.id.zan_num_tv)
    TextView zanNumTv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.play_num_tv)
    TextView playNumTv;
    @BindView(R.id.send_iv)
    ImageView sendIv;
    @BindView(R.id.download_nopress_iv)
    ImageView downloadNopressIv;
    @BindView(R.id.favor_iv)
    ImageView favorIv;
    @BindView(R.id.dic_layout)
    RelativeLayout dicLayout;
    @BindView(R.id.rv_guess_like)
    RecyclerView rvGuessLike;
    @BindView(R.id.love_more_tv)
    TextView loveMoreTv;
    @BindView(R.id.p_num_tv)
    TextView pNumTv;
    @BindView(R.id.new_tv)
    TextView newTv;
    @BindView(R.id.hot_tv)
    TextView hotTv;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.play_layout)
    LinearLayout playLayout;
    @BindView(R.id.title_tv1)
    TextView titleTv1;
    @BindView(R.id.close_iv)
    ImageView closeIv;
    @BindView(R.id.play_cnum_tv)
    TextView playCnumTv;
    @BindView(R.id.tag_rv)
    RecyclerView tagRv;
    @BindView(R.id.jianjie_tv)
    TextView jianjieTv;
    @BindView(R.id.child_layout)
    LinearLayout childLayout;
    @BindView(R.id.comment_close_iv)
    ImageView commentCloseIv;
    @BindView(R.id.head_image)
    RoundImageView headImage;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.show_content_tv)
    TextView showContentTv;
    @BindView(R.id.rv_comment_detail)
    RecyclerView rvCommentDetail;
    @BindView(R.id.ll_comment_detail)
    LinearLayout llCommentDetail;
    @BindView(R.id.iv_head)
    RoundImageView ivHead;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.tv_send_comment)
    TextView tvSendComment;
    @BindView(R.id.play_send_layout)
    LinearLayout playSendLayout;


    private String id;
    private MineUserInfoBean userInfoBean;
    private PlayGuessLikeAdapter guessLikeAdapter;

    private PlayDetailBean playDetailBean;
    //后台传的，广告播放时间。
    private int playDuration;
    private PlayCommentAdapter commentAdapter;
    private PlayCommentDetailAdapter commentDetailAdapter;
    private List<PlayCommentBean> commentList = new ArrayList<>();
    private int page = 1;
    private String mode = "zan";//(支持参数 time 时间排序,zan 赞排序)
    private List<ChildPlayCommentBean> childlist = new ArrayList<>();
    private String comment_id;
    /**
     * 添加评论
     */
    private String to_uid, reply_id;

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, PlayActivity.class);

        intent.putExtra("id", id);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_player;
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra("id");
        rvGuessLike.setLayoutManager(new LinearLayoutManager(this));
        rvGuessLike.setNestedScrollingEnabled(false);
        guessLikeAdapter = new PlayGuessLikeAdapter(R.layout.item_guess_like);
        rvGuessLike.setAdapter(guessLikeAdapter);

        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setNestedScrollingEnabled(false);
        commentDetailAdapter = new PlayCommentDetailAdapter(childlist);

        rvCommentDetail.setLayoutManager(new LinearLayoutManager(this));
        rvCommentDetail.setNestedScrollingEnabled(false);
        rvCommentDetail.setAdapter(commentDetailAdapter);
        commentDetailAdapter.setChildOnclick(this);

        tagRv.setLayoutManager(new GridLayoutManager(this, 4));

        rpbGg.setDirection(RoundProgressBar.Direction.FORWARD);
        rpbGg.setProgressChangeListener(new RoundProgressBar.ProgressChangeListener() {
            @Override
            public void onFinish() {
                flLoadGg.setVisibility(View.GONE);
                if (!mVideoView.isPlaying()) {
                    mVideoView.start();
                }
            }

            @Override
            public void onProgressChanged(int progress) {
                int text = playDuration - progress * playDuration / 100;
                rpbGg.setCenterText(text + "");
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        getUserInfo();
        getComment();
    }


    private void getComment() {
        RxHttpUtils.getWithToken(Api.comment)
                .add("type", "home")
                .add("av_id", id)
                .add("page", page)
                .add("mode", mode)
                .add("page_size", 10)
                .asDataListParser(PlayCommentBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (page == 1) {
                        commentList = s;
                    } else {
                        commentList.addAll(s);
                    }
                    commentAdapter = new PlayCommentAdapter(commentList, -1);
                    commentAdapter.setplayCommentListener(this);
                    rvComment.setAdapter(commentAdapter);
                }, throwable -> {
                });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarView(view)
                .statusBarColorInt(getResources().getColor(R.color.full_transparent)).statusBarDarkFont(false, 1f)
                .navigationBarColor(R.color.colorPrimary)
                .keyboardEnable(true)
                .init();
    }

    private void getUserInfo() {
        RxHttpUtils.getWithToken(Api.myinfo)
                .asParser(new CommonParser<MineUserInfoBean>(new TypeToken<BaseResponseBean<MineUserInfoBean>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (s.getCode() == 0) {
//                        setView(s.getData());
                        userInfoBean = s.getData();
                        Glide.with(this).load(userInfoBean.getUserinfo().getPortrait()).into(ivHead);
                        getVideoInfo();
                    } else {
                        FancyToast.makeText(PlayActivity.this, s.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }

                }, throwable -> {
                });
    }

    private void getVideoInfo() {
        RxHttp.setDebug(true);
        RxHttpUtils.getWithToken(Api.video)
                .add("id", id)
                .asParser(new CommonParser<PlayDetailBean>(new TypeToken<BaseResponseBean<PlayDetailBean>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (s.getCode() == 0) {
//                        saveVideo();
                        playDetailBean = s.getData();
                        setPlayer(s.getData());
                    } else {
                        FancyToast.makeText(PlayActivity.this, s.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }

                }, throwable -> {
                });
    }

    private void setPlayer(PlayDetailBean bean) {
        StandardVideoController controller = new StandardVideoController(this);
        //根据屏幕方向自动进入/退出全屏
        controller.setEnableOrientation(true);

        PrepareView prepareView = new PrepareView(this);//准备播放界面
        ImageView thumb = prepareView.findViewById(R.id.thumb);//封面图
        Glide.with(this).load(bean.getCover()).into(thumb);
        controller.addControlComponent(prepareView);


        controller.addControlComponent(new ErrorView(this));//错误界面

        TitleView titleView = new TitleView(this);//标题栏
        titleView.setTitle(bean.getName());
        controller.addControlComponent(titleView);


        VodControlView vodControlView = new VodControlView(this);//点播控制条
        //是否显示底部进度条。默认显示
//                vodControlView.showBottomProgress(false);
        controller.addControlComponent(vodControlView);
        GestureView gestureControlView = new GestureView(this);//滑动控制视图
        controller.addControlComponent(gestureControlView);
        //根据是否为直播决定是否需要滑动调节进度
        controller.setCanChangePosition(true);


        mVideoView.setVideoController(controller);

        mVideoView.setUrl(bean.getVideo_url());

        titleTv.setText(bean.getName());
        playNumTv.setText("播放: " + bean.getPlay_count() + "次播放");
        if (null != bean.getAdvertisement()) {
            Glide.with(this).load(bean.getAdvertisement().getPic()).into(adsIv);
        } else {
            adsIv.setVisibility(View.GONE);
        }
        tiemTv.setText(bean.getRun_date());
        numTv.setText(bean.getPlay_count() + "");
        playPBar.setMax(bean.getZan().getNo() + bean.getZan().getYes());
        playPBar.setProgress(bean.getZan().getYes());
        if (bean.getZan().getYes() + bean.getZan().getNo() == 0) {
            playPBar.setMax(100);
            playPBar.setProgress(1);
            zanNumTv.setText(0 + "%的人觉得很赞");
        } else {
            zanNumTv.setText((bean.getZan().getYes() * 100 / (bean.getZan().getYes() + bean.getZan().getNo())) + "%的人觉得很赞");
        }
        if (!bean.getPlay_advertisement().isPlay_hide()) {
            Glide.with(this).load(bean.getPlay_advertisement().getPic()).into(imgGg);
            playDuration = bean.getPlay_advertisement().getPlay_time();
            rpbGg.setCountDownTimeMillis(playDuration * 1000);
            rpbGg.start();
        } else {
            flLoadGg.setVisibility(View.GONE);
        }
        contentTv.setText(bean.getIntroduce());
        playNumTv.setText(bean.getComment_count() + "热评");
//                    adapter.setTag_list(bean.getTag_list());
        pNumTv.setText(bean.getComment_count() + "条");
        if (bean.isIs_collect()) {//（1为收藏过，0未收藏过）
            favorIv.setImageResource(R.mipmap.favor_press);
        } else {
            favorIv.setImageResource(R.mipmap.favor_nopress);
        }
        guessLikeAdapter.replaceData(bean.getYou_is_love());
    }

    private void saveVideo() {
        RxHttp.setDebug(true);
        RxHttpUtils.postWithToken(Api.video_watch_record)
                .add("vid", id)
                .asResponse(Object.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                }, throwable -> {
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void Onclick(int position, int islike) {
        putComment(commentList.get(position).getId() + "", islike, "comment");
    }

    private String type = "comment";

    private void putComment(String id, int islike, String comment) {
        String url = Api.comment + "/" + id;
        RxHttpUtils.postWithToken(url)
                .add("type", comment)
                .add("islike", islike)
                .asResponse(Object.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {
                    if (type.equals("reply")) {
                        getChildComment();
                    } else {
                        getComment();
                    }
                }, throwable -> {
                });
    }


    private void getChildComment() {
        RxHttpUtils.getWithToken(Api.comment)
                .add("comment_id", comment_id)
                .add("type", "reply")
                .add("av_id", id)
                .add("page", 1)
                .add("page_size", 10)
                .add("mode", mode)
                .asDataListParser(ChildPlayCommentBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {
                    childlist.addAll(s);
                    commentDetailAdapter.notifyDataSetChanged();
                }, throwable -> {
                });
    }

    @Override
    public void showClick(int position, String to_uid, PlayCommentBean playCommentBean) {
//        type = "reply";
//        this.to_uid = to_uid;
////        playSendLayout.setVisibility(View.VISIBLE);
//        playLayout.setVisibility(View.GONE);
//        llCommentDetail.setVisibility(View.VISIBLE);
//        reply_id = commentList.get(position).getId() + "";
//        comment_id = commentList.get(position).getId() + "";
//        Glide.with(this).load(playCommentBean.getPortrait()).into(headImage);
//        nameTv.setText(playCommentBean.getNickname());
//        timeTv.setText(playCommentBean.getCreate_time());
//        showContentTv.setText(playCommentBean.getContent() + "");
//        UIhelper.setGenderIcon(this, commentList.get(position).getSex(), nameTv);
//        getChildComment();
        new CommentDetailPopup(this).showPopupWindow(mVideoView);
    }

    @Override
    public void childClick(String id, String to_uid) {
//        playSendLayout.setVisibility(View.VISIBLE);
        this.to_uid = to_uid;
    }

    @OnClick({R.id.ads_iv, R.id.close_iv, R.id.comment_close_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ads_iv:
                if (null != playDetailBean.getAdvertisement() && !StringUtils.isEmpty(playDetailBean.getAdvertisement().getUrl())) {
                    UIhelper.addClickAdRecord(playDetailBean.getAdvertisement().getId());
                    Uri uri = Uri.parse(playDetailBean.getAdvertisement().getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                break;
            case R.id.close_iv:
                break;
            case R.id.comment_close_iv:
                type = "comment";
                playLayout.setVisibility(View.VISIBLE);
                llCommentDetail.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void childOnclick(int position, int islike) {
        type = "reply";
        reply_id = childlist.get(position).getId() + "";
        putComment(childlist.get(position).getId() + "", islike, "reply");
//        playSendLayout.setVisibility(View.VISIBLE);
//        Glide.with(this).load(head).into(send_image);
    }
}
