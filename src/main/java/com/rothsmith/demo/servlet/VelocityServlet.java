package com.rothsmith.demo.servlet;

import javax.servlet.annotation.WebServlet;

import org.apache.velocity.tools.view.VelocityLayoutServlet;

/**
 * Servlet implementation class VelocityServlet. This servlet uses annotations
 * to specify the URL patterns that it will service.
 * 
 * @author drothauser
 */
@WebServlet(urlPatterns = { "/index.vm", "/login.vm" })
public class VelocityServlet
        extends VelocityLayoutServlet {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -5379486912953912541L;

	/**
	 * Default constructor.
	 */
	@SuppressWarnings("PMD.UnnecessaryConstructor")
	public VelocityServlet() {
		// For future use.
	}

}
