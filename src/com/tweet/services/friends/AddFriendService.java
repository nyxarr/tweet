package com.tweet.services.friends;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.tweet.bd.DBStatic;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class AddFriendService {
	public static JSONObject addFriend(String key, String friend) {
		try {
			if (key == null || friend == null) {
				return ServicesTools.error("Wrong arguments", ServicesTools.WRONG_ARG_ERROR);
			}
			
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", ServicesTools.LOGIN_ERROR);
			}
			
			boolean friendExist = AuthentificationTools.userExists(friend);
			
			if (!friendExist) {
				return ServicesTools.error("This user does not exist.", ServicesTools.WRONG_ARG_ERROR);
			}
			
			JSONObject json = new JSONObject();
			
			int userId = ServicesTools.getIdUserBySession(key);
			int friendId = ServicesTools.getIdUser(friend);
			
			Mongo mongo = DBStatic.getMongo();
			DBCollection friends = DBStatic.getMongoCollection(mongo, "friends");
			
			BasicDBObject user = new BasicDBObject("user_id", userId);
			BasicDBObject friendDoc = new BasicDBObject();
			
			friendDoc.put("friend_username", friend);
			friendDoc.put("friend_id", friendId);
			
			BasicDBObject updateFriends = new BasicDBObject("$push", new BasicDBObject("friends", friendDoc));
			
			friends.update(user, updateFriends, true, false);
			
			mongo.close();
			return json;
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.UNKNOWN_HOST_EXCEPTION);
		}
	}
}
