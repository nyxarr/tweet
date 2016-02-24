package com.tweet.services.comments;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class AddCommentService {
	public static JSONObject addComment(String key, String comment) {
		JSONObject json = new JSONObject();
		
		try {
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", 0);
			}
			
			MongoClient mongo = new MongoClient("localhost", 27017); //132.227.201.129:27130
			MongoDatabase mongoDatabase = mongo.getDatabase("tweet");
			
			Document commentDoc = new Document();
			int userId = AuthentificationTools.getIdUserBySession(key);
			Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String postDate = dateFormat.format(cal.getTime());
			
			commentDoc.append("user", userId);
			commentDoc.append("data", comment);
			commentDoc.append("post_date", postDate);
			
			mongoDatabase.getCollection("comments").insertOne(commentDoc);
			mongo.close();
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), 1);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), 2);
		}
		
		return json;
	}
}
