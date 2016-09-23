package com.myhome.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;




/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project website-console
 * @author Wangfeng
 * @version 2.0
 * @date 2015年6月2日
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if(handlerMethod.getBean() instanceof Prepareable) {
			Prepareable currentAction = (Prepareable)handlerMethod.getBean();
			currentAction.prepare(request, response);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ResponseBody responseBodyAnnotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if(responseBodyAnnotation == null) {
			if(handlerMethod.getBean() instanceof Prepareable && modelAndView != null && modelAndView.getViewName() != null && !modelAndView.getViewName().startsWith("redirect")) {
				Prepareable currentAction = (Prepareable)handlerMethod.getBean();
				currentAction.postData(request, response, modelAndView);
			}
		}
//		if(handlerMethod.getBean() instanceof WebActionSupport) {
//			Object currentAction = handlerMethod.getBean(); 
//			for(Method method : currentAction.getClass().getMethods()) {
//				if(method.getName().startsWith("get")) {
//					Object value = method.invoke(currentAction);
//					String name = StringUtils.uncapitalize(method.getName().replaceFirst("get", ""));
//					modelAndView.addObject(name, value);
//				}
//			}
//		}
	}
	
	
}

