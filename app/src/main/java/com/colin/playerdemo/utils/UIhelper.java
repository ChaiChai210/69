package com.colin.playerdemo.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.colin.playerdemo.AppContext;
import com.colin.playerdemo.R;
import com.colin.playerdemo.net.BaseBean;
import com.colin.playerdemo.net.GsonHelper;
import com.colin.playerdemo.net.URLs;
import com.colin.playerdemo.net.rxjava.Api;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.text.DecimalFormat;


/**
 * 描述：界面工具类
 * 作者：李昌骏 on 2018\3\21 0021 09:18
 * 电话：13881771371
 */

public class UIhelper {
    private static AlertDialog dlg;

    /**
     * 全局web样式
     */
    // 链接样式文件，代码块高亮的处理
    public final static String linkCss = "<script type=\"text/javascript\" src=\"file:///android_asset/shCore.js\"></script>"
            + "<script type=\"text/javascript\" src=\"file:///android_asset/brush.js\"></script>"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shThemeDefault.css\">"
            + "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shCore.css\">"
            + "<script type=\"text/javascript\">SyntaxHighlighter.all();</script>";
    public final static String WEB_STYLE = linkCss
            + "<style>* {font-size:13px;line-height:23px;color:#999;font-family:STXihei;} a {color:#3E62A6;} img {max-width:310px;} "
            + "img.alignleft {float:left;max-width:120px;margin:0 10px 5px 0;border:1px solid #ccc;background:#ffeeeeee;padding:2px;} </style>";

    public static void setGenderIcon(Context context, String sex, TextView textView) {
        Drawable drawable = null;
        if (sex.equals("1")) {
            drawable = context.getResources().getDrawable(R.mipmap.boy_comment_mark);
        } else {
            drawable = context.getResources().getDrawable(R.mipmap.girl_comment_mark);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
        textView.setCompoundDrawablePadding(5);
    }

//    /**
//     * 获取屏幕尺寸
//     */
//    public static int getDisplayWidth() {
//        DisplayMetrics metrics = AppContext.applicationContext.getResources ().getDisplayMetrics ();
//        int screenWidth = metrics.widthPixels;
//        return screenWidth;
//    }
//
//    public static int getDisplayHeight() {
//        DisplayMetrics metrics = AppContext.applicationContext.getResources ().getDisplayMetrics ();
//        int screenHeight = metrics.heightPixels;
//        return screenHeight;
//    }

    /**
     * 添加广告点击记录
     */

    public static void addClickAdRecord(Context context, int ad_id) {
        HttpParams httpParams = new HttpParams();

        httpParams.put("phone_model", Build.MODEL);
        httpParams.put("ad_id", ad_id);
        OkGo.<String>post(URLs.ADDADVER).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
//                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);

//                //返回码为成功时的处理
//                if (baseBean.getCode() == 0) {
//                } else {
//
//                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    /**
     * 将 double 保留至两位小数
     *
     * @param d double
     * @return string
     */
    public static String formatDouble(Double d) {
        DecimalFormat format = new DecimalFormat("#######0.00");
        return format.format(d);
    }


    /**
     * 显示加载等待界面
     *
     * @param context
     */
    public static void showLoadingDialog(Context context) {
        if (dlg == null) {
            dlg = new AlertDialog.Builder(context, R.style.CustomDialog).create();
            dlg.show();
            dlg.setCancelable(false);
            Window window = dlg.getWindow();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.core_dialog_loading, null);
            window.setContentView(view);
        } else {
            if (!dlg.isShowing()) {
                dlg = new AlertDialog.Builder(context, R.style.CustomDialog).create();
                dlg.show();
                dlg.setCancelable(false);
                Window window = dlg.getWindow();
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.core_dialog_loading, null);
                window.setContentView(view);
            }
        }
    }

    /**
     * 取消加载界面
     */
    public static void stopLoadingDialog() {
        if (dlg != null) {
            dlg.dismiss();
        }
    }
    /**
     * 添加广告点击记录
     */

//    public static void addClickAdRecord(int ad_id) {
//        RxHttp.setDebug(true);
//        RxHttpUtils.postWithToken(Api.adClickRecord)
//                .add("phone_model", Build.MODEL)
//                .add("ad_id", ad_id)
//                .asResponse(Object.class)
//                .subscribe(s -> {          //订阅观察者，
//                    Log.e("chai","ad");
//                }, throwable -> {
//                });
//    }

    /**
     * 编辑栏错误提示
     *
     * @param str
     * @return
     * @Description
     * @author zipeng
     */
    public static CharSequence edtError(String str) {
        return Html.fromHtml("<font color=#ff0000>" + str + "</font>");
    }

    /**
     * 编辑栏错误提示
     *
     * @param editText
     * @param str
     */
    public static void edtError(EditText editText, String str) {
        if (editText != null)
            editText.setError(edtError(str));
    }

//    /**
//     * 发送App异常崩溃报告
//     *
//     * @param cont
//     * @param crashReport
//     */
//    public static void sendAppCrashReport(final Context cont,
//                                          final String crashReport) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
//        builder.setIcon(android.R.drawable.ic_dialog_info);
//        builder.setTitle(R.string.app_error);
//        builder.setMessage(R.string.app_error_message);
//        builder.setPositiveButton(R.string.submit_report,
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        // 发送异常报告
//                        Intent i = new Intent(Intent.ACTION_SEND);
//                        // i.setType("text/plain"); //模拟器
//                        i.setType("message/rfc822"); // 真机
//                        i.putExtra(Intent.EXTRA_EMAIL,
//                                new String[]{"zhangdeyi@oschina.net"});
//                        i.putExtra(Intent.EXTRA_SUBJECT, "客户端 - 错误报告");
//                        i.putExtra(Intent.EXTRA_TEXT, crashReport);
//                        cont.startActivity(Intent.createChooser(i, "发送错误报告"));
//                        // 退出
//                        AppManager.getAppManager().AppExit(cont);
//                    }
//                });
//        builder.setNegativeButton(R.string.sure,
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        // 退出
//                        AppManager.getAppManager().AppExit(cont);
//                    }
//                });
//        builder.show();
//    }

    /**
     * 弹出Toast消息
     *
     * @param msg
     */
    public static void ToastMessage(String msg) {
        Toast toast = Toast.makeText(AppContext.applicationContext, msg,
                Toast.LENGTH_SHORT);
        //可以控制toast显示的位置
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * 发送通知
     *
     * @param context    点击通知后跳转到的界面
     * @param tickerText 任务栏显示的内容
     * @param title      通知栏显示的标题
     * @param content    通知栏显示的内容
     * @Description
     * @author zipeng
     */
    public static void sendNotification(Context context, Intent intent,
                                        String tickerText, String title, String content) {
        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        Notification n = new Notification.Builder(context)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(tickerText)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .build();

//        n.icon = R.drawable.ic_launcher;
//        n.tickerText = tickerText;
//        n.when = System.currentTimeMillis();
//        n.defaults = Notification.DEFAULT_SOUND;
//        n.flags |= Notification.FLAG_AUTO_CANCEL;
        // n.flags=Notification.FLAG_ONGOING_EVENT;
        nm.notify(AppContext.notificationId, n);
        AppContext.notificationId++;
    }

    /**
     * 将dip单位的数值转化为px单位的值
     *
     * @param dpValue
     * @return
     * @Description
     * @author zipeng
     */
    public static int dip2px(float dpValue) {
        final float scale = AppContext.applicationContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }

    /**
     * 将px单位的数值转化为dip单位的值
     *
     * @param dpValue
     * @return
     * @Description
     * @author zipeng
     */
    public static int px2dip(float dpValue) {
        final float scale = AppContext.applicationContext.getResources().getDisplayMetrics().density;
        return (int) ((dpValue) / scale);

    }

    /**
     * sp转px
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, AppContext.applicationContext.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal) {
        return (pxVal / AppContext.applicationContext.getResources().getDisplayMetrics().scaledDensity);
    }


    /**
     * 获取宽度
     *
     * @param view
     * @return
     */
    public static int getViewWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }

    /**
     * 获取高度
     *
     * @param view
     * @return
     */
    public static int getViewHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredHeight();
    }

    /**
     * @param bmp
     * @param file
     * @Description 将图片保存到本地时进行压缩, 即将图片从Bitmap形式变为File形式时进行压缩
     * @author Administrator
     */
    public static void compressBmpToFile(Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while ((float) baos.toByteArray().length / 1024 > (float) 120) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static AssetManager mgr = AppContext.applicationContext.getAssets();//得到AssetManager
//    static Typeface tf = Typeface.createFromAsset (mgr, "DroidSansFallback.ttf");//根据路径得到Typeface

//    /**
//     * 设置字体
//     *
//     * @param view
//     */
//    public static void setTextFont(View view) {
//        if (view instanceof ViewGroup) {
//            ViewGroup viewGroup = (ViewGroup) view;
//            for (int i = 0; i < viewGroup.getChildCount (); i++) {
//                setTextFont (viewGroup.getChildAt (i));
//            }
//        } else if (view instanceof TextView) {
//            TextView tv = (TextView) view;
//            tv.setTypeface (tf);//设置字体
//        } else
//            return;
//    }


    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideSoftKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    public static void hideSoftInput(final View view) {
        InputMethodManager imm =
                (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
