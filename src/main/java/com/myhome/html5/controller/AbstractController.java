package com.myhome.html5.controller;

import javax.annotation.Resource;

import com.myhome.service.IArtistInfoService;
import com.myhome.service.IArtistService;
import com.myhome.service.IAuthenticationService;
import com.myhome.service.IBaseDataDictionaryService;
import com.myhome.service.IBaseDataDictionaryTypeService;
import com.myhome.service.ICommentService;
import com.myhome.service.IEmailAuthenticationService;
import com.myhome.service.IGameService;
import com.myhome.service.IGameWorksItemService;
import com.myhome.service.IGenerousGiftsService;
import com.myhome.service.IGiftsExchangeService;
import com.myhome.service.IHaveToDecorateService;
import com.myhome.service.IHelpChildrenService;
import com.myhome.service.ILoginAuthenticationService;
import com.myhome.service.IMobileAuthenticationService;
import com.myhome.service.IMyBalconyService;
import com.myhome.service.IOpinionService;
import com.myhome.service.IPageService;
import com.myhome.service.IPermissionService;
import com.myhome.service.IPictureService;
import com.myhome.service.IPraiseService;
import com.myhome.service.IQQAuthenticationService;
import com.myhome.service.IRecommendHelpPlayTourService;
import com.myhome.service.IRegionService;
import com.myhome.service.IRolePermissionItemService;
import com.myhome.service.IRoleService;
import com.myhome.service.ISponsorsService;
import com.myhome.service.ITagService;
import com.myhome.service.ITeacherService;
import com.myhome.service.IUserArtistItemService;
import com.myhome.service.IUserInfoService;
import com.myhome.service.IUserPermissionItemService;
import com.myhome.service.IUserRoleItemService;
import com.myhome.service.IUserService;
import com.myhome.service.IVerificationCodeService;
import com.myhome.service.IVersionService;
import com.myhome.service.IVotingService;
import com.myhome.service.IWantBuyHouseService;
import com.myhome.service.IWantToDecorateService;
import com.myhome.service.IWeiboAuthenticationService;
import com.myhome.service.IWeixinAuthenticationService;
import com.myhome.service.IWithPictureService;
import com.myhome.service.IWorksPictureItemService;
import com.myhome.service.IWorksService;
import com.myhome.service.IWorksTagItemService;


abstract public class AbstractController {
	@Resource(name = "regionServiceImpl")
	private IRegionService regionService;

	@Resource(name = "tagServiceImpl")
	private ITagService tagService;

	@Resource(name = "userServiceImpl")
	private IUserService userService;

	@Resource(name = "userInfoServiceImpl")
	private IUserInfoService userInfoService;

	@Resource(name = "roleServiceImpl")
	private IRoleService roleService;

	@Resource(name = "permissionServiceImpl")
	private IPermissionService permissionService;

	@Resource(name = "rolePermissionItemServiceImpl")
	private IRolePermissionItemService rolePermissionItemService;

	@Resource(name = "userRoleItemServiceImpl")
	private IUserRoleItemService userRoleItemService;

	@Resource(name = "userPermissionItemServiceImpl")
	private IUserPermissionItemService userPermissionItemService;

	@Resource(name = "authenticationServiceImpl")
	private IAuthenticationService authenticationService;

	@Resource(name = "loginAuthenticationServiceImpl")
	private ILoginAuthenticationService loginAuthenticationService;

	@Resource(name = "emailAuthenticationServiceImpl")
	private IEmailAuthenticationService emailAuthenticationService;

	@Resource(name = "mobileAuthenticationServiceImpl")
	private IMobileAuthenticationService mobileAuthenticationService;

	@Resource(name = "qQAuthenticationServiceImpl")
	private IQQAuthenticationService qQAuthenticationService;

	@Resource(name = "weiboAuthenticationServiceImpl")
	private IWeiboAuthenticationService weiboAuthenticationService;

	@Resource(name = "weixinAuthenticationServiceImpl")
	private IWeixinAuthenticationService weixinAuthenticationService;

	@Resource(name = "artistServiceImpl")
	private IArtistService artistService;

	@Resource(name = "artistInfoServiceImpl")
	private IArtistInfoService artistInfoService;

	@Resource(name = "worksServiceImpl")
	private IWorksService worksService;

	@Resource(name = "votingServiceImpl")
	private IVotingService votingService;

	@Resource(name = "praiseServiceImpl")
	private IPraiseService praiseService;

	@Resource(name = "gameServiceImpl")
	private IGameService gameService;

	@Resource(name = "gameWorksItemServiceImpl")
	private IGameWorksItemService gameWorksItemService;

	@Resource(name = "worksPictureItemServiceImpl")
	private IWorksPictureItemService worksPictureItemService;

	@Resource(name = "worksTagItemServiceImpl")
	private IWorksTagItemService worksTagItemService;

	@Resource(name = "commentServiceImpl")
	private ICommentService commentService;

	@Resource(name = "userArtistItemServiceImpl")
	private IUserArtistItemService userArtistItemService;

	@Resource(name = "VerificationCodeServiceImpl")
	private IVerificationCodeService verificationCodeService;

	@Resource(name = "pictureServiceImpl")
	private IPictureService pictureService;

	@Resource(name = "versionServiceImpl")
	private IVersionService versionService;

	@Resource(name = "opinionServiceImpl")
	private IOpinionService opinionService;
	
	@Resource(name = "teacherServiceImpl")
	private ITeacherService teacherService;
	
	@Resource(name = "pageServiceImpl")
	private IPageService pageService;
	
	@Resource(name = "sponsorsServiceImpl")
	private ISponsorsService sponsorsService;
	
	@Resource(name = "helpChildrenServiceImpl")
	private IHelpChildrenService helpChildrenService;
	
	
	@Resource(name = "withPictureServiceImpl")
	private IWithPictureService withPictureService;
	
	
	@Resource(name = "wantBuyHouseServiceImpl")
	private IWantBuyHouseService wantBuyHouseService;
	
	@Resource(name = "generousGiftsServiceImpl")
	private IGenerousGiftsService generousGiftsService;
	
	@Resource(name = "wantToDecorateServiceImpl")
	private IWantToDecorateService wantToDecorateService;

	@Resource(name = "giftsExchangeServiceImpl")
	private IGiftsExchangeService giftsExchangeService;
	
	@Resource(name = "myBalconyServiceImpl")
	private IMyBalconyService myBalconyService;

	@Resource(name = "haveToDecorateServiceImpl")
	private IHaveToDecorateService haveToDecorateService;
	
	@Resource(name = "recommendChildrenServiceImpl")
	private IRecommendHelpPlayTourService recommendChildrenService;
	
	
	@Resource(name = "baseDataDictionaryServiceImpl")
	private IBaseDataDictionaryService baseDataDictionaryService;
	
	@Resource(name = "baseDataDictionaryTypeServiceImpl")
	private IBaseDataDictionaryTypeService baseDataDictionaryTypeService;
	public IRegionService getRegionService() {
		return this.regionService;
	}

	public void setRegionService(IRegionService regionService) {
		this.regionService = regionService;
	}

	public ITagService getTagService() {
		return this.tagService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public IUserService getUserService() {
		return this.userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserInfoService getUserInfoService() {
		return this.userInfoService;
	}

	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public IRoleService getRoleService() {
		return this.roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IPermissionService getPermissionService() {
		return this.permissionService;
	}

	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public IRolePermissionItemService getRolePermissionItemService() {
		return this.rolePermissionItemService;
	}

	public void setRolePermissionItemService(IRolePermissionItemService rolePermissionItemService) {
		this.rolePermissionItemService = rolePermissionItemService;
	}

	public IUserRoleItemService getUserRoleItemService() {
		return this.userRoleItemService;
	}

	public void setUserRoleItemService(IUserRoleItemService userRoleItemService) {
		this.userRoleItemService = userRoleItemService;
	}

	public IUserPermissionItemService getUserPermissionItemService() {
		return this.userPermissionItemService;
	}

	public void setUserPermissionItemService(IUserPermissionItemService userPermissionItemService) {
		this.userPermissionItemService = userPermissionItemService;
	}

	public IAuthenticationService getAuthenticationService() {
		return this.authenticationService;
	}

	public void setAuthenticationService(IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public ILoginAuthenticationService getLoginAuthenticationService() {
		return this.loginAuthenticationService;
	}

	public void setLoginAuthenticationService(ILoginAuthenticationService loginAuthenticationService) {
		this.loginAuthenticationService = loginAuthenticationService;
	}

	public IEmailAuthenticationService getEmailAuthenticationService() {
		return this.emailAuthenticationService;
	}

	public void setEmailAuthenticationService(IEmailAuthenticationService emailAuthenticationService) {
		this.emailAuthenticationService = emailAuthenticationService;
	}

	public IMobileAuthenticationService getMobileAuthenticationService() {
		return this.mobileAuthenticationService;
	}

	public void setMobileAuthenticationService(IMobileAuthenticationService mobileAuthenticationService) {
		this.mobileAuthenticationService = mobileAuthenticationService;
	}

	public IQQAuthenticationService getQQAuthenticationService() {
		return this.qQAuthenticationService;
	}

	public void setQQAuthenticationService(IQQAuthenticationService qQAuthenticationService) {
		this.qQAuthenticationService = qQAuthenticationService;
	}

	public IWeiboAuthenticationService getWeiboAuthenticationService() {
		return this.weiboAuthenticationService;
	}

	public void setWeiboAuthenticationService(IWeiboAuthenticationService weiboAuthenticationService) {
		this.weiboAuthenticationService = weiboAuthenticationService;
	}

	public IWeixinAuthenticationService getWeixinAuthenticationService() {
		return this.weixinAuthenticationService;
	}

	public void setWeixinAuthenticationService(IWeixinAuthenticationService weixinAuthenticationService) {
		this.weixinAuthenticationService = weixinAuthenticationService;
	}

	public IArtistService getArtistService() {
		return this.artistService;
	}

	public void setArtistService(IArtistService artistService) {
		this.artistService = artistService;
	}

	public IArtistInfoService getArtistInfoService() {
		return this.artistInfoService;
	}

	public void setArtistInfoService(IArtistInfoService artistInfoService) {
		this.artistInfoService = artistInfoService;
	}

	public IWorksService getWorksService() {
		return this.worksService;
	}

	public void setWorksService(IWorksService worksService) {
		this.worksService = worksService;
	}

	public IVotingService getVotingService() {
		return this.votingService;
	}

	public void setVotingService(IVotingService votingService) {
		this.votingService = votingService;
	}

	public IPraiseService getPraiseService() {
		return this.praiseService;
	}

	public void setPraiseService(IPraiseService praiseService) {
		this.praiseService = praiseService;
	}

	public IGameService getGameService() {
		return this.gameService;
	}

	public void setGameService(IGameService gameService) {
		this.gameService = gameService;
	}

	public IGameWorksItemService getGameWorksItemService() {
		return this.gameWorksItemService;
	}

	public void setGameWorksItemService(IGameWorksItemService gameWorksItemService) {
		this.gameWorksItemService = gameWorksItemService;
	}

	public IWorksPictureItemService getWorksPictureItemService() {
		return this.worksPictureItemService;
	}

	public void setWorksPictureItemService(IWorksPictureItemService worksPictureItemService) {
		this.worksPictureItemService = worksPictureItemService;
	}

	public IWorksTagItemService getWorksTagItemService() {
		return this.worksTagItemService;
	}

	public void setWorksTagItemService(IWorksTagItemService worksTagItemService) {
		this.worksTagItemService = worksTagItemService;
	}

	public ICommentService getCommentService() {
		return this.commentService;
	}

	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}

	public IUserArtistItemService getUserArtistItemService() {
		return this.userArtistItemService;
	}

	public void setUserArtistItemService(IUserArtistItemService userArtistItemService) {
		this.userArtistItemService = userArtistItemService;
	}

	public IQQAuthenticationService getqQAuthenticationService() {
		return qQAuthenticationService;
	}

	public void setqQAuthenticationService(IQQAuthenticationService qQAuthenticationService) {
		this.qQAuthenticationService = qQAuthenticationService;
	}

	public IVerificationCodeService getVerificationCodeService() {
		return verificationCodeService;
	}

	public void setVerificationCodeService(IVerificationCodeService verificationCodeService) {
		this.verificationCodeService = verificationCodeService;
	}

	public IPictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(IPictureService pictureService) {
		this.pictureService = pictureService;
	}

	public IVersionService getVersionService() {
		return versionService;
	}

	public void setVersionService(IVersionService versionService) {
		this.versionService = versionService;
	}

	public IOpinionService getOpinionService() {
		return opinionService;
	}

	public void setOpinionService(IOpinionService opinionService) {
		this.opinionService = opinionService;
	}

	public IPageService getPageService() {
		return pageService;
	}

	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}

	public ITeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public ISponsorsService getSponsorsService() {
		return sponsorsService;
	}

	public void setSponsorsService(ISponsorsService sponsorsService) {
		this.sponsorsService = sponsorsService;
	}

	public IHelpChildrenService getHelpChildrenService() {
		return helpChildrenService;
	}

	public void setHelpChildrenService(IHelpChildrenService helpChildrenService) {
		this.helpChildrenService = helpChildrenService;
	}

	public IWithPictureService getWithPictureService() {
		return withPictureService;
	}

	public void setWithPictureService(IWithPictureService withPictureService) {
		this.withPictureService = withPictureService;
	}

	public IWantBuyHouseService getWantBuyHouseService() {
		return wantBuyHouseService;
	}

	public void setWantBuyHouseService(IWantBuyHouseService wantBuyHouseService) {
		this.wantBuyHouseService = wantBuyHouseService;
	}

	public IGenerousGiftsService getGenerousGiftsService() {
		return generousGiftsService;
	}

	public void setGenerousGiftsService(IGenerousGiftsService generousGiftsService) {
		this.generousGiftsService = generousGiftsService;
	}

	public IWantToDecorateService getWantToDecorateService() {
		return wantToDecorateService;
	}

	public void setWantToDecorateService(
			IWantToDecorateService wantToDecorateService) {
		this.wantToDecorateService = wantToDecorateService;
	}

	public IGiftsExchangeService getGiftsExchangeService() {
		return giftsExchangeService;
	}

	public void setGiftsExchangeService(IGiftsExchangeService giftsExchangeService) {
		this.giftsExchangeService = giftsExchangeService;
	}

	public IMyBalconyService getMyBalconyService() {
		return myBalconyService;
	}

	public void setMyBalconyService(IMyBalconyService myBalconyService) {
		this.myBalconyService = myBalconyService;
	}

	public IHaveToDecorateService getHaveToDecorateService() {
		return haveToDecorateService;
	}

	public void setHaveToDecorateService(
			IHaveToDecorateService haveToDecorateService) {
		this.haveToDecorateService = haveToDecorateService;
	}

	public IRecommendHelpPlayTourService getRecommendChildrenService() {
		return recommendChildrenService;
	}

	public void setRecommendChildrenService(
			IRecommendHelpPlayTourService recommendChildrenService) {
		this.recommendChildrenService = recommendChildrenService;
	}

	public IBaseDataDictionaryService getBaseDataDictionaryService() {
		return baseDataDictionaryService;
	}

	public void setBaseDataDictionaryService(
			IBaseDataDictionaryService baseDataDictionaryService) {
		this.baseDataDictionaryService = baseDataDictionaryService;
	}

	public IBaseDataDictionaryTypeService getBaseDataDictionaryTypeService() {
		return baseDataDictionaryTypeService;
	}

	public void setBaseDataDictionaryTypeService(
			IBaseDataDictionaryTypeService baseDataDictionaryTypeService) {
		this.baseDataDictionaryTypeService = baseDataDictionaryTypeService;
	}
	
	

}

