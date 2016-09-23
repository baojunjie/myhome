package com.myhome.dao;

import com.myhome.entity.Teacher;
import com.myhome.entity.User;

public interface ITeacherDao extends IDAO{

	public Long addMobileTeacher(Teacher teacher) throws Exception;

	public void updateMobileTeacher(Teacher teacher) throws Exception;

	public Teacher getMobileTeacher(Teacher teacher) throws Exception;

	public void getAuthenticationCheck(Long id, Integer status,String type) throws Exception;

	public boolean getMobileToken(String token,String HelpChildren) throws Exception;

    public Teacher get(Long id);
    /**
     * 根据userid获取教师信息
     * @param userid
     * @return
     */
    public Teacher getByUserid(Long userid);

}
