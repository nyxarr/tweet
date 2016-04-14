package com.tweet.services.servlets.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.account.AccountService;
import com.tweet.services.account.SaveAccountService;

public class SaveAccountServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String key = req.getParameter("key");
		String field = req.getParameter("field");
		String data = req.getParameter("data");
		
		JSONObject json = SaveAccountService.saveAccountInfo(key, field, data);
		
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		out.println(json.toString());
	}
}
