package com.colin.tomvod.popwindows;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.colin.tomvod.R;


public class Cache_Popwindows extends PopupWindow {
    public Cache_Popwindows(Context context, View parent) {
        super(context);
        init(context,parent);
    }
    LinearLayout cache_layout;
    void init(Context mContext, View parent) {
        View view = View
                .inflate(mContext, R.layout.pop_cache, null);
        view.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.pop_anim_fade_ins));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(null);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        cache_layout = view.findViewById(R.id.cache_layout);
        cache_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
