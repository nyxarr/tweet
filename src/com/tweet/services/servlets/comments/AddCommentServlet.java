package com.tweet.services.servlet.comments;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tweet.services.comments.AddCommentService;

public class AddCommentServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		String key = req.getParameter("key");
		String comment = req.getParameter("comment");
		
		AddCommentService.addComment(key, comment);
	}
}
