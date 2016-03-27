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
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class GetCommentService {
	public static JSONObject getComments(int page, String key) {
		JSONObject retour = new JSONObject();
		
		try {
			Mongo mongo = new Mongo("132.227.201.129", 27130); // "132.227.201.129", 27130
			DB mongoDatabase = mongo.getDB("gr3_guenfissi"); // gr3_guenfissi
			DBCollection comments = mongoDatabase.getCollection("comments");
			DBCursor cursor;
			
			if( key != null ) {
				int userId = AuthentificationTools.getIdUserBySession(key);

				BasicDBObject query = new BasicDBObject();
				query.put("user_id", userId);
				cursor = comments.find(query);
			} else {
				cursor = comments.find();
			}
			
			ArrayList<DBObject> commentsArray = new ArrayList<DBObject>();
			cursor.sort(new BasicDBObject("post_date", -1));
			cursor.skip(10 * (page - 1));
			cursor.limit(10);
			while (cursor.hasNext()) {
				commentsArray.add(cursor.next());
			}

			retour.put("comments", commentsArray);
			
			mongo.close();
		} catch (ClassNotFoundException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
		} catch (SQLException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.SQL_EXCEPTION);
		} catch (UnknownHostException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.UNKNOWN_HOST_EXCEPTION);
		} catch (JSONException e) {
			return ServicesTools.error(e.getMessage(), ServicesTools.JSON_EXCEPTION);
		}
		
		return retour;
	}
}
