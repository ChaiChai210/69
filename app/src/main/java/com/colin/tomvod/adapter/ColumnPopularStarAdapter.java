package com.colin.tomvod.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.Alone_Activity;
import com.colin.tomvod.bean.StarBean;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.List;

public class ColumnPopularStarAdapter extends BaseQuickAdapter<StarBean, BaseViewHolder> {
    public ColumnPopularStarAdapter(@Nullable List<StarBean> data) {
        super(R.layout.fragment_popular_star_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StarBean item) {
        Glide.with(mContext).load(item.getPortrait()).into((RoundImageView) helper.getView(R.id.column_image));
        helper.setText(R.id.column_class_tv,item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Alone_Activity.class).
                putExtra("id",item.getId()+"")));
        helper.setText(R.id.content_tv,item.getIntroduce());
        helper.setText(R.id.num_video_tv,item.getVideo_count()+"部电影");
        RecyclerView recyclerView = helper.getView(R.id.colum_h_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
       ColumnStarChildAdapter column_child_adapter = new ColumnStarChildAdapter(item.getVideo_list());
        recyclerView.setAdapter(column_child_adapter);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                itemView.getContext().startActivity(new Intent(itemView.getContext(),Star_Activity.class)
//                        .putExtra("id",list.get(position).getId()+""));
    }
}
