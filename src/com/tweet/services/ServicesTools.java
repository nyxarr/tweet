package com.tweet.services;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools {
	public static int WRONG_ARG_ERROR = 0;
	public static int LOGIN_ERROR = 1;
	public static int JSON_ERROR = 2;
	
	public static int SQL_EXCEPTION = 100;
	public static int CLASS_NOT_FOUND_EXCEPTION = 101;
	public static int JSON_EXCEPTION = 102;
	public static int UNKNOWN_HOST_EXCEPTION = 103;

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
