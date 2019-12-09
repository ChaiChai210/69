package com.colin.playerdemo.popwindows;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.colin.playerdemo.R;


public class PhotoPopWindow extends PopupWindow {
    Context mContext ;
    Activity activity;
    showPhoto showPhoto;
    private boolean hasDelete  = true;

    public void setHasDelete(boolean hasDelete) {
        this.hasDelete = hasDelete;
        if(hasDelete){
            delete_layout.setVisibility(View.VISIBLE);
        }else {
            delete_layout.setVisibility(View.GONE);
        }
    }

    public void setShowPhoto(PhotoPopWindow.showPhoto showPhoto) {
        this.showPhoto = showPhoto;
    }
    Button delete_Photo;
    LinearLayout delete_layout;
    public PhotoPopWindow(final Context mContext, View parent , final Activity activity) {
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
         delete_Photo =  view
                .findViewById(R.id.delete_Photo);
        delete_layout =  view
                .findViewById(R.id.delete_layout);
        delete_Photo.setVisibility(View.VISIBLE);
        delete_Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                showPhoto.onclick(3);
                dismiss();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 0);
                } else {
              showPhoto.onclick(1);
                }
                dismiss();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                } else {
                    showPhoto.onclick(2);

                }

                dismiss();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

    }
    public interface showPhoto{
         void onclick(int type);
    }
}
