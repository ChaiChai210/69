package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.PlayActivity;
import com.colin.playerdemo.bean.AloneBean;
import com.colin.playerdemo.bean.PlayDetailBean;
import com.colin.playerdemo.bean.SearchBean;
import com.colin.playerdemo.customeview.third.RoundImageView;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends BaseQuickAdapter<SearchBean, BaseViewHolder> {
    public SearchResultAdapter(@Nullable List<SearchBean> data) {
        super(R.layout.item_guess_like,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean item) {
        Glide.with(mContext).load(item.getCover()).into((RoundImageView) helper.getView(R.id.search_result_image));
        helper.setText(R.id.play_num_tv, item.getPlay_count() + "次播放");
        helper.setText(R.id.tv_play_name, item.getName());
//        helper.setText(R.id.tv_score,String.valueOf(item.get()));
        FlexboxLayoutManager layoutManager1 = new FlexboxLayoutManager(mContext);
        layoutManager1.setFlexWrap(FlexWrap.WRAP);
        layoutManager1.setFlexDirection(FlexDirection.ROW);
        layoutManager1.setAlignItems(AlignItems.STRETCH);
        layoutManager1.setJustifyContent(JustifyContent.FLEX_START);
        RecyclerView tagRecycler = helper.getView(R.id.rv_tags);
        tagRecycler.setLayoutManager(layoutManager1);
        TagAdapter tagAdapter = new TagAdapter(item.getTags());
        tagRecycler.setAdapter(tagAdapter);
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, PlayActivity.class).putExtra("id", item.getId() + "")));
    }

}
