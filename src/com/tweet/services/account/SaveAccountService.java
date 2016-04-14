package com.tweet.services.account;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.tweet.bd.DBStatic;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class SaveAccountService {
	public static JSONObject saveAccountInfo(String key, String field, String data) {
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
	        
	        String query = "UPDATE user SET " + field + " = ? WHERE id = ?";
	        int userId = ServicesTools.getIdUserBySession(key);
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setString(1, data);
	        statement.setInt(2, userId);
	        
	        statement.executeUpdate();
	        
	        if (field.equals("username")) {
		        Mongo mongo = DBStatic.getMongo();
				DBCollection comments = DBStatic.getMongoCollection(mongo, "comments");
				
				BasicDBObject user = new BasicDBObject();
				user.put("user_id", userId);
				DBCursor cursor;
				cursor = comments.find(user);
	
				while (cursor.hasNext()) {
					BasicDBObject updateFriends = new BasicDBObject("$set", new BasicDBObject("username", data));
				
					comments.update(cursor.next(), updateFriends, true, false);
				}
				
				mongo.close();
	        }
			
			return json;
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.UNKNOWN_HOST_EXCEPTION);
		}
	}
}
