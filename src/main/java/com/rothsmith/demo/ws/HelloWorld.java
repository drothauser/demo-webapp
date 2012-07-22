/*
 * (c) 2012 Rothsmith LLC All Rights Reserved.
 */
package com.rothsmith.demo.ws;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cliche Hello World class.
 * 
 * @version $Id: HelloWorld.java 78 2012-06-27 23:25:34Z drarch $
 * 
 * @author drothauser
 * 
 */
public class HelloWorld {

	/**
	 * SLF4J Logger for HelloWorld.
	 */
	private static final Logger LOGGER = LoggerFactory
	    .getLogger(HelloWorld.class);

	/**
	 * Say hello to the specified name.
	 * 
	 * @param name
	 *            the name to say hello to.
	 * @return A string formatted as "Hello, " + name + "!".
	 */
	public String execute(String name) {

		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException(
			    "name parameter must be populated.");
		}

		LOGGER.info("Going to say hello to: " + name);

		return "Hello, " + name + "!";

	}

}
