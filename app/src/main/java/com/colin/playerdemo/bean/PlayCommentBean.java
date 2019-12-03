package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class PlayCommentBean implements Serializable {

    /**
     * id : 23
     * content : 高数课上，两个男生想逃课，趁着教授在黑板上板书，顺着窗外的排水管打算从二楼爬下去，爬了一半，有人恶作剧，对他们说:“快上来，教授点名了！” 二人无奈，只好再往上爬，结果排水管支撑不住断了，两个倒霉蛋一下子摔倒地上，教授听到声音，往窗外看了一眼，嘴角抽搐了一下，没说什么，继续上课。 所有人都以为这两个男生死定了，结果一直到期末，两个人顺利通过考试，什么都没发生。 期末的教师聚会上，喝多了的教授哭着道出了真相: “你们他妈谁当老师有我失败？我的学生就因为嫌我的课难听居然跑去跳楼！”
     * create_time : 1小时前
     * uid : 162
     * nickname : 风萧萧兮易水寒
     * portrait : http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png
     * sex : 女
     * comment_count : 5
     * zan_count : 1
     * cover : http://www.cqwjit.com:8082
     * is_like : false
     * replys : [{"id":25,"comment_id":23,"uid":162,"nickname":"风萧萧兮易水寒","portrait":"http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png","sex":"女","level_id":null,"content":"一男的去相亲，女：\"你有房吗？\" \n男的拿出了一份房产证。女：\"你有车吗？\" \n男的拿出了驾驶证。\n女：\"你要是有飞机的话就更好了。\" \n男的拿出了一张飞机驾驶证。\n女：\"哇！好厉害，那你会开火车吗？\" \n男的拿出了一张火车驾驶证。\n女：\"我太爱你了，你是干什么工作的啊？\"\n男：\"我是专业办假证的\"","create_time":"1小时前","reply_id":0,"to_uid":162,"to_nickname":"风萧萧兮易水寒","to_portrait":"http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png","to_sex":"女","to_level_id":null,"zan_count":1},{"id":26,"comment_id":23,"uid":162,"nickname":"风萧萧兮易水寒","portrait":"http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png","sex":"女","level_id":null,"content":"以前初中同桌是女生，有次下课没事干伸个腰，不小心凑到了她胸口","create_time":"1小时前","reply_id":0,"to_uid":162,"to_nickname":"风萧萧兮易水寒","to_portrait":"http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png","to_sex":"女","to_level_id":null,"zan_count":0},{"id":27,"comment_id":23,"uid":162,"nickname":"风萧萧兮易水寒","portrait":"http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png","sex":"女","level_id":null,"content":"以前初中同桌是女生，有次下课没事干伸个腰，不小心凑到了她胸口","create_time":"1小时前","reply_id":0,"to_uid":162,"to_nickname":"风萧萧兮易水寒","to_portrait":"http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png","to_sex":"女","to_level_id":null,"zan_count":1}]
     */

    private int id;
    private String content;
    private String create_time;
    private long uid;
    private String nickname;
    private String portrait;
    private String sex;
    private int comment_count;
    private int zan_count;
    private String cover;
    private boolean is_like;
    private List<ReplysBean> replys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getZan_count() {
        return zan_count;
    }

    public void setZan_count(int zan_count) {
        this.zan_count = zan_count;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isIs_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }

    public List<ReplysBean> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplysBean> replys) {
        this.replys = replys;
    }

    public static class ReplysBean {
        /**
         * id : 25
         * comment_id : 23
         * uid : 162
         * nickname : 风萧萧兮易水寒
         * portrait : http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png
         * sex : 女
         * level_id : null
         * content : 一男的去相亲，女："你有房吗？"
         男的拿出了一份房产证。女："你有车吗？"
         男的拿出了驾驶证。
         女："你要是有飞机的话就更好了。"
         男的拿出了一张飞机驾驶证。
         女："哇！好厉害，那你会开火车吗？"
         男的拿出了一张火车驾驶证。
         女："我太爱你了，你是干什么工作的啊？"
         男："我是专业办假证的"
         * create_time : 1小时前
         * reply_id : 0
         * to_uid : 162
         * to_nickname : 风萧萧兮易水寒
         * to_portrait : http://www.cqwjit.com:8082/uploads/user/c9/b8039aeeb6e1a827b17c1c20ce773f.png
         * to_sex : 女
         * to_level_id : null
         * zan_count : 1
         */

        private int id;
        private int comment_id;
        private long uid;
        private String nickname;
        private String portrait;
        private String sex;
        private Object level_id;
        private String content;
        private String create_time;
        private int reply_id;
        private int to_uid;
        private String to_nickname;
        private String to_portrait;
        private String to_sex;
        private Object to_level_id;
        private int zan_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
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

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getLevel_id() {
            return level_id;
        }

        public void setLevel_id(Object level_id) {
            this.level_id = level_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getReply_id() {
            return reply_id;
        }

        public void setReply_id(int reply_id) {
            this.reply_id = reply_id;
        }

        public int getTo_uid() {
            return to_uid;
        }

        public void setTo_uid(int to_uid) {
            this.to_uid = to_uid;
        }

        public String getTo_nickname() {
            return to_nickname;
        }

        public void setTo_nickname(String to_nickname) {
            this.to_nickname = to_nickname;
        }

        public String getTo_portrait() {
            return to_portrait;
        }

        public void setTo_portrait(String to_portrait) {
            this.to_portrait = to_portrait;
        }

        public String getTo_sex() {
            return to_sex;
        }

        public void setTo_sex(String to_sex) {
            this.to_sex = to_sex;
        }

        public Object getTo_level_id() {
            return to_level_id;
        }

        public void setTo_level_id(Object to_level_id) {
            this.to_level_id = to_level_id;
        }

        public int getZan_count() {
            return zan_count;
        }

        public void setZan_count(int zan_count) {
            this.zan_count = zan_count;
        }
    }
}
