package com.colin.playerdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.FendBackBeanList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class My_Feedback_Fragment_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<FendBackBeanList> lists;

    public void setLists(List<FendBackBeanList> lists) {
        this.lists.clear();
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    public My_Feedback_Fragment_Adapter() {
        super();
        this.lists = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        My_Feedback_Fragment_AdapterHolder adapterHolder = new My_Feedback_Fragment_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_feedback_fragment_adapter, parent, false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        My_Feedback_Fragment_AdapterHolder adapterHolder = (My_Feedback_Fragment_AdapterHolder) holder;
        adapterHolder.showMy_Feedback_Fragment_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class My_Feedback_Fragment_AdapterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tiem_tv)
        TextView tiemTv;
        @BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.content_tv)
        TextView contentTv;
        @BindView(R.id.img_layout)
        LinearLayout img_layout;

        public My_Feedback_Fragment_AdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void showMy_Feedback_Fragment_AdapterHolder(int position) {
            tiemTv.setText("反馈时间:" + lists.get(position).getCreate_date());
            titleTv.setText("反馈类型:" + lists.get(position).getName());
            contentTv.setText("反馈内容:" + lists.get(position).getReplay());
            if (lists.get(position).getPic() != null) {
                ImageView imageView = new ImageView(itemView.getContext());
                Glide.with(itemView.getContext()).load(lists.get(position).getPic()).into(imageView);
                img_layout.addView(imageView);
            } else {
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
