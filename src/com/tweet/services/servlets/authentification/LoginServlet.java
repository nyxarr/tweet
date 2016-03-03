package com.tweet.services.servlets.authentification;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.authentification.LoginService;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		JSONObject json = LoginService.loginUser(username, password);
		
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		out.println(json.toString());
	}
}
