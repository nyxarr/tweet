package com.tweet.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tweet.services.tools.Database;

public class DBStatic {
	public static String mysql_host = "localhost";
	public static String mysql_db = "test";
	public static String mysql_username = "root";
	public static String mysql_password = "berber";
	public static boolean mysql_pooling = false;
	
	public static Connection getConnection(Database database) throws SQLException {
		if (DBStatic.mysql_pooling == false) {
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
}
