package com.tweet.services.servlets.friends;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tweet.services.friends.AddFriendService;

public class AddFriendServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		JSONObject json = new JSONObject();
		String key = req.getParameter("key");
		int friendId = Integer.parseInt(req.getParameter("friend"));
		
		AddFriendService.addFriend(key, friendId);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json.toString());
	}
}
