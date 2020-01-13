package com.colin.tomvod.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class VideoDownLoad extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String name;
    @Column(unique = true,nullable = false)
    private long id;

    private String url;
    private String filePath;

    private boolean finished;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
