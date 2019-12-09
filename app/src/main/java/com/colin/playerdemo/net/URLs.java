package com.colin.playerdemo.net;


public class URLs {
    private static String IP = "api.xxvfx.com"; // https://api.yydsb.com/
    private static String HOST = IP;
    private final static String HTTP = "https://";
    private final static String URL_SPLITTER = "/";
    private final static String APP_STORE_HOST = HTTP + HOST + URL_SPLITTER;
    public final static String APP_STORE = HTTP + HOST ;

    //----更新----
    public final static String CHECK_NEW_VERSION = APP_STORE_HOST + "upgrad/checkVerIsLastest"; // 请求更新接口

    //--------------login----------------
    public final static String LOGIN = APP_STORE_HOST + "api/user/login/edit"; // 登录
    public final static String REGISTER = APP_STORE_HOST + "api/user/register"; // 用户注册接口
    public final static String GETTOKEM = APP_STORE_HOST + "api/user/init"; // 注册token
    public final static String CHANGEPSD = APP_STORE_HOST + "api/user/admin"; // 修改密码
    public final static String RECORDS = APP_STORE_HOST + "api/records"; // 查询观看记录-APP-Web
    public final static String USERINFO = APP_STORE_HOST + "api/user"; // 查询用户信息-APP-WEB
    public final static String ADDFILE = APP_STORE_HOST + "api/file"; // 上传
    public final static String CHANGE_USERINFO = APP_STORE_HOST + "api/user"; // 修改个人资料
    public final static String GETCODE = APP_STORE_HOST + "api/sms"; // 发送短信验证码
    public final static String RECOMVIDEO = APP_STORE_HOST + "api/recomvideo"; // 获取推荐列表
    public final static String APPMAIN = APP_STORE_HOST + "api/app/main"; // 首页数据
    public final static String APPHOME = APP_STORE_HOST + "api/app/home"; // 首页数据
    public final static String SEARCHGOT = APP_STORE_HOST + "api/search/hot"; // 热门搜索内容
    public final static String PROBLEMS = APP_STORE_HOST + "api/problems"; // 查询问题
    public final static String OPINIONTYPE = APP_STORE_HOST + "api/opiniontype"; // 查询
    public final static String OPINION= APP_STORE_HOST + "api/opinion"; // 添加用户反馈信息
    public final static String NOTICE= APP_STORE_HOST + "api/notice"; // 查询公告
    public final static String COLLECT= APP_STORE_HOST + "api/collect"; // 查询收藏视频
    public final static String STARLIST= APP_STORE_HOST + "api/star"; // 查询明星列表
    public final static String TAGTYPE= APP_STORE_HOST + "api/tagtype"; // 查询标签类别
    public final static String TAGLIST= APP_STORE_HOST + "api/tag"; // 获取标签列表
    public final static String SEARCH= APP_STORE_HOST + "api/search"; // 全局搜索
    public final static String MINEUSERINFO= APP_STORE_HOST + "api/app/myinfo"; // 我的页面数据获取
    public final static String ADDADVER= APP_STORE_HOST + "api/advertislog"; // 添加广告点击记录
    public final static String CHANGEPASD= APP_STORE_HOST + "api/user/newpass"; // 用户修改密码
    public final static String CDK= APP_STORE_HOST + "api/reward"; // 兑换激活码
    public final static String VIDEO= APP_STORE_HOST + "api/video/getInfo"; // 视频查询
    public final static String CATEGORY= APP_STORE_HOST + "api/category"; // 查询分类
    public final static String CHANNEL= APP_STORE_HOST + "api/app"; // 频道页
    public final static String HOTSUB= APP_STORE_HOST + "api/topic"; // 获取热门
    public final static String TOPICONFO= APP_STORE_HOST + "api/topic/topicinfo"; // 获取专题详情
    public final static String COMMENT= APP_STORE_HOST + "api/comment"; // 评论
    public final static String MESSAGE= APP_STORE_HOST + "api/message"; // 查询评论回复列表
    public final static String STARINFO= APP_STORE_HOST + "api/star/info"; // 明星详细信息查询
    public final static String CONFIGURE= APP_STORE_HOST + "api/configure"; // 查询当前配置
    public final static String INVITE= APP_STORE_HOST + "api/inviteshare"; // 推广记录查询
    public final static String SAVEQCODE= APP_STORE_HOST + "api/extend/saveqcode"; // 保存二维码
    public final static String VERSION= APP_STORE_HOST + "index/version"; // 版本控制
    public final static String ADVERTISE= APP_STORE_HOST + "api/advertise"; // 查询广告列表

}