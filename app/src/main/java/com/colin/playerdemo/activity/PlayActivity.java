package com.colin.playerdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.PlayCommentAdapter;
import com.colin.playerdemo.adapter.PlayGuessLikeAdapter;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.ChildPlayCommentBean;
import com.colin.playerdemo.bean.ConFigBean;
import com.colin.playerdemo.bean.MineUserInfoBean;
import com.colin.playerdemo.bean.PlayCommentBean;
import com.colin.playerdemo.bean.PlayDetailBean;
import com.colin.playerdemo.customeview.third.RoundImageView;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.BaseListBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.popwindows.CommentDetailPopup;
import com.colin.playerdemo.popwindows.VideoIntroPopup;
import com.colin.playerdemo.utils.AppUtils;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.qfxl.view.RoundProgressBar;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayActivity extends BaseActivity implements PlayCommentAdapter.PlayCommentListener {
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
    @BindView(R.id.iv_unlike)
    ImageView ivUnlike;
    @BindView(R.id.play_pBar)
    ProgressBar playPBar;
    @BindView(R.id.iv_like)
    ImageView ivLike;
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
    @BindView(R.id.iv_head)
    RoundImageView ivHead;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.tv_send_comment)
    TextView tvSendComment;
    @BindView(R.id.play_send_layout)
    LinearLayout playSendLayout;
    @BindView(R.id.iv_left)
    ImageView ivLeft;


    private String id;
    private MineUserInfoBean userInfoBean;
    BaseBean<MineUserInfoBean> mineUserInfoBeanBaseBean;
    private PlayGuessLikeAdapter guessLikeAdapter;

    private BaseBean<PlayDetailBean> playDetailBeanBaseBean;
    private PlayDetailBean playDetailBean;
    //后台传的，广告播放时间。
    private int playDuration;
    private PlayCommentAdapter commentAdapter;
    //    private PlayCommentDetailAdapter commentDetailAdapter;
    private List<PlayCommentBean> commentList = new ArrayList<>();
    private int page = 1;
    private String mode = "zan";//(支持参数 time 时间排序,zan 赞排序)
    private List<ChildPlayCommentBean> childlist = new ArrayList<>();
    private String comment_id;
    private String downUrl;
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
//        commentDetailAdapter = new PlayCommentDetailAdapter(childlist);

//        rvCommentDetail.setLayoutManager(new LinearLayoutManager(this));
//        rvCommentDetail.setNestedScrollingEnabled(false);
//        rvCommentDetail.setAdapter(commentDetailAdapter);
//        commentDetailAdapter.setChildOnclick(this);


        rpbGg.setDirection(RoundProgressBar.Direction.FORWARD);
        rpbGg.setProgressChangeListener(new RoundProgressBar.ProgressChangeListener() {
            @Override
            public void onFinish() {
                if (flLoadGg != null) {
                    flLoadGg.setVisibility(View.GONE);
                }
                if (mVideoView != null && !mVideoView.isPlaying()) {
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
        config();
        getUserInfo();
        getComment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mVideoView != null) {
            mVideoView.release();
        }
        id = intent.getStringExtra("id");
        getUserInfo();
        getComment();
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

    private void getComment() {

        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "home");
        httpParams.put("av_id", id);
        httpParams.put("page", page);
        httpParams.put("mode", mode);
        httpParams.put("page_size", 10);
        OkGo.<String>get(URLs.COMMENT).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<PlayCommentBean>>() {
                }.getType();
                BaseListBean<PlayCommentBean> beanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
//                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (beanBaseListBean.getResCode() == 0) {
                    if (page == 1) {
                        commentList = beanBaseListBean.getData();
                    } else {
                        commentList.addAll(beanBaseListBean.getData());
                    }
                    commentAdapter = new PlayCommentAdapter(commentList, -1);
                    commentAdapter.setplayCommentListener(PlayActivity.this);
                    rvComment.setAdapter(commentAdapter);

                } else {

                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
//                UIhelper.showLoadingDialog(Play_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
//                UIhelper.stopLoadingDialog();

            }
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
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.MINEUSERINFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<MineUserInfoBean>>() {
                }.getType();
                mineUserInfoBeanBaseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (mineUserInfoBeanBaseBean.getCode() == 0) {
                    userInfoBean = mineUserInfoBeanBaseBean.getData();
                    Glide.with(PlayActivity.this).load(userInfoBean.getUserinfo().getPortrait()).into(ivHead);
                    getVideoInfo();
                } else {
                    FancyToast.makeText(PlayActivity.this, mineUserInfoBeanBaseBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(PlayActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void getVideoInfo() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        OkGo.<String>post(URLs.VIDEO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<PlayDetailBean>>() {
                }.getType();
                playDetailBeanBaseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (playDetailBeanBaseBean.getCode() == 0) {
                    playDetailBean = playDetailBeanBaseBean.getData();
                    setPlayer(playDetailBean);
                } else {
                    FancyToast.makeText(PlayActivity.this, playDetailBeanBaseBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                UIhelper.showLoadingDialog(PlayActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();
            }
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

        controller.addControlComponent(new CompleteView(this));//自动完成播放界面
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

//    private void saveVideo() {
//        RxHttp.setDebug(true);
//        RxHttpUtils.postWithToken(Api.video_watch_record)
//                .add("vid", id)
//                .asResponse(Object.class)
//                .as(RxLife.asOnMain(this))//返回String类型
//                .subscribe(s -> {          //订阅观察者，
//                }, throwable -> {
//                });
//    }

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
        putComment(commentList.get(position).getId() + "", islike);
    }

    private String type = "comment";

    private void putComment(String comment_id, int islike) {
        HttpParams httpParams = new HttpParams();
        String url;
        url = URLs.COMMENT + "/" + comment_id;
        httpParams.put("type", "comment");
        httpParams.put("islike", islike);
        OkGo.<String>put(url).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
//                UIhelper.stopLoadingDialog();
                Type types = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBeana = GsonHelper.gson.fromJson(response.body(), types);

                if (baseBeana.getCode() == 0) {
                    page = 1;
                    getComment();
                } else {
//                    UIhelper.ToastMessage(baseBeana.getMsg());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
//                UIhelper.showLoadingDialog(Play_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
//                UIhelper.stopLoadingDialog();
            }
        });
    }


    @Override
    public void showClick(PlayCommentBean playCommentBean) {

        CommentDetailPopup popup = new CommentDetailPopup(this, id, playCommentBean, mode);
//        popup.setAlignBackground(true);
        popup.showPopupWindow(mVideoView);
        popup.linkTo(mVideoView);

    }

    @Override
    public void childClick(String id, String to_uid) {
//        playSendLayout.setVisibility(View.VISIBLE);
        this.to_uid = to_uid;
    }

    @OnClick({R.id.ads_iv, R.id.title_layout, R.id.favor_iv, R.id.send_iv, R.id.love_more_tv, R.id.new_tv, R.id.hot_tv, R.id.tv_send_comment,R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ads_iv:
                if (null != playDetailBean.getAdvertisement() && !StringUtils.isEmpty(playDetailBean.getAdvertisement().getUrl())) {
                    UIhelper.addClickAdRecord(PlayActivity.this, playDetailBean.getAdvertisement().getId());
                    Uri uri = Uri.parse(playDetailBean.getAdvertisement().getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                break;
            case R.id.title_layout:
                new VideoIntroPopup(this, playDetailBean).showPopupWindow(mVideoView);
                break;
            case R.id.favor_iv:
                if (playDetailBean.isIs_collect()) {
                    deleteCollectList();
                } else {
                    collect();
                }
                break;
            case R.id.send_iv://
                AppUtils.copyToClipboard(this, downUrl);
                Toast.makeText(this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
                break;
            case R.id.love_more_tv://
                startActivity(new Intent(this, Class_activity.class).putExtra("id", playDetailBean.getClassify_id() + ""));
                break;
            case R.id.new_tv:
                mode = "time";//(支持参数 time 时间排序,zan 赞排序)
                page = 1;
                getComment();
                newTv.setBackground(getResources().getDrawable(R.drawable.coumm_change_shade));
                hotTv.setBackground(null);
                hotTv.setTextColor(Color.parseColor("#333333"));
                newTv.setTextColor(Color.parseColor("#ffffff"));
                break;
            case R.id.hot_tv:
                mode = "zan";//(支持参数 time 时间排序,zan 赞排序)
                page = 1;
                getComment();
                hotTv.setBackground(getResources().getDrawable(R.drawable.coumm_change_shade));
                newTv.setBackground(null);
                newTv.setTextColor(Color.parseColor("#333333"));
                hotTv.setTextColor(Color.parseColor("#ffffff"));
                break;
            case R.id.tv_send_comment:
                String cotent = etComment.getText().toString();
                if (StringUtils.isEmpty(cotent)) {
                    Toast.makeText(this, "请输入评论内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                postComment(cotent);
                UIhelper.hideSoftKeyboard(PlayActivity.this);
                break;
        }
    }

    /**
     * 添加评论
     */

    private void postComment(String content) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("av_id", id);
        httpParams.put("content", content);
        httpParams.put("type", "comment");
        OkGo.<String>post(URLs.COMMENT).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBeana = GsonHelper.gson.fromJson(response.body(), type);

                //返回码为成功时的处理
                if (baseBeana.getCode() == 0) {
                    mode = "time";//(支持参数 time 时间排序,zan 赞排序)
                    page = 1;
                    getComment();
                } else {
                    UIhelper.ToastMessage(baseBeana.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(PlayActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void collect() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("vid", id);
        OkGo.<String>post(URLs.COLLECT).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBeana = GsonHelper.gson.fromJson(response.body(), type);

                //返回码为成功时的处理
                if (baseBeana.getCode() == 0) {
                    favorIv.setImageResource(R.mipmap.favor_press);
                    playDetailBean.setIs_collect(true);
                } else {
                    FancyToast.makeText(PlayActivity.this, "收藏失败，稍后重试", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
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
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void deleteCollectList() {
        HttpParams httpParams = new HttpParams();
        String url;
        url = URLs.COLLECT + "/" + id;
        OkGo.<String>delete(url).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBeans = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (baseBeans.getCode() == 0) {
                    favorIv.setImageResource(R.mipmap.favor_nopress);
                    playDetailBean.setIs_collect(false);
                } else {
                    FancyToast.makeText(PlayActivity.this, "删除失败，稍后重试", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
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
//                UIhelper.stopLoadingDialog();
            }
        });
    }


    @OnClick()
    public void onViewClicked() {
    }
}
