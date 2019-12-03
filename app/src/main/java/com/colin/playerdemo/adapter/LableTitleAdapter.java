package com.colin.playerdemo.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.TagTypeListBean;

import java.util.ArrayList;
import java.util.List;


public class LableTitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TagTypeListBean> stringList;
    LableListener lableListener;
    int item = 0;

    public void setItem(int item) {
        this.item = item;
        notifyDataSetChanged();
    }

    public void setLableListener(LableListener lableListener) {
        this.lableListener = lableListener;
    }

    public void setStringList(List<TagTypeListBean> stringList) {
        this.stringList.clear();
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public LableTitleAdapter() {
        super();
        this.stringList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LableTitle_AdapterHolder adapterHolder = new LableTitle_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lable_title, parent, false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LableTitle_AdapterHolder adapterHolder = (LableTitle_AdapterHolder) holder;
        adapterHolder.showLableTitle_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class LableTitle_AdapterHolder extends RecyclerView.ViewHolder {
        TextView lable_tv;

        public LableTitle_AdapterHolder(View itemView) {
            super(itemView);
            lable_tv = itemView.findViewById(R.id.lable_tv);
        }

        void showLableTitle_AdapterHolder(final int position) {
            if(item == position){
                itemView.setBackgroundResource(R.drawable.boder_all_12);
            }else {
                itemView.setBackgroundResource(R.drawable.boder_all_circular_gray_12);
//                itemView.setBackground(itemView.getContext().getDrawable(R.drawable.boder_all_circular_gray_12));
            }
            if (position == stringList.size() - 1) {
                Drawable dra = itemView.getContext().getResources().getDrawable(R.mipmap.ic_cz);
                dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
                lable_tv.setCompoundDrawables(dra, null, null, null);
                lable_tv.setCompoundDrawablePadding(5) ;
            }else {
                lable_tv.setCompoundDrawables(null, null, null, null);
                lable_tv.setCompoundDrawablePadding(5) ;
            }
            lable_tv.setText(stringList.get(position).getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lableListener.onclick(position);
                }
            });
        }
    }
    public interface LableListener {
        void onclick(int position);
    }
}
