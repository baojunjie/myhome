package com.myhome.entity.vo;

import java.sql.Date;


/**
 * 
 * @author lqf
 *
 */
public class GameVo {
    /**
     *  作者id
     */
    private Integer id;
    /**
     *  作品id
     */
    private Integer worksid;
    
	/**
	 * 	作者姓名
	 */
	private String name;
	/**
	 * 作者性别
	 */
	private String male;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 作者生日
	 */
	private Date birthday;
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
	 * 身份证号
	 */
	private String idCode;
	/**
     * 赛区（省）
     */
    private String province;
    /**
     * 赛区（市）
     */
    private String city;
	/**
	 * 所在区域（城市）
	 */
	private String region;
	/**
	 * 籍贯
	 */
	private String origin;
	/**
	 * 家长电话
	 */
	private String parentMobile;
	/**
	 * 学校
	 */
	private String school;
	/**
	 * 学校号码
	 */
	private String schoolMobile;
	/**
     * 推荐人学校
     */
    private String referrerSchool;
    /**
     * 推荐人联系方式
     */
    private String referrerMobile;
	/**
	 * 组织老师
	 */
	private String instructor;
	/**
	 * 组织老师号码
	 */
	private String instructorMobile;
	/**
	 * 指导老师
	 */
	private String teacher;
	/**
	 * 指导老师号码
	 */
	private String teacherMobile;
	/**
	 * 作品类型
	 */
	private Integer workstag;
	/**
	 * 作品名称
	 */
	private String worksname;
	/**
	 * 作品内容
	 */
	private String description;
	/**
	 * 画家原图
	 * @return
	 */
	private String orginimg;
	/**
	 * 画家缩略图
	 * @return
	 */
	private String img;
	/**
     * 作品原图
     * @return
     */
    private String orginpath;
    /**
     * 作品缩略图
     * @return
     */
    private String path;
    /**
     * 不保存的无效路径
     * @return
     */
    private String picturetag="useless";
    /**
     * 卡通形象
     */
    private String cartoon;
    /**
     * 2.0
     */
    private String  alipayAccount;   // 支付宝账户

    private String  bankAccount;     // 银行卡号

    private String  bankName;        // 开户银行名称

    private String  accountName;     // 开户人

    private String  weChatAccount;   // 微信账户
    private String trainingInstitution;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMale() {
        return male;
    }
    public void setMale(String male) {
        this.male = male;
    }
    public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getParentMobile() {
		return parentMobile;
	}
	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchoolMobile() {
		return schoolMobile;
	}
	public void setSchoolMobile(String schoolMobile) {
		this.schoolMobile = schoolMobile;
	}
	public String getReferrerSchool() {
		return referrerSchool;
	}
	public void setReferrerSchool(String referrerSchool) {
		this.referrerSchool = referrerSchool;
	}
	public String getReferrerMobile() {
		return referrerMobile;
	}
	public void setReferrerMobile(String referrerMobile) {
		this.referrerMobile = referrerMobile;
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
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getTeacherMobile() {
		return teacherMobile;
	}
	public void setTeacherMobile(String teacherMobile) {
		this.teacherMobile = teacherMobile;
	}
	public Integer getWorkstag() {
		return workstag;
	}
	public void setWorkstag(Integer workstag) {
		this.workstag = workstag;
	}
	public String getWorksname() {
		return worksname;
	}
	public void setWorksname(String worksname) {
		this.worksname = worksname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
    public String getOrginimg() {
        return orginimg;
    }
    public void setOrginimg(String orginimg) {
        this.orginimg = orginimg;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getOrginpath() {
        return orginpath;
    }
    public void setOrginpath(String orginpath) {
        this.orginpath = orginpath;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getWorksid() {
        return worksid;
    }
    public void setWorksid(Integer worksid) {
        this.worksid = worksid;
    }
    public String getPicturetag() {
        return picturetag;
    }
    public void setPicturetag(String picturetag) {
        this.picturetag = picturetag;
    }
    public String getCartoon() {
        return cartoon;
    }
    public void setCartoon(String cartoon) {
        this.cartoon = cartoon;
    }
    public String getAlipayAccount() {
        return alipayAccount;
    }
    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getWeChatAccount() {
        return weChatAccount;
    }
    public void setWeChatAccount(String weChatAccount) {
        this.weChatAccount = weChatAccount;
    }
	public String getTrainingInstitution() {
		return trainingInstitution;
	}
	public void setTrainingInstitution(String trainingInstitution) {
		this.trainingInstitution = trainingInstitution;
	}
	
    
}
