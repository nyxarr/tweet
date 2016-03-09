package com.tweet.services.authentification;

import java.sql.SQLException;

import javax.servlet.http.Cookie;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class LoginService {
	public static JSONObject loginUser(String username, String password) {
		if (username == null || password == null) {
			return ServicesTools.error("Wrong arguments", 0);
		}
		
		try {
			boolean isUser = AuthentificationTools.userExists(username);
			if (!isUser) {
				return ServicesTools.error("Unknown user " + username, 1);
			}
			
			boolean passwordCheck = AuthentificationTools.checkPassword(username, password);
			if (!passwordCheck) {
				return ServicesTools.error("Bad password " + username, 2);
			}
			
			int idUser = AuthentificationTools.getIdUser(username);
			
			JSONObject response = new JSONObject();
			String key = AuthentificationTools.insertSession(idUser, false);
			response.put("key", key);
			
			return response;
		} catch (JSONException e) {
			return(ServicesTools.error("JSON Problem : "+e.getMessage(),100));
		} catch (SQLException e) {
			return(ServicesTools.error("SQL Problem : " + e.getMessage(), 101));
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), 102);
		}
	}
}
