package com.myhome.service;

import java.util.Date;
import java.util.List;

import com.myhome.entity.VerificationCode;

public interface IVerificationCodeService extends IService{

	boolean save(String mobile, int code);

	public List<VerificationCode> findByMobile(String mobile, String verificationCode) throws Exception;

	public void saveMobile(String mobile, int code) throws Exception;

    public int findCount(String mobile, Date date) throws Exception;
}
