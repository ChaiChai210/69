package com.colin.tomvod.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

public class HistorySearchAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HistorySearchAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_search_hot,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView hotSearchItem = helper.getView(R.id.tv_tag);
        hotSearchItem.setText(item);
        ViewGroup.LayoutParams lp = hotSearchItem.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams) hotSearchItem.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
    }
}
