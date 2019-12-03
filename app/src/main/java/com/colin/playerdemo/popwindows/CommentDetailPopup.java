package com.colin.playerdemo.popwindows;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import com.colin.playerdemo.R;
import com.colin.playerdemo.utils.ButterKnifeUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import razerdp.basepopup.BasePopup;
import razerdp.basepopup.BasePopupSupporter;
import razerdp.basepopup.BasePopupSupporterX;
import razerdp.basepopup.BasePopupWindow;

public class CommentDetailPopup extends BasePopupWindow {
    public CommentDetailPopup(Context context) {
        super(context);
        ButterKnifeUtil.bind(this, getContentView());
        setPopupGravity(Gravity.BOTTOM);
    }

    @Override
    public View onCreateContentView() {
            return createPopupById(R.layout.popup_comment_detail);
    }



    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(-1f, 0f, 500);
    }


    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0f, -1f, 500);
    }

    @OnClick(R.id.comment_close_iv)
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
