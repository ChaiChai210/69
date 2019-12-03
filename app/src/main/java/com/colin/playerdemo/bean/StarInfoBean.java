package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class StarInfoBean implements Serializable {

    /**
     * id : 1
     * name : 沈腾
     * portrait : http://www.cqwjit.com:8082/uploads/star/59/3f7acbf46f88de0d81d1e6163e917b.jpg
     * nationality : 中国
     * height : 175
     * sex : 0
     * age : 38
     * cup : C
     * bwh_bust : 88
     * bwh_waist : 88
     * bwh_hips : 88
     * pic : http://www.cqwjit.com:8082/uploads/star/de/e2e1f04c2cf24257033fd6a4741383.jpg
     * introduce : 沈腾，中国喜剧演员
     * video_list : [{"cover":"http://www.cqwjit.com:8082/uploads/video/pic/32/c045bed8c891b9eeb393e6b8fb71ab.jpg","name":"新年一起过","introduce":"跨年大片,与你过年","play_count":5,"score_not":0,"score_yes":2},{"cover":"http://www.cqwjit.com:8082/uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg","name":"自己过，去吧","introduce":"跨年大片,与你过年","play_count":4,"score_not":2,"score_yes":1}]
     */

    private int id;
    private String name;
    private String portrait;
    private String nationality;
    private String height;
    private int sex;
    private int age;
    private String cup;
    private int bwh_bust;
    private int bwh_waist;
    private int bwh_hips;
    private int video_count;
    private String pic;
    private String introduce;
    private List<VideoListBean> video_list;

    public int getVideo_count() {
        return video_count;
    }

    public void setVideo_count(int video_count) {
        this.video_count = video_count;
    }

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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public int getBwh_bust() {
        return bwh_bust;
    }

    public void setBwh_bust(int bwh_bust) {
        this.bwh_bust = bwh_bust;
    }

    public int getBwh_waist() {
        return bwh_waist;
    }

    public void setBwh_waist(int bwh_waist) {
        this.bwh_waist = bwh_waist;
    }

    public int getBwh_hips() {
        return bwh_hips;
    }

    public void setBwh_hips(int bwh_hips) {
        this.bwh_hips = bwh_hips;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<VideoListBean> getVideo_list() {
        return video_list;
    }

    public void setVideo_list(List<VideoListBean> video_list) {
        this.video_list = video_list;
    }

    public static class VideoListBean {
        /**
         * cover : http://www.cqwjit.com:8082/uploads/video/pic/32/c045bed8c891b9eeb393e6b8fb71ab.jpg
         * name : 新年一起过
         * introduce : 跨年大片,与你过年
         * play_count : 5
         * score_not : 0
         * score_yes : 2
         */

        private String cover;
        private String name;
        private String introduce;
        private int play_count;
        private String score;
        private int score_not;
        private int score_yes;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public int getScore_not() {
            return score_not;
        }

        public void setScore_not(int score_not) {
            this.score_not = score_not;
        }

        public int getScore_yes() {
            return score_yes;
        }

        public void setScore_yes(int score_yes) {
            this.score_yes = score_yes;
        }
    }
}
