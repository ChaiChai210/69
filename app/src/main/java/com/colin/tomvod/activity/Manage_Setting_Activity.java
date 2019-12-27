package com.colin.tomvod.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.colin.tomvod.R;
import com.colin.tomvod.base.CommonImmerseActivity;
import com.colin.tomvod.bean.Change_Head_bean;
import com.colin.tomvod.bean.CodeTokenbean;
import com.colin.tomvod.bean.UserInfoBean;
import com.colin.tomvod.customeview.third.RoundImageView;
import com.colin.tomvod.net.BaseBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.popwindows.Name_Popwindows;
import com.colin.tomvod.popwindows.PhotoPopWindow;
import com.colin.tomvod.popwindows.Sex_PopupWindows;
import com.colin.tomvod.utils.AppUtils;
import com.colin.tomvod.utils.Constant;
import com.colin.tomvod.utils.SPUtils;
import com.colin.tomvod.utils.StringUtils;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;


public class Manage_Setting_Activity extends CommonImmerseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.tv_center)
    TextView activityTitleIncludeCenterTv;
    @BindView(R.id.tv_right)
    TextView activityTitleIncludeRightTv;
    @BindView(R.id.iv_right)
    ImageView activityTitleIncludeRightIv;
    @BindView(R.id.set_image)
    RoundImageView setImage;
    @BindView(R.id.next_iv)
    ImageView nextIv;
    @BindView(R.id.change_head_layout)
    RelativeLayout changeHeadLayout;
    @BindView(R.id.next_iv1)
    ImageView nextIv1;
    @BindView(R.id.change_name_layout)
    RelativeLayout changeNameLayout;
    @BindView(R.id.next_iv0)
    ImageView nextIv0;
    @BindView(R.id.sex_tv)
    TextView sexTv;
    @BindView(R.id.change_sex_layout)
    RelativeLayout changeSexLayout;
    @BindView(R.id.next_iv2)
    ImageView nextIv2;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.change_phone_layout)
    RelativeLayout changePhoneLayout;
    @BindView(R.id.next_iv3)
    ImageView nextIv3;
    @BindView(R.id.change_psd_tv)
    TextView changePsdTv;
    @BindView(R.id.change_psd_layout)
    RelativeLayout changePsdLayout;
    @BindView(R.id.quit_tv)
    TextView quitTv;
    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.manage_layout)
    LinearLayout manage_layout;
    @BindView(R.id.head_1_iv)
    ImageView head_1_iv;
    @BindView(R.id.name_1_iv)
    ImageView name_1_iv;
    @BindView(R.id.sex_1_iv)
    ImageView sex_1_iv;

    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private String portrait, nickname, sex, password;

    @Override
    protected int getLayoutResId() {
        return R.layout.manage_setting_activity;
    }

    @Override
    protected void initView() {
        darkImmerseFontColor();
        activityTitleIncludeCenterTv.setText("设置");
        getUserInfo();
    }


    @OnClick({R.id.iv_left, R.id.change_psd_layout, R.id.change_head_layout, R.id.change_sex_layout, R.id.change_name_layout, R.id.quit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.change_psd_layout://修改密码
                startActivity(new Intent(this, changePwdActivity.class).putExtra("phone", userInfoBeanBaseBean.getData().getPhone()));
                break;
            case R.id.change_head_layout://修改头像
                PhotoPopWindow popupWindows = new PhotoPopWindow(Manage_Setting_Activity.this, manage_layout, Manage_Setting_Activity.this);
                popupWindows.setHasDelete(false);
                popupWindows.setShowPhoto(type -> {
                    if (type == 1) {
                        fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + "photo.jpg");
                        imageUri = Uri.fromFile(fileUri);
                        imageUri = FileProvider.getUriForFile(Manage_Setting_Activity.this, "com.tmkj.vod.FileProvider", fileUri);//通过FileProvider创建一个content类型的Uri
                        AppUtils.takePicture(Manage_Setting_Activity.this, imageUri, 5);
                    } else if (type == 2) {
                        AppUtils.openPic(Manage_Setting_Activity.this, 2);
                    }
                });
                break;
            case R.id.change_sex_layout:
                Sex_PopupWindows sex_popupWindows = new Sex_PopupWindows(Manage_Setting_Activity.this, manage_layout, Manage_Setting_Activity.this);
                sex_popupWindows.setHasDelete(false);
                sex_popupWindows.setShowPhoto(type -> {
                    if (type == 1) {
                        sex = "1";//性别(1男,0女)
                        changeUserInfo();

                    } else if (type == 2) {
                        sex = "0";//性别(1男,0女)
                        changeUserInfo();

                    }
                });
                break;
            case R.id.change_name_layout:
                Name_Popwindows name_popwindows = new Name_Popwindows(Manage_Setting_Activity.this, manage_layout);
                name_popwindows.setNameListener(name -> {
                    nickname = name;
                    changeUserInfo();

                });
                break;
            case R.id.quit_tv:
                SettingActivity u_set_activity = new SettingActivity();
                u_set_activity.finishActivity();
                SPUtils.remove(Constant.SP_LOGIN_TOKEN);
                if (StringUtils.isEmpty(SPUtils.getDefaultToken())) {
                    getToken();
                } else {
                    HttpHeaders headers = new HttpHeaders();
                    headers.put("app-token", SPUtils.getDefaultToken());
                    OkGo.getInstance().addCommonHeaders(headers);
                    startActivity(new Intent(this, Login_Activity.class));
                    finish();
                }
                break;
        }
    }

    /**
     * token
     */
    BaseBean<CodeTokenbean> baseBean;

    private void getToken() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.GETTOKEM).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<CodeTokenbean>>() {
                }.getType();
                baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (baseBean.isSuccess()) {
                    //获取解析后的的数据为getData里面的数据
                    //baseBean.getData ()
                    HttpHeaders headers = new HttpHeaders();
                    headers.put("app-token", baseBean.getData().getApptoken());
                    SPUtils.put(Constant.SP_DEFAULT_TOKEN,baseBean.getData().getApptoken());
                    OkGo.getInstance().addCommonHeaders(headers);
                    startActivity(new Intent(Manage_Setting_Activity.this, Login_Activity.class));
                    finish();
                } else {
                    UIhelper.ToastMessage(baseBean.getInfo());
                }


            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Manage_Setting_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });


    }

    /**
     * 查询用户信息-APP-WEB
     */
    BaseBean<UserInfoBean> userInfoBeanBaseBean;

    private void getUserInfo() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.USERINFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<UserInfoBean>>() {
                }.getType();
                userInfoBeanBaseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (userInfoBeanBaseBean.getCode() == 0) {
                    Glide.with(Manage_Setting_Activity.this).applyDefaultRequestOptions(new RequestOptions().error(R.mipmap.ic_head_l))
                            .load(userInfoBeanBaseBean.getData().getPortrait()).into(setImage);
                    name_tv.setText(userInfoBeanBaseBean.getData().getNickname());
                    sexTv.setText(userInfoBeanBaseBean.getData().getSex() + "");
                    phoneTv.setText(userInfoBeanBaseBean.getData().getPhone());
                    if (userInfoBeanBaseBean.getData().getIs_change_portrait() == 1) {
                        head_1_iv.setVisibility(View.GONE);
                    }
                    if (userInfoBeanBaseBean.getData().getIs_change_name() == 1) {
                        name_1_iv.setVisibility(View.GONE);
                    }
                    if (userInfoBeanBaseBean.getData().getIs_change_sex() == 1) {
                        sex_1_iv.setVisibility(View.GONE);
                    }
                } else {
                    UIhelper.ToastMessage(userInfoBeanBaseBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Manage_Setting_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2://访问相册完成回调
                    if (AppUtils.hasSdcard()) {
                        Uri newUri = Uri.parse(AppUtils.getPath(this, data.getData()));
                        fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + "crop_photo.jpg");
                        if (fileCropUri.exists())
                            fileCropUri.delete();
                        cropImageUri = Uri.fromFile(fileCropUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.tmkj.vod.FileProvider", new File(newUri.getPath()));
                        AppUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, 480, 480, 3);
                    } else {
                        Toast.makeText(Manage_Setting_Activity.this, "设备没有SD卡", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 3://裁剪完成
                    getQNTk(fileCropUri);
                    break;
                case 5://拍照完成
                    fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + "crop_photo.jpg");
                    if (fileCropUri.exists())
                        fileCropUri.delete();
                    cropImageUri = Uri.fromFile(fileCropUri);
                    AppUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, 480, 480, 3);
                    break;
            }
        }
    }

    private void getQNTk(File fileCropUri) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "user_pic");
        httpParams.put("file", fileCropUri);
        OkGo.<String>post(URLs.ADDFILE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<Change_Head_bean>>() {
                }.getType();
                BaseBean<Change_Head_bean> baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();

                UIhelper.ToastMessage(baseBean.getInfo());
                //返回码为成功时的处理
                if (baseBean.isSuccess()) {
                    Glide.with(Manage_Setting_Activity.this).applyDefaultRequestOptions(new RequestOptions().error(R.mipmap.ic_head_l))
                            .load(baseBean.getData().getUrl()).into(setImage);
                    portrait = baseBean.getData().getUrl();
                    changeUserInfo();
                } else {
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Manage_Setting_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });

    }


    private void changeUserInfo() {
        HttpParams httpParams = new HttpParams();
        if (!StringUtils.isEmpty(portrait)) {
            httpParams.put("portrait", portrait);
        }
        if (!StringUtils.isEmpty(nickname)) {
            httpParams.put("nickname", nickname);
        }
        if (!StringUtils.isEmpty(sex)) {
            httpParams.put("sex", sex);
        }
        OkGo.<String>post(URLs.CHANGE_USERINFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();

                UIhelper.ToastMessage(baseBean.getInfo());
                //返回码为成功时的处理
                if (baseBean.isSuccess()) {
                    if (!StringUtils.isEmpty(sex)) {
                        if (sex.equals("1")) {
                            sexTv.setText("男");
                        } else if (sex.equals("0")) {
                            sexTv.setText("女");
                        } else {
                            sexTv.setText("未设定");
                        }
                        sex_1_iv.setVisibility(View.GONE);
                    }
                    if (!StringUtils.isEmpty(portrait)) {
                        Glide.with(Manage_Setting_Activity.this).applyDefaultRequestOptions(new RequestOptions().error(R.mipmap.ic_head_l))
                                .load(portrait).into(setImage);
                        head_1_iv.setVisibility(View.GONE);
                    }
                    if (!StringUtils.isEmpty(nickname)) {
                        name_tv.setText(nickname);
                        name_1_iv.setVisibility(View.GONE);
                    }
                    portrait = "";
                    sex = "";
                    nickname = "";
                } else {
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Manage_Setting_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });

    }

}
