package com.tweet.services.messages;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.tweet.bd.DBStatic;
import com.tweet.services.ServicesTools;

public class GetMessageService {
	public static JSONObject getMessages(String key) {
		JSONObject retour = new JSONObject();
		
		try {
			Mongo mongo = DBStatic.getMongo();
			DBCollection comments = DBStatic.getMongoCollection(mongo, "messages");
			DBCursor cursor;
			
			if( key != null && !key.isEmpty()) {
				int userId = ServicesTools.getIdUserBySession(key);
				String username = ServicesTools.getUsernameById(userId);

				BasicDBObject query = new BasicDBObject();
				query.put("to", username);
				cursor = comments.find(query);
			} else {
				cursor = comments.find();
			}
			
			ArrayList<DBObject> messagesArray = new ArrayList<DBObject>();
			cursor.sort(new BasicDBObject("post_date", -1));
			while (cursor.hasNext()) {
				messagesArray.add(cursor.next());
			}

			retour.put("messages", messagesArray);
			
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
