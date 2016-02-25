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
		JSONObject retour = AddCommentService.addComment(json.getString("key"), "Test ajout commentaire");
		System.out.println(retour.toString());
		} catch (JSONException e) {
			JSONObject error = ServicesTools.error(e.getMessage(), 0);
			System.out.println(error.toString());
		}
	}
}
