package com.myhome.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/** 
 *
 *
 * @Project: trade
 * @File: Prepareable.java 
 * @Date: 2014年2月10日 
 * @Author: 周强
 * @Copyright: 版权所有 (C) 2014 浙江中国轻纺城网络有限公司，并保留所有权利. 
 *
 * 注意：本内容仅限于浙江中国轻纺城网络有限公司内部传阅，禁止外泄以及用于其他的商业目的 
 */

public interface Prepareable {
	
	public void prepare(HttpServletRequest servletRequest, HttpServletResponse servletResponse);

	public void postData(HttpServletRequest servletRequest, HttpServletResponse servletResponse, ModelAndView modelAndView);
  
}

