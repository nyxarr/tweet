package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.ServicesTools;
import com.tweet.services.authentification.LoginService;
import com.tweet.services.comments.AddCommentService;

public class CommentsTest {
	public static void main(String [] args) {
		try {
		JSONObject json = LoginService.loginUser("test1", "azerty");
		
		AddCommentService.addComment(json.getString("key"), "Test ajout commentaire");
		} catch (JSONException e) {
			ServicesTools.error(e.getMessage(), 0);
		}
	}
}
