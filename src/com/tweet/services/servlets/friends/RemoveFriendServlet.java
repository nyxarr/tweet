package com.tweet.services.servlets.friends;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.friends.RemoveFriendService;

public class RemoveFriendServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		JSONObject json = new JSONObject();
		
		String key = req.getParameter("key");
		String friend = req.getParameter("friend");
		
		RemoveFriendService.removeFriend(key, friend);
		
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		out.print(json);
	}
}
