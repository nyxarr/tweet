package com.tweet.services.servlets.authentification;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.authentification.RegisterService;

public class RegisterServlet extends HttpServlet {
	public final static long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String email = req.getParameter("email");
		JSONObject json = RegisterService.registerUser(username, password, lastname, firstname, email);
		
		PrintWriter out = resp.getWriter();
		out.println("<html><body><strong>" + json.toString() + "</strong></body></html>");
	}
}
