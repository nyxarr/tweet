package com.tweet.services.vote;

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

public class DownVoteService {
	public static JSONObject downVoteComment(String key, String objectId) {
		JSONObject retour = new JSONObject();
		
		try {
			boolean checkSession = AuthentificationTools.checkSession(key);
			
			if (!checkSession) {
				return ServicesTools.error("You are not logged in!", ServicesTools.LOGIN_ERROR);
			}
			
			Mongo mongo = DBStatic.getMongo();
			DBCollection comments = DBStatic.getMongoCollection(mongo, "comments");
			
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(objectId));
			DBCursor cursor;
			cursor = comments.find(query);

			while (cursor.hasNext()) {
				BasicDBObject updateVote = new BasicDBObject("$inc", new BasicDBObject("votes.$.down", -1));
				
				comments.update(cursor.next(), updateVote, true, false);
			}
			
			mongo.close();
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.UNKNOWN_HOST_EXCEPTION);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		}
		
		return retour;
	}
}
