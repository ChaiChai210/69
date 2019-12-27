package com.colin.tomvod.popwindows;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.colin.tomvod.R;
import com.colin.tomvod.utils.StringUtils;


public class Name_Popwindows extends PopupWindow {
    public Name_Popwindows(Context context, View parent) {
        super(context);
        init(context,parent);
    }
    NameListener nameListener;

    public void setNameListener(NameListener nameListener) {
        this.nameListener = nameListener;
    }

    Button cancel_Photo,delete_Photo;
    EditText name_edt;
    void init(final Context mContext, View parent) {
        View view = View
                .inflate(mContext, R.layout.pop_change_nick_name, null);
        view.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.pop_anim_fade_ins));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(null);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);

        delete_Photo = view.findViewById(R.id.delete_Photo);
        cancel_Photo = view.findViewById(R.id.cancel_Photo);
        name_edt = view.findViewById(R.id.name_edt);
        cancel_Photo.setOnClickListener(v -> dismiss());
        delete_Photo.setOnClickListener(v -> {
            if(StringUtils.isEmpty(name_edt.getText().toString())){
                Toast.makeText(mContext, "请输入昵称", Toast.LENGTH_SHORT).show();
                return;
            }
            nameListener.Onclick(name_edt.getText().toString());
            dismiss();
        });

    }
    public interface NameListener {
        void Onclick(String name);
    }
}
