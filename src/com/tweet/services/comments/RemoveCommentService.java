package com.tweet.services.comments;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.tweet.bd.DBStatic;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class RemoveCommentService {
	public static JSONObject removeComment(String key, String objectId) {
		JSONObject retour = new JSONObject();
		
		try {
			if (key == null || objectId == null) {
				return ServicesTools.error("Wrong arguments", ServicesTools.WRONG_ARG_ERROR);
			}
			
			boolean sessionExists = AuthentificationTools.checkSession(key);
			
			if (!sessionExists) {
				return ServicesTools.error("You are not logged in!", ServicesTools.LOGIN_ERROR);
			}
			
			int userId = ServicesTools.getIdUserBySession(key);
			
			Mongo mongo = DBStatic.getMongo();
			DBCollection comments = DBStatic.getMongoCollection(mongo, "comments");
			
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(objectId));
			query.put("user_id", userId);
			DBCursor cursor;
			cursor = comments.find(query);

			if (cursor.hasNext())
				comments.remove(query);
			
			mongo.close();
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.UNKNOWN_HOST_EXCEPTION);
		}
		
		return retour;
	}
}
