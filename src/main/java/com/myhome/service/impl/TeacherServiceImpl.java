package com.myhome.service.impl;


import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Teacher;
import com.myhome.entity.UserInfo;
import com.myhome.service.ITeacherService;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.PicturePath;
import com.myhome.utils.Tools;

@Component("teacherServiceImpl")
public class TeacherServiceImpl extends AbstractServiceImpl implements ITeacherService {

	/**
	 * 新增老师
	 * 
	 * @see com.myhome.service.ITeacherService#addMobileTeacher(com.myhome.entity.Teacher)
	 */
	@Override
	@Transactional
	public Long addMobileTeacher(Teacher teacher) throws Exception {
		
		boolean falg=	super.getTeacherDao().getMobileToken(teacher.getToken(),"t_teacher");
		if(!falg){
			return 0l;
		}
		
		if(Tools.notEmpty(teacher.getCeltyl())){
		    Map<String, String> path = ImageUpload.imageUpload(teacher.getCeltyl(), PicturePath.teacher);
	        if (null == path || "".equals(path)) {
	            throw new Exception("教师资格证上传失败");
	        } else {
	            teacher.setCeltyl(path.get("orginimg"));
	            teacher.setThumbnailCeltyl(path.get("thumb_path"));
	        }
		}
		
		Map<String, String> path2 = ImageUpload.imageUpload(teacher.getImg(), PicturePath.teacherphoto);
		if (null == path2 || "".equals(path2)) {
			throw new Exception("教师头像上传失败");
		} else {
			teacher.setImg(path2.get("orginimg"));
			teacher.setThumbnailImg(path2.get("thumb_path"));
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setId(teacher.getUser().getId());
		userInfo.setType("4");
		super.getUserDAO().updateUserMobile(userInfo);
		return super.getTeacherDao().addMobileTeacher(teacher);
	}

	/**
	 * 修改老师
	 * 
	 * @see com.myhome.service.ITeacherService#updateMobileTeacher(com.myhome.entity.Teacher)
	 */
	@Override
	@Transactional
	public void updateMobileTeacher(Teacher teacher) throws Exception {

		if (teacher.getCeltyl() != null&&teacher.getCeltyl().length()>0) {
			Map<String, String> path = ImageUpload.imageUpload(teacher.getCeltyl(), PicturePath.teacher);
			if (null == path || "".equals(path)) {
				throw new Exception("教师资格证上传失败");
			} else {
				teacher.setCeltyl(path.get("orginimg"));
				teacher.setThumbnailCeltyl(path.get("thumb_path"));
			}
		}
		if (teacher.getImg() != null) {
			Map<String, String> path2 = ImageUpload.imageUpload(teacher.getImg(), PicturePath.teacher);
			if (null == path2 || "".equals(path2)) {
				throw new Exception("教师资格证上传失败");
			} else {
				teacher.setImg(path2.get("orginimg"));
				teacher.setThumbnailImg(path2.get("thumb_path"));
			}
		}

		super.getTeacherDao().updateMobileTeacher(teacher);
	}

	/**
	 * 根据用户id查询老师信息 包括用户信息
	 * 
	 * @see com.myhome.service.ITeacherService#getMobileTeacher(com.myhome.entity.User)
	 */
	@Override
	@Transactional(readOnly = true)
	public Teacher getMobileTeacher(Teacher teacher) throws Exception {

		Teacher te = super.getTeacherDao().getMobileTeacher(teacher);

		return te;

	}

	@Override
	@Transactional
	public void getAuthenticationCheck(Long id, Integer status, String type) throws Exception {
		super.getTeacherDao().getAuthenticationCheck(id, status, type);
	}
    @Override
    @Transactional
    public void save(Teacher teacher) {
         getTeacherDao().add(teacher);
        
    }

    @Override
    @Transactional(readOnly=true)
    public Teacher get(Long id) {
        return getTeacherDao().get(id);
    }

    @Override
    @Transactional
    public void update(Teacher teacher) {
        getTeacherDao().update(teacher);
        
    }

    @Override
    @Transactional
    public Teacher getByUserid(Long userid) {
        return getTeacherDao().getByUserid(userid);
    }
}
