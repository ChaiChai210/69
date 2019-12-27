package com.colin.tomvod.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.bean.HotSearchBean;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

public class HotSearchAdapter extends BaseQuickAdapter<HotSearchBean, BaseViewHolder> {
    public HotSearchAdapter(@Nullable List<HotSearchBean> data) {
        super(R.layout.adapter_search_hot,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSearchBean item) {
        TextView hotSearchItem = helper.getView(R.id.tv_tag);
        hotSearchItem.setText(item.getContent());
        ViewGroup.LayoutParams lp = hotSearchItem.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams) hotSearchItem.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
    }
}
