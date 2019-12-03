package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class AloneBean implements Serializable {

    /**
     * id : 1
     * name : 圣诞节
     * pic : http://www.cqwjit.com:8082/uploads/topic/6a/335524248af652c6f2e1cb364d404c.jpg
     * list : [{"id":4,"cover":"/uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg","name":"自己过，去吧","score_not":2,"score_yes":1,"play_count":4},{"id":1,"cover":"/uploads/video/pic/e8/c7f8262f1d1f834d00f222e7fb94d9.jpg","name":"asdasdasd","score_not":2,"score_yes":4,"play_count":1},{"id":2,"cover":"/uploads/video/pic/32/c045bed8c891b9eeb393e6b8fb71ab.jpg","name":"新年一起过","score_not":0,"score_yes":2,"play_count":5}]
     */

    private int id;
    private String name;
    private String pic;
    private String introduce;
    private List<ListBean> list;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 4
         * cover : /uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg
         * name : 自己过，去吧
         * score_not : 2
         * score_yes : 1
         * play_count : 4
         */

        private int id;
        private String cover;
        private String name;
        private String score;
        private int score_not;
        private int score_yes;
        private int play_count;
        private int collection;
        private String video_url;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }
    }
}
