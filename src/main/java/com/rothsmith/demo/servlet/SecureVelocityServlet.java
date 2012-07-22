package com.rothsmith.demo.servlet;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;

import org.apache.velocity.tools.view.VelocityLayoutServlet;

/**
 * Servlet implementation class SecureVelocityServlet. This servlet uses
 * annotations to specify the URL patterns that it will service. It also
 * provides security constraints to control who can access the specified URLs.
 * 
 * @author drothauser
 */
@WebServlet(urlPatterns = { "/welcome.vm" })
@ServletSecurity(@HttpConstraint(rolesAllowed = { "demo-user" }))
public class SecureVelocityServlet
        extends VelocityLayoutServlet {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -5379486912953912541L;

	/**
	 * Default constructor.
	 */
	@SuppressWarnings("PMD.UnnecessaryConstructor")
	public SecureVelocityServlet() {
		// For future use.
	}

}
