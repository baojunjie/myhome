package com.myhome.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.HelpChildren;
import com.myhome.entity.Sponsors;
import com.myhome.entity.Teacher;
import com.myhome.entity.User;
import com.myhome.service.IArtistService;
import com.myhome.service.IAuthenticationService;
import com.myhome.service.IUserService;
import com.myhome.utils.ReturnData;
import com.myhome.utils.SysConstants;
import com.myhome.utils.UtilMD5Encoder;

/**
 *  ctx url拦截器
 *  
 * @author lijy
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter {
    @Resource(name = "userServiceImpl")
    private IUserService           userService;
    @Resource(name = "artistServiceImpl")
    private IArtistService         artistService;
    @Resource(name = "authenticationServiceImpl")
    private IAuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Integer user_id = request.getSession().getAttribute(SysConstants.USER_ID) == null ? null
            : Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
        Artist artist = new Artist();
        if (user_id != null) {
            artist = artistService.getByUserid(user_id.toString());
            User user = userService.get(Long.valueOf(user_id.toString()));
            if (user.getUserInfo().getImg() != null) {
                request.getSession().setAttribute(SysConstants.USER_PATH,
                    user.getUserInfo().getImg());
            }
            if (user.getUserInfo().getType() != null) {
                request.getSession().setAttribute(SysConstants.USER_TYPE,
                    user.getUserInfo().getType());
            }
            if (artist != null) {
                request.getSession().setAttribute(SysConstants.ARTIST_ID, artist.getId());
            }
        }
        HandlerMethod hd = (HandlerMethod) handler;

        if (hd.getMethod().getName().contains("interceptor")) {//判断路径
            //拦截H5  ywz
            if ("h5".equals(request.getParameter("platform"))) {
                // 参数验证
                String login = request.getParameter("loginVerification");
                String password = request.getParameter("passwordVerification");

                Authentication authentication = new Authentication();
                // 根据用户名查询salt
                String salt = "";
                authentication.setLogin(login);
                if (authenticationService.getMobileAuthenticationByNameCount(authentication) == 0) {
                    response.sendRedirect(request.getContextPath()
                                          + "/H5/login/authentication/loginindex.do");
                    return false;
                }
                Authentication auth1 = authenticationService
                    .getMobileAuthenticationByName(authentication);
                salt = auth1.getSalt();
                Authentication au = new Authentication();
                au.setLogin(login);
                String pwdSalt = UtilMD5Encoder.encodePassword(password, salt);
                au.setPassword(pwdSalt);
                if (!auth1.getPassword().equals(pwdSalt)) {
                    response.sendRedirect(request.getContextPath()
                                          + "/H5/login/authentication/loginindex.do");
                    return false;
                }
                //                } else if (auth1.getUser().getId().equals(request.getParameter("userId"))) {
                //                    response.sendRedirect(request.getContextPath()
                //                                          + "/H5/login/authentication/loginindex.do");
                //                    return false;
                //
                //                }

            } else {
                if (user_id == null) {
                    response.sendRedirect(request.getContextPath()
                                          + "/login/authentication/loginindex.do");
                    return false;
                } else {
                    if (hd.getMethod().getName().equals("interceptorjoinindex") && artist != null
                        && artist.getId() != null) {
                        // 参赛拦截
                        response.sendRedirect(request.getContextPath()
                                              + "/artist/personal.do?artist=" + artist.getId());
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
