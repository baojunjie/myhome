package com.myhome.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 画家（艺术家）信息
 * 
 */
public class ArtistInfo extends AbstractEntity {

    @JsonIgnore
    private Artist  artist;

    private Boolean male = true;

    private Date    birthday;
    /**
     * 
     * 籍贯
     * 
     */
    private String  origin;
    /**
     * 
     * 选送地区，目前是市一级
     * 
     */

    private Region  region;

    private String  name;

    /**
     * 
     * 民族
     * 
     */
    private String  nation;

    /**
     * 
     * 所属星座，例如：狮子座
     * 
     */
    private String  constellation;

    /**
     * 
     * 生肖
     * 
     */
    private String  zodiac;

    /**
     * 
     * 身份证号码
     * 
     */
    private String  idCode;

    /**
     * 
     * 家长的联系方式（手机号）
     * 
     */
    private String  parentMobile;

    /**
     * 
     * 学校
     * 
     */
    private String  school;
    
    /**
     * 画家培训机构
     */
    private String trainingInstitution;

    /**
     * 
     * 学校联系方式（手机号）
     * 
     */
    private String  schoolMobile;
    

    /**
     * 
     * 指导老师（辅导员）
     * 
     */
    private String  instructor;

    /**
     * 
     * 指导老师（组织老师）的联系方式（手机号）
     * 
     */
    private String  instructorMobile;

    /**
     * 
     * 组织老师
     * 
     */
    private String  teacher;

    /**
     * 
     *组织老师的联系方式（手机号）
     * 
     */
    private String  teacherMobile;
    /**
     * 缩略图
     */
    private String  img;
    /**
     * 原图
     */
    private String  orginimg;
    /**
     * 推荐方学校/培训机构
     */
    private String  referrerSchool;
    /**
     * 推荐方学校/培训机构 联系方式
     */
    private String  referrerMobile;
    /**
     * 年龄
     * 
     * @return
     */
    private Integer age;

    private String  cartoon;         // 最喜欢的动漫

    private String  token;           // 验证手机端上传是否成功

    /**
     * 用户id
     */
    @JsonIgnore
    private User    user;

    /**
     * 2.0
     */
    private String  alipayAccount;   // 支付宝账户

    private String  bankAccount;     // 银行卡号

    private String  bankName;        // 开户银行名称

    private String  accountName;     // 开户人

    private String  weChatAccount;   // 微信账户

    //private String invitationCode;// 邀请码

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Boolean getMale() {
        return this.male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getSchoolMobile() {
        return this.schoolMobile;
    }

    public void setSchoolMobile(String schoolMobile) {
        this.schoolMobile = schoolMobile;
    }

    public String getInstructor() {
        return this.instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructorMobile() {
        return this.instructorMobile;
    }

    public void setInstructorMobile(String instructorMobile) {
        this.instructorMobile = instructorMobile;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrginimg() {
        return orginimg;
    }

    public void setOrginimg(String orginimg) {
        this.orginimg = orginimg;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCartoon() {
        return cartoon;
    }

    public void setCartoon(String cartoon) {
        this.cartoon = cartoon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
