package com.tweet.services.comments;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;



//import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
//import com.mongodb.MongoClient;
//import com.mongodb.client.MongoDatabase;
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
				return ServicesTools.error("You are not logged in!", 1);
			}
			
			/*MongoClient mongo = new MongoClient("132.227.201.129", 27130); // 132.227.201.129:27130
			MongoDatabase mongoDatabase = mongo.getDatabase("gr3_guenfissi"); // gr3_guenfissi
			
			Document commentDoc = new Document();*/
			
			Mongo mongo = new Mongo("132.227.201.129", 27130);
			DB mongoDatabase = mongo.getDB("gr3_guenfissi");
			DBCollection comments = mongoDatabase.getCollection("comments");
		    
			BasicDBObject commentDoc = new BasicDBObject();
			
			int userId = AuthentificationTools.getIdUserBySession(key);
			String username = AuthentificationTools.getUsernameById(userId);
			Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String postDate = dateFormat.format((new Timestamp(cal.getTime().getTime())));
            
			commentDoc.put("user_id", userId);
			commentDoc.put("username", username);
			commentDoc.put("data", comment);
			commentDoc.put("post_date", postDate);
			comments.insert(commentDoc);
			
			mongo.close();
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), 2);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), 3);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), 4);
		}
		
		return json;
	}
}
