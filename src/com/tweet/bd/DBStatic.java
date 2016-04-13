package com.tweet.bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class DBStatic {
	public static String mysql_host = "132.227.201.129:33306";
	public static String mysql_db = "gr3_guenfissi";
	public static String mysql_username = "gr3_guenfissi";
	public static String mysql_password = "XUf3KU";
	public static boolean mysql_pooling = false;
	
	public static Connection getConnection(Database database) throws SQLException, ClassNotFoundException {
		if (DBStatic.mysql_pooling == false) {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://" + DBStatic.mysql_host + "/" + DBStatic.mysql_db,
					DBStatic.mysql_username,
					DBStatic.mysql_password);
		} else {
			if (database == null) {
				database = new Database("jdbc/db");
			}
			
			return database.getConnection();
		}
	}
	
	public static Mongo getMongo() throws UnknownHostException {
		Mongo m = new Mongo("132.227.201.129", 27130);
		return m;
	}

	public static DBCollection getMongoCollection(Mongo m, String base) {
		DB db = m.getDB("gr3_guenfissi");
		DBCollection collection = db.getCollection(base);
		return collection;
	}
}
