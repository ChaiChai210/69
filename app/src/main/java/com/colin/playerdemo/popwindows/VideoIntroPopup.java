package com.colin.playerdemo.popwindows;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.adapter.VideoTagAdapter;
import com.colin.playerdemo.bean.PlayDetailBean;
import com.colin.playerdemo.utils.ButterKnifeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.BasePopupWindow;

public class VideoIntroPopup extends BasePopupWindow {

    @BindView(R.id.tv_video_title)
    TextView tvVideoTitle;
    @BindView(R.id.close_iv)
    ImageView closeIv;
    @BindView(R.id.tv_video_play_count)
    TextView tvVideoPlayCount;
    @BindView(R.id.tag_rv)
    RecyclerView tagRv;
    @BindView(R.id.tv_video_intro)
    TextView tvVideoIntro;
    private Context mContext;

    private VideoTagAdapter videoTagAdapter;
    private List<PlayDetailBean.TagListBean> tagListBeans = new ArrayList<>();

    public VideoIntroPopup(Context context, PlayDetailBean bean) {
        super(context);
        this.mContext = context;
        ButterKnifeUtil.bind(this, getContentView());
        setPopupGravity(Gravity.BOTTOM);
        initView(context, bean);
    }

    private void initView(Context context, PlayDetailBean bean) {
        tagRv.setLayoutManager(new GridLayoutManager(context, 4));
        videoTagAdapter = new VideoTagAdapter(bean.getTag_list());
        tagRv.setAdapter(videoTagAdapter);
        tvVideoTitle.setText(bean.getName());
        tvVideoPlayCount.setText("播放: " + bean.getPlay_count() + "次播放");
        tvVideoIntro.setText(bean.getIntroduce());
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.pop_video_intro);
    }


    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(-1f, 0f, 500);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0f, -1f, 500);
    }

    @OnClick(R.id.close_iv)
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
