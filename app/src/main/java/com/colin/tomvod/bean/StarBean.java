package com.colin.tomvod.bean;

import java.io.Serializable;
import java.util.List;


public class StarBean implements Serializable {

    /**
     * id : 3
     * name : 亚瑟王
     * introduce : Fate线的女主角，身份为古不列颠传说中的亚瑟王。性格忠诚正直，谦逊有礼，个性认真
     * portrait : http://www.cqwjit.com:8082//uploads/star/2f/5fa54a28d86730ccf80997c108733f.jpg
     * video_count : 1
     * video_list : [{"id":4,"star_id":3,"vid":4,"name":"自己过，去吧","video_url":"http://www.cqwjit.com:8082//videofile/123d5asd4qw5ras56dsad/","cover":"http://www.cqwjit.com:8082//uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg"}]
     */

    private int id;
    private String name;
    private String introduce;
    private String portrait;
    private int video_count;
    private List<VideoListBean> video_list;

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getVideo_count() {
        return video_count;
    }

    public void setVideo_count(int video_count) {
        this.video_count = video_count;
    }

    public List<VideoListBean> getVideo_list() {
        return video_list;
    }

    public void setVideo_list(List<VideoListBean> video_list) {
        this.video_list = video_list;
    }

    public static class VideoListBean {
        /**
         * id : 4
         * star_id : 3
         * vid : 4
         * name : 自己过，去吧
         * video_url : http://www.cqwjit.com:8082//videofile/123d5asd4qw5ras56dsad/
         * cover : http://www.cqwjit.com:8082//uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg
         */

        private int id;
        private int star_id;
        private int vid;
        private String name;
        private String video_url;
        private String cover;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStar_id() {
            return star_id;
        }

        public void setStar_id(int star_id) {
            this.star_id = star_id;
        }

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
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
    }
}
