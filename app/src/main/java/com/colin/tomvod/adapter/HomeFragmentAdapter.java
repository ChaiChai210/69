package com.colin.tomvod.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.Class_activity;
import com.colin.tomvod.bean.MainBean;
import com.colin.tomvod.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MainBean.VideoBean> video;
    private OnAdClickListener adClickListener;

    public void setOnAdClickListener(OnAdClickListener adClickListener) {
        this.adClickListener = adClickListener;
    }

    public void setVideoData(List<MainBean.VideoBean> video) {
        this.video = video;
        notifyDataSetChanged();
    }

    public HomeFragmentAdapter() {
        super();
        this.video = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Home_Fragment_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dapter_home_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Home_Fragment_AdapterHolder adapterHolder = (Home_Fragment_AdapterHolder) holder;
        adapterHolder.showHome_Fragment_AdapterHolder(video.get(position), position);
    }

    @Override
    public int getItemCount() {
        return video.size();
    }

    public class Home_Fragment_AdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.child_relayout)
        RelativeLayout childRelayout;
        @BindView(R.id.child_rv)
        RecyclerView childRv;
        @BindView(R.id.adv_tv)
        TextView advTv;
        @BindView(R.id.ad_iv)
        ImageView ad_iv;
        @BindView(R.id.adv_layout)
        LinearLayout advLayout;
        @BindView(R.id.child_title_tv)
        TextView child_title_tv;

        public Home_Fragment_AdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void showHome_Fragment_AdapterHolder(final MainBean.VideoBean videoBean, final int position) {
            //更多
            childRelayout.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), Class_activity.class).putExtra("name", videoBean.getTitle())));
            childRv.setNestedScrollingEnabled(false);
            child_title_tv.setText(videoBean.getTitle());
            if (null != videoBean.getAdver()) {
                advLayout.setVisibility(View.VISIBLE);
                advTv.setText(videoBean.getAdver().getName());
                Glide.with(itemView.getContext()).load(videoBean.getAdver().getPic()).into(ad_iv);
                advLayout.setOnClickListener(v -> {
                    if (!StringUtils.isEmpty(videoBean.getAdver().getUrl())) {
                        adClickListener.OnAdclick(videoBean.getAdver().getUrl(), videoBean.getAdver().getId());
                    }

                });
            } else {
                advLayout.setVisibility(View.GONE);
            }
            switch (position) {
                case 0:
                case 1:
                    childRv.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
                    HomeType1Adapter one_adapter = new HomeType1Adapter(videoBean.getList());
                    childRv.setAdapter(one_adapter);
                    break;

                case 2:
                    childRv.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                    HomeType2Adapter two_adapter;
                    two_adapter = new HomeType2Adapter(videoBean.getList());
                    childRv.setAdapter(two_adapter);
                    break;

                default:
                    childRv.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));
                    HomeType3Adapter three_adapter = new HomeType3Adapter(videoBean.getList());
                    childRv.setAdapter(three_adapter);
                    break;
            }
        }
    }

    public interface OnAdClickListener {
        void OnAdclick(String url, int id);
    }
}
