package com.colin.playerdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.colin.playerdemo.R;
import com.colin.playerdemo.base.BaseActivity;
import com.colin.playerdemo.customeview.CustomViewPager;
import com.colin.playerdemo.fragment.Fragment_History;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HistoryActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tab_head)
    SlidingTabLayout tabHead;
    @BindView(R.id.chat_room_vp)
    CustomViewPager chatRoomVp;
    private List<String> names = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<Boolean> show = new ArrayList<>();
    public static List<List<Boolean>> historybooleanslsit = new ArrayList<>();
    private int item = 0;


    @OnClick({R.id.iv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                Fragment_History fragment_history = (Fragment_History) mFragments.get(item);
                show.set(item, !show.get(item));
                fragment_history.ChangeClick(show.get(item));
                if (show.get(item)) {
                    tvRight.setText("取消");
                } else {
                    tvRight.setText("编辑");
                }
                break;

        }
    }


    private void initTab() {
        List<String> tabList = new ArrayList<>(names);

        String[] tabs = getArr(tabList);

        //添加其他分类fragment
        mFragments.add(Fragment_History.newInstance(0));
        mFragments.add(Fragment_History.newInstance(1));
        mFragments.add(Fragment_History.newInstance(2));
        // 定义 SlidingTabLayout
        tabHead.setViewPager(chatRoomVp, tabs, this, mFragments);
        tabHead.setIndicatorHeight(2);
        tabHead.setIndicatorColor(Color.parseColor("#C39B69"));
        tabHead.setIndicatorWidth(20);
        tabHead.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                item = position;
                if (show.get(position)) {
                    tvRight.setText("取消");
                } else {
                    tvRight.setText("编辑");
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    public static String[] getArr(List<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_history;
    }

    @Override
    protected void initView() {
        tvCenter.setText("历史记录");
        tvRight.setText("编辑");
        tvRight.setTextSize(12);
        darkImmerseFontColor();

        names.add("今日");
        names.add("七日");
        names.add("更早");
        show.add(false);
        show.add(false);
        show.add(false);
        historybooleanslsit.clear();
        historybooleanslsit.add(new ArrayList<Boolean>());
        historybooleanslsit.add(new ArrayList<Boolean>());
        historybooleanslsit.add(new ArrayList<Boolean>());
        initTab();
    }
}
