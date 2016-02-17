package com.tweet.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tweet.services.tools.Database;

public class DBStatic {
	private static String mysql_host = "132.227.201.129:33306";
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
}
