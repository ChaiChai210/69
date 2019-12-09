package com.colin.playerdemo.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ProblemDetailActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.proble_iv)
    ImageView probleIv;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_problem_detail;
    }

    @Override
    protected void initView() {
        tvCenter.setText("问题详情");
        darkImmerseFontColor();
        textContent.setText(getIntent().getStringExtra("content"));
    }

    @OnClick({R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;

        }
    }
}
