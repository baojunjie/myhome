package com.myhome.service;

import com.myhome.entity.Teacher;


public interface ITeacherService extends IService{

	public Long addMobileTeacher(Teacher teacher) throws Exception;

	public void updateMobileTeacher(Teacher teacher) throws Exception;

	public Teacher  getMobileTeacher(Teacher teacher)  throws Exception;
	
	 public void getAuthenticationCheck(Long id, Integer status, String type) throws Exception;
	/**
	 * 保存老师信息
	 * lqf
	 * @param teacher
	 */
    public void save(Teacher teacher);
    /**
     * 获取老师信息
     * lqf
     * @param id
     * @return
     */

    public Teacher get(Long id);
    /**
     * 修改老师信息
     * lqf
     * @param teacher
     */
    public void update(Teacher teacher);

    /**
     * 更具userid获取信息
     * @param valueOf
     * @return
     */
    public Teacher getByUserid(Long valueOf);
	
	
}

