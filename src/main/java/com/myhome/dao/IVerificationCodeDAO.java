package com.myhome.dao;

import java.util.Date;
import java.util.List;

import com.myhome.entity.VerificationCode;

public interface IVerificationCodeDAO {

	public void save(VerificationCode model);


	public List<VerificationCode> findByMobile(String mobile,String verificationCode)  throws Exception;


	public void saveMobile(String mobile, int code) throws Exception;


	public void upadteVerificationCode(String mobile) throws Exception;

    public int findCount(String mobile, Date date) throws Exception;
}
