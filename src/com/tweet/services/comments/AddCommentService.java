package com.tweet.services.comments;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AddCommentService {
	public static JSONObject addComment(String key, String comment) {
		JSONObject json = new JSONObject();
		MongoClient mongo = new MongoClient("localhost", 27015); // upmc : "132.227.201.129", 27130
		MongoDatabase mongoDatabase = mongo.getDatabase("tweet");
		
		Document commentDoc = new Document();
		commentDoc.append("user", comment);
		commentDoc.append("data", comment);
		
		mongoDatabase.getCollection("comments").insertOne(commentDoc);
		
		return json;
	}
}
