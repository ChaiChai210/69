package com.colin.playerdemo.popwindows;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.colin.playerdemo.R;


public class PromotionTipPopWindow extends PopupWindow {
    public PromotionTipPopWindow(Context context, View parent) {
        super(context);
        init(context, parent);
    }

    TextView qq_tv;
    LinearLayout tg_p_layout;

    void init(final Context mContext, View parent) {
        View view = View
                .inflate(mContext, R.layout.pop_promotion_tip, null);
        view.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.pop_anim_fade_ins));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(null);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        tg_p_layout = view.findViewById(R.id.tg_p_layout);
        qq_tv = view.findViewById(R.id.qq_tv);
        tg_p_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
//        qq_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, Popularize_Activity.class));
//                dismiss();
//            }
//        });
    }
}
