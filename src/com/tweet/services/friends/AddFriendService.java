package com.tweet.services.friends;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class AddFriendService {
	public static JSONObject addFriend(String key, int friendId) {
		try {
			if (key == null || new Integer(friendId) == null) {
				return ServicesTools.error("Wrong arguments", 0);
			}
			
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", 1);
			}
			
			JSONObject json = new JSONObject();
			
			int userId = AuthentificationTools.getIdUserBySession(key);
			String friendName = AuthentificationTools.getUsernameById(friendId);
			
			Mongo mongo = new Mongo("localhost", 27017);
			DB mongoDatabase = mongo.getDB("test");
			DBCollection friends = mongoDatabase.getCollection("friends");
			
			BasicDBObject user = new BasicDBObject("user_id", userId);
			BasicDBObject friendDoc = new BasicDBObject();
			
			friendDoc.put("user_id", friendId);
			friendDoc.put("username", friendName);
			
			BasicDBObject updateFriends = new BasicDBObject("$push", new BasicDBObject("friends", friendDoc));
			
			friends.update(user, updateFriends, true, false);
			
			return json;
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), 100);
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), 101);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), 102);
		}
	}
}
