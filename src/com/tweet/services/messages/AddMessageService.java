package com.tweet.services.messages;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.tweet.bd.DBStatic;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class AddMessageService {
	public static JSONObject addMessage(String key, String to, String message) {
		JSONObject json = new JSONObject();
		
		try {
			if (key == null || message == null) {
				return ServicesTools.error("Wrong arguments", 0);
			}
			
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", ServicesTools.LOGIN_ERROR);
			}
			
			Mongo mongo = DBStatic.getMongo();
			DBCollection notifications = DBStatic.getMongoCollection(mongo, "messages");
			
			BasicDBObject notification = new BasicDBObject();
			
			int userId = ServicesTools.getIdUserBySession(key);
			String username = ServicesTools.getUsernameById(userId);
			Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String postDate = dateFormat.format((new Timestamp(cal.getTime().getTime())));
            
            notification.put("user_id", userId);
            notification.put("from", username);
            notification.put("to", to);
            notification.put("data", message);
            notification.put("post_date", postDate);
            notifications.insert(notification);
			
			mongo.close();
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.UNKNOWN_HOST_EXCEPTION);
		}
		
		return json;
	}
}
