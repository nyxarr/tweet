package com.tweet.services.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

import com.tweet.bd.DBStatic;

public class AuthentificationTools {
	public static boolean userExists(String username) throws SQLException, ClassNotFoundException {
		Connection conn = DBStatic.getConnection(null);
		
		String searchUser = "SELECT username FROM user WHERE LOWER(username) LIKE LOWER(?)";
		PreparedStatement statement = conn.prepareStatement(searchUser);
		statement.setString(1, username);
		
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean checkPassword(String username, String password) throws SQLException, ClassNotFoundException {
		Connection conn = DBStatic.getConnection(null);
		
		String userPassword = "SELECT username, password FROM user WHERE username LIKE ? AND password LIKE ?";
		PreparedStatement statement = conn.prepareStatement(userPassword);
		statement.setString(1, username);
		statement.setString(2, password);
		
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			return true;
		}
		
		return false;
	}
	
	public static int getIdUser(String username) throws SQLException, ClassNotFoundException {
		Connection conn = DBStatic.getConnection(null);
		
		String idUser = "SELECT id FROM user WHERE username LIKE LOWER(?)";
		PreparedStatement statement = conn.prepareStatement(idUser);
		statement.setString(1, username);
		
		ResultSet result = statement.executeQuery();
		result.first();
		
		return result.getInt(1);
	}
	
	public static String insertSession(int idUser, boolean admin) throws SQLException, ClassNotFoundException {
		Connection conn = DBStatic.getConnection(null);
		
		UUID uuid = UUID.randomUUID();
		String key = uuid.toString().replace("-", "");
		
		String id = "UPDATE session SET session_key = ?, user_id = ? WHERE user_id = ?";
		PreparedStatement statement = conn.prepareStatement(id);
		statement.setString(1, key);
		statement.setInt(2, idUser);
		statement.setInt(3, idUser);
		
		statement.executeUpdate();
		
		return key;
	}
	
	public static void insertUser(String username, String password, String lastname, String firstname, String email)
		throws SQLException, ClassNotFoundException {
		
		Connection conn = DBStatic.getConnection(null);
		
		Calendar calendar = Calendar.getInstance();
		Date dateSub = new Date(calendar.getTime().getTime());
		System.out.println(dateSub.getTime());
		
		String sql =
				"INSERT INTO user (username, password, lastname, firstname, email, sub_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, password);
		statement.setString(3, lastname);
		statement.setString(4, firstname);
		statement.setString(5, email);
		statement.setDate(6, dateSub);
		statement.executeUpdate();
	}
}
