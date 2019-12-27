package com.colin.tomvod.bean;

import java.io.Serializable;
import java.util.List;


public class ClassBean implements Serializable {

    /**
     * code : 0
     * info : 查询成功
     * data : [{"id":1002,"name":"游戏改编","pic":"dst_uin=16572513&spec=100"},{"id":1003,"name":"恐怖","pic":"dst_uin=16572513&spec=100"}]
     */

    private int code;
    private String info;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1002
         * name : 游戏改编
         * pic : dst_uin=16572513&spec=100
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
}
