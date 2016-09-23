package com.myhome.entity;

/**
 * created by 重剑 on 2015/9/9 0009
 */
public class Picture extends AbstractEntity {

    /**
     *缩略图路径
     * 本地存储路径，是相对路径，以“/”开头
     *
     */
    private String path;
    /**
     * 原图路径
     */
    private String orginpath;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOrginpath() {
        return orginpath;
    }

    public void setOrginpath(String orginpath) {
        this.orginpath = orginpath;
    }
    
}

