package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.authentification.LoginService;
import com.tweet.services.friends.AddFriendService;

public class FriendsTest {
	public static void main(String [] args) {
		JSONObject json = LoginService.loginUser("test", "azerty");
		
		try {
			AddFriendService.addFriend(json.getString("key"), 3);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
