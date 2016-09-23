package com.myhome.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.IVerificationCodeDAO;
import com.myhome.entity.VerificationCode;
import com.myhome.service.IVerificationCodeService;

@Component("VerificationCodeServiceImpl")
public class VerificationCodeServiceImpl extends AbstractServiceImpl implements IVerificationCodeService{
	@Autowired
	 private  IVerificationCodeDAO verificationCodeDAO;
	@Override
	@Transactional
	public boolean save(String mobile, int code) {
		try{
		VerificationCode model = new VerificationCode();
		model.setMobile(mobile);
		model.setCode(String.valueOf(code));
		verificationCodeDAO.save(model);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	@Transactional(readOnly=true)
	public List<VerificationCode> findByMobile(String mobile, String verificationCode)  throws Exception{
		return verificationCodeDAO.findByMobile(mobile,verificationCode);
	}
	@Override
	@Transactional
    public void saveMobile(String mobile, int code) throws Exception {
		verificationCodeDAO.upadteVerificationCode(mobile);
		verificationCodeDAO.saveMobile(mobile,code);
	    
    }

	
    @Override
    @Transactional(readOnly = true)
    public int findCount(String mobile, Date date) throws Exception {
        return verificationCodeDAO.findCount(mobile, date);
    }
}
