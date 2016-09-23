package com.myhome.dao;

import com.myhome.entity.Picture;


public interface IPictureDAO extends IDAO {

	Long addPictureMobile(Picture picture)  throws Exception;

	void updatePictureMobile(Picture pic)  throws Exception;

	

}
