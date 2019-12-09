package com.colin.playerdemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.UserInfoBean;
import com.colin.playerdemo.customeview.third.RoundImageView;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;

public class Popularize_Activity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.tv_center)
    TextView activityTitleIncludeCenterTv;
    @BindView(R.id.tv_right)
    TextView activityTitleIncludeRightTv;
    @BindView(R.id.iv_right)
    ImageView activityTitleIncludeRightIv;
    @BindView(R.id.pop_image)
    RoundImageView popImage;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.code_tv)
    TextView codeTv;
    @BindView(R.id.leve_1_iv)
    ImageView leve1Iv;
    @BindView(R.id.leve_1_tv)
    TextView leve1Tv;
    @BindView(R.id.user_center_pBar)
    ProgressBar userCenterPBar;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.leve_2_iv)
    ImageView leve2Iv;
    @BindView(R.id.leve_2_tv)
    TextView leve2Tv;
    @BindView(R.id.kevel_1_iv)
    ImageView kevel1Iv;
    @BindView(R.id.kevel_1_name_iv)
    TextView kevel1NameIv;
    @BindView(R.id.kevel_2_iv)
    ImageView kevel2Iv;
    @BindView(R.id.kevel_2_name_iv)
    TextView kevel2NameIv;
    @BindView(R.id.kevel_3_iv)
    ImageView kevel3Iv;
    @BindView(R.id.kevel_3_name_iv)
    TextView kevel3NameIv;
    @BindView(R.id.kevel_4_iv)
    ImageView kevel4Iv;
    @BindView(R.id.kevel_4_name_iv)
    TextView kevel4NameIv;
    @BindView(R.id.popular_tv)
    TextView popularTv;
    @BindView(R.id.today_num_tv)
    TextView today_num_tv;
    @BindView(R.id.shenyu_tv)
    TextView shenyu_tv;
    @BindView(R.id.popularze_iv)
    ImageView popularze_iv;
    UserInfoBean userInfoBean;
    BaseBean<UserInfoBean> baseBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_popularize;
    }

    @Override
    protected void initView() {
        activityTitleIncludeRightTv.setText("我的推广");
        getUserInfo();
    }
    @OnClick({R.id.popular_tv, R.id.tv_right, R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.popular_tv:
                if (null != userInfoBean) {
                    startActivity(new Intent(this, Generalize_Activity.class).putExtra("code", userInfoBean.getInvite_code()));
                }
                break;
            case R.id.tv_right:
                startActivity(new Intent(this, My_Generalize_Activity.class));
                break;
            case R.id.iv_left:
                finish();
                break;

        }
    }
    private void getUserInfo() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.USERINFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<UserInfoBean>>() {
                }.getType();
                baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    userInfoBean = baseBean.getData();
                    Glide.with(Popularize_Activity.this).applyDefaultRequestOptions(new RequestOptions().error(R.mipmap.ic_head_l)).load(userInfoBean.getPortrait()).into(popularze_iv);
                    codeTv.setText("我的邀请码:" + userInfoBean.getInvite_code());
                    nameTv.setText(userInfoBean.getStatus());
                    if (null == userInfoBean.getThis_level()) {
                        leve1Tv.setText("小白");
                    } else {
                        leve1Tv.setText(userInfoBean.getThis_level().getLevel_name());
                    }
                    if (null != userInfoBean.getNext_level()) {
                        leve2Tv.setText(userInfoBean.getNext_level().getLevel_name() + "");
                        userCenterPBar.setMax(userInfoBean.getNext_level().getShare_num());
                        userCenterPBar.setProgress(userInfoBean.getNext_level().getShare_num() - userInfoBean.getNext_level_lack());
                    } else {
                        if (null != userInfoBean.getThis_level()) {
                            leve2Tv.setText(userInfoBean.getThis_level().getLevel_name());
                            userCenterPBar.setMax(userInfoBean.getThis_level().getShare_num());
                            userCenterPBar.setProgress(userInfoBean.getThis_level().getShare_num());
                            numTv.setText("当前等级已是最高");
                        }
                    }

                    if (baseBean.getData().getVideo_avail_day() < baseBean.getData().getVideo_avail()) {
                        shenyu_tv.setText(baseBean.getData().getVideo_avail() - baseBean.getData().getVideo_avail_use() + "");
                        today_num_tv.setText(userInfoBean.getVideo_avail() + "");
                    } else {
                        today_num_tv.setText(userInfoBean.getVideo_avail_day() + "");
                        shenyu_tv.setText((userInfoBean.getVideo_avail_day() - userInfoBean.getVideo_avail_use()) + "");
                    }
                    if (null != baseBean.getData().getThis_level()) {
                        switch (baseBean.getData().getThis_level().getId()) {
                            case 0:
                                leve1Iv.setImageResource(R.mipmap.ic_none);
                                leve2Iv.setImageResource(R.mipmap.icon_level1);
                                break;
                            case 1:
                                leve1Iv.setImageResource(R.mipmap.icon_level1);
                                leve2Iv.setImageResource(R.mipmap.icon_level2);
                                break;
                            case 2:
                                leve1Iv.setImageResource(R.mipmap.icon_level2);
                                leve2Iv.setImageResource(R.mipmap.icon_level3);
                                break;
                            case 3:
                                leve1Iv.setImageResource(R.mipmap.icon_level3);
                                leve2Iv.setImageResource(R.mipmap.icon_level4);
                                break;
                            case 4:
                                leve1Iv.setImageResource(R.mipmap.icon_level4);
                                leve2Iv.setImageResource(R.mipmap.icon_level5);
                                break;
                            case 5:
                                leve1Iv.setImageResource(R.mipmap.icon_level5);
                                leve2Iv.setImageResource(R.mipmap.icon_level6);
                                break;
                            case 6:
                                leve1Iv.setImageResource(R.mipmap.icon_level6);
                                leve2Iv.setImageResource(R.mipmap.icon_level6);
                                break;
                        }
                    }
                } else {
                    UIhelper.ToastMessage(baseBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Popularize_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }
}
