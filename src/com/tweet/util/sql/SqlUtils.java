package com.tweet.util.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlUtils {
	public static void closeSqlConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
