package com.tweet.services.authentification;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class RegisterService {	
	public static JSONObject registerUser(String username, String password, String lastname, String firstname, String email) {
		if (username == null || password == null || lastname == null || firstname == null || email == null) {
			return ServicesTools.error("Wrong arguments", 0);
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Verifie que l'utilisateur n'existe pas sinon ERROR 1
			boolean checkUser = AuthentificationTools.userExists(username);
			if (checkUser) {
				return ServicesTools.error("Username already exists", ServicesTools.REGISTER_ERROR);
			}
			
			boolean checkEmail = AuthentificationTools.emailExists(email);
			if (checkEmail) {
				return ServicesTools.error("Email already exists", ServicesTools.REGISTER_ERROR);
			}
			
			// Insere l'utilisateur dans la BD
			AuthentificationTools.insertUser(username, password, lastname, firstname, email);
			

			JSONObject json = new JSONObject();

			json.put("user", username);
			
			return json;
		} catch (SQLException e) {
			return ServicesTools.error("Database problem : "+e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (JSONException e) {
			return ServicesTools.error("JSON problem : "+e.getMessage(), ServicesTools.JSON_EXCEPTION);
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		}
	}
}
