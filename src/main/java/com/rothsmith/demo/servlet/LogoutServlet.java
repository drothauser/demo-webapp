/*
 * (c) 2012 Rothsmith LLC All Rights Reserved.
 */
package com.rothsmith.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logout technique implemented through a Servlet 3.0. After logout, the
 * application flow is redirected to the application's home page.
 * 
 * @version $Id: LogoutServlet.java 78 2012-06-27 23:25:34Z drarch $
 * 
 * @author drothauser
 */
@WebServlet(name = "/LogoutServlet", urlPatterns = { "/logout" })
@ServletSecurity(@HttpConstraint(rolesAllowed = { "demo-user" }))
public final class LogoutServlet
        extends HttpServlet {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * SLF4J Logger for LogoutServlet.
	 */
	private static final Logger LOGGER = LoggerFactory
	    .getLogger(LogoutServlet.class);

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

		LOGGER.info("Logging out...");

		HttpSession session = request.getSession(false);
		if (session != null) {
			request.logout();
			removeCookies(request, response);
		}

		response.sendRedirect("");

	}

	/**
	 * This method removes all cookies from the session.
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param response
	 *            {@link HttpServletResponse}
	 */
	private void removeCookies(HttpServletRequest request,
	    HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setValue("");
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
