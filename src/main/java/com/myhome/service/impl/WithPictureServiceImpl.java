package com.myhome.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.WithPicture;
import com.myhome.service.IWithPictureService;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.PicturePath;

@Component("withPictureServiceImpl")
public class WithPictureServiceImpl extends AbstractServiceImpl implements IWithPictureService {

	@Override
	@Transactional
	public WithPicture updateMobileWithPicture(WithPicture withPicture) throws Exception {
		
		if(withPicture.getOriginPath()!=null){
			Map<String, String> path = ImageUpload.imageUpload(withPicture.getOriginPath(), PicturePath.helppic);
			if (null == path || "".equals(path)) {
				throw new Exception("");
			} else{
				withPicture.setOriginPath(path.get("orginimg"));
				withPicture.setPath(path.get("thumb_path"));		
			}
		}
		return super.getWithPictureDAO().updateMobileWithPicture(withPicture);
	}


	@Override
	@Transactional
	public void deleteMobileWithPicture(WithPicture withPicture) throws Exception {
		super.getWithPictureDAO().deleteMobileWithPicture(withPicture);
	}

	@Override
	@Transactional(readOnly=true)
	public WithPicture getMobileWithPicture(WithPicture withPicture) throws Exception {
		WithPicture wh = super.getWithPictureDAO().getMobileWithPicture(withPicture);
		return wh;
	}

	@Override
	@Transactional(readOnly=true)
	public List<WithPicture> getMobileWithPictureList(WithPicture withPicture) throws Exception {
		return super.getWithPictureDAO().getMobileWithPictureList(withPicture);
	}

	@Override
	@Transactional
	public WithPicture addMobileWithPicture(WithPicture withPicture) throws Exception {

		boolean falg =super.getTeacherDao().getMobileToken(withPicture.getToken(),"t_with_picture");
		if(!falg){
			return withPicture;
		}
		
		Map<String, String> path = ImageUpload.imageUpload(withPicture.getOriginPath(), "withPicture");
		if (null == path || "".equals(path)) {
		 throw new Exception("上传图片失败") ;
		}else{
			withPicture.setOriginPath(path.get("orginimg"));
			withPicture.setPath(path.get("thumb_path"));
		}
		return super.getWithPictureDAO().addMobileWithPicture(withPicture);
	}

}

