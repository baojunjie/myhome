package com.myhome.service.impl;

import javax.annotation.Resource;

import com.myhome.dao.IActivityDAO;
import com.myhome.dao.IArtistDAO;
import com.myhome.dao.IArtistInfoDAO;
import com.myhome.dao.IAuthenticationDAO;
import com.myhome.dao.IBaseDataDictionaryDAO;
import com.myhome.dao.IBaseDataDictionaryTypeDAO;
import com.myhome.dao.ICommentDAO;
import com.myhome.dao.IEmailAuthenticationDAO;
import com.myhome.dao.IGameDAO;
import com.myhome.dao.IGameWorksItemDAO;
import com.myhome.dao.IGenerousGiftsDAO;
import com.myhome.dao.IGiftsExchangeDAO;
import com.myhome.dao.IHaveToDecorateDAO;
import com.myhome.dao.IHelpChildrenDao;
import com.myhome.dao.ILoginAuthenticationDAO;
import com.myhome.dao.IMobileAuthenticationDAO;
import com.myhome.dao.IMyBalconyDAO;
import com.myhome.dao.IOpinionDao;
import com.myhome.dao.IPageDAO;
import com.myhome.dao.IParameterDAO;
import com.myhome.dao.IPermissionDAO;
import com.myhome.dao.IPictureDAO;
import com.myhome.dao.IPraiseDAO;
import com.myhome.dao.IQQAuthenticationDAO;
import com.myhome.dao.IRecommendHelpPlayTourDAO;
import com.myhome.dao.IRegionDAO;
import com.myhome.dao.IRoleDAO;
import com.myhome.dao.IRolePermissionItemDAO;
import com.myhome.dao.ISponsorsDao;
import com.myhome.dao.ITagDAO;
import com.myhome.dao.ITeacherDao;
import com.myhome.dao.IUserArtistItemDAO;
import com.myhome.dao.IUserDAO;
import com.myhome.dao.IUserInfoDAO;
import com.myhome.dao.IUserPermissionItemDAO;
import com.myhome.dao.IUserRoleItemDAO;
import com.myhome.dao.IVersionDao;
import com.myhome.dao.IVotingDAO;
import com.myhome.dao.IWantBuyHouseDAO;
import com.myhome.dao.IWantToDecorateDAO;
import com.myhome.dao.IWeiboAuthenticationDAO;
import com.myhome.dao.IWeixinAuthenticationDAO;
import com.myhome.dao.IWithPictureDAO;
import com.myhome.dao.IWorksDAO;
import com.myhome.dao.IWorksPictureItemDAO;
import com.myhome.dao.IWorksTagItemDAO;
import com.myhome.service.IActivityService;
import com.myhome.service.IService;



abstract public class AbstractServiceImpl 
        implements IService {

    @Resource(name="pageDAOHibernateImpl")
    private IPageDAO pageDAO;
    
    @Resource(name="regionDAOHibernateImpl")
    private IRegionDAO regionDAO;
    
    @Resource(name="tagDAOHibernateImpl")
    private ITagDAO tagDAO;
    
    @Resource(name="userDAOHibernateImpl")
    private IUserDAO userDAO;
    
    @Resource(name="userInfoDAOHibernateImpl")
    private IUserInfoDAO userInfoDAO;
    
    @Resource(name="roleDAOHibernateImpl")
    private IRoleDAO roleDAO;
    
    @Resource(name="permissionDAOHibernateImpl")
    private IPermissionDAO permissionDAO;
    
    @Resource(name="rolePermissionItemDAOHibernateImpl")
    private IRolePermissionItemDAO rolePermissionItemDAO;
    
    @Resource(name="userRoleItemDAOHibernateImpl")
    private IUserRoleItemDAO userRoleItemDAO;
    
    @Resource(name="userPermissionItemDAOHibernateImpl")
    private IUserPermissionItemDAO userPermissionItemDAO;
    
    @Resource(name="authenticationDAOHibernateImpl")
    private IAuthenticationDAO authenticationDAO;
    
    @Resource(name="loginAuthenticationDAOHibernateImpl")
    private ILoginAuthenticationDAO loginAuthenticationDAO;
    
    @Resource(name="emailAuthenticationDAOHibernateImpl")
    private IEmailAuthenticationDAO emailAuthenticationDAO;
    
    @Resource(name="mobileAuthenticationDAOHibernateImpl")
    private IMobileAuthenticationDAO mobileAuthenticationDAO;
    
    @Resource(name="QQAuthenticationDAOHibernateImpl")
    private IQQAuthenticationDAO QQAuthenticationDAO;
    
    @Resource(name="weiboAuthenticationDAOHibernateImpl")
    private IWeiboAuthenticationDAO weiboAuthenticationDAO;
    
    @Resource(name="weixinAuthenticationDAOHibernateImpl")
    private IWeixinAuthenticationDAO weixinAuthenticationDAO;
    
    @Resource(name="artistDAOHibernateImpl")
    private IArtistDAO artistDAO;
    
    @Resource(name="artistInfoDAOHibernateImpl")
    private IArtistInfoDAO artistInfoDAO;
    
    @Resource(name="worksDAOHibernateImpl")
    private IWorksDAO worksDAO;
    
    @Resource(name="votingDAOHibernateImpl")
    private IVotingDAO votingDAO;
    
    @Resource(name="praiseDAOHibernateImpl")
    private IPraiseDAO praiseDAO;
    
    @Resource(name="gameDAOHibernateImpl")
    private IGameDAO gameDAO;
    
    @Resource(name="gameWorksItemDAOHibernateImpl")
    private IGameWorksItemDAO gameWorksItemDAO;
    
    @Resource(name="worksPictureItemDAOHibernateImpl")
    private IWorksPictureItemDAO worksPictureItemDAO;
    
    @Resource(name="worksTagItemDAOHibernateImpl")
    private IWorksTagItemDAO worksTagItemDAO;
    
    @Resource(name="commentDAOHibernateImpl")
    private ICommentDAO commentDAO;
    
    @Resource(name="userArtistItemDAOHibernateImpl")
    private IUserArtistItemDAO userArtistItemDAO;
    
    @Resource(name="pictureDAOHibernateImpl")
    private  IPictureDAO pictureDAO ;
    
    
    
    @Resource(name="opinionDaoImpl")
    private  IOpinionDao opinionDao ;
    
    
    @Resource(name="versionDaoImpl")
    private  IVersionDao versionDao ;
    
    @Resource(name="teacherDAOHibernateImpl")
    private  ITeacherDao teacherDao ;
    
    @Resource(name="helpChildrenDAOHibernateImpl")
    private  IHelpChildrenDao helpChildrenDao ;
    
    @Resource(name="sponsorsDAOHibernateImpl")
    private  ISponsorsDao sponsorsDao ;
    
    
    @Resource(name="withPictureDAOHibernateImpl")
    private  IWithPictureDAO withPictureDAO ;
    
    @Resource(name="wantToDecorateDAOHibernateImpl")
    private  IWantToDecorateDAO wantToDecorateDAO ;
    
    @Resource(name="wantBuyHouseDAOHibernateImpl")
    private  IWantBuyHouseDAO wantBuyHouseDAO ;
    
    @Resource(name="generousGiftsDAOHibernateImpl")
    private  IGenerousGiftsDAO generousGiftsDAO ;
    
    
    @Resource(name="recommendChildrenDAOHibernateImpl")
    private  IRecommendHelpPlayTourDAO recommendChildrenDAO ;
    
    @Resource(name="giftsExchangeDAOHibernateImpl")
    private  IGiftsExchangeDAO giftsExchangeDAO ;
    
    @Resource(name="haveToDecorateDAOHibernateImpl")
    private  IHaveToDecorateDAO haveToDecorateDAO ;
    
    @Resource(name="myBalconyDAOHibernateImpl")
    private  IMyBalconyDAO myBalconyDAO ;
    
    
    
    @Resource(name="baseDataDictionaryDAOHibernateImpl")
    private  IBaseDataDictionaryDAO baseDataDictionaryDAO ;
    
    @Resource(name="baseDataDictionaryTypeDAOHibernateImpl")
    private  IBaseDataDictionaryTypeDAO baseDataDictionaryTypeDAO ;
    
    @Resource(name = "activityDAOHibernateImpl")
    private IActivityDAO               activityDAO;

    @Resource(name = "parameterDAOHibernateImpl")
    private IParameterDAO               parameterDAO;

    public IPageDAO getPageDAO() {
        return this.pageDAO;
    }
    
    public void setPageDAO(IPageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }
    
    public IRegionDAO getRegionDAO() {
        return this.regionDAO;
    }
    
    public void setRegionDAO(IRegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }
    
    public ITagDAO getTagDAO() {
        return this.tagDAO;
    }
    
    public void setTagDAO(ITagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }
    
    public IUserDAO getUserDAO() {
        return this.userDAO;
    }
    
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public IUserInfoDAO getUserInfoDAO() {
        return this.userInfoDAO;
    }
    
    public void setUserInfoDAO(IUserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }
    
    public IRoleDAO getRoleDAO() {
        return this.roleDAO;
    }
    
    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
    
    public IPermissionDAO getPermissionDAO() {
        return this.permissionDAO;
    }
    
    public void setPermissionDAO(IPermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }
    
    public IRolePermissionItemDAO getRolePermissionItemDAO() {
        return this.rolePermissionItemDAO;
    }
    
    public void setRolePermissionItemDAO(IRolePermissionItemDAO rolePermissionItemDAO) {
        this.rolePermissionItemDAO = rolePermissionItemDAO;
    }
    
    public IUserRoleItemDAO getUserRoleItemDAO() {
        return this.userRoleItemDAO;
    }
    
    public void setUserRoleItemDAO(IUserRoleItemDAO userRoleItemDAO) {
        this.userRoleItemDAO = userRoleItemDAO;
    }
    
    public IUserPermissionItemDAO getUserPermissionItemDAO() {
        return this.userPermissionItemDAO;
    }
    
    public void setUserPermissionItemDAO(IUserPermissionItemDAO userPermissionItemDAO) {
        this.userPermissionItemDAO = userPermissionItemDAO;
    }
    
    public IAuthenticationDAO getAuthenticationDAO() {
        return this.authenticationDAO;
    }
    
    public void setAuthenticationDAO(IAuthenticationDAO authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
    }
    
    public ILoginAuthenticationDAO getLoginAuthenticationDAO() {
        return this.loginAuthenticationDAO;
    }
    
    public void setLoginAuthenticationDAO(ILoginAuthenticationDAO loginAuthenticationDAO) {
        this.loginAuthenticationDAO = loginAuthenticationDAO;
    }
    
    public IEmailAuthenticationDAO getEmailAuthenticationDAO() {
        return this.emailAuthenticationDAO;
    }
    
    public void setEmailAuthenticationDAO(IEmailAuthenticationDAO emailAuthenticationDAO) {
        this.emailAuthenticationDAO = emailAuthenticationDAO;
    }
    
    public IMobileAuthenticationDAO getMobileAuthenticationDAO() {
        return this.mobileAuthenticationDAO;
    }
    
    public void setMobileAuthenticationDAO(IMobileAuthenticationDAO mobileAuthenticationDAO) {
        this.mobileAuthenticationDAO = mobileAuthenticationDAO;
    }
    
    public IQQAuthenticationDAO getQQAuthenticationDAO() {
        return this.QQAuthenticationDAO;
    }
    
    public void setQQAuthenticationDAO(IQQAuthenticationDAO QQAuthenticationDAO) {
        this.QQAuthenticationDAO = QQAuthenticationDAO;
    }
    
    public IWeiboAuthenticationDAO getWeiboAuthenticationDAO() {
        return this.weiboAuthenticationDAO;
    }
    
    public void setWeiboAuthenticationDAO(IWeiboAuthenticationDAO weiboAuthenticationDAO) {
        this.weiboAuthenticationDAO = weiboAuthenticationDAO;
    }
    
    public IWeixinAuthenticationDAO getWeixinAuthenticationDAO() {
        return this.weixinAuthenticationDAO;
    }
    
    public void setWeixinAuthenticationDAO(IWeixinAuthenticationDAO weixinAuthenticationDAO) {
        this.weixinAuthenticationDAO = weixinAuthenticationDAO;
    }
    
    public IArtistDAO getArtistDAO() {
        return this.artistDAO;
    }
    
    public void setArtistDAO(IArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }
    
    public IArtistInfoDAO getArtistInfoDAO() {
        return this.artistInfoDAO;
    }
    
    public void setArtistInfoDAO(IArtistInfoDAO artistInfoDAO) {
        this.artistInfoDAO = artistInfoDAO;
    }
    
    public IWorksDAO getWorksDAO() {
        return this.worksDAO;
    }
    
    public void setWorksDAO(IWorksDAO worksDAO) {
        this.worksDAO = worksDAO;
    }
    
    public IVotingDAO getVotingDAO() {
        return this.votingDAO;
    }
    
    public void setVotingDAO(IVotingDAO votingDAO) {
        this.votingDAO = votingDAO;
    }
    
    public IPraiseDAO getPraiseDAO() {
        return this.praiseDAO;
    }
    
    public void setPraiseDAO(IPraiseDAO praiseDAO) {
        this.praiseDAO = praiseDAO;
    }
    
    public IGameDAO getGameDAO() {
        return this.gameDAO;
    }
    
    public void setGameDAO(IGameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }
    
    public IGameWorksItemDAO getGameWorksItemDAO() {
        return this.gameWorksItemDAO;
    }
    
    public void setGameWorksItemDAO(IGameWorksItemDAO gameWorksItemDAO) {
        this.gameWorksItemDAO = gameWorksItemDAO;
    }
    
    public IWorksPictureItemDAO getWorksPictureItemDAO() {
        return this.worksPictureItemDAO;
    }
    
    public void setWorksPictureItemDAO(IWorksPictureItemDAO worksPictureItemDAO) {
        this.worksPictureItemDAO = worksPictureItemDAO;
    }
    
    public IWorksTagItemDAO getWorksTagItemDAO() {
        return this.worksTagItemDAO;
    }
    
    public void setWorksTagItemDAO(IWorksTagItemDAO worksTagItemDAO) {
        this.worksTagItemDAO = worksTagItemDAO;
    }
    
    public ICommentDAO getCommentDAO() {
        return this.commentDAO;
    }
    
    public void setCommentDAO(ICommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
    
    public IUserArtistItemDAO getUserArtistItemDAO() {
        return this.userArtistItemDAO;
    }
    
    public void setUserArtistItemDAO(IUserArtistItemDAO userArtistItemDAO) {
        this.userArtistItemDAO = userArtistItemDAO;
    }

	public IPictureDAO getPictureDAO() {
		return pictureDAO;
	}

	public void setPictureDAO(IPictureDAO pictureDAO) {
		this.pictureDAO = pictureDAO;
	}

	public IOpinionDao getOpinionDao() {
		return opinionDao;
	}

	public void setOpinionDao(IOpinionDao opinionDao) {
		this.opinionDao = opinionDao;
	}

	public IVersionDao getVersionDao() {
		return versionDao;
	}

	public void setVersionDao(IVersionDao versionDao) {
		this.versionDao = versionDao;
	}

	public ITeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public IHelpChildrenDao getHelpChildrenDao() {
		return helpChildrenDao;
	}

	public void setHelpChildrenDao(IHelpChildrenDao helpChildrenDao) {
		this.helpChildrenDao = helpChildrenDao;
	}

	public ISponsorsDao getSponsorsDao() {
		return sponsorsDao;
	}

	public void setSponsorsDao(ISponsorsDao sponsorsDao) {
		this.sponsorsDao = sponsorsDao;
	}

	public IWithPictureDAO getWithPictureDAO() {
		return withPictureDAO;
	}

	public void setWithPictureDAO(IWithPictureDAO withPictureDAO) {
		this.withPictureDAO = withPictureDAO;
	}

	public IWantToDecorateDAO getWantToDecorateDAO() {
		return wantToDecorateDAO;
	}

	public void setWantToDecorateDAO(IWantToDecorateDAO wantToDecorateDAO) {
		this.wantToDecorateDAO = wantToDecorateDAO;
	}

	public IWantBuyHouseDAO getWantBuyHouseDAO() {
		return wantBuyHouseDAO;
	}

	public void setWantBuyHouseDAO(IWantBuyHouseDAO wantBuyHouseDAO) {
		this.wantBuyHouseDAO = wantBuyHouseDAO;
	}

	public IGenerousGiftsDAO getGenerousGiftsDAO() {
		return generousGiftsDAO;
	}

	public void setGenerousGiftsDAO(IGenerousGiftsDAO generousGiftsDAO) {
		this.generousGiftsDAO = generousGiftsDAO;
	}

	public IRecommendHelpPlayTourDAO getRecommendChildrenDAO() {
		return recommendChildrenDAO;
	}

	public void setRecommendChildrenDAO(IRecommendHelpPlayTourDAO recommendChildrenDAO) {
		this.recommendChildrenDAO = recommendChildrenDAO;
	}

	public IGiftsExchangeDAO getGiftsExchangeDAO() {
		return giftsExchangeDAO;
	}

	public void setGiftsExchangeDAO(IGiftsExchangeDAO giftsExchangeDAO) {
		this.giftsExchangeDAO = giftsExchangeDAO;
	}

	public IHaveToDecorateDAO getHaveToDecorateDAO() {
		return haveToDecorateDAO;
	}

	public void setHaveToDecorateDAO(IHaveToDecorateDAO haveToDecorateDAO) {
		this.haveToDecorateDAO = haveToDecorateDAO;
	}

	public IMyBalconyDAO getMyBalconyDAO() {
		return myBalconyDAO;
	}

	public void setMyBalconyDAO(IMyBalconyDAO myBalconyDAO) {
		this.myBalconyDAO = myBalconyDAO;
	}

	public IBaseDataDictionaryDAO getBaseDataDictionaryDAO() {
		return baseDataDictionaryDAO;
	}

	public void setBaseDataDictionaryDAO(
			IBaseDataDictionaryDAO baseDataDictionaryDAO) {
		this.baseDataDictionaryDAO = baseDataDictionaryDAO;
	}

	public IBaseDataDictionaryTypeDAO getBaseDataDictionaryTypeDAO() {
		return baseDataDictionaryTypeDAO;
	}

	public void setBaseDataDictionaryTypeDAO(
			IBaseDataDictionaryTypeDAO baseDataDictionaryTypeDAO) {
		this.baseDataDictionaryTypeDAO = baseDataDictionaryTypeDAO;
	}

    public IActivityDAO getActivityDAO() {
        return activityDAO;
    }

    public void setActivityDAO(IActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }

    public IParameterDAO getParameterDAO() {
        return parameterDAO;
    }

    public void setParameterDAO(IParameterDAO parameterDAO) {
        this.parameterDAO = parameterDAO;
    }






}

