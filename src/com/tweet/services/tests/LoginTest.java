package com.tweet.services.tests;

import org.json.JSONObject;

import com.tweet.services.authentification.LoginService;

public class LoginTest {

	public static void main(String[] args) {
		JSONObject json = LoginService.loginUser("nyxar", "azerty");

		System.out.println(json.toString());
	}

}
