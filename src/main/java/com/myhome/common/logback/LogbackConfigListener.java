/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2014-11-26
 */
package com.myhome.common.logback;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Logback 监听
 * 
 * @author Flouny.Caesar
 *
 */
public class LogbackConfigListener implements ServletContextListener {
	
	@Override
    public void contextInitialized(ServletContextEvent event) {
		LogbackWebConfigurer.initLogging(event.getServletContext());
	}
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
		// ...
	}
}