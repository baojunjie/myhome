package com.myhome.service.impl;

import java.util.List;

import com.myhome.entity.Authentication;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.MobileAuthentication;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.service.ILoginAuthenticationService;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.UtilMD5;
import com.myhome.utils.UtilMD5Encoder;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("loginAuthenticationServiceImpl")
public class LoginAuthenticationServiceImpl extends AbstractServiceImpl 
        implements ILoginAuthenticationService {

    

    



    @Override
    @Transactional(readOnly=true)
    public LoginAuthentication get(Long id) {
        return getLoginAuthenticationDAO().get(id);
    }
    
    
    @Transactional
    public void add(LoginAuthentication loginAuthentication) {
    	  getLoginAuthenticationDAO().add(loginAuthentication);
    }
    
    
    @Transactional
    public int update(LoginAuthentication loginAuthentication) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int remove(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @Transactional
    public int delete(Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }


	@Override
	public Long registerImport(String mobile, String name) {
		try{ 
		if(name!=""&&mobile!=""){
			 String password= UtilMD5.getMD5ofStr(mobile.substring(5)).toLowerCase();
			 List<Authentication> authenticationlist = getAuthenticationDAO().findByLogin(mobile);
			 List<UserInfo>  list  =  getUserInfoDAO().findByName(name);
	            if (CommonUtils.isEmpty(authenticationlist)&&CommonUtils.isEmpty(list)) {
	            	 String salt = RandomDataUtil.SixRadom();
	    			 User user = new User();
	    			 UserInfo userinfo = new UserInfo();
	    			 userinfo.setName(name);
	    			 userinfo.setType("1");
	    			 user.setUserInfo(userinfo);
	    			 userinfo.setUser(user);
	    			 userinfo.setRegion(null);
	    			 getUserDAO().addUserMobile(user);// 注册成功后吧信息写入session
	    			 //保存手机认证
	    			 MobileAuthentication mobileAuthentication = new MobileAuthentication();
	    			 mobileAuthentication.setUser(user);
	    			 mobileAuthentication.setLogin(mobile);
	    			 mobileAuthentication.setSalt(salt);
	    			 mobileAuthentication.setInvalid(true);
	    			 mobileAuthentication.setPassword(UtilMD5Encoder.encodePassword(
	    					 password,salt));
	    		 	 getMobileAuthenticationDAO().add(mobileAuthentication);
	    		 	 //保存用户名认证
	    		 	 LoginAuthentication loginAuthentication = new LoginAuthentication();
	    		 	 loginAuthentication.setUser(user);
	    		 	 loginAuthentication.setLogin(name);
	    		 	 loginAuthentication.setSalt(salt);
	    		 	 loginAuthentication.setInvalid(true);
	    		 	 loginAuthentication.setPassword(UtilMD5Encoder.encodePassword(
	    					 password,salt));
	    		 	 getLoginAuthenticationDAO().add(loginAuthentication);
	    		 	 return user.getId();
	            }
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
    
    
    

}
