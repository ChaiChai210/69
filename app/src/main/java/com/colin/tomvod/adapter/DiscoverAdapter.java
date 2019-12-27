package com.colin.tomvod.adapter;

import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.bean.DisconverBean;
import com.colin.tomvod.net.BaseBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.utils.UIhelper;
import com.dueeeke.videocontroller.component.PrepareView;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.lang.reflect.Type;
import java.util.List;

public class DiscoverAdapter extends BaseQuickAdapter<DisconverBean.DataBean, BaseViewHolder> {
    private CollectListener collectListener;
    private OnItemChildClickListener mOnItemChildClickListener;

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setCollectListener(CollectListener collectListener) {
        this.collectListener = collectListener;
    }

    public DiscoverAdapter(@Nullable List<DisconverBean.DataBean> data) {
        super(R.layout.item_video, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DisconverBean.DataBean item) {
        helper.setText(R.id.play_num_tv, item.getPlay_count() + "次播放");
        helper.setText(R.id.title_tv, item.getName());
//
        ImageView ivFavorite = helper.getView(R.id.favor_iv);
        if (item.getIs_collect() == 1) {//（1为收藏过，0未收藏过）
            ivFavorite.setImageResource(R.mipmap.favor_press);
        } else {
            ivFavorite.setImageResource(R.mipmap.favor_nopress);
        }
//        ivFavorite.setOnClickListener(v -> collectListener.onclick(helper.getLayoutPosition(), 2));
        ivFavorite.setOnClickListener(v -> {
            if (item.getIs_collect() == 1) {
                deleteCollectList(item, ivFavorite, helper.getLayoutPosition());
            } else {
                collect(item, ivFavorite, helper.getLayoutPosition());
            }
        });
        helper.getView(R.id.send_iv).setOnClickListener(v -> collectListener.onclick(helper.getLayoutPosition(), 3));

        helper.setText(R.id.title_tv, item.getName());

        FrameLayout mPlayerContainer = helper.getView(R.id.player_container);
        PrepareView mPrepareView = helper.getView(R.id.prepare_view);
        ImageView mThumb = mPrepareView.findViewById(R.id.thumb);
        Glide.with(mContext)
                .load(item.getCover())
                .placeholder(R.mipmap.pl_home_320180)
                .into(mThumb);
        if (mOnItemChildClickListener != null) {
            mPlayerContainer.setOnClickListener(v -> mOnItemChildClickListener.onItemChildClick(helper.getAdapterPosition()));
        }
    }

    private void collect(DisconverBean.DataBean item, final ImageView ivFavorite, int pos) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("vid", item.getId());
        OkGo.<String>post(URLs.COLLECT).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);

                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
//                    ivFavorite.setImageResource(R.mipmap.favor_nopress);
                    item.setIs_collect(1);
                    notifyItemChanged(pos);
                } else {
                    UIhelper.ToastMessage(baseBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(mContext);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void deleteCollectList(DisconverBean.DataBean item, ImageView ivFavorite, int pos) {
        HttpParams httpParams = new HttpParams();
        String url;
        url = URLs.COLLECT + "/" + item.getId();
        OkGo.<String>delete(url).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBeans = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (baseBeans.getCode() == 0) {
                    ivFavorite.setImageResource(R.mipmap.favor_nopress);
                    item.setIs_collect(0);
                    notifyItemChanged(pos);
                } else {
                    FancyToast.makeText(mContext, "删除失败，稍后重试", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
//                UIhelper.stopLoadingDialog();
            }
        });
    }

    public interface CollectListener {
        void onclick(int position, int type);
    }

    public interface OnItemChildClickListener {
        void onItemChildClick(int position);
    }
}
