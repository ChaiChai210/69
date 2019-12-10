package com.colin.playerdemo.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.FeedbackActivity;
import com.colin.playerdemo.activity.HistoryActivity;
import com.colin.playerdemo.activity.Login_Activity;
import com.colin.playerdemo.activity.NotificationActivity;
import com.colin.playerdemo.activity.Popularize_Activity;
import com.colin.playerdemo.activity.U_Set_activity;
import com.colin.playerdemo.activity.VipActivity;
import com.colin.playerdemo.adapter.DownloadAdapter;
import com.colin.playerdemo.adapter.HistoryRecordAdapter;
import com.colin.playerdemo.adapter.MyFavoriteAdapter;
import com.colin.playerdemo.base.BaseFragment;
import com.colin.playerdemo.bean.ConFigBean;
import com.colin.playerdemo.bean.MineUserInfoBean;
import com.colin.playerdemo.bean.VideoBean;
import com.colin.playerdemo.customeview.third.RoundImageView;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.SPUtils;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {


    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.vip_tv)
    TextView vipTv;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.tv_tv)
    TextView tvTv;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.iv_lever)
    ImageView ivLever;
    @BindView(R.id.tv_promotion)
    TextView tvPromotion;
    @BindView(R.id.rl_promotion)
    RelativeLayout rlPromotion;
    @BindView(R.id.iv_promotion)
    ImageView ivPromotion;
    @BindView(R.id.ll_promotion)
    LinearLayout llPromotion;
    @BindView(R.id.feedback_layout)
    LinearLayout feedbackLayout;
    @BindView(R.id.notification_layout)
    LinearLayout notificationLayout;
    @BindView(R.id.ll_chat)
    LinearLayout llChat;
    @BindView(R.id.ads_iv)
    ImageView adsIv;
    @BindView(R.id.history_iv)
    ImageView historyIv;
    @BindView(R.id.history_tv)
    TextView historyTv;
    @BindView(R.id.hostory_num_tv)
    TextView hostoryNumTv;
    @BindView(R.id.history_rv)
    RecyclerView historyRv;
    @BindView(R.id.history_layout)
    RelativeLayout historyLayout;
    @BindView(R.id.download_iv)
    ImageView downloadIv;
    @BindView(R.id.download_tv)
    TextView downloadTv;
    @BindView(R.id.down_num_tv)
    TextView downNumTv;
    @BindView(R.id.download_rv)
    RecyclerView downloadRv;
    @BindView(R.id.download_layout)
    RelativeLayout downloadLayout;
    @BindView(R.id.favor_iv)
    ImageView favorIv;
    @BindView(R.id.favor_tv)
    TextView favorTv;
    @BindView(R.id.tv_favor_num)
    TextView tvFavorNum;
    @BindView(R.id.favor_rv)
    RecyclerView favorRv;
    @BindView(R.id.love_layout)
    RelativeLayout loveLayout;
    @BindView(R.id.center_head_image)
    RoundImageView centerHeadImage;
    @BindView(R.id.center_head_b_image)
    RoundImageView centerHeadBImage;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.mine_layout)
    RelativeLayout mineLayout;
    @BindView(R.id.refresh_mine)
    SmartRefreshLayout refreshMine;

    private HistoryRecordAdapter historyRecordAdapter;
    private MyFavoriteAdapter myFavoriteAdapter;
    private DownloadAdapter downloadAdapter;
    private List<MineUserInfoBean.RecordstlistBean> recordstlistBeans = new ArrayList<>();
    private List<MineUserInfoBean.CollectlistBean> collectlistBeans = new ArrayList<>();
    private List<VideoBean> videoBeans = new ArrayList<>();

    private String chatText;
    private MineUserInfoBean userInfoBean;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        historyRv.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        historyRecordAdapter = new HistoryRecordAdapter(recordstlistBeans);
        historyRv.setAdapter(historyRecordAdapter);
        downloadRv.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        downloadAdapter = new DownloadAdapter(videoBeans);
        downloadRv.setAdapter(downloadAdapter);
        favorRv.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        myFavoriteAdapter = new MyFavoriteAdapter(collectlistBeans);
        favorRv.setAdapter(myFavoriteAdapter);


        Animation operatingAnim = AnimationUtils.loadAnimation(activity, R.anim.rotate_anim);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ivPromotion.setAnimation(operatingAnim);
        ivPromotion.startAnimation(operatingAnim);

        refreshMine.setOnRefreshListener(refreshlayout -> {
            getData();
            refreshMine.finishRefresh(1000);
        });
        refreshMine.setOnLoadMoreListener(refreshLayout -> refreshMine.finishLoadMore());
        getData();
    }

    private void getData() {
        config();
//        scanFile(activity, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/vod");
        getUserInfo();
//        getFilePath();
    }

    private void getUserInfo() {

        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.MINEUSERINFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类

                Type type = new TypeToken<BaseBean<MineUserInfoBean>>() {
                }.getType();
                BaseBean<MineUserInfoBean> beanBaseBean = GsonHelper.gson.fromJson(response.body(), type);
                if (beanBaseBean.getCode() == 0) {
//                        setView(s.getData());
                    userInfoBean = beanBaseBean.getData();
                    setUser(userInfoBean);
                } else {
                    FancyToast.makeText(activity, beanBaseBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
                UIhelper.stopLoadingDialog();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void setUser(MineUserInfoBean userInfoBean) {
        Glide.with(activity).applyDefaultRequestOptions(new RequestOptions().error(R.mipmap.ic_head_l))
                .load(userInfoBean.getUserinfo().getPortrait()).into(centerHeadImage);
//        Tools.setSharedPreferencesValues(activity, "head", userInfoBean.getUserinfo().getPortrait());
        if (null != userInfoBean.getPresentlevel()) {
            loginTv.setText(userInfoBean.getPresentlevel().getLevel_name());
        } else {
            loginTv.setText("小白");
        }
        if (!StringUtils.isEmpty(userInfoBean.getUserinfo().getPhone())) {
            nameTv.setText(userInfoBean.getUserinfo().getPhone());
        }
        if (userInfoBean.getUserinfo().getSex().equals("1")) {
            Drawable dra = activity.getResources().getDrawable(R.mipmap.boy_comment_mark);
            dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
            nameTv.setCompoundDrawables(dra, null, null, null);
        } else {
            Drawable dra = activity.getResources().getDrawable(R.mipmap.girl_comment_mark);
            dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
            nameTv.setCompoundDrawables(dra, null, null, null);
        }
        if (userInfoBean.getUserinfo().getVideo_avail_day() < userInfoBean.getUserinfo().getVideo_avail()) {
            numTv.setText(userInfoBean.getUserinfo().getVideo_avail() - userInfoBean.getUserinfo().getVideo_avail_use() + "/" + userInfoBean.getUserinfo().getVideo_avail());

        } else {
            numTv.setText((userInfoBean.getUserinfo().getVideo_avail_day() - userInfoBean.getUserinfo().getVideo_avail_use()) + "/" + userInfoBean.getUserinfo().getVideo_avail_day());
        }
        if (userInfoBean.getAdname().size() > 0) {
            Glide.with(activity).applyDefaultRequestOptions(new RequestOptions().error(R.mipmap.ads))
                    .load(userInfoBean.getAdname().get(0).getPic()).into(adsIv);
        } else {
            adsIv.setVisibility(View.GONE);
        }
        if (null != userInfoBean.getNextlevel() && null != userInfoBean.getPresentlevel()) {
            tvPromotion.setText("下一等级还差" + (userInfoBean.getNextlevel().getShare_num() - userInfoBean.getPresentlevel().getShare_num()) + "人");
        } else if (null != userInfoBean.getPresentlevel()) {
            tvPromotion.setText("当前已经是最高级");
        }
        if (null != userInfoBean.getPresentlevel()) {
            switch (userInfoBean.getPresentlevel().getId()) {
                case 0:
                    centerHeadBImage.setImageResource(R.mipmap.ic_none);
                    break;
                case 1:
                    centerHeadBImage.setImageResource(R.mipmap.icon_level1);
                    break;
                case 2:
                    centerHeadBImage.setImageResource(R.mipmap.icon_level2);
                    break;
                case 3:
                    centerHeadBImage.setImageResource(R.mipmap.icon_level3);
                    break;
                case 4:
                    centerHeadBImage.setImageResource(R.mipmap.icon_level4);
                    break;
                case 5:
                    centerHeadBImage.setImageResource(R.mipmap.icon_level5);
                    break;
                case 6:
                    centerHeadBImage.setImageResource(R.mipmap.icon_level6);
                    break;

            }

        }
        hostoryNumTv.setText("目前历史观看过" + userInfoBean.getRecordstlist_count() + "部");
        tvFavorNum.setText("目前已有喜欢" + userInfoBean.getCollectlist_count() + "部");
//       recordstlistBeans.clear();
//       recordstlistBeans.addAll(userInfoBean.getRecordstlist());
//       historyRecordAdapter.notifyDataSetChanged();
//       collectlistBeans.clear();
//       collectlistBeans.addAll(userInfoBean.getCollectlist());
//       myFavoriteAdapter.notifyDataSetChanged();
        historyRecordAdapter.replaceData(userInfoBean.getRecordstlist());
        myFavoriteAdapter.replaceData(userInfoBean.getCollectlist());
    }

    @OnClick({R.id.iv_setting, R.id.rl_promotion, R.id.feedback_layout, R.id.notification_layout,
            R.id.history_layout, R.id.download_layout, R.id.love_layout, R.id.vip_tv, R.id.ll_promotion, R.id.ads_iv, R.id.ll_chat, R.id.name_tv, R.id.login_tv})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_setting://设置
//                if (!Login_Activity.checkLogin(activity)) {
//                    return;
//                }
                activity.startActivity(new Intent(activity, U_Set_activity.class));
                break;
            case R.id.name_tv:
            case R.id.login_tv:
                if (!Login_Activity.checkLogin(activity)) {
                    return;
                }
//                activity.startActivity(new Intent(activity, Login_Activity.class));
                break;
            case R.id.rl_promotion://推广
            case R.id.ll_promotion://推广
                activity.startActivity(new Intent(activity, Popularize_Activity.class));
                break;
            case R.id.feedback_layout://反馈
                activity.startActivity(new Intent(activity, FeedbackActivity.class));
                break;
            case R.id.notification_layout://通知
                activity.startActivity(new Intent(activity, NotificationActivity.class));
                break;
            case R.id.history_layout://历史记录
                activity.startActivity(new Intent(activity, HistoryActivity.class));
                break;
            case R.id.download_layout://缓存
//                if (null != videoBeans && videoBeans.size() > 0) {
//                    activity.startActivity(new Intent(activity, Cache_Activity.class));
//                }
                break;
            case R.id.love_layout://我的喜欢
//                activity.startActivity(new Intent(activity, FavoriteActivity.class));
                break;
            case R.id.vip_tv://VIP等级兑换
                activity.startActivity(new Intent(activity, VipActivity.class).putExtra("phone", userInfoBean.getUserinfo().getPhone()));
                break;
            case R.id.ads_iv://广告
                if (userInfoBean.getAdname().size() > 0 && !StringUtils.isEmpty(userInfoBean.getAdname().get(0).getUrl())) {
                    Uri uri = Uri.parse(userInfoBean.getAdname().get(0).getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    UIhelper.addClickAdRecord(activity, userInfoBean.getAdname().get(0).getId());
                }

                break;
            case R.id.ll_chat://交流群
                Uri uri = Uri.parse(chatText);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
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
                    setConfig(conFigBean.getData());
                } else {
                    UIhelper.ToastMessage(conFigBean.getInfo());
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

    private void setConfig(List<ConFigBean.DataBean> dataBeans) {
        int size = dataBeans.size();
        for (int i = 0; i < size; i++) {
            if (dataBeans.get(i).getType().equals("hop_group")) {
                chatText = dataBeans.get(i).getContent();
            }
        }
    }

}
