/*
 * (c) 2011 FCCI Insurance Group All Rights Reserved.
 */
package com.rothsmith.demo.ws;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello World Web Service.
 * 
 * @version $Id: HelloWorldWs.java 79 2012-06-29 16:08:57Z drarch $
 * 
 * @author drothauser
 * 
 */
@WebService
// CHECKSTYLE:OFF
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
// CHECKSTYLE:ON
public class HelloWorldWs {

	/**
	 * 2 Seconds - 2000 milliseconds.
	 */
	private static final int TWO_SECONDS = 2000;

	/**
	 * SLF4J Logger for HelloWorldWs.
	 */
	private static final Logger LOGGER = LoggerFactory
	    .getLogger(HelloWorldWs.class);

	/**
	 * @param name
	 *            Name to say hello to
	 * @return Hello text
	 */
	public String sayHello(String name) {

		LOGGER.info("Going to say hello to: " + name);

		return (name == null) ? "Hello" : "Hello, " + name + "!";

	}

	/**
	 * Long running sayHello().
	 * 
	 * @param name
	 *            Name to say hello to
	 * @return Hello text
	 */
	public String sayHelloDelayed(String name) {

		LOGGER.info("Going to say hello to: " + name
		    + " but will take a while.");

		for (int i = 1; i < 5; i++) {
			LOGGER.info("sayHello is working...");
			try {
				Thread.sleep(TWO_SECONDS);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage());
			}
		}

		return (name == null) ? "Hello" : "Hello, " + name + "!";

	}

}
