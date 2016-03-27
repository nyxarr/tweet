package com.tweet.services.friends;

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

public class GetFriendService {
	public static JSONObject getFriends(String key) {
		try {
			JSONObject json = new JSONObject();
			
			if (key == null) {
				return ServicesTools.error("Wrong arguments.", ServicesTools.WRONG_ARG_ERROR);
			}
			
			boolean session = AuthentificationTools.checkSession(key);
			if (!session) {
				return ServicesTools.error("Session expired.", 1);
			}
			
			int userId = AuthentificationTools.getIdUserBySession(key);
			
			Mongo mongo = new Mongo("132.227.201.129", 27130);
			DB mongoDatabase = mongo.getDB("gr3_guenfissi");
			DBCollection friends = mongoDatabase.getCollection("friends");
			DBCursor cursor;
			
			BasicDBObject query = new BasicDBObject();
			query.put("user_id", userId);
			cursor = friends.find(query);
			

			if (cursor.hasNext()) {
				json.put("friends", cursor.next().get("friends"));
			} else {
				json.put("friends", new BasicDBObject());
			}
			
			return json;
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.UNKNOWN_HOST_EXCEPTION);
		} catch (JSONException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.JSON_EXCEPTION);
		}
	}
}
