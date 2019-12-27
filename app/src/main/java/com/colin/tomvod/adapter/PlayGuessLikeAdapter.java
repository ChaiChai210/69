package com.colin.tomvod.adapter;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.PlayActivity;
import com.colin.tomvod.bean.PlayDetailBean;
import com.colin.tomvod.customeview.third.RoundImageView;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

public class PlayGuessLikeAdapter extends BaseQuickAdapter<PlayDetailBean.YouIsLoveBean, BaseViewHolder> {
    public PlayGuessLikeAdapter(int layoutResId) {
        super(layoutResId);
    }
//    public PlayGuessLikeAdapter(@Nullable List<PlayDetailBean.YouIsLoveBean> data) {
//        super(, data);
//    }

    @Override
    protected void convert(BaseViewHolder helper, PlayDetailBean.YouIsLoveBean item) {
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
        List<String> tags = changeTag(item.getTags());
        TagAdapter tagAdapter = new TagAdapter(tags);
        tagRecycler.setAdapter(tagAdapter);
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, PlayActivity.class).putExtra("id", item.getVid() + "")));
    }

    private List<String> changeTag(List<PlayDetailBean.YouIsLoveBean.TagsBean> tags) {
        List<String> tagString = new ArrayList<>(tags.size());
        for (PlayDetailBean.YouIsLoveBean.TagsBean item : tags) {
            tagString.add(item.getName());
        }
        return tagString;
    }
}
