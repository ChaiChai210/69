package com.colin.playerdemo.popwindows;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.colin.playerdemo.R;


public class Clean_Cache_Popwindows extends PopupWindow {
    public Clean_Cache_Popwindows(Context context, View parent, TextView cash_tv) {
        super(context);
        init(context,parent,cash_tv);
    }
    TextView cancel_tv,confirm_tv;
    void init(final Context mContext, View parent, final TextView cash_tv) {
        View view = View
                .inflate(mContext, R.layout.pop_clean_cache, null);
        view.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.pop_anim_fade_ins));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(null);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        cancel_tv = view.findViewById(R.id.cancel_tv);
        confirm_tv = view.findViewById(R.id.confirm_tv);
        confirm_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DataCleanManager.clearAllCache (mContext);
////                DataCleanManager.getTotalCacheSize (this);
//                Tools.showTip (mContext, "缓存清理完毕");
                cash_tv.setText("0M");
                dismiss();
            }
        });
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
