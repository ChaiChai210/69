package com.colin.playerdemo.popwindows;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colin.playerdemo.R;


//帮助界面
public class HelpWindow extends PopupWindow {
    RelativeLayout pop2_layout, pop3_layout, pop4_layout, pop5_layout, pop6_layout;
    LinearLayout pop1_layout;
    TextView next_tv, next_tv1, next_tv2, next_tv3, next_tv4, next_tv5;
    PopwindowsListener popwindowsListener;

    public HelpWindow(Context context, View parent) {
        super(context);
        init(context, parent);
    }


    void init(Context mContext, View parent) {
        View view = View
                .inflate(mContext, R.layout.pop_help, null);
        view.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.pop_anim_fade_ins));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(null);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        next_tv = view.findViewById(R.id.next_tv);
        next_tv1 = view.findViewById(R.id.next_tv1);
        next_tv2 = view.findViewById(R.id.next_tv2);
        next_tv3 = view.findViewById(R.id.next_tv3);
        next_tv4 = view.findViewById(R.id.next_tv4);
        next_tv5 = view.findViewById(R.id.next_tv5);
        pop1_layout = view.findViewById(R.id.pop1_layout);
        pop2_layout = view.findViewById(R.id.pop2_layout);
        pop3_layout = view.findViewById(R.id.pop3_layout);
        pop4_layout = view.findViewById(R.id.pop4_layout);
        pop5_layout = view.findViewById(R.id.pop5_layout);
        pop6_layout = view.findViewById(R.id.pop6_layout);
        next_tv.setOnClickListener(v -> {
            pop1_layout.setVisibility(View.GONE);
            pop2_layout.setVisibility(View.VISIBLE);
        });
        next_tv1.setOnClickListener(v -> {
            pop2_layout.setVisibility(View.GONE);
            pop3_layout.setVisibility(View.VISIBLE);
        });
        next_tv2.setOnClickListener(v -> {
            pop3_layout.setVisibility(View.GONE);
            pop4_layout.setVisibility(View.VISIBLE);
            popwindowsListener.Onclick(1);
        });
        next_tv3.setOnClickListener(v -> {
            pop4_layout.setVisibility(View.GONE);
            pop5_layout.setVisibility(View.VISIBLE);

            popwindowsListener.Onclick(2);
        });
        next_tv4.setOnClickListener(v -> {
            pop5_layout.setVisibility(View.GONE);
            pop6_layout.setVisibility(View.VISIBLE);
            popwindowsListener.Onclick(3);
        });
        next_tv5.setOnClickListener(v -> {
            popwindowsListener.Onclick(4);
            dismiss();
        });
    }

    public interface PopwindowsListener {
        void Onclick(int state);
    }

    public void setPopwindowsListener(PopwindowsListener popwindowsListener) {
        this.popwindowsListener = popwindowsListener;
    }
}
