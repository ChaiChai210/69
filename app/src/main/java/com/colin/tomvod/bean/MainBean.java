package com.colin.tomvod.bean;

import java.io.Serializable;
import java.util.List;


public class MainBean implements Serializable {
    private List<AdChartBean> ad_chart;
    private List<AdStripeBean> ad_stripe;
    private List<TypeVideoBean> type_video;
    private List<VideoBean> video;

    public List<AdChartBean> getAd_chart() {
        return ad_chart;
    }

    public void setAd_chart(List<AdChartBean> ad_chart) {
        this.ad_chart = ad_chart;
    }

    public List<AdStripeBean> getAd_stripe() {
        return ad_stripe;
    }

    public void setAd_stripe(List<AdStripeBean> ad_stripe) {
        this.ad_stripe = ad_stripe;
    }

    public List<TypeVideoBean> getType_video() {
        return type_video;
    }

    public void setType_video(List<TypeVideoBean> type_video) {
        this.type_video = type_video;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class AdChartBean {
        /**
         * id : 134
         * position_id : 3
         * name : 努力工作太辛苦轻松打牌成富翁
         * pic : http://c8kkdleodf.69sp2.world/uploads/advertise/83/8acd334c8482236746c6327430252e.jpg
         * url : https://www.676qipai.com/mk01/
         * exposure : 0
         * create_time : 2019-03-16 02:34:09
         */

        private int id;
        private int position_id;
        private String name;
        private String pic;
        private String url;
        private int exposure;
        private String create_time;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }

    public static class AdStripeBean {
        /**
         * id : 145
         * position_id : 2
         * name : 轻松心情将快乐留给自己
         * pic : http://c8kkdleodf.69sp2.world/uploads/advertise/07/007e7626f69d6e6c38842380c8882a.jpg
         * url : https://www.676qipai.com/mk01/
         * exposure : 0
         * create_time : 2019-03-16 02:42:57
         */

        private int id;
        private int position_id;
        private String name;
        private String pic;
        private String url;
        private int exposure;
        private String create_time;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }

    public static class TypeVideoBean {
        /**
         * id : 1
         * name : 超美少女
         * pic : http://c8kkdleodf.69sp2.world/uploads/classify/e8/c7f8262f1d1f834d00f222e7fb94d9.jpg
         * is_home : 1
         */

        private int id;
        private String name;
        private String pic;
        private int is_home;

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

        public int getIs_home() {
            return is_home;
        }

        public void setIs_home(int is_home) {
            this.is_home = is_home;
        }
    }

    public static class VideoBean {


        private String title;
        private AdverBean adver;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public AdverBean getAdver() {
            return adver;
        }

        public void setAdver(AdverBean adver) {
            this.adver = adver;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AdverBean {
            /**
             * id : 150
             * position_id : 2
             * name : 女神都明码标签行动让梦想成真
             * pic : http://c8kkdleodf.69sp2.world/uploads/advertise/10/79435a807a051e4f0a5a9e60e7f596.jpg
             * url : https://www.676qipai.com/mk01/
             * exposure : 0
             * create_time : 2019-03-16 02:33:10
             */

            private int id;
            private int position_id;
            private String name;
            private String pic;
            private String url;
            private int exposure;
            private String create_time;

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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }

        public static class ListBean {
            /**
             * id : 126
             * name : 盛り顔射祭 蒼井そら
             * video_url : /20190313/2U9WJf10/index.m3u8
             * cover : http://c8kkdleodf.69sp2.world/uploads/video/pic/3b/95e260f3fabe23dad7e2a8b1e17950.jpg
             * introduce : 盛り顔射祭 蒼井そら
             * run_date : 2019-03-14
             * classify_id : 1
             * score : 8.0
             * play_count : 563
             * status : 1
             * stor_id : 5c88c1e31424d40544f640c3
             * stor_size : 110351894
             * stor_orig_name : ()メガ盛り顔射祭 蒼井そら.mp4
             * create_at : 1552534309
             * create_time : 2019-03-20 11:12:37
             * collection : 1
             * org_path : /20190313/2U9WJf10
             * video_download_url : /20190313/2U9WJf10/mp4/2U9WJf10.mp4
             * classify_name : 超美少女
             */

            private int id;
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
            private int collection;
            private String org_info;
            private String org_path;
            private String video_download_url;
            private String classify_name;

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

            public String getClassify_id() {
                return classify_id;
            }

            public void setClassify_id(String classify_id) {
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

            public int getCollection() {
                return collection;
            }

            public void setCollection(int collection) {
                this.collection = collection;
            }

            public String getOrg_info() {
                return org_info;
            }

            public void setOrg_info(String org_info) {
                this.org_info = org_info;
            }

            public String getOrg_path() {
                return org_path;
            }

            public void setOrg_path(String org_path) {
                this.org_path = org_path;
            }

            public String getVideo_download_url() {
                return video_download_url;
            }

            public void setVideo_download_url(String video_download_url) {
                this.video_download_url = video_download_url;
            }

            public String getClassify_name() {
                return classify_name;
            }

            public void setClassify_name(String classify_name) {
                this.classify_name = classify_name;
            }
        }
    }
}
