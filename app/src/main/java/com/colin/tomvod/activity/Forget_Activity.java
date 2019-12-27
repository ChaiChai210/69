package com.colin.tomvod.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.tomvod.R;
import com.colin.tomvod.base.BaseActivity;
import com.colin.tomvod.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class Forget_Activity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView activityTitleIncludeLeftIv;
    @BindView(R.id.phone_iv)
    ImageView phoneIv;
    @BindView(R.id.phone_add_tv)
    TextView phoneAddTv;
    @BindView(R.id.phone_edt)
    EditText phoneEdt;
    @BindView(R.id.next_tv)
    TextView nextTv;

    private String phone;

    @Override
    protected int getLayoutResId() {
        return R.layout.forget_activity;
    }

    @Override
    protected void initView() {
        darkImmerseFontColor();
    }


    @OnClick({R.id.next_tv, R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next_tv:
                phone = phoneEdt.getText().toString();
                if (StringUtils.isEmpty(phone)) {
                    Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(this, Forget_End_Activity.class).putExtra("phone", phone));
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
