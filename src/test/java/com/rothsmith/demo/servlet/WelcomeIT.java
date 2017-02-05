/*
 * (c) 2012 FCCI Insurance Group All Rights Reserved.
 */
package com.rothsmith.demo.servlet;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import com.thoughtworks.selenium.SeleneseTestCase;

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
public final class WelcomeIT {
	// extends SeleneseTestCase {

	/**
	 * SLF4J Logger for WelcomeIT.
	 */
	private static final Logger LOGGER =
	    LoggerFactory.getLogger(WelcomeIT.class);

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

	private WebDriver driver;

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

		// setUp(URL, browser);

		DesiredCapabilities capabilities =
		    DesiredCapabilities.internetExplorer();
		capabilities.setCapability(
		    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		    true);
		File file = new File(getClass().getClassLoader()
		    .getResource("IEDriverServer.exe").getFile());
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		capabilities.setCapability("requireWindowFocus", true);
		driver = new InternetExplorerDriver(capabilities);

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

		// selenium.open(URL);
		driver.navigate().to(URL);

		LOGGER.debug(driver.getPageSource());

		// selenium.click("link=User Information");
		WebElement findElement =
		    driver.findElement(By.linkText("User Information"));
		findElement.click();

		// selenium.waitForPageToLoad("30000");

		// selenium.type("j_username", "admin");
		// selenium.type("j_password", "admin123");
		// selenium.click("//input[@type='submit' and @value='Submit']");

		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement username = driver.findElement(By.name("j_username"));
		wait.until(ExpectedConditions.urlMatches(".*login.*"));
		username.sendKeys("admin");
		WebElement password = driver.findElement(By.name("j_password"));
		password.sendKeys("admin123");
		password.submit();

		// selenium.waitForPageToLoad("30000");
		// wait.until(ExpectedConditions.titleContains("User Information"));
		wait.until(ExpectedConditions.urlMatches(".*welcome.*"));

		LOGGER.debug(driver.getPageSource());

		// return selenium.isTextPresent("Welcome");
		return true;
	}

	// END SNIPPET: loginToWebApplication

	/**
	 * Test logging out.
	 * 
	 * @return true if successful, else false.
	 */
	private boolean logoutFromWebApplication() {
		// selenium.click("link=Logout");

		WebElement logoutLink = driver.findElement(By.linkText("Logout"));
		logoutLink.click();

		// selenium.waitForPageToLoad("10000");
		WebDriverWait wait = new WebDriverWait(driver, 5);

		wait.until(ExpectedConditions.urlMatches(".*/demo-webapp.*"));

		LOGGER.debug(driver.getPageSource());

		// return selenium.isTextPresent("User Info");
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@After
	@SuppressWarnings(value = "IJU_TEARDOWN_NO_SUPER", justification = "super."
	    + "teardown() steps on SeleneseTestCase initializaton")
	public void tearDown() {

		driver.close();

		// selenium.close();
		// selenium.stop();

	}

}