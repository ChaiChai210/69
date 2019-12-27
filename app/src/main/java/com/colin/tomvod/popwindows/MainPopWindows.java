package com.colin.tomvod.popwindows;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.colin.tomvod.R;
import com.colin.tomvod.utils.StringUtils;

/**
 *
 */

public class MainPopWindows extends PopupWindow {
    public MainPopWindows(Context context, View parent, String alertText, String domain) {
        super(context);
        init(context, parent, alertText, domain);
    }

    LinearLayout qq_layout;
    TextView qq_tv, domain_tv;

    void init(final Context mContext, View parent, final String alertText, final String domain) {
        View view = View
                .inflate(mContext,
                        R.layout.pop_home_fragment, null);
        view.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.pop_anim_fade_ins));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(null);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        qq_layout = view.findViewById(R.id.qq_layout);
        qq_tv = view.findViewById(R.id.qq_tv);
        domain_tv = view.findViewById(R.id.domain_tv);
        SpannableStringBuilder ss = new SpannableStringBuilder("官网地址:" + domain);
        ss.setSpan(new ClickableSpan() {
                       @Override
                       public void onClick(View widget) {
                           Uri uri = Uri.parse("https://" + domain);
                           Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                           mContext.startActivity(intent);
                       }

                   }, 5, domain.length() + 5
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#5e9bff")), 5, domain.length() + 5
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        domain_tv.setMovementMethod(LinkMovementMethod.getInstance());
        domain_tv.setText(ss);
        if (StringUtils.isEmpty(alertText)) {
            qq_tv.setText("暂无");
        } else {
            qq_tv.setText("" + alertText);
        }
        qq_layout.setOnClickListener(v -> dismiss());
    }
}
