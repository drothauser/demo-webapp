/*
 * (c) 2012 FCCI Insurance Group All Rights Reserved.
 */
package com.rothsmith.demo.servlet;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.SeleneseTestCase;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

/**
 * Selenium integration test that tests logging in and logging out of the
 * welcome page. The application will be tested using the following browsers:
 * 
 * <ul>
 * <li>Internet Explorer</li>
 * <li>Firefox</li>
 * </ul>
 * 
 * @author drothauser
 * 
 */
@RunWith(Parameterized.class)
public final class WelcomeIT
        extends SeleneseTestCase {

	/**
	 * SLF4J Logger for WelcomeIT.
	 */
	private static final Logger LOGGER = LoggerFactory
	    .getLogger(WelcomeIT.class);

	/**
	 * Web container port number.
	 */
	private static final int PORT = 9292;

	/**
	 * Context root of web application.
	 */
	private static final String CONTEXT_ROOT = "/demo-webapp/";

	/**
	 * Base URL of application.
	 */
	private static final String URL = "http://localhost:" + PORT + CONTEXT_ROOT;

	/**
	 * Browser to use for the test.
	 */
	private final String browser;

	/**
	 * Collection of browser types to test with.
	 * 
	 * @return {@link Collection} of browser types.
	 */
	@Parameters
	public static Collection<Object[]> browsers() {
		// Object[][] data = new Object[][] { { "*iexplore" }, { "*firefox" } };
		Object[][] data = new Object[][] { { "*iexplore" } };
		return Arrays.asList(data);
	}

	/**
	 * Constructor used by each type to initial the browser to test with.
	 * 
	 * @param browser
	 *            The browser to run the test with.
	 */
	public WelcomeIT(String browser) {
		this.browser = browser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Before
	@SuppressWarnings(value = "IJU_SETUP_NO_SUPER", justification = "super."
	    + "setup() steps on SeleneseTestCase initializaton")
	public void setUp() throws Exception {

		setUp(URL, browser);
	}

	/**
	 * Test logging in and logging ut.
	 */
	@Test
	public void testLoginLogout() {

		assertTrue("Login test failed.", loginToWebApplication());

		assertTrue("Logout test failed", logoutFromWebApplication());

	}

	// START SNIPPET: loginToWebApplication
	/**
	 * Test logging in.
	 * 
	 * @return true if successful, else false.
	 */
	private boolean loginToWebApplication() {

		selenium.open(URL);

		LOGGER.debug(selenium.getHtmlSource());

		selenium.click("link=User Information");
		selenium.waitForPageToLoad("30000");
		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin123");
		selenium.click("//input[@type='submit' and @value='Submit']");
		selenium.waitForPageToLoad("30000");

		LOGGER.debug(selenium.getHtmlSource());

		return selenium.isTextPresent("Welcome");
	}

	// END SNIPPET: loginToWebApplication

	/**
	 * Test logging out.
	 * 
	 * @return true if successful, else false.
	 */
	private boolean logoutFromWebApplication() {
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("10000");

		LOGGER.debug(selenium.getHtmlSource());

		return selenium.isTextPresent("User Info");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@After
	@SuppressWarnings(value = "IJU_TEARDOWN_NO_SUPER", justification = "super."
	    + "teardown() steps on SeleneseTestCase initializaton")
	public void tearDown() throws Exception {

		selenium.close();
		selenium.stop();

		super.tearDown();

	}

}