package com.colin.playerdemo.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.PlayCommentBean;
import com.colin.playerdemo.customeview.third.RoundImageView;
import com.colin.playerdemo.popwindows.CommentDetailPopup;
import com.colin.playerdemo.utils.UIhelper;

import java.util.List;

public class PlayCommentAdapter extends BaseQuickAdapter<PlayCommentBean, BaseViewHolder> {
    PlayCommentListener playCommentListener;
    private int show = -1;

    public void setplayCommentListener(PlayCommentListener playCommentListener) {
        this.playCommentListener = playCommentListener;
    }

    public PlayCommentAdapter(@Nullable List<PlayCommentBean> data, int show) {
        super(R.layout.item_play_comment, data);
        this.show = show;
    }

    @Override
    protected void convert(BaseViewHolder helper, PlayCommentBean item) {
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

        if (show == -1) {
            reply1.setVisibility(View.GONE);
            reply2.setVisibility(View.GONE);
            if (item.getReplys().size() > 0) {
                reply1.setVisibility(View.VISIBLE);
                allReply.setText(String.format("查看全部%d条回复", item.getComment_count()));
                reply1.setText(String.format("%s:%s", item.getReplys().get(0).getNickname(), item.getReplys().get(0).getContent()));
            } else if (item.getReplys().size() > 1) {
                reply2.setVisibility(View.VISIBLE);
                reply2.setText(String.format("%s:%s", item.getReplys().get(1).getNickname(), item.getReplys().get(1).getContent()));
            }
            if (item.getReplys().size() == 0) {
                allReply.setVisibility(View.GONE);
            }
        } else {
            reply1.setVisibility(View.GONE);
            reply2.setVisibility(View.GONE);
            allReply.setVisibility(View.GONE);
            for (int i = 0; i < item.getReplys().size(); i++) {
                TextView textView = new TextView(mContext);
                textView.setPadding(15, 15, 15, 0);
                textView.setText(String.format("%s:%s", item.getReplys().get(i).getNickname(), item.getReplys().get(i).getContent()));
                ((LinearLayout) helper.getView(R.id.comment_layout)).addView(textView);
            }
        }
        likeCount.setOnClickListener(v -> {
            if (item.isIs_like()) {
                playCommentListener.Onclick(helper.getLayoutPosition(), 0);
            } else {
                playCommentListener.Onclick(helper.getLayoutPosition(), 1);
            }

        });
        helper.itemView.setOnClickListener(v -> {
            playCommentListener.showClick(item);
        });

    }

    public interface PlayCommentListener {
        void Onclick(int position, int islike);

        void showClick(PlayCommentBean playCommentBean);

    }
}
