package com.tweet.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.bd.DBStatic;

public class ServicesTools {
	public static int WRONG_ARG_ERROR = 0;
	public static int LOGIN_ERROR = 1;
	public static int REGISTER_ERROR = 2;
	public static int JSON_ERROR = 3;
	
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

	public static int getIdUser(String username) throws SQLException, ClassNotFoundException {
	    Connection conn = DBStatic.getConnection(null);
	    
	    String idUser = "SELECT id FROM user WHERE username LIKE LOWER(?)";
	    PreparedStatement statement = conn.prepareStatement(idUser);
	    statement.setString(1, username);
	    
	    ResultSet result = statement.executeQuery();
	    result.first();
	    
	    return result.getInt(1);
	}

	public static String getUsernameById(int id) throws SQLException, ClassNotFoundException {
		Connection conn = DBStatic.getConnection(null);
	    
	    String idUser = "SELECT username FROM user WHERE id = ?";
	    PreparedStatement statement = conn.prepareStatement(idUser);
	    statement.setInt(1, id);
	    
	    ResultSet result = statement.executeQuery();
	    result.first();
	    
	    return result.getString(1);
	}

	public static int getIdUserBySession(String key) throws SQLException, ClassNotFoundException {
		Connection conn = DBStatic.getConnection(null);
	    
	    String idUser = "SELECT user_id FROM session WHERE session_key LIKE ?";
	    PreparedStatement statement = conn.prepareStatement(idUser);
	    statement.setString(1, key);
	    
	    ResultSet result = statement.executeQuery();
	    result.first();
	    
	    return result.getInt(1);
	}
}
