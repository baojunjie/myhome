package com.myhome.entity;

/**
 * 赞助商
 * 
 * @author gwb
 * @version $Id: sponsors.java, v 0.1 2015年10月10日 下午1:52:37 gwb Exp $
 */
public class Sponsors extends AbstractEntity {

    private String logo;     
    private String thumbnailLogo;           //LOGO(缩略图)
    private String companyName;     //公司名称
    private String mobile;          //号码
    private String businessLicense; //营业执照
    private String tBusinessLicense; //营业执照(缩略图)
    private String openAccount;     //开户
    private String taxRegistration; //税务登记
    private String organizationCode;//组织编号
    private Region region;
    private String address;         //地址
    private User user;
    
    private String certificate;            //证书文件
    private String SCertificate;       //证书文件缩列图
    
    private Integer certificateType;       //证书文件类型
    
    private String token;//验证手机端上传是否成功
    
    
    /**
     * 2.0 新增
     * 
     * @return
     */
    
    private String administrativeLevel;//行政级别
    
    private String enterpriseProperty;//企业性质
    
    private String scale;//企业规模
    
    private String registeredFund;//注册资金
    
    private String legalPerson;//法人代表
    
    private String idCodePositive;//身份证正面
    
    private String idCodeBack;//身份证背面
    
    private String idCodeBackThum;//身份证背面缩略图
    
    private String idCodePositiveThum;//身份证正面缩略图
    
    private String linkman;//联系人
    
    private String email;//邮箱
    
    private String fax;//传真
    
    private String companyProfile;//公司简介
    
    private String enterpriseSort;//企业类别
    
    private String companyUrl;//企业网址
    
    
    
    
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getOpenAccount() {
        return openAccount;
    }

    public void setOpenAccount(String openAccount) {
        this.openAccount = openAccount;
    }

    public String getTaxRegistration() {
        return taxRegistration;
    }

    public void setTaxRegistration(String taxRegistration) {
        this.taxRegistration = taxRegistration;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getSCertificate() {
        return SCertificate;
    }

    public void setSCertificate(String sCertificate) {
        SCertificate = sCertificate;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

	public String getThumbnailLogo() {
		return thumbnailLogo;
	}

	public void setThumbnailLogo(String thumbnailLogo) {
		this.thumbnailLogo = thumbnailLogo;
	}

	public String gettBusinessLicense() {
		return tBusinessLicense;
	}

	public void settBusinessLicense(String tBusinessLicense) {
		this.tBusinessLicense = tBusinessLicense;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAdministrativeLevel() {
		return administrativeLevel;
	}

	public void setAdministrativeLevel(String administrativeLevel) {
		this.administrativeLevel = administrativeLevel;
	}

	public String getEnterpriseProperty() {
		return enterpriseProperty;
	}

	public void setEnterpriseProperty(String enterpriseProperty) {
		this.enterpriseProperty = enterpriseProperty;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getRegisteredFund() {
		return registeredFund;
	}

	public void setRegisteredFund(String registeredFund) {
		this.registeredFund = registeredFund;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getIdCodePositive() {
		return idCodePositive;
	}

	public void setIdCodePositive(String idCodePositive) {
		this.idCodePositive = idCodePositive;
	}

	public String getIdCodeBack() {
		return idCodeBack;
	}

	public void setIdCodeBack(String idCodeBack) {
		this.idCodeBack = idCodeBack;
	}

	public String getIdCodeBackThum() {
		return idCodeBackThum;
	}

	public void setIdCodeBackThum(String idCodeBackThum) {
		this.idCodeBackThum = idCodeBackThum;
	}

	public String getIdCodePositiveThum() {
		return idCodePositiveThum;
	}

	public void setIdCodePositiveThum(String idCodePositiveThum) {
		this.idCodePositiveThum = idCodePositiveThum;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	public String getEnterpriseSort() {
		return enterpriseSort;
	}

	public void setEnterpriseSort(String enterpriseSort) {
		this.enterpriseSort = enterpriseSort;
	}

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

}
