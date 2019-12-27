package com.colin.tomvod.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.colin.tomvod.R;
import com.colin.tomvod.adapter.Need_Feedback_Adapter;
import com.colin.tomvod.base.BaseFragment;
import com.colin.tomvod.bean.Change_Head_bean;
import com.colin.tomvod.bean.FeedBackBean;
import com.colin.tomvod.net.BaseBean;
import com.colin.tomvod.net.BaseListBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.popwindows.PhotoPopWindow;
import com.colin.tomvod.utils.AppUtils;
import com.colin.tomvod.utils.StringUtils;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class Need_Feedback_Fragment extends BaseFragment {
    @BindView(R.id.feedback_rv)
    RecyclerView feedbackRv;
    @BindView(R.id.feed_edt)
    EditText feedEdt;
    @BindView(R.id.add_iv)
    ImageView addIv;
    @BindView(R.id.commit_tv)
    TextView commitTv;
    @BindView(R.id.feedback_layout)
    NestedScrollView feedback_layout;
    Need_Feedback_Adapter adapter;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;


    @Override
    public int getContentViewId() {
        return R.layout.need_feedback_fragment;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        feedbackRv.setLayoutManager(new GridLayoutManager(activity, 3));
        adapter = new Need_Feedback_Adapter();
        feedbackRv.setAdapter(adapter);
        getSearchHot();
        adapter.setItemOnclikListener(new Need_Feedback_Adapter.ItemOnclikListener() {
            @Override
            public void Onclick(int position, String id) {
                opiniontype_id = id;
                adapter.setItemClick(position);
            }
        });
    }

    @OnClick({R.id.add_iv, R.id.commit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_iv:
                PhotoPopWindow popupWindows = new PhotoPopWindow(activity, feedback_layout, activity);
                popupWindows.setHasDelete(false);
                popupWindows.setShowPhoto(new PhotoPopWindow.showPhoto() {
                    @Override
                    public void onclick(int type) {
                        if (type == 1) {
                            fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + "photo.jpg");
                            imageUri = Uri.fromFile(fileUri);
                            imageUri = FileProvider.getUriForFile(activity, "com.tmkj.vod.FileProvider", fileUri);//通过FileProvider创建一个content类型的Uri
                            AppUtils.takePicture(activity, imageUri, 5);
                        } else if (type == 2) {
                            AppUtils.openPic(activity, 2);
                        }
                    }
                });
                break;
            case R.id.commit_tv:
                content = feedEdt.getText().toString();
                if (StringUtils.isEmpty(content)) {
                    Toast.makeText(activity, "请填写反馈内容", Toast.LENGTH_SHORT).show();
                    return;
                } else if (content.length() < 10) {
                    Toast.makeText(activity, "反馈内容长度必须大于10字", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isEmpty(opiniontype_id)) {
                    Toast.makeText(activity, "请选择反馈类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                addOpinion();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2://访问相册完成回调
                    if (AppUtils.hasSdcard()) {
                        Uri newUri = Uri.parse(AppUtils.getPath(activity, data.getData()));
                        fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + "crop_photo.jpg");
                        if (fileCropUri.exists())
                            fileCropUri.delete();
                        cropImageUri = Uri.fromFile(fileCropUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(activity, "com.colin.playerdemo.FileProvider", new File(newUri.getPath()));
                        AppUtils.cropImageUri(activity, newUri, cropImageUri, 1, 1, 480, 480, 3);
                    } else {
                        Toast.makeText(activity, "设备没有SD卡", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 3://裁剪完成
                    getQNTk(fileCropUri);
                    break;
                case 5://拍照完成
                    fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + "crop_photo.jpg");
                    if (fileCropUri.exists())
                        fileCropUri.delete();
                    cropImageUri = Uri.fromFile(fileCropUri);
                    AppUtils.cropImageUri(activity, imageUri, cropImageUri, 1, 1, 480, 480, 3);
                    break;
            }
        }
    }

    private void getQNTk(File fileCropUri) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "user_pic");
        httpParams.put("file", fileCropUri);
        OkGo.<String>post(URLs.ADDFILE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean<Change_Head_bean>>() {
                }.getType();
                BaseBean<Change_Head_bean> baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();


                //返回码为成功时的处理
                if (baseBean.isSuccess()) {
                    Glide.with(activity).applyDefaultRequestOptions(new RequestOptions().error(R.mipmap.ic_head_l))
                            .load(baseBean.getData().getDomain()+baseBean.getData().getUrl()).into(addIv);
                    pic = baseBean.getData().getUrl();
                } else {
                    UIhelper.ToastMessage(baseBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });

    }

    /**
     * 查询
     */
    private void getSearchHot() {
        HttpParams httpParams = new HttpParams();
        OkGo.<String>get(URLs.OPINIONTYPE).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<FeedBackBean>>() {
                }.getType();
                BaseListBean<FeedBackBean> searchHotBeanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();


                //返回码为成功时的处理
                if (searchHotBeanBaseListBean.getResCode() == 0) {
                    adapter.setList(searchHotBeanBaseListBean.getData());
                } else {
                    UIhelper.ToastMessage(searchHotBeanBaseListBean.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    /**
     * 添加用户反馈信息
     */
    private String pic, opiniontype_id, content;

    private void addOpinion() {
        HttpParams httpParams = new HttpParams();
        if (!StringUtils.isEmpty(pic)) {
            httpParams.put("pic", pic);
        }
        httpParams.put("opiniontype_id", opiniontype_id);
        httpParams.put("content", content);
        OkGo.<String>post(URLs.OPINION).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();
                UIhelper.ToastMessage(baseBean.getInfo());
                //返回码为成功时的处理
                if (baseBean.getCode() == 0) {
                    activity.finish();
                } else {
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(activity);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }
}
