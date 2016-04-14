package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.ServicesTools;
import com.tweet.services.authentification.LoginService;
import com.tweet.services.messages.AddMessageService;
import com.tweet.services.messages.GetMessageService;

public class MessagesTest {

	public static void main(String[] args) {
		try {
			JSONObject json = LoginService.loginUser("test1", "azerty");
			AddMessageService.addMessage(json.getString("key"), "test", "Test ajout message");
			JSONObject retour = GetMessageService.getMessages(json.getString("key"));
			System.out.println(retour.toString());
		} catch (JSONException e) {
			JSONObject error = ServicesTools.error(e.getMessage(), 0);
			System.out.println(error.toString());
		}
	}

}
