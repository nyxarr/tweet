package com.tweet.services.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.ServicesTools;
import com.tweet.services.account.AccountService;
import com.tweet.services.account.SaveAccountService;
import com.tweet.services.authentification.LoginService;

public class AccountTest {

	public static void main(String[] args) {
		try {
			JSONObject json = LoginService.loginUser("test1", "azerty");
			JSONObject retour = AccountService.getAccountInfo(json.getString("key"));
			JSONObject retourSave = SaveAccountService.saveAccountInfo(json.getString("key"), "email", "update@email.fr");
			
			System.out.println(retourSave.toString());
		} catch (JSONException e) {
			JSONObject error = ServicesTools.error(e.getMessage(), 0);
			System.out.println(error.toString());
		}
	}

}
