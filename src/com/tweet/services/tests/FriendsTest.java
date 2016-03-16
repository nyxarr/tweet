package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.authentification.LoginService;
import com.tweet.services.friends.AddFriendService;
import com.tweet.services.friends.GetFriendService;
import com.tweet.services.friends.RemoveFriendService;

public class FriendsTest {
	public static void main(String [] args) {
		JSONObject json = LoginService.loginUser("test", "azerty");
		
		try {
			//JSONObject friends = RemoveFriendService.removeFriend(json.getString("key"), "boulbix75");
			AddFriendService.addFriend(json.getString("key"), 4);
			//System.out.println(friends.get("friends"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
