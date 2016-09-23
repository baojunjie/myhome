package com.myhome.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 *
 * 作品
 *
 */
public class Works extends AbstractEntity {

    /**
     *
     * 作品里面其他关于作者的姓名作为作品问世时画家的信息副本
     *
     */
    private Artist artist;
    
    /**
     *
     * 所获投票数
     *
     */
    private Integer votenum;
    
    /**
     *
     * 所获点赞数
     *
     */
    private Integer praise;
    
    /**
     *
     * null 代表 不清楚性别
     *
     */
    private Boolean male = true;
    
    /**
     *
     * 所在地区，目前为城市
     *
     */
    @JsonIgnore
    private Region region;
    
    private Integer age;
    
    /**
     *
     * 作品名称
     *
     */
    private String name;
    
    private String author;
    
    private String school;//指导老师
    
    private String schoolMobile;//指导老师联系方式

    /**
     *
     * 作品简介
     *
     */
    private String description;

    private Set<WorksPictureItem> worksPictureItemSet = new LinkedHashSet<WorksPictureItem>();
    
    /**
     * 评论总数
     */
    private Integer commentNum;
    /**
     * 排名
     * @return
     */
    private Integer index;
    
    private String token;//验证手机端上传是否成功
    
    private Integer type=0;//1:区初选2:区复选3:区终选4:市初选5:市复选6:市终选7:省初选8:省复选9:省终选10:全国复选
    
    private Integer awards =0;//全国特等奖11 全国金奖10 全国银奖9 全国铜奖8 全国创作奖7 省金奖6 省银奖5 省铜奖4 市金奖3 市银奖2 市铜奖1 普通0

    // 2.1新增
    // 指导老师
    private String instructor;
    // 指导老师电话
    private String instructorMobile;
    
    public Set<WorksPictureItem> getWorksPictureItemSet() {
		return worksPictureItemSet;
	}

	public void setWorksPictureItemSet(Set<WorksPictureItem> worksPictureItemSet) {
		this.worksPictureItemSet = worksPictureItemSet;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Artist getArtist() {
        return this.artist;
    }
    
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
    public Integer getVotenum() {
        return this.votenum;
    }
    
    public void setVotenum(Integer votenum) {
        this.votenum = votenum;
    }
    
    public Integer getPraise() {
        return this.praise;
    }
    
    public void setPraise(Integer praise) {
        this.praise = praise;
    }
    
    public Boolean getMale() {
        return this.male;
    }
    
    public void setMale(Boolean male) {
        this.male = male;
    }
    
    public Region getRegion() {
        return this.region;
    }
    
    public void setRegion(Region region) {
        this.region = region;
    }
    
    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    public String getSchoolMobile() {
        return schoolMobile;
    }

    public void setSchoolMobile(String schoolMobile) {
        this.schoolMobile = schoolMobile;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAwards() {
        return awards;
    }

    public void setAwards(Integer awards) {
        this.awards = awards;
    }



}
