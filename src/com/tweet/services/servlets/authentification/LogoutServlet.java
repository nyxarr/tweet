package com.tweet.services.servlets.authentification;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.authentification.LogoutService;

public class LogoutServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String key = req.getParameter("key");
		
		JSONObject json = LogoutService.logoutUser(key);
		
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		out.println(json);
	}
}