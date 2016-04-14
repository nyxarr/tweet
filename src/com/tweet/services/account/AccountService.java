package com.tweet.services.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.bd.DBStatic;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class AccountService {
	public static JSONObject getAccountInfo(String key) {
		JSONObject json = new JSONObject();
		
		try {
			if (key == null) {
				return ServicesTools.error("Wrong arguments", ServicesTools.WRONG_ARG_ERROR);
			}
			
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", ServicesTools.LOGIN_ERROR);
			}
			
			Connection conn = DBStatic.getConnection(null);
	        
	        String info = "SELECT username, email, firstname, lastname, sub_date FROM user WHERE id LIKE ?";
	        int userId = ServicesTools.getIdUserBySession(key);
	        
	        PreparedStatement statement = conn.prepareStatement(info);
	        statement.setInt(1, userId);
	        
	        ResultSet result = statement.executeQuery();
	        
	        while (result.next()) {
	        	Map<String, Object> map = new HashMap<String, Object>();
	        	map.put("username", result.getString("username"));
	        	map.put("email", result.getString("email"));
	        	map.put("firstname", result.getString("firstname"));
	        	map.put("lastname", result.getString("lastname"));
	        	map.put("sub_date", result.getDate("sub_date"));
	        	
	        	json.put("account_info", map);
	        }
			
			return json;
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (JSONException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.JSON_EXCEPTION);
		}
	}
}
