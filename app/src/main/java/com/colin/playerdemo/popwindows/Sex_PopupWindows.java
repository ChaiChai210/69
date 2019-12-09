package com.colin.playerdemo.popwindows;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.colin.playerdemo.R;


public class Sex_PopupWindows extends PopupWindow {
    Context mContext;
    Activity activity;
    showPhoto showPhoto;
    private boolean hasDelete = true;

    public void setHasDelete(boolean hasDelete) {
        this.hasDelete = hasDelete;
        if (hasDelete) {
            delete_layout.setVisibility(View.VISIBLE);
        } else {
            delete_layout.setVisibility(View.GONE);
        }
    }

    public void setShowPhoto(Sex_PopupWindows.showPhoto showPhoto) {
        this.showPhoto = showPhoto;
    }

    Button delete_Photo;
    LinearLayout delete_layout;

    public Sex_PopupWindows(final Context mContext, View parent, final Activity activity) {
        this.mContext = mContext;
        this.activity = activity;
        View view = View
                .inflate(mContext, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.fade_ins));
        LinearLayout ll_popup = view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.push_bottom_in_2));

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(null);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        update();

        Button bt1 = view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = view
                .findViewById(R.id.item_popupwindows_cancel);
        delete_Photo = view
                .findViewById(R.id.delete_Photo);
        delete_layout = view
                .findViewById(R.id.delete_layout);
        delete_Photo.setVisibility(View.VISIBLE);
        bt1.setText("男");
        bt2.setText("女");
        delete_Photo.setOnClickListener(v -> dismiss());
        bt1.setOnClickListener(v -> {
            showPhoto.onclick(1);
            dismiss();
        });
        bt2.setOnClickListener(v -> {

            showPhoto.onclick(2);
            dismiss();
        });
        bt3.setOnClickListener(v -> dismiss());

    }

    public interface showPhoto {
        void onclick(int type);
    }
}
