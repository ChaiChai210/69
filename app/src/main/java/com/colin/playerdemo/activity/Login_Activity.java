package com.colin.playerdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.playerdemo.MainActivity;
import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.CodeTokenbean;
import com.colin.playerdemo.bean.LoginBean;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.Constant;
import com.colin.playerdemo.utils.SPUtils;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;

public class Login_Activity extends BaseActivity {
    @BindView(R.id.phone_login_tv)
    TextView phoneLoginTv;
    @BindView(R.id.psd_login_tv)
    TextView psdLoginTv;
    @BindView(R.id.phone_edt)
    EditText phoneEdt;
    @BindView(R.id.cod_iv)
    ImageView codIv;
    @BindView(R.id.psd_edt)
    EditText psdEdt;
    @BindView(R.id.show_iv)
    ImageView showIv;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.new_tv)
    TextView newTv;
    @BindView(R.id.fgt_tv)
    TextView fgt_tv;
    @BindView(R.id.phone_iv)
    ImageView phoneIv;
    @BindView(R.id.phone_add_tv)
    TextView phoneAddTv;

    BaseBean<CodeTokenbean> baseBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        showIv.setImageResource(R.mipmap.login_eye_close); //否则隐藏密码
        psdEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        if (isLogin(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            if (StringUtils.isEmpty(SPUtils.getLoginToken())) {
                getToken();
            }
        }
    }


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
                    OkGo.getInstance().addCommonHeaders(headers);
                } else {
                    UIhelper.ToastMessage(baseBean.getInfo());
                }


            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Login_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });


    }

    /**
     * 检查登录
     *
     * @param activity
     */
    public static boolean checkLogin(Context activity) {
        if (StringUtils.isEmpty(SPUtils.getLoginToken())) {
            Intent intent = new Intent(activity, Login_Activity.class);
            activity.startActivity(intent);
            return false;
        }
        return true;
    }

    /**
     * 检查登录
     *
     * @param activity
     */
    public static boolean isLogin(Context activity) {
        return !StringUtils.isEmpty(SPUtils.getLoginToken());
    }

    private boolean show = true;

    @OnClick({R.id.new_tv, R.id.login_tv, R.id.fgt_tv, R.id.phone_login_tv, R.id.psd_login_tv, R.id.show_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_tv://注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_tv://登录
                if (phoneEdt.getHint().toString().equals("请输入手机号")) {
                    phone = phoneEdt.getText().toString();
                    nickname = "";
                    if (StringUtils.isEmpty(phone)) {
                        Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else if (phoneEdt.getHint().toString().equals("请输入用户名")) {
                    phone = "";
                    nickname = phoneEdt.getText().toString();
                    if (StringUtils.isEmpty(nickname)) {
                        Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                password = psdEdt.getText().toString();
                if (StringUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Login();
                break;
            case R.id.fgt_tv://忘记密码
                startActivity(new Intent(this, Forget_Activity.class));
                break;
            case R.id.phone_login_tv://手机账号登录
                phoneLoginTv.setTextColor(Color.parseColor("#B88A52"));
                psdLoginTv.setTextColor(Color.parseColor("#DDD4C5"));
                phoneIv.setImageResource(R.mipmap.ic_phone);
                phoneAddTv.setVisibility(View.VISIBLE);
                phoneEdt.setHint("请输入手机号");
                phoneEdt.setText("");
                phoneEdt.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case R.id.psd_login_tv://用户名登录
                phoneLoginTv.setTextColor(Color.parseColor("#DDD4C5"));
                psdLoginTv.setTextColor(Color.parseColor("#B88A52"));
                phoneIv.setImageResource(R.mipmap.ic_username);
                phoneAddTv.setVisibility(View.GONE);
                phoneEdt.setText("");
                phoneEdt.setHint("请输入用户名");
                phoneEdt.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case R.id.show_iv://
                show = !show;
                if (show) {
                    showIv.setImageResource(R.mipmap.login_eye_open);
                    psdEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    showIv.setImageResource(R.mipmap.login_eye_close);
                    psdEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }

    /**
     * 登录
     */
    private String phone, nickname, password;

    private void Login() {
        HttpParams httpParams = new HttpParams();
        if (!StringUtils.isEmpty(phone)) {
            httpParams.put("phone", phone);
        }
        if (!StringUtils.isEmpty(nickname)) {
            httpParams.put("nickname", nickname);
        }
        httpParams.put("password", password);
        OkGo.<String>get(URLs.LOGIN).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<LoginBean>() {
                }.getType();
                LoginBean loginBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.ToastMessage(loginBean.getInfo());
                //返回码为成功时的处理
                if (loginBean.getCode() == 0) {
                    String token = loginBean.getData().getApptoken();
                    HttpHeaders headers = new HttpHeaders();
                    if (!StringUtils.isEmpty(token)) {
                        headers.put("app-token", token);
                        SPUtils.put(Constant.SP_LOGIN_TOKEN, token);
                    }
                    OkGo.getInstance().addCommonHeaders(headers);
                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    FancyToast.makeText(mContext, loginBean.getInfo(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Login_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }
}
