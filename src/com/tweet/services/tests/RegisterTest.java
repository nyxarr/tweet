package com.tweet.services.tests;

import org.json.JSONObject;

import com.tweet.services.RegisterService;

public class RegisterTest {

	public static void main( String[] args ) {
		JSONObject json = RegisterService.registerUser("test3", "azerty", "test@test.fr");
		
		System.out.println(json.toString());
	}

}
