package com.tweet.services.tests;

import org.json.JSONObject;
import org.json.JSONException;

import com.tweet.services.authentification.LoginService;
import com.tweet.services.authentification.LogoutService;

public class LogoutTest {
	public static void main(String[] args) {
        JSONObject resp = LoginService.loginUser("test", "azerty");

        try {
            JSONObject logout =  LogoutService.logoutUser(resp.getString("key"));
            System.out.println(logout.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}
}