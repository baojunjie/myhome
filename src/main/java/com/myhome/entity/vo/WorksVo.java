/**
 * Project Name:web_myhome
 * File Name:WorksVo.java
 * Package Name:com.myhome.entity.vo
 * Date:2015年9月12日上午10:04:10
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
 */

package com.myhome.entity.vo;

import com.myhome.entity.AbstractEntity;

/**
 * ClassName:WorksVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年9月12日 上午10:04:10 <br/>
 * 
 * @author 1
 * @version
 * @since JDK 1.6
 * @see
 */
public class WorksVo extends AbstractEntity {

	private Long worksId;// works id
	private Long tagId;// tag id
	private Long wtiId;// t_works_tag_item id
	private Long pictureId; // 图片id  
	private String instructor;
	private String instructorMobile;
	private String picture;// 图片  缩略图、
	
	private String orginimg;// 图片  原图
	
	private String description;// 作品描述
	private String name;// 作品名

	
	public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructorMobile() {
        return instructorMobile;
    }

    public void setInstructorMobile(String instructorMobile) {
        this.instructorMobile = instructorMobile;
    }

    public Long getWorksId() {
		return worksId;
	}

	public void setWorksId(Long worksId) {
		this.worksId = worksId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getWtiId() {
		return wtiId;
	}

	public void setWtiId(Long wtiId) {
		this.wtiId = wtiId;
	}

	

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrginimg() {
		return orginimg;
	}

	public void setOrginimg(String orginimg) {
		this.orginimg = orginimg;
	}

}
