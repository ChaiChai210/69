package com.colin.tomvod.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.colin.tomvod.R;
import com.colin.tomvod.base.CommonImmerseActivity;
import com.colin.tomvod.bean.ConFigBean;
import com.colin.tomvod.bean.UserInfoBean;
import com.colin.tomvod.customeview.third.RoundImageView;
import com.colin.tomvod.net.BaseBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.utils.AppUtils;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Generalize_Activity extends CommonImmerseActivity {
    @BindView(R.id.gener_image)
    RoundImageView generImage;
    @BindView(R.id.code_tv)
    TextView codeTv;
    @BindView(R.id.code_layout)
    LinearLayout codeLayout;
    @BindView(R.id.save_tv)
    TextView saveTv;
    @BindView(R.id.copy_tv)
    TextView copyTv;
    @BindView(R.id.guanwang_tv)
    TextView guanwang_tv;
    @BindView(R.id.gener_layout)
    RelativeLayout gener_layout;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    private String code;
    String copy = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_generalize;
    }

    @Override
    protected void initView() {
        tvCenter.setText(getString(R.string.text_promote_refund));
    }

    @Override
    public void initData() {
        darkImmerseFontColor();
        Login();
        getUserInfo();
    }

    /**
     * 查询用户信息-APP-WEB
     */
    BaseBean<UserInfoBean> baseBean;

    private void getUserInfo() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.USERINFO).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<UserInfoBean>>() {
                }.getType();
                baseBean = GsonHelper.gson.fromJson(response.body(), type);
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    code = baseBean.getData().getInvite_code();
                    codeTv.setText(code + "");
                } else {
                    FancyToast.makeText(Generalize_Activity.this, baseBean.getInfo(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
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


    @OnClick({R.id.iv_left, R.id.save_tv, R.id.copy_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_tv://保存
                getViewBp(gener_layout);
                break;
            case R.id.copy_tv://复制
                Toast.makeText(this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
                AppUtils.copyToClipboard(this, copy);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }


    /**
     * 查询当前配置
     */
    private void Login() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.CONFIGURE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<ConFigBean>() {
                }.getType();
                ConFigBean conFigBean = GsonHelper.gson.fromJson(response.body(), type);
                //返回码为成功时的处理
                if (conFigBean.getCode() == 0) {
                    for (int i = 0; i < conFigBean.getData().size(); i++) {
                        if (conFigBean.getData().get(i).getType().equals("domain")) {
                            guanwang_tv.setText("使用前请保存官网,可在官网下载最新APP官网地址: " + conFigBean.getData().get(i).getContent());
//                            generImage.setImageBitmap(QRCodeUtil.createQRCodeBitmap(conFigBean.getData().get(i).getContent(), generImage.getWidth(),
//                                    generImage.getHeight()));
                            Bitmap bitmap = CodeCreator.createQRCode(conFigBean.getData().get(i).getContent(), generImage.getWidth(),
                                    generImage.getHeight(), null);
                            generImage.setImageBitmap(bitmap);
                        }
                        if (conFigBean.getData().get(i).getType().equals("shareText")) {
                            copy = conFigBean.getData().get(i).getContent();
                        }
                    }

                } else {
                    UIhelper.ToastMessage(conFigBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Generalize_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }


    public void getViewBp(View v) {
        //相关权限的申请 存储权限
        try {
            if (ActivityCompat.checkSelfPermission(Generalize_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(Generalize_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
                ActivityCompat.requestPermissions(Generalize_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                saveMyBitmap("AuthCode", createViewBitmap(v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//使用IO流将bitmap对象存到本地指定文件夹

    public void saveMyBitmap(final String bitName, final Bitmap bitmap) {
        new Thread(new Runnable() {
            @Override

            public void run() {
                String filePath = Environment.getExternalStorageDirectory().getPath();
                File file = new File(filePath + "/DCIM/Camera/" + bitName + ".png");
                try {
                    file.createNewFile();


                    FileOutputStream fOut = null;
                    fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    Message msg = Message.obtain();
                    msg.obj = file.getPath();
                    handler.sendMessage(msg);
//Toast.makeText(PayCodeActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String picFile = (String) msg.obj;
            String[] split = picFile.split("/");
            String fileName = split[split.length - 1];
            try {
                MediaStore.Images.Media.insertImage(getApplicationContext()
                        .getContentResolver(), picFile, fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                    + picFile)));
            saveCode();

        }
    };

    //权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
// If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
//                        saveMyBitmap("AuthCode", createViewBitmap(v));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Generalize_Activity.this, "请先开启读写权限", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    /**
     * 保存二维码
     */


    private void saveCode() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("isbtn", "1");//	1表示当日保存的第一次，0表示当日以保存，没有则默认当日以保存
        OkGo.<String>get(URLs.SAVEQCODE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    Toast.makeText(Generalize_Activity.this, "二维码保存成功", Toast.LENGTH_LONG).show();
                } else {
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(Generalize_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
