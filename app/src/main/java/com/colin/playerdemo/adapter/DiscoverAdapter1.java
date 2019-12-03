package com.colin.playerdemo.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.DisconverBean;
import com.dueeeke.videocontroller.component.PrepareView;

import java.util.List;

public class DiscoverAdapter1 extends BaseQuickAdapter<DisconverBean.DataBean, BaseViewHolder> {
    //    private PlayerConfig playerConfig;
    CollectListener collectListener;
    private boolean play = true;
    private OnItemChildClickListener mOnItemChildClickListener;
    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setCollectListener(CollectListener collectListener) {
        this.collectListener = collectListener;
    }

    public DiscoverAdapter1(@Nullable List<DisconverBean.DataBean> data) {
        super(R.layout.item_video, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DisconverBean.DataBean item) {
        helper.setText(R.id.play_num_tv, item.getPlay_count() + "次播放");
        helper.setText(R.id.title_tv, item.getName());
//
//        ImageView ivFavorite = helper.getView(R.id.favor_iv);
//        if (item.getIs_collect() == 1) {//（1为收藏过，0未收藏过）
//            ivFavorite.setImageResource(R.mipmap.favor_press);
//        } else {
//            ivFavorite.setImageResource(R.mipmap.favor_nopress);
//        }
//        ivFavorite.setOnClickListener(v -> collectListener.onclick(helper.getLayoutPosition(), 2));
//        helper.getView(R.id.send_iv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                collectListener.onclick(helper.getLayoutPosition(), 3);
//            }
//        });
//        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Play_Activity.class).
//                putExtra("id", item.getId() + "")));

//        initPlayer(playPlayer, helper.getLayoutPosition(), item);
        helper.setText(R.id.title_tv, item.getName());

        FrameLayout mPlayerContainer = helper.getView(R.id.player_container);
        PrepareView mPrepareView = helper.getView(R.id.prepare_view);
        ImageView mThumb = mPrepareView.findViewById(R.id.thumb);
        Glide.with(mContext)
                .load(item.getCover())
                .placeholder(android.R.color.white)
                .into(mThumb);
        if (mOnItemChildClickListener != null) {
            mPlayerContainer.setOnClickListener(v -> mOnItemChildClickListener.onItemChildClick(helper.getAdapterPosition()));
        }
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


    public interface CollectListener {
        void onclick(int position, int type);
    }

    public interface OnItemChildClickListener {
        void onItemChildClick(int position);
    }
}
