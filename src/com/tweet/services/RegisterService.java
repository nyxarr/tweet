package com.tweet.services;

import java.sql.Connection;
import java.sql.DriverManager;

public class RegisterService {
	public static void registerUser(String username, String password) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/test?" +
                    "user=root&password=");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
