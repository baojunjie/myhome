package com.myhome.dao;

import java.util.List;

import com.myhome.entity.WithPicture;

public interface IWithPictureDAO extends IDAO{

	
	public WithPicture  updateMobileWithPicture (WithPicture withPicture) throws Exception;
	
	public void  deleteMobileWithPicture (WithPicture withPicture) throws Exception;
	
	public WithPicture  getMobileWithPicture (WithPicture withPicture) throws Exception;
	
	public List<WithPicture>  getMobileWithPictureList (WithPicture withPicture) throws Exception;

	public WithPicture addMobileWithPicture(WithPicture withPicture) throws Exception;

    public WithPicture get(Long valueOf);
    /**
     * 更具userid
     * 获取他的图片(扩展性弱)
     * @param id
     * @return
     */
    public List<WithPicture> findByUser(Long id);
}
