package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.authentification.LoginService;
import com.tweet.services.friends.AddFriendService;
import com.tweet.services.friends.GetFriendService;
import com.tweet.services.friends.RemoveFriendService;

public class FriendsTest {
	public static void main(String [] args) {
		JSONObject json = LoginService.loginUser("test1", "azerty");
		
		try {
			AddFriendService.addFriend(json.getString("key"), "test2");
			JSONObject data = GetFriendService.getFriends(json.getString("key"));
			System.out.println(data.get("friends"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
