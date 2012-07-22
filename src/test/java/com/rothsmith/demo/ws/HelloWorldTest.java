/*
 * (c) 2012 FCCI Insurance Group All Rights Reserved.
 */
package com.rothsmith.demo.ws;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for HelloWorld class.
 * 
 * @version $Id: HelloWorldTest.java 78 2012-06-27 23:25:34Z drarch $
 * 
 * @author drothauser
 * 
 */
public class HelloWorldTest {

	/**
	 * Test method for {@link HelloWorld#execute(String)}.
	 */
	@Test
	public void testExecute() {

		HelloWorld helloWorld = new HelloWorld();
		String name = "Tom";
		String helloName = helloWorld.execute(name);
		assertEquals("Hello, " + name + "!", helloName);
	}

	/**
	 * Test method for {@link HelloWorld#execute(String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExecuteNull() {

		HelloWorld helloWorld = new HelloWorld();
		helloWorld.execute(null);

	}

}
