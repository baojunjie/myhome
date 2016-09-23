package com.myhome.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.entity.Sponsors;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.vo.SponsorsVo;
import com.myhome.service.ISponsorsService;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.ImageUpload;
import com.myhome.utils.PicturePath;
import com.myhome.utils.SysConstants;
import com.myhome.utils.Tools;

@Component("sponsorsServiceImpl")
public class SponsorsServiceImpl extends AbstractServiceImpl implements ISponsorsService {

	@Override
	@Transactional
	public void updateMobileSponsors(Sponsors sponsors) throws Exception {

		boolean falg = super.getTeacherDao().getMobileToken(sponsors.getToken(), "t_sponsors");
		if (!falg) {
			return;
		}

		if (Tools.notEmpty(sponsors.getLogo())) {
			Map<String, String> path = ImageUpload.imageUpload(sponsors.getLogo(), PicturePath.comlogo);
			if (null == path || "".equals(path)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setLogo(path.get("orginimg"));
				sponsors.setThumbnailLogo(path.get("thumb_path"));

			}
		}

		if (Tools.notEmpty(sponsors.getBusinessLicense())) {
			Map<String, String> path3 = ImageUpload.imageUpload(sponsors.getBusinessLicense(), PicturePath.comlicence);
			if (null == path3 || "".equals(path3)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setBusinessLicense(path3.get("orginimg"));
				sponsors.settBusinessLicense(path3.get("thumb_path"));
			}
		}

		if (Tools.notEmpty(sponsors.getCertificate())) {
			Map<String, String> path1 = ImageUpload.imageUpload(sponsors.getCertificate(), PicturePath.comfile);
			if (null == path1 || "".equals(path1)) {

			} else {
				sponsors.setCertificate(path1.get("orginimg"));
				sponsors.setSCertificate(path1.get("thumb_path"));
			}
		}

		if (sponsors.getIdCodeBack() != null) {
			Map<String, String> path1 = ImageUpload.imageUpload(sponsors.getIdCodeBack(), PicturePath.comfile);
			if (null == path1 || "".equals(path1)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setIdCodeBack(path1.get("orginimg"));
				sponsors.setIdCodeBackThum(path1.get("thumb_path"));
			}
		}

		if (sponsors.getIdCodePositive() != null) {
			Map<String, String> path1 = ImageUpload.imageUpload(sponsors.getIdCodePositive(), PicturePath.comfile);
			if (null == path1 || "".equals(path1)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setIdCodePositive(path1.get("orginimg"));
				sponsors.setIdCodePositiveThum(path1.get("thumb_path"));
			}
		}

		super.getSponsorsDao().updateMobileSponsors(sponsors);

	}

	@Override
	public void deleteMobileHSponsors(Sponsors children) throws Exception {
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Sponsors getMobileSponsors(Sponsors children) throws Exception {
		return super.getSponsorsDao().getMobileSponsors(children);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Sponsors> getMobileSponsorsList(Sponsors children) throws Exception {
		return super.getSponsorsDao().getMobileSponsorsList(children);
	}

	@Override
	@Transactional
	public Sponsors addMobileSponsors(Sponsors sponsors) throws Exception {
		boolean falg = super.getTeacherDao().getMobileToken(sponsors.getToken(), "t_sponsors");
		if (!falg) {
			return sponsors;
		}

		if (sponsors.getLogo() != null) {
			Map<String, String> path = ImageUpload.imageUpload(sponsors.getLogo(), PicturePath.comlogo);
			if (null == path || "".equals(path)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setLogo(path.get("orginimg"));
				sponsors.setThumbnailLogo(path.get("thumb_path"));

			}
		}

		if (sponsors.getBusinessLicense() != null) {
			Map<String, String> path3 = ImageUpload.imageUpload(sponsors.getBusinessLicense(), PicturePath.comlicence);
			if (null == path3 || "".equals(path3)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setBusinessLicense(path3.get("orginimg"));
				sponsors.settBusinessLicense(path3.get("thumb_path"));
			}
		}

		if (sponsors.getCertificate() != null) {
			Map<String, String> path1 = ImageUpload.imageUpload(sponsors.getCertificate(), PicturePath.comfile);
			if (null == path1 || "".equals(path1)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setCertificate(path1.get("orginimg"));
				sponsors.setSCertificate(path1.get("thumb_path"));
			}
		}

		if (sponsors.getIdCodeBack() != null) {
			Map<String, String> path1 = ImageUpload.imageUpload(sponsors.getCertificate(), PicturePath.comfile);
			if (null == path1 || "".equals(path1)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setIdCodeBack(path1.get("orginimg"));
				sponsors.setIdCodeBackThum(path1.get("thumb_path"));
			}
		}

		if (sponsors.getIdCodePositive() != null) {
			Map<String, String> path1 = ImageUpload.imageUpload(sponsors.getCertificate(), PicturePath.comfile);
			if (null == path1 || "".equals(path1)) {
				throw new Exception("图片失败");
			} else {
				sponsors.setIdCodePositive(path1.get("orginimg"));
				sponsors.setIdCodePositiveThum(path1.get("thumb_path"));
			}
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setId(sponsors.getUser().getId());
		userInfo.setType("5");
		super.getUserDAO().updateUserMobile(userInfo);
		return super.getSponsorsDao().addMobileSponsors(sponsors);
	}
    @Override
    public SponsorsVo getWebinfo(Long id) {
        try{
            Sponsors sponsors = getSponsorsDao().get(id);
            SponsorsVo company = new SponsorsVo();
            company.setId(sponsors.getId());
            // logo
            company.setLogo(sponsors.getLogo());
            company.setThumbnailLogo(sponsors.getThumbnailLogo());
            // 企业名称
            company.setCompanyName(sponsors.getCompanyName());
            // 企业地址
            company.setAddress(sponsors.getAddress());
            // 行业类别
            company.setEnterpriseSort(sponsors.getEnterpriseSort());
            // 注册资金
            company.setRegisteredFund(sponsors.getRegisteredFund());
            // 法人代表
            company.setLegalPerson(sponsors.getLegalPerson());
            // 法人身份证(正面)
            company.setIdCodePositive(sponsors.getIdCodePositive());
            // 法人身份证(反面)
            company.setIdCodeBack(sponsors.getIdCodeBack());
            // 营业执照
            company.setBusinessLicense(sponsors.getBusinessLicense());
            company.settBusinessLicense(sponsors.gettBusinessLicense());
            // 证书文件类型
            company.setCertificateType(sponsors.getCertificateType());
            // 证书文件
            company.setCertificate(sponsors.getCertificate());
            company.setSCertificate(sponsors.getSCertificate());
            // 联系人
            company.setLinkman(sponsors.getLinkman());
            // 联系方式
            company.setMobile(sponsors.getMobile());
            // 邮箱
            company.setEmail(sponsors.getEmail());
            // 传真
            company.setFax(sponsors.getFax());
            // 公司网址
            company.setCompanyUrl(sponsors.getCompanyUrl());
            // 公司描述
            company.setCompanyProfile(sponsors.getCompanyProfile());
            // 企业规模
            company.setScale(sponsors.getScale());

            company.setUser(sponsors.getUser());
            company.setStatus(sponsors.getStatus());
            return company;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    @Transactional
    public Sponsors saveOrUpdate(Integer userid, SponsorsVo model,HttpServletRequest request) {
        String pattern = "yyy-MM-dd"; //首先定义时间格式
        SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
        User user = new User();
        user.setId(Long.valueOf(userid));
       try{
           Sponsors sponsors = new Sponsors();
           if(model.getId()==null){
               // LOGO
               sponsors.setLogo(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlogo/" + model.getLogo());
               // LOGO(缩略图)
               sponsors.setThumbnailLogo(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlogo/thumb_" + model.getLogo());
               // 企业名称
               sponsors.setCompanyName(model.getCompanyName());
               // 企业地址
               sponsors.setAddress(model.getAddress());
               // 企业类别
               sponsors.setEnterpriseSort(model.getEnterpriseSort());
               // 注册资金
               sponsors.setRegisteredFund(model.getRegisteredFund());
               // 法人代表
               sponsors.setLegalPerson(model.getLegalPerson());
               // 法人身份证(正面)
               sponsors.setIdCodePositive(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodePositive/" + model.getIdCodePositive());
               // 法人身份证(正面缩略图)
               sponsors.setIdCodePositiveThum(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodePositive/thumb_" + model.getIdCodePositiveThum());
               // 法人身份证(反面)
               sponsors.setIdCodeBack(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodeBack/" + model.getIdCodeBack());
               // 法人身份证(反面缩略图)
               sponsors.setIdCodeBackThum(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodeBack/thumb_" + model.getIdCodeBackThum());
               // 营业执照
               sponsors.setBusinessLicense(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlicence/" + model.getBusinessLicense());
               // 营业执照(缩略图)
               sponsors.settBusinessLicense(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlicence/thumb_" + model.getBusinessLicense());
               // 证书文件类型
               sponsors.setCertificateType(model.getCertificateType());
               // 证书文件
               sponsors.setCertificate(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comfile/" + model.getCertificate());
               // 证书文件(缩略图)
               sponsors.setSCertificate(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comfile/thumb_" + model.getCertificate());
               // 联系人
               sponsors.setLinkman(model.getLinkman());
               // 联系方式
               sponsors.setMobile(model.getMobile());
               // 邮箱
               sponsors.setEmail(model.getEmail());
               // 传真(选填)
               if(null!=model.getFax() && !"".equals(model.getFax())){
                   sponsors.setFax(model.getFax());
               }
               // 公司网址(选填)
               if(null!=model.getCompanyUrl() && !"".equals(model.getCompanyUrl())){
                    sponsors.setCompanyUrl(model.getCompanyUrl());
               }
               // 公司简介(选填)
               if(null!=model.getCompanyProfile() && !"".equals(model.getCompanyProfile())){
                   sponsors.setCompanyProfile(model.getCompanyProfile());
               }
               // 公司性质(选填)
               if(null!=model.getEnterpriseProperty() && !"".equals(model.getEnterpriseProperty())){
                   sponsors.setEnterpriseProperty(model.getEnterpriseProperty());
               }
               // 公司规模(选填)
               if(null!=model.getScale() && !"".equals(model.getScale())){
                   sponsors.setScale(model.getScale());
               }
               // 状态
               sponsors.setStatus(2);

               sponsors.setUser(user);
               getSponsorsDao().add(sponsors);
               
               //保存角色类型
               UserInfo  userinfo = getUserInfoDAO().get(Long.valueOf(userid));
               userinfo.setType("5");
               getUserInfoDAO().update(userinfo); 
               request.getSession().setAttribute(SysConstants.USER_TYPE, userinfo.getType());
           }else{
               sponsors = getSponsorsDao().get(model.getId());
               if(!"useless".equals(model.getLogo())){
                   // LOGO
                   sponsors.setLogo(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlogo/" + model.getLogo());
                   // LOGO(缩略图)
                   sponsors.setThumbnailLogo(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlogo/thumb_" + model.getLogo());
               }
               // 企业名称
               sponsors.setCompanyName(model.getCompanyName());
               // 企业地址
               sponsors.setAddress(model.getAddress());
               // 企业类别
               sponsors.setEnterpriseSort(model.getEnterpriseSort());
               // 注册资金
               sponsors.setRegisteredFund(model.getRegisteredFund());
               // 法人代表
               sponsors.setLegalPerson(model.getLegalPerson());
               if(!"useless".equals(model.getIdCodePositive())){
                   // 法人身份证(正面)
                   sponsors.setIdCodePositive(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodePositive/" + model.getIdCodePositive());
                   // 法人身份证(正面缩略图)
                   sponsors.setIdCodePositiveThum(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodePositive/thumb_" + model.getIdCodePositiveThum());
               }
               if(!"useless".equals(model.getIdCodeBack())){
                   // 法人身份证(反面)
                   sponsors.setIdCodeBack(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodeBack/" + model.getIdCodeBack());
                   // 法人身份证(反面缩略图)
                   sponsors.setIdCodeBackThum(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comidCodeBack/thumb_" + model.getIdCodeBackThum());
               }

               if(!"useless".equals(model.getBusinessLicense())){
                   // 营业执照
                   sponsors.setBusinessLicense(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlicence/" + model.getBusinessLicense());
                   // 营业执照(缩略图)
                   sponsors.settBusinessLicense(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comlicence/thumb_" + model.getBusinessLicense());
               }
               // 证书文件类型
               sponsors.setCertificateType(model.getCertificateType());
               if(!"useless".equals(model.getCertificate())){
                   // 证书文件
                   sponsors.setCertificate(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comfile/" + model.getCertificate());
                   // 证书文件(缩略图)
                   sponsors.setSCertificate(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/comfile/thumb_" + model.getCertificate());
               }
               // 联系人
               sponsors.setLinkman(model.getLinkman());
               // 联系方式
               sponsors.setMobile(model.getMobile());
               // 邮箱
               sponsors.setEmail(model.getEmail());
               // 传真(选填)
               if(null!=model.getFax() && !"".equals(model.getFax())){
                   sponsors.setFax(model.getFax());
               }
               // 公司网址(选填)
               if(null!=model.getCompanyUrl() && !"".equals(model.getCompanyUrl())){
                    sponsors.setCompanyUrl(model.getCompanyUrl());
               }
               // 公司简介(选填)
               if(null!=model.getCompanyProfile() && !"".equals(model.getCompanyProfile())){
                   sponsors.setCompanyProfile(model.getCompanyProfile());
               }
               // 公司性质(选填)
               if(null!=model.getEnterpriseProperty() && !"".equals(model.getEnterpriseProperty())){
                   sponsors.setEnterpriseProperty(model.getEnterpriseProperty());
               }
               // 公司规模(选填)
               if(null!=model.getScale() && !"".equals(model.getScale())){
                   sponsors.setScale(model.getScale());
               }
               // 状态2
               sponsors.setStatus(2);
               getSponsorsDao().update(sponsors);
           }
           return sponsors;
           }catch(Exception e){
               e.printStackTrace();
               return null;
           }
      
    }
	@Override
	public Sponsors get(Long id) {
		return getSponsorsDao().get(id);
	}

	@Override
	public Sponsors getByUserid(Long userid) {
		return getSponsorsDao().getByUserid(userid);
	}

}
