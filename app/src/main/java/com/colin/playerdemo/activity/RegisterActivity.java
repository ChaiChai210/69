package com.colin.playerdemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.utils.AppUtils;
import com.colin.playerdemo.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.phone_login_tv)
    TextView phoneLoginTv;
    @BindView(R.id.Invitation_edt)
    EditText InvitationEdt;
    @BindView(R.id.phone_iv)
    ImageView phoneIv;
    @BindView(R.id.phone_add_tv)
    TextView phoneAddTv;
    @BindView(R.id.phone_edt)
    EditText phoneEdt;
    @BindView(R.id.next_tv)
    TextView nextTv;
    @BindView(R.id.xieyi_tv)
    TextView xieyiTv;
    @BindView(R.id.login_tv)
    TextView loginTv;

    String invite_code, phone;
    @Override
    protected int getLayoutResId() {

        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        xieyiTv.setText("<用户注册协议>");
    }

    @OnClick({R.id.next_tv,R.id.xieyi_tv,R.id.login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next_tv:
                invite_code = InvitationEdt.getText().toString();
                phone = phoneEdt.getText().toString();
                if (StringUtils.isEmpty(phone)) {
                    Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(AppUtils.isMobile(phone)){
                    startActivity(new Intent(this, Code_Activity.class)
                            .putExtra("invite_code",invite_code)
                            .putExtra("phone",phone)
                    );
                }else {
                    Toast.makeText(this, "输入号码不正确", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.xieyi_tv:
                startActivity(new Intent(this,Protocol_Activity.class));
                break;
            case R.id.login_tv:
                finish();
                break;
        }
    }
}
