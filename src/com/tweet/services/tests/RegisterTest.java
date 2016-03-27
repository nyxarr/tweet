package com.tweet.services.tests;

import org.json.JSONObject;

import com.tweet.services.authentification.RegisterService;

public class RegisterTest {

	public static void main( String[] args ) {
		JSONObject json = RegisterService.registerUser("test2", "azerty", "test", "test", "test2@test.fr");
		
		System.out.println(json.toString());
	}

}
