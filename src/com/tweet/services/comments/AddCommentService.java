package com.tweet.services.comments;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.tweet.bd.DBStatic;
import com.tweet.bd.Database;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class AddCommentService {
	public static JSONObject addComment(String key, String comment) {
		JSONObject json = new JSONObject();
		
		try {
			if (key == null || comment == null) {
				return ServicesTools.error("Wrong arguments", 0);
			}
			
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", ServicesTools.LOGIN_ERROR);
			}
			
			Mongo mongo = DBStatic.getMongo();
			DBCollection comments = DBStatic.getMongoCollection(mongo, "comments");
			
			BasicDBObject commentDoc = new BasicDBObject();
			BasicDBObject votes = new BasicDBObject();
			votes.put("up", 0);
			votes.put("down", 0);
			
			int userId = ServicesTools.getIdUserBySession(key);
			String username = ServicesTools.getUsernameById(userId);
			Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String postDate = dateFormat.format((new Timestamp(cal.getTime().getTime())));
            
			commentDoc.put("user_id", userId);
			commentDoc.put("username", username);
			commentDoc.put("data", comment);
			commentDoc.put("post_date", postDate);
			commentDoc.put("votes", votes);
			comments.insert(commentDoc);
			
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
