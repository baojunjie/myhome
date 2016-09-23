/**
 * Project Name:web_myhome
 * File Name:PictureServiceImpl.java
 * Package Name:com.myhome.service.impl
 * Date:2015年9月10日下午3:39:36
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.myhome.service.impl;

import org.springframework.stereotype.Component;

import com.myhome.entity.Picture;
import com.myhome.service.IPictureService;

/**
 * ClassName:PictureServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年9月10日 下午3:39:36 <br/>
 */


@Component("pictureServiceImpl")
public class PictureServiceImpl extends AbstractServiceImpl 
implements IPictureService{

	@Override
    public Long addPictureMobile(Picture picture) throws Exception {
	    
	    super.getPictureDAO().addPictureMobile(picture);
	    return picture.getId();
    }

}

