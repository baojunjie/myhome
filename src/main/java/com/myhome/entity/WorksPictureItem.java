package com.myhome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 *
 * 作品图片
 *
 */
public class WorksPictureItem extends AbstractEntity {

    @JsonIgnore
    private Works works;

    private Picture picture;

    public Works getWorks() {
        return works;
    }

    public void setWorks(Works works) {
        this.works = works;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
