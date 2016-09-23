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

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

/**
 * 
 * @author Flouny.Caesar
 *
 */
public abstract class LogbackWebConfigurer {
	
	/** Parameter specifying the location of the logback config file */
	public static final String CONFIG_LOCATION_PARAM = "logbackConfigLocation";
	
	public static void initLogging(ServletContext servletContext) {
		String location = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
		if (location != null) {
			servletContext.log("Initializing logback from [" + location + "]");
			
			try {
				LogbackConfigurer.initLogging(location);
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Invalid 'logbackConfigLocation' parameter: " + e.getMessage());
			}
		}
	}
}