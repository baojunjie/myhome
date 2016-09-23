package com.myhome.service;

import java.util.List;

import com.myhome.entity.WithPicture;

public interface IWithPictureService extends IService{

	
	public WithPicture  updateMobileWithPicture (WithPicture withPicture) throws Exception;
	
	public void  deleteMobileWithPicture (WithPicture withPicture) throws Exception;
	
	public WithPicture  getMobileWithPicture (WithPicture withPicture) throws Exception;
	
	public List<WithPicture>  getMobileWithPictureList (WithPicture withPicture) throws Exception;

	public WithPicture addMobileWithPicture(WithPicture withPicture) throws Exception;
}
