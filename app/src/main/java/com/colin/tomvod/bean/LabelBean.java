package com.colin.tomvod.bean;

import java.io.Serializable;


public class LabelBean implements Serializable {

    /**
     * id : 1
     * name : 太监
     */

    private int id;
    private String name;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    @Override
    public String toString() {
        return "LabelBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
