package com.colin.playerdemo.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class Column_Bean implements Serializable {

    /**
     * tjtopic : [{"name":"69出品","introduce":"69出品","create_date":"2019-01-29 17:39:46"},{"name":"圣诞节","introduce":"刺你的大脑","create_date":"2019-01-29 17:41:29"},{"name":"韩演艺圈","introduce":"韩演艺圈","create_date":"2019-01-29 17:42:25"},{"name":"国产高清","introduce":"好慌好暴力","create_date":"2019-01-29 17:43:16"}]
     * hottopic : [{"id":2,"name":"圣诞节"},{"id":3,"name":"韩演艺圈"},{"id":4,"name":"国产高清"},{"id":5,"name":"小鸟酱"},{"id":6,"name":"性肌肤旅程"},{"id":7,"name":"巨乳"},{"id":11,"name":"如你所愿"},{"id":12,"name":"我随风而行"}]
     * advertise : [{"id":100,"pic":"http://www.cqwjit.com:8082uploads/advertise/d8/da2dc186a2c932e164baa5ce8f3368.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/95172831417f07b1c?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":106,"pic":"http://www.cqwjit.com:8082uploads/advertise/ea/bed104f3c311d6cd168d4afc81654c.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9128ab3847b0dab15?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":107,"pic":"http://www.cqwjit.com:8082uploads/advertise/6d/711d6db24cfbee3f5eccec61ae5a7d.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/95473999b9ac8eb39?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":108,"pic":"http://www.cqwjit.com:8082uploads/advertise/02/2bbdc938801c59485741728db387b2.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/92bb2b2f82ceb9410?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":109,"pic":"http://www.cqwjit.com:8082uploads/advertise/52/6bb809337acac7420291db7948124c.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9cdc79d5365a1e4a7?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":111,"pic":"http://www.cqwjit.com:8082uploads/advertise/2d/2a910e44ca9460d44f6932e16aaaac.jpg","url":"http://www.cqwjit.com:8082dsfdsfsd"},{"id":112,"pic":"http://www.cqwjit.com:8082uploads/advertise/2d/2a910e44ca9460d44f6932e16aaaac.jpg","url":"http://www.cqwjit.com:8082aSASADS"},{"id":114,"pic":"http://www.cqwjit.com:8082uploads/advertise/d7/ccbf76c018fb0b5b8971030fbd2a96.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9f0065bfe0a876bb5?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":116,"pic":"http://www.cqwjit.com:8082uploads/advertise/2d/2a910e44ca9460d44f6932e16aaaac.jpg","url":"http://www.cqwjit.com:8082"},{"id":117,"pic":"http://www.cqwjit.com:8082uploads/advertise/54/2df9877a3d4423db870822bd3a2073.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9f61dba49a096b6a3?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":118,"pic":"http://www.cqwjit.com:8082uploads/advertise/54/2df9877a3d4423db870822bd3a2073.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9f61dba49a096b6a3?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":119,"pic":"http://www.cqwjit.com:8082uploads/advertise/54/2df9877a3d4423db870822bd3a2073.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9f61dba49a096b6a3?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":120,"pic":"http://www.cqwjit.com:8082uploads/advertise/f6/7bbc9c198a82398d579baec1b85d72.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9a62038b28e3911c3?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"},{"id":121,"pic":"http://www.cqwjit.com:8082uploads/advertise/a0/169ac46f4688e5413fedbc50a4389b.png","url":"http://www.cqwjit.com:8082https://sh.qihoo.com/9f322a8fb24db4e38?djsource=ZF90WY&refer_scene=0&scene=1&sign=360dh&uid=e975d479d6c5f7bf20b774a0299b06c5"}]
     * star : {"1":{"id":1,"name":"沈腾","introduce":"沈腾，中国喜剧演员","portrait":"http://www.cqwjit.com:8082/uploads/star/6a/335524248af652c6f2e1cb364d404c.jpg","video_count":3,"video_list":[{"id":1,"star_id":1,"vid":1,"name":"呵呵呵","video_url":"http://www.cqwjit.com:8082/videos/5c4032232914735a09f21741/index.m3u8","cover":"http://www.cqwjit.com:8082/uploads/video/pic/cf/693408dfb66fc1175d488f2542469f.jpg"},{"id":2,"star_id":1,"vid":2,"name":"自己过，去吧","video_url":"http://www.cqwjit.com:8082/videos/5c4f32232914735a09f21741/index.m3u8","cover":"http://www.cqwjit.com:8082/uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg"},{"id":3,"star_id":1,"vid":3,"name":"HHH","video_url":"http://www.cqwjit.com:8082/videos/5c4032s2914735a09f21741/index.m3u8","cover":"http://www.cqwjit.com:8082/uploads/video/pic/c8/421b2e4d8ee27d9ba6a78f483a2e9a.png"}]},"2":{"id":2,"name":"兔子野","introduce":"兔子野,日本AV女优","portrait":"http://www.cqwjit.com:8082/uploads/star/ae/8e1142f7aece12e7084de44496f40e.jpg","video_list":[]},"3":{"id":3,"name":"亚瑟王","introduce":"Fate线的女主角，身份为古不列颠传说中的亚瑟王。性格忠诚正直，谦逊有礼，个性认真","portrait":"http://www.cqwjit.com:8082/uploads/star/2f/5fa54a28d86730ccf80997c108733f.jpg","video_count":2,"video_list":[{"id":4,"star_id":3,"vid":4,"name":"是的支持下","video_url":"http://www.cqwjit.com:8082/videos/5c403f232914735a09f21741/index.m3u8","cover":"http://www.cqwjit.com:8082/uploads/video/pic/78/9c6af1f53ae651b38adde13594a13d.jpg"},{"id":1,"star_id":3,"vid":1,"name":"呵呵呵","video_url":"http://www.cqwjit.com:8082/videos/5c4032232914735a09f21741/index.m3u8","cover":"http://www.cqwjit.com:8082/uploads/video/pic/cf/693408dfb66fc1175d488f2542469f.jpg"}]},"4":{"id":4,"name":"黄渤","introduce":"好样的！！！","portrait":"http://www.cqwjit.com:8082/uploads/star/a0/81585c430c7472a63d32c2158c4f77.jpg","video_count":2,"video_list":[{"id":1,"star_id":4,"vid":1,"name":"呵呵呵","video_url":"http://www.cqwjit.com:8082/videos/5c4032232914735a09f21741/index.m3u8","cover":"http://www.cqwjit.com:8082/uploads/video/pic/cf/693408dfb66fc1175d488f2542469f.jpg"}]}}
     */

    private Map<String,StarBean> star;
    private List<TjtopicBean> tjtopic;
    private List<HottopicBean> hottopic;
    private List<MainBean.AdChartBean> advertise;

    public Map<String, StarBean> getStar() {
        return star;
    }

    public void setStar(Map<String, StarBean> star) {
        this.star = star;
    }

    public List<TjtopicBean> getTjtopic() {
        return tjtopic;
    }

    public void setTjtopic(List<TjtopicBean> tjtopic) {
        this.tjtopic = tjtopic;
    }

    public List<HottopicBean> getHottopic() {
        return hottopic;
    }

    public void setHottopic(List<HottopicBean> hottopic) {
        this.hottopic = hottopic;
    }



    public static class TjtopicBean {
        /**
         * name : 69出品
         * introduce : 69出品
         * create_date : 2019-01-29 17:39:46
         */

        private String name;
        private String introduce;
        private String create_date;
        private String pic;
        private String id;

        public String getPic() {
            return pic;
        }

        public String getId() {
            return id;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setId(String id) {
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

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }
    }

    public static class HottopicBean {
        /**
         * id : 2
         * name : 圣诞节
         */

        private int id;
        private String name;
        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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
    }

    public List<MainBean.AdChartBean> getAdvertise() {
        return advertise;
    }

    public void setAdvertise(List<MainBean.AdChartBean> advertise) {
        this.advertise = advertise;
    }
}
