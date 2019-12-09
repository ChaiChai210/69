package com.colin.playerdemo.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mock.alipay.Callback;
import com.mock.alipay.view.PasswordKeyboard;
import com.mock.alipay.view.PasswordView;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;


public class changePwdActivity extends BaseActivity implements PasswordKeyboard.OnPasswordInputListener {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.tv_center)
    TextView activityTitleIncludeCenterTv;
    @BindView(R.id.tv_right)
    TextView activityTitleIncludeRightTv;
    @BindView(R.id.iv_right)
    ImageView activityTitleIncludeRightIv;
    @BindView(R.id._phone_tv)
    TextView PhoneTv;
    @BindView(R.id.quit_tv)
    TextView quitTv;
    @BindView(R.id.password_inputBox)
    PasswordView passwordView;
    @BindView(R.id.mine_tv)
    TextView mineTv;
    @BindView(R.id.psd_edt)
    EditText psdEdt;
    @BindView(R.id.show_iv)
    ImageView showIv;
    @BindView(R.id.password_keyboard)
    PasswordKeyboard passwordKeyboard;
    @BindView(R.id.pas_layout)
    RelativeLayout pas_layout;
    @BindView(R.id.cancel_dialog)
    TextView cancel_dialog;
    private StringBuffer mPasswordBuffer = new StringBuffer();
    private boolean passwordState = true;
    private String phone;

    @Override
    protected int getLayoutResId() {
        return  R.layout.change_psd_activity;
    }

    @Override
    protected void initView() {
        darkImmerseFontColor();
        activityTitleIncludeCenterTv.setText("");
        phone = getIntent().getStringExtra("phone");
        PhoneTv.setText(String.format("已向手机%s发送验证码", phone));
        passwordKeyboard.setOnPasswordInputListener(this);
        passwordView.setPasswordCount(4);
        passwordView.setOnClickListener(v -> {
            if (pas_layout.getVisibility() == View.VISIBLE) {
                pas_layout.setVisibility(View.GONE);
            } else {
                pas_layout.setVisibility(View.VISIBLE);
            }
        });
        if(!StringUtils.isEmpty(phone)){
            getCode();
            mineTv.setEnabled(false);
            mHander.postDelayed(runnable, 1000);
        }
        showIv.setImageResource(R.mipmap.login_eye_close); //否则隐藏密码
        psdEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void initData() {
      
    }



    @OnClick({R.id.iv_left, R.id.quit_tv,R.id.cancel_dialog,R.id.mine_tv,R.id.show_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.quit_tv:
                password = psdEdt.getText().toString();
                if(StringUtils.isEmpty(password)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(StringUtils.isEmpty(check_code)){
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                changePsd();
                break;
            case R.id.cancel_dialog:
                if (mCallback != null) {
                    mCallback.onCancel();
                }
                break;
            case R.id.mine_tv:
                mineTv.setSelected(true);
                mineTv.setEnabled(false);
                mHander.postDelayed(runnable, 1000);
                getCode();
                break;
            case R.id.show_iv:
                show = !show;
                if (show) {
                    showIv.setImageResource(R.mipmap.login_eye_open);      //如果选中，显示密码
                    psdEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    showIv.setImageResource(R.mipmap.login_eye_close); //否则隐藏密码
                    psdEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

        }
    }
    private boolean show = false;
    private int count = 60;
    /**
     * 计时
     */
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            Message msg = new Message();
            msg.what = 0;
            Bundle data = new Bundle();
            data.putInt("count", count--);
            msg.setData(data);
            mHander.sendMessage(msg);
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler mHander = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0://消息
                    int num = msg.getData().getInt("count");
                    if (num != 0) {
                        mineTv.setText(num + "秒重发");
                        mineTv.setTextColor(Color.parseColor("#FFB760"));
                        mineTv.postDelayed(runnable, 1000);
                    } else {
                        mineTv.setSelected(false);
                        mineTv.setText("重新发送");
                        mineTv.setEnabled(true);
                        count = 60;
                        mineTv.setTextColor(Color.parseColor("#FFB760"));
                    }
                    break;

            }
        }
    };
    /**
     * 发送短信验证码
     */
    private void getCode() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type","newpass");
        httpParams.put("phone",phone);
        OkGo.<String>post(URLs.GETCODE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean    baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (baseBean.isSuccess()) {

                } else {
                }
                UIhelper.ToastMessage(baseBean.getInfo());


            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(changePwdActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    /**
     * 用户修改密码
     */
    private String password,check_code;
    private void changePsd() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("password",password);
        httpParams.put("phone",phone);
        httpParams.put("check_code",check_code);
        OkGo.<String>post(URLs.CHANGEPASD).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean    baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                UIhelper.ToastMessage(baseBean.getInfo());
                if (baseBean.isSuccess()) {
                        finish();
                } else {
                }


            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(changePwdActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }
    @Override
    public void onInput(String character) {
        if (PasswordKeyboard.DEL.equals(character)) {
            if (mPasswordBuffer.length() > 0) {
                mPasswordBuffer.delete(mPasswordBuffer.length() - 1, mPasswordBuffer.length());
            }
        } else if (PasswordKeyboard.DONE.equals(character)) {
        } else {
            if (!passwordState) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                return;
            }
            mPasswordBuffer.append(character);
        }
        passwordView.setPassword(mPasswordBuffer);
        if (mPasswordBuffer.length() == passwordView.getPasswordCount()) {
            startLoading(mPasswordBuffer);
        }
    }

    private Callback mCallback = new Callback() {
        @Override
        public void onForgetPassword() {

        }

        @Override
        public void onInputCompleted(CharSequence password) {
            check_code  = password.toString();
        }

        @Override
        public void onPasswordCorrectly() {

        }

        @Override
        public void onCancel() {
            pas_layout.setVisibility(View.GONE);
        }
    };

    private void startLoading(CharSequence password) {
        pas_layout.setVisibility(View.GONE);
        if (mCallback != null) {
            mCallback.onInputCompleted(password);
        }
    }
}
