package com.colin.tomvod.popwindows;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.tomvod.R;
import com.colin.tomvod.adapter.PlayCommentDetailAdapter;
import com.colin.tomvod.bean.ChildPlayCommentBean;
import com.colin.tomvod.bean.PlayCommentBean;
import com.colin.tomvod.customeview.third.RoundImageView;
import com.colin.tomvod.net.BaseBean;
import com.colin.tomvod.net.BaseListBean;
import com.colin.tomvod.net.GsonHelper;
import com.colin.tomvod.net.URLs;
import com.colin.tomvod.utils.ButterKnifeUtil;
import com.colin.tomvod.utils.StringUtils;
import com.colin.tomvod.utils.UIhelper;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.BasePopupWindow;

public class CommentDetailPopup extends BasePopupWindow implements PlayCommentDetailAdapter.CommentListener {

    @BindView(R.id.comment_close_iv)
    ImageView commentCloseIv;
    @BindView(R.id.rl_comment_header)
    RelativeLayout rlCommentHeader;
    @BindView(R.id.head_image)
    RoundImageView headImage;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.rl_user_comment)
    RelativeLayout rlUserComment;
    @BindView(R.id.show_content_tv)
    TextView showContentTv;
    @BindView(R.id.tv_all_reply)
    TextView tvAllReply;
    @BindView(R.id.rv_comment_detail)
    RecyclerView rvCommentDetail;
    @BindView(R.id.srl_container)
    SmartRefreshLayout srlContainer;
    @BindView(R.id.iv_head)
    RoundImageView ivHead;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.tv_send_comment)
    TextView tvSendComment;
    @BindView(R.id.play_send_layout)
    LinearLayout playSendLayout;
    private Context mContext;
    private PlayCommentBean bean;
    private PlayCommentDetailAdapter commentDetailAdapter;
    private List<ChildPlayCommentBean> childlist = new ArrayList<>();
    private int page = 1;
    private String id;
    private String mode;
    private boolean hasMore = true;

    public CommentDetailPopup(Context context, String id, PlayCommentBean bean, String mode) {
        super(context);
        this.mContext = context;
        this.bean = bean;
        this.id = id;
        this.mode = mode;
        ButterKnifeUtil.bind(this, getContentView());
//        setPopupGravity(Gravity.BOTTOM);
        initView(bean);
        getChildComment(id, bean, mode);
    }

    private void initView(PlayCommentBean bean) {
//        srlContainer.setRefreshHeader(new ClassicsHeader(mContext));
        srlContainer.setEnableLoadMore(true);
        srlContainer.setEnableOverScrollBounce(true);
        srlContainer.setEnableOverScrollDrag(true);
        srlContainer.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh();
            page = 1;
            hasMore = true;
            getChildComment(id, bean, mode);
        });
        srlContainer.setEnableRefresh(false);
        srlContainer.setOnLoadMoreListener(refreshLayout -> {
            if (hasMore) {
                refreshLayout.finishLoadMore();
                page++;
            } else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });

        Glide.with(mContext).load(bean.getPortrait()).into(headImage);
        Glide.with(mContext).load(bean.getPortrait()).into(ivHead);
        nameTv.setText(bean.getNickname());
        timeTv.setText(bean.getCreate_time());
        showContentTv.setText(bean.getContent());
        UIhelper.setGenderIcon(mContext, bean.getSex(), nameTv);

        rvCommentDetail.setLayoutManager(new LinearLayoutManager(mContext));
        commentDetailAdapter = new PlayCommentDetailAdapter(childlist);
        rvCommentDetail.setAdapter(commentDetailAdapter);
        commentDetailAdapter.setChildOnclick(this::childOnclick);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_comment_detail);
    }


    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(-1f, 0f, 500);
    }


    private void getChildComment(String id, PlayCommentBean bean, String mode) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("comment_id", bean.getId());
        httpParams.put("type", "reply");
        httpParams.put("av_id", id);
        httpParams.put("page", page);
        httpParams.put("mode", mode);
        httpParams.put("page_size", 10);
        OkGo.<String>get(URLs.COMMENT).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //解析data里面为数组的形式，用的baseListBean基本类
                Type type = new TypeToken<BaseListBean<ChildPlayCommentBean>>() {
                }.getType();
                BaseListBean<ChildPlayCommentBean> beanBaseListBean = GsonHelper.gson.fromJson(response.body(), type);
                UIhelper.stopLoadingDialog();

                //返回码为成功时的处理
                if (beanBaseListBean.getResCode() == 0) {
                    if (beanBaseListBean.getData().size() < 10) {
                        hasMore = false;
                    }
                    if (page == 1) {
                        commentDetailAdapter.replaceData(beanBaseListBean.getData());
                    } else {
                        commentDetailAdapter.addData(beanBaseListBean.getData());
                    }
                } else {
                    FancyToast.makeText(mContext, beanBaseListBean.getInfo(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(mContext);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    private void putComment(String comment_id, int islike) {
        HttpParams httpParams = new HttpParams();
        String url;
        url = URLs.COMMENT + "/" + comment_id;
        httpParams.put("type", "reply");
        httpParams.put("islike", islike);
        OkGo.<String>put(url).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
//                UIhelper.stopLoadingDialog();
                Type types = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBeana = GsonHelper.gson.fromJson(response.body(), types);

                if (baseBeana.getCode() == 0) {
                    page = 1;
                    getChildComment(id, bean, mode);
                } else {
//                    UIhelper.ToastMessage(baseBeana.getMsg());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
//                UIhelper.showLoadingDialog(Play_Activity.this);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
//                UIhelper.stopLoadingDialog();
            }
        });
    }

    private void postchildComment(String content) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("comment_id", bean.getId());
        httpParams.put("content", content);
        httpParams.put("to_uid", bean.getUid());
        httpParams.put("type", "reply");
        OkGo.<String>post(URLs.COMMENT).params(httpParams).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UIhelper.stopLoadingDialog();

                Type type = new TypeToken<BaseBean>() {
                }.getType();
                BaseBean baseBeana = GsonHelper.gson.fromJson(response.body(), type);

                //返回码为成功时的处理
                if (baseBeana.getCode() == 0) {
                    mode = "time";//(支持参数 time 时间排序,zan 赞排序)
                    page = 1;
                    getChildComment(id, bean, mode);
                } else {
                    UIhelper.ToastMessage(baseBeana.getInfo());
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //显示loading框
                UIhelper.showLoadingDialog(mContext);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                UIhelper.stopLoadingDialog();

            }
        });
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0f, -1f, 500);
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void childOnclick(int position, int islike) {
        putComment(childlist.get(position).getId() + "", islike);
    }

    @OnClick({R.id.comment_close_iv, R.id.tv_send_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comment_close_iv:
                dismiss();
                break;
            case R.id.tv_send_comment:
                String cotent = etComment.getText().toString();
                if (StringUtils.isEmpty(cotent)) {
                    Toast.makeText(mContext, "请输入评论内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                postchildComment(cotent);
                UIhelper.hideSoftInput(view);
                break;
        }
    }
}
