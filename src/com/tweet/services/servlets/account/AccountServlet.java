package com.tweet.services.servlets.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.account.AccountService;

public class AccountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String key = req.getParameter("key");
		
		JSONObject json = AccountService.getAccountInfo(key);
		
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		out.println(json.toString());
	}
}
