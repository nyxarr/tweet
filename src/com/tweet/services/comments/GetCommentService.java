package com.tweet.services.comments;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class GetCommentService {
	public static JSONObject getComments(String key) {
		JSONObject retour = new JSONObject();
		
		try {
			if (key == null){
				return ServicesTools.error("Wrong argument.", 0);
			}
			
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", 1);
			}
			
			int userId = AuthentificationTools.getIdUserBySession(key);
			Mongo mongo = new Mongo("132.227.201.129", 27130);
			DB mongoDatabase = mongo.getDB("gr3_guenfissi");
			DBCollection comments = mongoDatabase.getCollection("comments");
			
			DBCursor c = comments.find(new BasicDBObject("user", userId));
			
			ArrayList<String> commentsArray = new ArrayList<String>();
			while (c.hasNext()) {
				commentsArray.add(c.next().toString());
			}
			
			retour.put("comments", commentsArray);
			
			mongo.close();
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), 2);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), 3);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), 4);
		} catch (JSONException e) {
			return ServicesTools.error(e.getMessage(), 5);
		}
		
		return retour;
	}
}
