package com.colin.playerdemo.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.DisconverBean;
import com.dueeeke.videocontroller.StandardVideoController;

import java.util.List;

public class DiscoverAdapter extends BaseQuickAdapter<DisconverBean.DataBean, BaseViewHolder> {
//    private PlayerConfig playerConfig;
    CollectListener collectListener;
    private boolean play = true;

    public void setCollectListener(CollectListener collectListener) {
        this.collectListener = collectListener;
    }

    public DiscoverAdapter(@Nullable List<DisconverBean.DataBean> data) {
        super(R.layout.item_fragment_discover, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, DisconverBean.DataBean item) {
//        IjkVideoView playPlayer = helper.getView(R.id.play_player);
        helper.setText(R.id.play_num_tv, item.getPlay_count() + "次播放");
        helper.setText(R.id.title_tv, item.getName());

        ImageView ivFavorite = helper.getView(R.id.favor_iv);
        if (item.getIs_collect() == 1) {//（1为收藏过，0未收藏过）
            ivFavorite.setImageResource(R.mipmap.favor_press);
        } else {
            ivFavorite.setImageResource(R.mipmap.favor_nopress);
        }
        ivFavorite.setOnClickListener(v -> collectListener.onclick(helper.getLayoutPosition(), 2));
        helper.getView(R.id.send_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectListener.onclick(helper.getLayoutPosition(), 3);
            }
        });
//        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Play_Activity.class).
//                putExtra("id", item.getId() + "")));

//        initPlayer(playPlayer, helper.getLayoutPosition(), item);

    }
//
//    private void initPlayer(IjkVideoView playPlayer, int position, DisconverBean.DataBean item) {
//        StandardVideoController controller = new StandardVideoController(mContext);
//        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions()
//                .placeholder(android.R.color.white)
//                .error(android.R.color.white)).load(item.getCover()).into(controller.getThumb());
//        playPlayer.setPlayerConfig(playerConfig);
//        playPlayer.setUrl(item.getVideo_url());
//        playPlayer.setTitle(item.getName() + "");
//        playPlayer.setVideoController(controller);
//        playPlayer.setStartPlayListener(new BaseIjkVideoView.StartPlayListener() {
//            @Override
//            public void PlayListener() {
//                collectListener.onclick(position, 1);
//            }
//        });
//        if (!play) {
//            Log.e("chai", "stoplayer");
//            playPlayer.stopPlayback();
//        }
//    }

    public void stopPlayer() {
        play = false;
        notifyDataSetChanged();
    }

    public interface CollectListener {
        void onclick(int position, int type);
    }

}
