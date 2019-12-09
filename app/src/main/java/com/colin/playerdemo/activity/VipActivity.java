package com.colin.playerdemo.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.customeview.third.RoundImageView;
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

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.vip_image)
    RoundImageView vipImage;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.code_edt)
    EditText codeEdt;
    @BindView(R.id.code_tv)
    TextView codeTv;
    @BindView(R.id.jihuoma_edt)
    EditText jihuomaEdt;
    @BindView(R.id.duihuan_tv)
    TextView duihuanTv;

    private String phone;
    private String check_code, cdk;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void initView() {
        tvCenter.setText("VIP等级兑换");
        phone = getIntent().getStringExtra("phone");
        darkImmerseFontColor();
        if (!StringUtils.isEmpty(phone)) {
            getCode();
            codeTv.setEnabled(false);
            mHander.postDelayed(runnable, 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_left, R.id.code_tv, R.id.duihuan_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.code_tv:
                codeTv.setSelected(true);
                codeTv.setEnabled(false);
                mHander.postDelayed(runnable, 1000);
                break;
            case R.id.duihuan_tv:
                check_code = codeEdt.getText().toString();
                if (StringUtils.isEmpty(check_code)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                cdk = jihuomaEdt.getText().toString();
                if (StringUtils.isEmpty(cdk)) {
                    Toast.makeText(this, "请输激活码", Toast.LENGTH_SHORT).show();
                    return;
                }
                ADDAds();
                break;
        }
    }

    private void ADDAds() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("check_code", check_code);
        httpParams.put("cdk", cdk);
        String url;
        url = URLs.CDK + "/" + cdk;
        OkGo.<String>put(url).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                UIhelper.ToastMessage(baseBean.getInfo());
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    finish();
                } else {

                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(VipActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
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
                        codeTv.setText(num + "秒重发");
                        codeTv.setTextColor(Color.parseColor("#999999"));
                        codeTv.postDelayed(runnable, 1000);
                    } else {
                        codeTv.setSelected(false);
                        codeTv.setText("重新发送");
                        codeTv.setEnabled(true);
                        count = 60;
                        codeTv.setTextColor(Color.parseColor("#999999"));
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
        httpParams.put("type", "cdk");
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
                UIhelper.showLoadingDialog(VipActivity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }
}
