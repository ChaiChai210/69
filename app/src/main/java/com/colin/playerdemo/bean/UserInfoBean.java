package com.colin.playerdemo.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {

    /**
     * uid : 175
     * nickname : 69video15488991
     * password : dc483e80a7a0bd9ef71d8cf973673924
     * create_date : 2019-01-31 09:44:19
     * portrait : http://www.cqwjit.com:8082/static/defaultPortrait.jpg
     * status : 正式用户
     * login_ip : null
     * login_time : null
     * phone : 15882187469
     * invite_num : 0
     * invite_code : 448F9A
     * invite_bind_uid : null
     * level_id : null
     * lvshare_num : 1
     * video_avail : 5
     * video_avail_day : 3
     * video_avail_use : 0
     * video_cache : 0
     * video_cache_day : 0
     * video_cache_use : 0
     * sex : 未设定
     * next_level : {"id":4,"share_num":3,"level_name":"进阶徽章","illustrate":"推广3人-每日观影次数+5，缓存次数+3","pic":"http://www.cqwjit.com:8082/uploads/level/d7/139a2bbac7c851c325c522c07d22ea.png","video_cache_add":3,"video_avail_add":5}
     * next_level_lack : 2
     * this_level : {"id":3,"share_num":1,"level_name":"入门徽章","illustrate":"推广1人-每次观影次数+2，缓存次数+1","pic":"http://www.cqwjit.com:8082/uploads/level/8d/881b4c6ca1cbd0be455f022ef5e9c4.png","video_cache_add":1,"video_avail_add":2}
     */

    private long uid;
    private String nickname;
    private String password;
    private String create_date;
    private String portrait;
    private String status;
    private Object login_ip;
    private Object login_time;
    private String phone;
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
    private int is_change_sex;
    private int is_change_name;
    private int is_change_portrait;
    private String sex;
    private NextLevelBean next_level;
    private int next_level_lack;
    private ThisLevelBean this_level;

    public int getIs_change_sex() {
        return is_change_sex;
    }

    public void setIs_change_sex(int is_change_sex) {
        this.is_change_sex = is_change_sex;
    }

    public int getIs_change_name() {
        return is_change_name;
    }

    public void setIs_change_name(int is_change_name) {
        this.is_change_name = is_change_name;
    }

    public int getIs_change_portrait() {
        return is_change_portrait;
    }

    public void setIs_change_portrait(int is_change_portrait) {
        this.is_change_portrait = is_change_portrait;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public NextLevelBean getNext_level() {
        return next_level;
    }

    public void setNext_level(NextLevelBean next_level) {
        this.next_level = next_level;
    }

    public int getNext_level_lack() {
        return next_level_lack;
    }

    public void setNext_level_lack(int next_level_lack) {
        this.next_level_lack = next_level_lack;
    }

    public ThisLevelBean getThis_level() {
        return this_level;
    }

    public void setThis_level(ThisLevelBean this_level) {
        this.this_level = this_level;
    }

    public static class NextLevelBean {
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

    public static class ThisLevelBean {
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
}
