package com.myhome.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.HelpChildren;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.WithPicture;
import com.myhome.entity.vo.HelpChildrenVo;
import com.myhome.service.IHelpChildrenService;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.DateUtil;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.PicturePath;
import com.myhome.utils.SysConstants;


@Component("helpChildrenServiceImpl")
public class HelpChildrenServiceImpl extends AbstractServiceImpl implements IHelpChildrenService{

	@Override
	@Transactional
    public Long addMobileHelpChildren(HelpChildren children,HttpServletRequest request) throws Exception {
		boolean falg=	super.getTeacherDao().getMobileToken(children.getToken(),"t_help_children");
		if(!falg){
			return 0l;
		}
		String userType="3";
		if(children.getUser()!=null&&children.getUser().getUserInfo()!=null&&children.getUser().getUserInfo().getType()!=null){
			 userType=children.getUser().getUserInfo().getType();
		}
		if(children.getImg()!=null){
			Map<String, String> path = ImageUpload.imageUpload(children.getImg(), PicturePath.helppic);
			if (null == path || "".equals(path)) {
				throw new Exception("");
			} else{
				children.setImg(path.get("orginimg"));
				children.setThumbnailImg(path.get("thumb_path"));		
			}
		}
		
		UserInfo userInfo=new UserInfo();
		userInfo.setId(children.getUser().getId());
		userInfo.setType(userType);
		super.getUserDAO().updateUserMobile(userInfo);
	    return super.getHelpChildrenDao().addMobileHelpChildren(children);
    }

	@Override
	@Transactional
    public void updateMobileHelpChildren(HelpChildren children) throws Exception {
		
		if(children.getImg()!=null){
			Map<String, String> path = ImageUpload.imageUpload(children.getImg(), PicturePath.helppic);
			if (null == path || "".equals(path)) {
				throw new Exception("");
			} else{
				children.setImg(path.get("orginimg"));
				children.setThumbnailImg(path.get("thumb_path"));		
			}
		}
		super.getHelpChildrenDao().updateMobileHelpChildren(children);
    }

	@Override
    public void deleteMobileHelpChildren(HelpChildren children) throws Exception {
    }

	@Override
	@Transactional(readOnly=true)
    public HelpChildren getMobileHelpChildren(HelpChildren children) throws Exception {
		WithPicture withPicture=new WithPicture();
		withPicture.setUser(children.getUser());
		withPicture.setType(3);
		List<WithPicture> list=	super.getWithPictureDAO().getMobileWithPictureList(withPicture);
		
		HelpChildren ch =	super.getHelpChildrenDao().getMobileHelpChildren(children); 
		if(list.size()>0){
			ch.setWithPictureList(list);
		}
	    return ch;
    }

	@Override
	@Transactional(readOnly=true)
    public List<HelpChildren> getMobileHelpChildrenList(HelpChildren children) throws Exception {
	    return super.getHelpChildrenDao().getMobileHelpChildrenList(children);
    }


    @Override
    @Transactional
    public HelpChildren saveOrUpdate(Integer userid, HelpChildrenVo model,HttpServletRequest request) {
        String pattern = "yyy-MM-dd"; //首先定义时间格式
        SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
        User user = new User();
        user.setId(Long.valueOf(userid));
       try{
           HelpChildren helpchildren = new HelpChildren();
           if(model.getId()==null){
               helpchildren.setName(model.getName());
               helpchildren.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helpchild/" + model.getImg());
               helpchildren.setThumbnailImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helpchild/thumb_" + model.getImg());
               helpchildren.setMale(model.getMaleStr()==1);
               helpchildren.setBirthday(Date.valueOf(model.getBirthdayStr()));
               helpchildren.setAge(DateUtil.getage(helpchildren.getBirthday()));
               helpchildren.setZodiac(DateUtil.getZodica(helpchildren.getBirthday()));
               helpchildren.setConstellation(DateUtil.getConstellation(helpchildren.getBirthday()));
//             helpchildren.setZodiac(zodiac);  //属相
//             helpchildren.setConstellation(constellation);//星座
               helpchildren.setNation(model.getNation());
               helpchildren.setOrigin(model.getOrigin());
               helpchildren.setAddress(model.getAddress());
               helpchildren.setIdCode(model.getIdCode());
               helpchildren.setStatus(2);
               helpchildren.setParentMobile(model.getParentMobile());
               helpchildren.setParnetIdCode(model.getParnetIdCode());
//             helpchildren.setParentName(parentName);
               helpchildren.setCurrentStatus(model.getCurrentStatus());
               helpchildren.setWish(model.getWish());
               helpchildren.setSignificative(model.getSignificative());
               helpchildren.setDreamFund(model.getDreamFund());
               helpchildren.setSchool(model.getSchool());
               //2.0新增
               helpchildren.setAlipayAccount(model.getAlipayAccount());
               helpchildren.setBankAccount(model.getBankAccount());
               helpchildren.setBankName(model.getBankName());
               helpchildren.setAccountName(model.getAccountName());
               helpchildren.setWeChatAccount(model.getWeChatAccount());

               helpchildren.setUser(user);
               getHelpChildrenDao().add(helpchildren);
               
               //保存角色类型
               UserInfo  userinfo = getUserInfoDAO().get(Long.valueOf(userid));
               if("2".equals(userinfo.getType())){
                   userinfo.setType("6");
               }else{
                   userinfo.setType("3");
               }
               getUserInfoDAO().update(userinfo); 
               request.getSession().setAttribute(SysConstants.USER_TYPE, userinfo.getType());
               
               //保存图片
               if(model.getPic1()!=null&&(!"".equals(model.getPic1()))){
                   WithPicture withpic = new WithPicture();
                   withpic.setDescription("幸运小朋友附加图片");
                   withpic.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/" + model.getPic1());
                   withpic.setOriginPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/thumb_" + model.getPic1());
                   withpic.setUser(user);
                   getWithPictureDAO().add(withpic);
               }
               if(model.getPic2()!=null&&(!"".equals(model.getPic2()))){
                   WithPicture withpic = new WithPicture();
                   withpic.setDescription("幸运小朋友附加图片");
                   withpic.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/" + model.getPic2());
                   withpic.setOriginPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/thumb_" + model.getPic2());
                   withpic.setUser(user);
                   getWithPictureDAO().add(withpic);
                  }
               if(model.getPic3()!=null&&(!"".equals(model.getPic3()))){
                   WithPicture withpic = new WithPicture();
                   withpic.setDescription("幸运小朋友附加图片");
                   withpic.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/" + model.getPic3());
                   withpic.setOriginPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/thumb_" + model.getPic3());
                   withpic.setUser(user);
                   getWithPictureDAO().add(withpic);
                  }
           }else{
               helpchildren  = getHelpChildrenDao().get(model.getId());
               helpchildren.setName(model.getName());
               if (!"useless".equals(model.getImg())) {
                   helpchildren.setImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helpchild/" + model.getImg());
                   helpchildren.setThumbnailImg(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helpchild/thumb_" + model.getImg());
               }
               helpchildren.setMale(model.getMaleStr()==1);
               helpchildren.setBirthday(Date.valueOf(model.getBirthdayStr()));
               helpchildren.setAge(DateUtil.getage(helpchildren.getBirthday()));
               helpchildren.setZodiac(DateUtil.getZodica(helpchildren.getBirthday()));
               helpchildren.setConstellation(DateUtil.getConstellation(helpchildren.getBirthday()));
//             helpchildren.setZodiac(zodiac);  //属相
//             helpchildren.setConstellation(constellation);//星座
               helpchildren.setNation(model.getNation());
               helpchildren.setOrigin(model.getOrigin());
               helpchildren.setAddress(model.getAddress());
               helpchildren.setIdCode(model.getIdCode());
               helpchildren.setParentMobile(model.getParentMobile());
               helpchildren.setParnetIdCode(model.getParnetIdCode());
//             helpchildren.setParentName(parentName);
               helpchildren.setCurrentStatus(model.getCurrentStatus());
               helpchildren.setWish(model.getWish());
               helpchildren.setSignificative(model.getSignificative());
               helpchildren.setDreamFund(model.getDreamFund());
               helpchildren.setSchool(model.getSchool());
               //2.0新增
               helpchildren.setAlipayAccount(model.getAlipayAccount());
               helpchildren.setBankAccount(model.getBankAccount());
               helpchildren.setBankName(model.getBankName());
               helpchildren.setAccountName(model.getAccountName());
               helpchildren.setWeChatAccount(model.getWeChatAccount());

               helpchildren.setStatus(2);
               getHelpChildrenDao().update(helpchildren);
             //保存图片
               if(!"useless".equals(model.getPic1())){
            	   WithPicture withpic = new WithPicture();
            	   if(model.getPicid1()!=null&&(!"".equals(model.getPicid1()))){
            		   withpic = getWithPictureDAO().get(Long.valueOf(model.getPicid1()));
            	   }
                   withpic.setDescription("幸运小朋友附加图片");
                   withpic.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/" + model.getPic1());
                   withpic.setOriginPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/thumb_" + model.getPic1());
                   withpic.setUser(user);
                   if(model.getPicid1()!=null&&(!"".equals(model.getPicid1()))){
                	   getWithPictureDAO().update(withpic);
            	   }else{
            		   getWithPictureDAO().add(withpic);
            	   }
               }
               if(!"useless".equals(model.getPic2())){
            	   WithPicture withpic = new WithPicture();
            	   if(model.getPicid2()!=null&&(!"".equals(model.getPicid2()))){
            		   withpic = getWithPictureDAO().get(Long.valueOf(model.getPicid2()));
            	   }
                   withpic.setDescription("幸运小朋友附加图片");
                   withpic.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/" + model.getPic2());
                   withpic.setOriginPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/thumb_" + model.getPic2());
                   withpic.setUser(user);
                   if(model.getPicid2()!=null&&(!"".equals(model.getPicid2()))){
                	   getWithPictureDAO().update(withpic);
            	   }else{
            		   getWithPictureDAO().add(withpic);
            	   }
                  }
               if(!"useless".equals(model.getPic3())){
            	   WithPicture withpic = new WithPicture();
            	   if(model.getPicid3()!=null&&(!"".equals(model.getPicid3()))){
            		   withpic = getWithPictureDAO().get(Long.valueOf(model.getPicid3()));
            	   }
                   withpic.setDescription("幸运小朋友附加图片");
                   withpic.setPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/" + model.getPic3());
                   withpic.setOriginPath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/helppic/thumb_" + model.getPic3());
                   withpic.setUser(user);
                   if(model.getPicid3()!=null&&(!"".equals(model.getPicid3()))){
                	   getWithPictureDAO().update(withpic);
            	   }else{
            		   getWithPictureDAO().add(withpic);
            	   }
                  }
               
           }
           return helpchildren;
           }catch(Exception e){
               e.printStackTrace();
               return null;
           }
      }

    @Override
    public HelpChildrenVo getWebinfo(Long id) {
        try{
            HelpChildren helpchildren  = getHelpChildrenDao().get(id);
            HelpChildrenVo help = new HelpChildrenVo();
            help.setId(helpchildren.getId());
            help.setName(helpchildren.getName());
            help.setImg(helpchildren.getImg());
            help.setThumbnailImg(helpchildren.getThumbnailImg());
            help.setMale(helpchildren.getMale());
            help.setBirthday(helpchildren.getBirthday());
            help.setZodiac(helpchildren.getZodiac());  //属相
            help.setConstellation(helpchildren.getConstellation());//星座
            help.setNation(helpchildren.getNation());
            help.setOrigin(helpchildren.getOrigin());
            help.setAddress(helpchildren.getAddress());
            help.setIdCode(helpchildren.getIdCode());
            help.setParentMobile(helpchildren.getParentMobile());
            help.setParnetIdCode(helpchildren.getParnetIdCode());
//          helpchildren.setParentName(parentName);
            help.setCurrentStatus(helpchildren.getCurrentStatus());
            help.setWish(helpchildren.getWish());
            help.setSignificative(helpchildren.getSignificative());
            help.setDreamFund(helpchildren.getDreamFund());
            help.setSchool(helpchildren.getSchool());
            help.setUser(helpchildren.getUser());
            // 2.0新增
            help.setAlipayAccount(helpchildren.getAlipayAccount());
            help.setWeChatAccount(helpchildren.getWeChatAccount());
            help.setBankAccount(helpchildren.getBankAccount());
            help.setAccountName(helpchildren.getAccountName());

            help.setStatus(helpchildren.getStatus());
            List<WithPicture> list  = getWithPictureDAO().findByUser(helpchildren.getUser().getId());
             for(int i= 0 ;i<3;i++){
                 if(i==0&&list.size()>0){
                     help.setPic1(list.get(i).getOriginPath());
                     help.setPicid1(list.get(i).getId().toString());
                 }else if(i==1&&list.size()>1){
                     help.setPic2(list.get(i).getOriginPath());
                     help.setPicid2(list.get(i).getId().toString());
                 }else if(i==2&&list.size()>2){
                     help.setPic3(list.get(i).getOriginPath());
                     help.setPicid3(list.get(i).getId().toString());
                 }
             }
            return help;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
       }

    @Override
    public HelpChildren get(Long id) {
        return getHelpChildrenDao().get(id);
    }

    @Override
    public HelpChildren getByUserid(Long userid) {
        return getHelpChildrenDao().getByUserid(userid);
    }

}
