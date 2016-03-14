package com.tweet.services.servlets.friends;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.friends.GetFriendService;

public class GetFriendServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		String key = req.getParameter("key");
		JSONObject json = GetFriendService.getFriends(key);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json.toString());
	}
}
