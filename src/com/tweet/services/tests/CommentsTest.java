package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.ServicesTools;
import com.tweet.services.authentification.LoginService;
import com.tweet.services.comments.AddCommentService;
import com.tweet.services.comments.GetCommentService;

public class CommentsTest {
	public static void main(String [] args) {
		try {
			JSONObject json = LoginService.loginUser("nyxar", "berber");
			//AddCommentService.addComment(json.getString("key"), "Test ajout commentaire");
			JSONObject retour = GetCommentService.getComments(1, json.getString("key"));
			System.out.println(retour.toString());
		} catch (JSONException e) {
			JSONObject error = ServicesTools.error(e.getMessage(), 0);
			System.out.println(error.toString());
		}
	}
}
