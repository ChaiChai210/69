package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;


public class PlayDetailBean implements Serializable {

    /**
     * id : 1
     * name : 呵呵呵
     * video_url : http://video.imgapi.net:3389/videos/5c4032232914735a09f21741/index.m3u8
     * cover : http://www.cqwjit.com:8082/uploads/video/pic/cf/693408dfb66fc1175d488f2542469f.jpg
     * introduce : 123
     * run_date : 1993-06-22
     * classify_id : 5
     * score : 5.6
     * play_count : 5
     * status : 已发布
     * stor_id : 5c4032232914735a09f21741
     * stor_size : 2705641
     * stor_orig_name : QQ视频20190109200358.mp4
     * create_at : 1547711011744
     * create_time : 2019-01-31 09:25:15
     * collect_count : 1
     * zan : {"yes":3,"no":3}
     * classify : {"id":1,"name":"无码","pic":"http://www.cqwjit.com:8082/uploads/classify/e8/c7f8262f1d1f834d00f222e7fb94d9.jpg"}
     * star_list : [{"id":1,"name":"沈腾","portrait":"http://www.cqwjit.com:8082/uploads/star/6a/335524248af652c6f2e1cb364d404c.jpg","nationality":"中国","height":"175cm","sex":"男","age":38,"cup":"C","bwh_bust":88,"bwh_waist":88,"bwh_hips":88,"pic":"http://www.cqwjit.com:8082/uploads/star/d4/fc42b90b89c0e9c5bc2cb59419a372.jpg","introduce":"沈腾，中国喜剧演员"},{"id":3,"name":"亚瑟王","portrait":"http://www.cqwjit.com:8082/uploads/star/2f/5fa54a28d86730ccf80997c108733f.jpg","nationality":"古不列颠王国 ","height":"42cm","sex":"女","age":24,"cup":"c","bwh_bust":73,"bwh_waist":54,"bwh_hips":76,"pic":"http://www.cqwjit.com:8082/uploads/star/1b/1557f95e03df333b84375da3d5620a.jpg","introduce":"Fate线的女主角，身份为古不列颠传说中的亚瑟王。性格忠诚正直，谦逊有礼，个性认真"},{"id":4,"name":"黄渤","portrait":"http://www.cqwjit.com:8082/uploads/star/a0/81585c430c7472a63d32c2158c4f77.jpg","nationality":"美国","height":"192.1cm","sex":"女","age":18,"cup":"MAX","bwh_bust":30,"bwh_waist":40,"bwh_hips":0,"pic":"http://www.cqwjit.com:8082/uploads/star/3c/6fde229fedcbb03a391fbec0263421.jpg","introduce":"好样的！！！"}]
     * tag_list : [{"vid":1,"name":"剧情","id":2},{"vid":1,"name":"进阶","id":1}]
     * topic_list : [{"id":5,"photo":"http://www.cqwjit.com:8082/uploads/topic/cc/a5663d1ea86468265a0d8fa76d3a52.jpg","pic":"http://www.cqwjit.com:8082/uploads/topic/cc/a5663d1ea86468265a0d8fa76d3a52.jpg","name":"小鸟酱","vid":1,"topic_id":5}]
     * advertisement : {"id":121,"position_id":6,"name":"金箍棒为何只认悟空一人？答案就藏在棒身上，你看看上面刻了啥？","pic":"http://www.cqwjit.com:8082uploads/advertise/a0/169ac46f4688e5413fedbc50a4389b.png","url":"https://sh.qihoo.com/9f322a8fb24db4e38?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5","exposure":0}
     * comment_count : 16
     * is_collect : false
     * you_is_love : [{"vid":1,"cover":"http://www.cqwjit.com:8082/uploads/video/pic/cf/693408dfb66fc1175d488f2542469f.jpg","name":"呵呵呵","play_count":3,"tags":[{"id":2,"name":"69出品","tagid":2},{"id":1,"name":"国产","tagid":1}]},{"vid":3,"cover":"http://www.cqwjit.com:8082/uploads/video/pic/c8/421b2e4d8ee27d9ba6a78f483a2e9a.png","name":"HHH","play_count":5,"tags":[{"id":4,"name":"中文字幕","tagid":4}]},{"vid":7,"cover":"http://www.cqwjit.com:8082","name":null,"play_count":0,"tags":[]}]
     */

    private int id;
    private String name;
    private String video_download_url;
    private String video_url;
    private String cover;
    private String introduce;
    private String run_date;
    private int classify_id;
    private String score;
    private int play_count;
    private String status;
    private String stor_id;
    private String stor_size;
    private String stor_orig_name;
    private String create_at;
    private String create_time;
    private int collect_count;
    private ZanBean zan;
    private ClassifyBean classify;
    private AdvertisementBean advertisement;
    private Play_advertisement play_advertisement;
    private int comment_count;
    private boolean is_collect;
    private List<StarListBean> star_list;
    private List<TagListBean> tag_list;
    private List<TopicListBean> topic_list;
    private List<YouIsLoveBean> you_is_love;

    public Play_advertisement getPlay_advertisement() {
        return play_advertisement;
    }

    public void setPlay_advertisement(Play_advertisement play_advertisement) {
        this.play_advertisement = play_advertisement;
    }

    public static class Play_advertisement implements Serializable {
        private int id;
        private int position_id;
        private String name;
        private String pic;
        private String url;
        private int exposure;
        private boolean play_hide;
        private int play_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPosition_id() {
            return position_id;
        }

        public void setPosition_id(int position_id) {
            this.position_id = position_id;
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

        public boolean isPlay_hide() {
            return play_hide;
        }

        public void setPlay_hide(boolean play_hide) {
            this.play_hide = play_hide;
        }

        public int getPlay_time() {
            return play_time;
        }

        public void setPlay_time(int play_time) {
            this.play_time = play_time;
        }
    }
    public String getVideo_download_url() {
        return video_download_url;
    }

    public void setVideo_download_url(String video_download_url) {
        this.video_download_url = video_download_url;
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

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public ZanBean getZan() {
        return zan;
    }

    public void setZan(ZanBean zan) {
        this.zan = zan;
    }

    public ClassifyBean getClassify() {
        return classify;
    }

    public void setClassify(ClassifyBean classify) {
        this.classify = classify;
    }

    public AdvertisementBean getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(AdvertisementBean advertisement) {
        this.advertisement = advertisement;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public boolean isIs_collect() {
        return is_collect;
    }

    public void setIs_collect(boolean is_collect) {
        this.is_collect = is_collect;
    }

    public List<StarListBean> getStar_list() {
        return star_list;
    }

    public void setStar_list(List<StarListBean> star_list) {
        this.star_list = star_list;
    }

    public List<TagListBean> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<TagListBean> tag_list) {
        this.tag_list = tag_list;
    }

    public List<TopicListBean> getTopic_list() {
        return topic_list;
    }

    public void setTopic_list(List<TopicListBean> topic_list) {
        this.topic_list = topic_list;
    }

    public List<YouIsLoveBean> getYou_is_love() {
        return you_is_love;
    }

    public void setYou_is_love(List<YouIsLoveBean> you_is_love) {
        this.you_is_love = you_is_love;
    }

    public static class ZanBean {
        /**
         * yes : 3
         * no : 3
         */

        private int yes;
        private int no;

        public int getYes() {
            return yes;
        }

        public void setYes(int yes) {
            this.yes = yes;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }

    public static class ClassifyBean {
        /**
         * id : 1
         * name : 无码
         * pic : http://www.cqwjit.com:8082/uploads/classify/e8/c7f8262f1d1f834d00f222e7fb94d9.jpg
         */

        private int id;
        private String name;
        private String pic;

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
    }

    public static class AdvertisementBean {
        /**
         * id : 121
         * position_id : 6
         * name : 金箍棒为何只认悟空一人？答案就藏在棒身上，你看看上面刻了啥？
         * pic : http://www.cqwjit.com:8082uploads/advertise/a0/169ac46f4688e5413fedbc50a4389b.png
         * url : https://sh.qihoo.com/9f322a8fb24db4e38?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5
         * exposure : 0
         */

        private int id;
        private int position_id;
        private String name;
        private String pic;
        private String url;
        private int exposure;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPosition_id() {
            return position_id;
        }

        public void setPosition_id(int position_id) {
            this.position_id = position_id;
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

    public static class StarListBean {
        /**
         * id : 1
         * name : 沈腾
         * portrait : http://www.cqwjit.com:8082/uploads/star/6a/335524248af652c6f2e1cb364d404c.jpg
         * nationality : 中国
         * height : 175cm
         * sex : 男
         * age : 38
         * cup : C
         * bwh_bust : 88
         * bwh_waist : 88
         * bwh_hips : 88
         * pic : http://www.cqwjit.com:8082/uploads/star/d4/fc42b90b89c0e9c5bc2cb59419a372.jpg
         * introduce : 沈腾，中国喜剧演员
         */

        private int id;
        private String name;
        private String portrait;
        private String nationality;
        private String height;
        private String sex;
        private int age;
        private String cup;
        private int bwh_bust;
        private int bwh_waist;
        private int bwh_hips;
        private String pic;
        private String introduce;

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
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
    }

    public static class TagListBean {
        /**
         * vid : 1
         * name : 剧情
         * id : 2
         */

        private int vid;
        private String name;
        private int id;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class TopicListBean {
        /**
         * id : 5
         * photo : http://www.cqwjit.com:8082/uploads/topic/cc/a5663d1ea86468265a0d8fa76d3a52.jpg
         * pic : http://www.cqwjit.com:8082/uploads/topic/cc/a5663d1ea86468265a0d8fa76d3a52.jpg
         * name : 小鸟酱
         * vid : 1
         * topic_id : 5
         */

        private int id;
        private String photo;
        private String pic;
        private String name;
        private int vid;
        private int topic_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }
    }

    public static class YouIsLoveBean {
        /**
         * vid : 1
         * cover : http://www.cqwjit.com:8082/uploads/video/pic/cf/693408dfb66fc1175d488f2542469f.jpg
         * name : 呵呵呵
         * play_count : 3
         * tags : [{"id":2,"name":"69出品","tagid":2},{"id":1,"name":"国产","tagid":1}]
         */

        private int vid;
        private String cover;
        private String name;
        private int play_count;
        private List<TagsBean> tags;

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
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

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            /**
             * id : 2
             * name : 69出品
             * tagid : 2
             */

            private int id;
            private String name;
            private int tagid;

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

            public int getTagid() {
                return tagid;
            }

            public void setTagid(int tagid) {
                this.tagid = tagid;
            }
        }
    }
}
