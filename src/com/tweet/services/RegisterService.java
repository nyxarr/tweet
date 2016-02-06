package com.tweet.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.util.sql.SqlUtils;

public class RegisterService {
	public static JSONObject registerUser(String username, String password, String email) {
		JSONObject json = new JSONObject();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=berber");
		} catch (Exception e) {
			try {
				json.put("error", "DB connection : " + e.getMessage());
				return json;
			} catch (JSONException jsonException) {
				jsonException.printStackTrace();
			}
		}

		if (conn != null) {
			Calendar calendar = Calendar.getInstance();
			Date dateSub = new Date(calendar.getTime().getTime());
			
			try {
				String searchDuplicate =
						"SELECT username FROM user WHERE LOWER(username) LIKE LOWER(?)";

				PreparedStatement statement = conn.prepareStatement(searchDuplicate);
				statement.setString(1, username);

				ResultSet result = statement.executeQuery();
				if (result.next()) {
					json.put("error", "Nom d'utilisateur déjà utilisé.");
					SqlUtils.closeSqlConnection(conn);
					
					return json;
				}
				
				String sql =
						"INSERT INTO user (username, password, email, sub_date) "
						+ "VALUES (?, ?, ?, ?)";
				
				statement = conn.prepareStatement(sql);
				statement.setString(1, username);
				statement.setString(2, password);
				statement.setString(3, email);
				statement.setDate(4, dateSub);
				statement.executeUpdate();
				
				json.put("success", "Utilisateur enregistré.");
				
				JSONObject user = new JSONObject();
				user.put("username", username);
				user.put("email", email);
				
				json.put("user", user);
				
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				System.out.println("Statement : " + e.getMessage());
			}
		}
		
		SqlUtils.closeSqlConnection(conn);
		
		return json;
	}
}
