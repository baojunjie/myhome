package com.myhome.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 *
 * 用户信息
 *
 */
public class UserInfo extends AbstractEntity {

	@JsonIgnore
    private User user ;
    
    private Boolean male;
    
    private Date birthday;
    
    /**
     *
     * 选送地区，目前是市一级
     *
     */

   
    private Region region;
    
    private String name;  //用户名
    
    /**
     *
     * 所属星座，例如：狮子座
     *
     */
    private String constellation;
    
    /**
     *
     * 生肖
     *
     */
    private String zodiac; 
    
    /**
     *
     * 身份证号码
     *
     */
    private String idCode;
    
    /**
     *
     * 家长的联系方式（手机号）
     *
     */
    private String parentMobile;
    
    /**
     *
     * 学校或者培训机构
     *
     */
    private String school;
    
    /**
     *
     * 指导老师
     *
     */
    private String teacher;
    
    /**
     *
     * 老师的联系方式（手机号）
     *
     */
    private String teacherMobile;
    
    /**
     *
     * 民族
     *
     */
    private String nation;
    
  
    
    private String img;//普通用户头像   缩略图
    
    private String originImg;//普通用户头像   原图
    
    private String type; //用户类型
    
    private String profession;//职业
    
    private String mobile;//联系方式
    
    private String nickName;//用户登录名/昵称
    
    private String account;//账户名
    
    private String balance;//账户余额
    
    private String token;//验证手机端上传是否成功
    
    private String invitationCode;// 邀请码
    
    private Integer score=0;//积分
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Boolean getMale() {
        return this.male;
    }
    
    public void setMale(Boolean male) {
        this.male = male;
    }
    
    public Date getBirthday() {
    	
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday =birthday;
	}
    
    public Region getRegion() {
        return this.region;
    }
    
    public void setRegion(Region region) {
        this.region = region;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getConstellation() {
        return this.constellation;
    }
    
    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }
    
    public String getZodiac() {
        return this.zodiac;
    }
    
    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }
    
    public String getIdCode() {
        return this.idCode;
    }
    
    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
    
    public String getParentMobile() {
        return this.parentMobile;
    }
    
    public void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile;
    }
    
    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }
    
    public String getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    
    public String getTeacherMobile() {
        return this.teacherMobile;
    }
    
    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
    }
    
    public String getNation() {
        return this.nation;
    }
    
    public void setNation(String nation) {
        this.nation = nation;
    }

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getOriginImg() {
		return originImg;
	}

	public void setOriginImg(String originImg) {
		this.originImg = originImg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	
    
}

