package com.colin.tomvod.net.rxjava;

public interface Api {
    String ads = "api/advertise";
    String token = "api/user/init";
    String home_data = "api/app/home";
    String channel = "api/app";  // 频道页
    String label_title = "api/tagtype";  // 频道页
    String tagDetail =  "api/tag"; // 获取标签列表
    String special_topic = "api/topic/topicinfo"; // 获取专题详情
    String myinfo = "api/app/myinfo"; // 我的页面数据获取
    String video ="api/video/getInfo"; // 视频查询
    String video_watch_record = "api/records"; // 查询观看记录-APP-Web
    String discover_collect = "api/collect"; // 查询收藏视频
    String frag_discover_data ="api/recomvideo"; // 获取推荐列表
    String config = "api/configure"; // 查询当前配置
    String adClickRecord = "api/advertislog"; // 添加广告点击记录
    String hotSearch =  "api/search/hot"; // 热门搜索内容
    String searchResult = "api/search"; // 全局搜索
    String comment = "api/comment"; // 评论
    String comment_reply = "api/message"; // 查询评论回复列表
    String user_verification_all = "home/user/user_verification_all";

    String company_com_sea_index = "home/task/company_com_sea_index";
    String receive_sea_number = "home/task/receive_sea_number";
    String com_sea_number_index = "home/task/com_sea_number_index";
    String task_index = "home/task/task_index";
    String task_number_index = "home/task/task_number_index";
    String unconnected_type_list = "home/task/unconnected_type_list";
    String unconnected_list = "home/task/unconnected_list";
    String sea_unconnected_type_list = "home/task/sea_unconnected_type_list";
    String sea_unconnected_list = "home/task/sea_unconnected_list";

    String direct_dial = "home/task/direct_dial";
    String request_call = "home/task/request_call";
    String get_call_log_index = "home/task/get_call_log_index";
    String get_custom_list = "home/task/get_custom_list";
    String get_company_tag = "home/task/get_company_tag";
    String get_industry = "home/task/get_industry";
    String get_remind_index = "home/task/get_remind_index";
    String add_remind = "home/task/add_remind";
    String del_remind = "home/task/del_remind";
    String get_connect_log = "home/task/get_connect_log";
    String get_task_info = "home/task/get_task_info";
    String update_personnel_call_log = "home/task/update_personnel_call_log";
    String add_sea_number = "home/task/add_sea_number";
    String get_call_remind = "home/task/get_call_remind";
    String get_call_info = "home/task/get_call_info";
    String get_remind = "home/task/get_remind";
    String edit_remind = "home/task/edit_remind";
    String set_user_info = "home/user/set_user_info";
    String personnel_call_log = "home/task/personnel_call_log";
    String update_encryption_call_log = "home/task/update_encryption_call_log";
    String card_call = "home/task/card_call";
    String update_number_status = "home/task/update_number_status";
    String query_mobile_task_status = "home/task/query_mobile_task_status";
    String get_login_status = "user/public/get_login_status";
    String app_error_log = "home/public/app_error_log";
    String finance_index = "home/Finance/finance_index";
    String recharge_log = "home/Finance/recharge_log";
    String recharge = "home/Finance/recharge";
    String get_sy_package_list = "home/Finance/get_sy_package_list";
    String package_list = "home/Finance/package_list";
    String get_receivables = "home/Finance/get_receivables";
    String get_package_list_info = "home/Finance/get_package_list_info";
    String get_role_user = "home/Finance/get_role_user";
    String get_role_list = "home/Finance/get_role_list";
    String renew_package = "home/Finance/renew_package";
    String imgUrl = "http://zkh.changliaoyun.com/upload/app/image/app_ico1.png";
    String company_dir = "home/user/compandyDirectory";

}
