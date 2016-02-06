package com.tweet.services.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.RegisterService;

public class RegisterServlet extends HttpServlet {
	public final static long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		JSONObject json = RegisterService.registerUser(username, password, email);
	}
}
