package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class MineUserInfoBean implements Serializable {


    private UserinfoBean userinfo;
    private PresentlevelBean presentlevel;
    private NextlevelBean nextlevel;
    private int collectlist_count;
    private int recordstlist_count;
    private String extendcode;
    private List<AdnameBean> adname;
    private List<CollectlistBean> collectlist;
    private List<RecordstlistBean> recordstlist;

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public PresentlevelBean getPresentlevel() {
        return presentlevel;
    }

    public void setPresentlevel(PresentlevelBean presentlevel) {
        this.presentlevel = presentlevel;
    }

    public NextlevelBean getNextlevel() {
        return nextlevel;
    }

    public void setNextlevel(NextlevelBean nextlevel) {
        this.nextlevel = nextlevel;
    }

    public int getCollectlist_count() {
        return collectlist_count;
    }

    public void setCollectlist_count(int collectlist_count) {
        this.collectlist_count = collectlist_count;
    }

    public int getRecordstlist_count() {
        return recordstlist_count;
    }

    public void setRecordstlist_count(int recordstlist_count) {
        this.recordstlist_count = recordstlist_count;
    }

    public String getExtendcode() {
        return extendcode;
    }

    public void setExtendcode(String extendcode) {
        this.extendcode = extendcode;
    }

    public List<AdnameBean> getAdname() {
        return adname;
    }

    public void setAdname(List<AdnameBean> adname) {
        this.adname = adname;
    }

    public List<CollectlistBean> getCollectlist() {
        return collectlist;
    }

    public void setCollectlist(List<CollectlistBean> collectlist) {
        this.collectlist = collectlist;
    }

    public List<RecordstlistBean> getRecordstlist() {
        return recordstlist;
    }

    public void setRecordstlist(List<RecordstlistBean> recordstlist) {
        this.recordstlist = recordstlist;
    }

    public static class UserinfoBean {
        /**
         * uid : 191
         * nickname : 69video15489146
         * create_date : 2019-01-31 14:04:36
         * portrait : http://www.cqwjit.com:8082/uploads/user/40/ca92f87b4da8ab423db958fa9b04e3.jpg
         * phone : 15882187469
         * status : 1
         * login_ip : null
         * login_time : null
         * invite_num : 0
         * invite_code : A6C022
         * invite_bind_uid : null
         * level_id : null
         * lvshare_num : 1
         * video_avail : 5
         * video_avail_day : 3
         * video_avail_use : 0
         * video_cache : 0
         * video_cache_day : 0
         * video_cache_use : 0
         * sex : 2
         */

        private long uid;
        private String nickname;
        private String create_date;
        private String portrait;
        private String phone;
        private int status;
        private Object login_ip;
        private Object login_time;
        private int invite_num;
        private String invite_code;
        private Object invite_bind_uid;
        private Object level_id;
        private int lvshare_num;
        private int video_avail;
        private int video_avail_day;
        private int video_avail_use;
        private int video_cache;
        private int video_cache_day;
        private int video_cache_use;
        private String sex;

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getLogin_ip() {
            return login_ip;
        }

        public void setLogin_ip(Object login_ip) {
            this.login_ip = login_ip;
        }

        public Object getLogin_time() {
            return login_time;
        }

        public void setLogin_time(Object login_time) {
            this.login_time = login_time;
        }

        public int getInvite_num() {
            return invite_num;
        }

        public void setInvite_num(int invite_num) {
            this.invite_num = invite_num;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public Object getInvite_bind_uid() {
            return invite_bind_uid;
        }

        public void setInvite_bind_uid(Object invite_bind_uid) {
            this.invite_bind_uid = invite_bind_uid;
        }

        public Object getLevel_id() {
            return level_id;
        }

        public void setLevel_id(Object level_id) {
            this.level_id = level_id;
        }

        public int getLvshare_num() {
            return lvshare_num;
        }

        public void setLvshare_num(int lvshare_num) {
            this.lvshare_num = lvshare_num;
        }

        public int getVideo_avail() {
            return video_avail;
        }

        public void setVideo_avail(int video_avail) {
            this.video_avail = video_avail;
        }

        public int getVideo_avail_day() {
            return video_avail_day;
        }

        public void setVideo_avail_day(int video_avail_day) {
            this.video_avail_day = video_avail_day;
        }

        public int getVideo_avail_use() {
            return video_avail_use;
        }

        public void setVideo_avail_use(int video_avail_use) {
            this.video_avail_use = video_avail_use;
        }

        public int getVideo_cache() {
            return video_cache;
        }

        public void setVideo_cache(int video_cache) {
            this.video_cache = video_cache;
        }

        public int getVideo_cache_day() {
            return video_cache_day;
        }

        public void setVideo_cache_day(int video_cache_day) {
            this.video_cache_day = video_cache_day;
        }

        public int getVideo_cache_use() {
            return video_cache_use;
        }

        public void setVideo_cache_use(int video_cache_use) {
            this.video_cache_use = video_cache_use;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public static class PresentlevelBean {
        /**
         * id : 3
         * share_num : 1
         * level_name : 入门徽章
         * illustrate : 推广1人-每次观影次数+2，缓存次数+1
         * pic : http://www.cqwjit.com:8082/uploads/level/8d/881b4c6ca1cbd0be455f022ef5e9c4.png
         * video_cache_add : 1
         * video_avail_add : 2
         */

        private int id;
        private int share_num;
        private String level_name;
        private String illustrate;
        private String pic;
        private int video_cache_add;
        private int video_avail_add;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShare_num() {
            return share_num;
        }

        public void setShare_num(int share_num) {
            this.share_num = share_num;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getIllustrate() {
            return illustrate;
        }

        public void setIllustrate(String illustrate) {
            this.illustrate = illustrate;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getVideo_cache_add() {
            return video_cache_add;
        }

        public void setVideo_cache_add(int video_cache_add) {
            this.video_cache_add = video_cache_add;
        }

        public int getVideo_avail_add() {
            return video_avail_add;
        }

        public void setVideo_avail_add(int video_avail_add) {
            this.video_avail_add = video_avail_add;
        }
    }

    public static class NextlevelBean {
        /**
         * id : 4
         * share_num : 3
         * level_name : 进阶徽章
         * illustrate : 推广3人-每日观影次数+5，缓存次数+3
         * pic : http://www.cqwjit.com:8082/uploads/level/d7/139a2bbac7c851c325c522c07d22ea.png
         * video_cache_add : 3
         * video_avail_add : 5
         */

        private int id;
        private int share_num;
        private String level_name;
        private String illustrate;
        private String pic;
        private int video_cache_add;
        private int video_avail_add;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShare_num() {
            return share_num;
        }

        public void setShare_num(int share_num) {
            this.share_num = share_num;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getIllustrate() {
            return illustrate;
        }

        public void setIllustrate(String illustrate) {
            this.illustrate = illustrate;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getVideo_cache_add() {
            return video_cache_add;
        }

        public void setVideo_cache_add(int video_cache_add) {
            this.video_cache_add = video_cache_add;
        }

        public int getVideo_avail_add() {
            return video_avail_add;
        }

        public void setVideo_avail_add(int video_avail_add) {
            this.video_avail_add = video_avail_add;
        }
    }

    public static class AdnameBean {
        /**
         * id : 106
         * name : 世界上最小的猴子是什么猴，体型10厘米/体重80克/和拇指大小相似
         * position_id : 3
         * pic : uploads/advertise/ea/bed104f3c311d6cd168d4afc81654c.png
         * url : https://sh.qihoo.com/9128ab3847b0dab15?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5
         * exposure : 0
         */

        private int id;
        private String name;
        private int position_id;
        private String pic;
        private String url;
        private int exposure;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPosition_id() {
            return position_id;
        }

        public void setPosition_id(int position_id) {
            this.position_id = position_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getExposure() {
            return exposure;
        }

        public void setExposure(int exposure) {
            this.exposure = exposure;
        }
    }

    public static class CollectlistBean {
        /**
         * id : 81
         * vid : 81
         * uid : 191
         * create_date : 2019-02-01 11:53:42
         * name : null
         * video_url : /videos/5c529b5e28cfbd696339d332/index.m3u8
         * cover : null
         * introduce : null
         * run_date : null
         * classify_id : null
         * score : 8.0
         * play_count : 0
         * status : 0
         * stor_id : 5c529b5e28cfbd696339d332
         * stor_size : 16235222
         * stor_orig_name : test7.mp4
         * create_at : 2019-01-31 06:53:18
         * create_time : 2019-02-01 10:05:34
         */

        private int id;
        private int vid;
        private long uid;
        private String create_date;
        private String name;
        private String video_url;
        private String cover;
        private String introduce;
        private String run_date;
        private String classify_id;
        private String score;
        private int play_count;
        private int status;
        private String stor_id;
        private String stor_size;
        private String stor_orig_name;
        private String create_at;
        private String create_time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getRun_date() {
            return run_date;
        }

        public void setRun_date(String run_date) {
            this.run_date = run_date;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }


        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }


        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStor_id() {
            return stor_id;
        }

        public void setStor_id(String stor_id) {
            this.stor_id = stor_id;
        }

        public String getStor_size() {
            return stor_size;
        }

        public void setStor_size(String stor_size) {
            this.stor_size = stor_size;
        }

        public String getStor_orig_name() {
            return stor_orig_name;
        }

        public void setStor_orig_name(String stor_orig_name) {
            this.stor_orig_name = stor_orig_name;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }

    public static class RecordstlistBean {
        /**
         * id : 2
         * vid : 2
         * cdate : 2019-02-01 11:13:19
         * uid : 191
         * go_time : 2019-02-01 11:13:19
         * play_count : 4
         * go_progress : 0
         * name : 自己过，去吧
         * video_url : /videos/5c4f32232914735a09f21741/index.m3u8
         * cover : /uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg
         * introduce : 跨年大片,与你过年
         * run_date : 2019-01-01
         * classify_id : 2
         * score : 4.3
         * status : 1
         * stor_id : 5c4032232914735a09f21745
         * stor_size : 12345142
         * stor_orig_name : Ox_B1231241235
         * create_at :
         * create_time : 2019-01-31 09:25:18
         */

        private String id;
        private int vid;
        private String cdate;
        private long uid;
        private String go_time;
        private int play_count;
        private int go_progress;
        private String name;
        private String video_url;
        private String cover;
        private String introduce;
        private String run_date;
        private int classify_id;
        private String score;
        private int status;
        private String stor_id;
        private String stor_size;
        private String stor_orig_name;
        private String create_at;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public String getCdate() {
            return cdate;
        }

        public void setCdate(String cdate) {
            this.cdate = cdate;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getGo_time() {
            return go_time;
        }

        public void setGo_time(String go_time) {
            this.go_time = go_time;
        }

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public int getGo_progress() {
            return go_progress;
        }

        public void setGo_progress(int go_progress) {
            this.go_progress = go_progress;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getRun_date() {
            return run_date;
        }

        public void setRun_date(String run_date) {
            this.run_date = run_date;
        }

        public int getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(int classify_id) {
            this.classify_id = classify_id;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStor_id() {
            return stor_id;
        }

        public void setStor_id(String stor_id) {
            this.stor_id = stor_id;
        }

        public String getStor_size() {
            return stor_size;
        }

        public void setStor_size(String stor_size) {
            this.stor_size = stor_size;
        }

        public String getStor_orig_name() {
            return stor_orig_name;
        }

        public void setStor_orig_name(String stor_orig_name) {
            this.stor_orig_name = stor_orig_name;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
