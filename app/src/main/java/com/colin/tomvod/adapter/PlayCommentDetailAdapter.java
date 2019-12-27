package com.colin.tomvod.adapter;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.bean.ChildPlayCommentBean;
import com.colin.tomvod.customeview.third.RoundImageView;
import com.colin.tomvod.utils.UIhelper;

import java.util.List;

public class PlayCommentDetailAdapter extends BaseQuickAdapter<ChildPlayCommentBean, BaseViewHolder> {
    private CommentListener childOnclick;

    public void setChildOnclick(CommentListener childOnclick) {
        this.childOnclick = childOnclick;
    }
    private int show = -1;


    public PlayCommentDetailAdapter(@Nullable List<ChildPlayCommentBean> data) {
        super(R.layout.item_play_comment_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChildPlayCommentBean item) {
        TextView nickName = helper.getView(R.id.name_tv);
        TextView content = helper.getView(R.id.content_tv);
        TextView likeCount = helper.getView(R.id.tv_like_count);
        TextView reply1 = helper.getView(R.id.tv_reply1);
        TextView reply2 = helper.getView(R.id.tv_reply2);
        TextView allReply = helper.getView(R.id.tv_all_reply);

        Glide.with(mContext).load(item.getPortrait()).into((RoundImageView) helper.getView(R.id.head_image));
        helper.setText(R.id.name_tv, item.getNickname());

        helper.setText(R.id.time_tv, item.getCreate_time());

        likeCount.setText(item.getZan_count() + "");
        content.setText(item.getContent());
        if (item.isIs_like()) {
            Drawable dra = mContext.getResources().getDrawable(R.mipmap.image_comment_zan_focus);
            dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
            likeCount.setCompoundDrawables(dra, null, null, null);
            likeCount.setCompoundDrawablePadding(5);
        } else {
            Drawable dra = mContext.getResources().getDrawable(R.mipmap.image_comment_zan_normal);
            dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
            likeCount.setCompoundDrawables(dra, null, null, null);
            likeCount.setCompoundDrawablePadding(5);
        }

        UIhelper.setGenderIcon(mContext, item.getSex(), nickName);


        likeCount.setOnClickListener(v -> {
            if (item.isIs_like()) {
                childOnclick.childOnclick(helper.getLayoutPosition(), 0);
            } else {
                childOnclick.childOnclick(helper.getLayoutPosition(), 1);
            }

        });


    }

    public interface CommentListener{
        void childOnclick(int position, int islike);
    }
}
