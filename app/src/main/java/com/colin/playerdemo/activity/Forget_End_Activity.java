package com.colin.playerdemo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.bean.CodeTokenbean;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;


public class Forget_End_Activity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.cod_iv)
    ImageView codIv;
    @BindView(R.id.code_edt)
    EditText codeEdt;
    @BindView(R.id.get_code_tv)
    TextView getCodeTv;
    @BindView(R.id.psd_iv)
    ImageView psdIv;
    @BindView(R.id.psd_edt)
    EditText psdEdt;
    @BindView(R.id.show_iv)
    ImageView showIv;
    @BindView(R.id.next_tv)
    TextView nextTv;
    private String phone;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forget_end;
    }

    @Override
    protected void initView() {
        darkImmerseFontColor();
        if (getIntent().hasExtra("phone")) {
            phone = getIntent().getStringExtra("phone");
            phoneTv.setText("+86 " + phone);
        }
        getToken();
    }


    private boolean show = false;

    @OnClick({R.id.next_tv, R.id.iv_left, R.id.show_iv, R.id.get_code_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next_tv:
                newpassword = psdEdt.getText().toString();
                if (StringUtils.isEmpty(newpassword)) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newpassword.length() < 6 || newpassword.length() > 20) {
                    Toast.makeText(this, "密码由6-20位字符组成", Toast.LENGTH_SHORT).show();
                    return;
                }
                check_code = codeEdt.getText().toString();
                if (StringUtils.isEmpty(check_code)) {
                    Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                changePsd();
                break;

            case R.id.iv_left:
                finish();
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
                break;
            case R.id.get_code_tv:
                getCodeTv.setSelected(true);
                getCodeTv.setEnabled(false);
                mHander.postDelayed(runnable, 1000);
                getCode();
                break;
        }
    }

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
                        getCodeTv.setText(num + "秒后重发");
                        getCodeTv.setTextColor(Color.parseColor("#FFB760"));
                        getCodeTv.postDelayed(runnable, 1000);
                    } else {
                        getCodeTv.setSelected(false);
                        getCodeTv.setText("重新发送");
                        getCodeTv.setEnabled(true);
                        count = 60;
                        getCodeTv.setTextColor(Color.parseColor("#FFB760"));
                    }
                    break;

            }
        }
    };
    /**
     * 修改密码
     */
    private String check_code, newpassword;

    private void changePsd() {
        HttpParams httpParams = new HttpParams();
        if (!StringUtils.isEmpty(check_code)) {
            httpParams.put("check_code", check_code);
        }
        httpParams.put("password", newpassword);
        httpParams.put("phone", phone);
        OkGo.<String>post(URLs.CHANGEPASD).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
//                Type type = new TypeToken<BaseListBean<DemoBean>> () {
//                }.getType ();
//                BaseListBean<DemoBean> baseBean = GsonHelper.gson.fromJson (response.body (), type);
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.ToastMessage(baseBean.getInfo());
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    startActivity(new Intent(Forget_End_Activity.this, Login_Activity.class));
                    finish();
                } else {
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Forget_End_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
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
                    OkGo.getInstance().addCommonHeaders(headers);
                    getCode();
                    getCodeTv.setEnabled(false);
                    mHander.postDelayed(runnable, 1000);
                } else {
                    UIhelper.ToastMessage(baseBean.getInfo());
                }


            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Forget_End_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    /**
     * 发送短信验证码
     */
    private void getCode() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "newpass");
        httpParams.put("phone", phone);
        OkGo.<String>post(URLs.GETCODE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);
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
                UIhelper.showLoadingDialog(Forget_End_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });


    }
}
