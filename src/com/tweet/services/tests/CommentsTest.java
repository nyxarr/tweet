package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.authentification.LoginService;
import com.tweet.services.comments.AddCommentService;

public class CommentsTest {
	public static void main(String [] args) {
		try {
		JSONObject json = LoginService.loginUser("test", "azerty");
		
		AddCommentService.addComment(json.getString("key"), "test comment");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
