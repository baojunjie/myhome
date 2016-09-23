/**
 * Project Name:webmyhome
 * File Name:VersionServiceImpl.java
 * Package Name:com.myhome.service.impl
 * Date:2015年9月21日下午6:19:03
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Version;
import com.myhome.service.IVersionService;

/**
 * ClassName:VersionServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年9月21日 下午6:19:03 <br/>
 * @author   1
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service("versionServiceImpl")
public class VersionServiceImpl extends AbstractServiceImpl implements IVersionService{

	/**
	 * 获取版本号
	 */
	
	@Override
	@Transactional(readOnly=true)
    public Map<String,String>  getVersionMobile() throws Exception{
	    return super.getVersionDao().getVersionMobile();
    }

}

