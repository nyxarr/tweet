package com.tweet.services;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools {
	public static JSONObject error(String message, int errorCode) {
		JSONObject errResult = new JSONObject();
		JSONObject error = new JSONObject();
		
		try {
			error.put("code", errorCode);
			error.put("message", message);
			errResult.put("error", error);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return errResult;
	}
}
