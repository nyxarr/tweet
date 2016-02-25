package com.tweet.services.tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    
    public static int getIdUserBySession(String key) throws SQLException, ClassNotFoundException {
    	Connection conn = DBStatic.getConnection(null);
        
        String idUser = "SELECT user_id FROM session WHERE session_key LIKE ?";
        PreparedStatement statement = conn.prepareStatement(idUser);
        statement.setString(1, key);
        
        ResultSet result = statement.executeQuery();
        result.first();
        
        return result.getInt(1);
    }
    
    public static String insertSession(int idUser, boolean admin) throws SQLException, ClassNotFoundException {
        Connection conn = DBStatic.getConnection(null);
        
        // Génération clé de session
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString().replace("-", "");
        
        if (!admin) {
            // Date d'expiration de 24 heures
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR_OF_DAY, 24);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateExpire = dateFormat.format(cal.getTime());
            
            String id = "INSERT INTO session (user_id, session_key, expire_date) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE session_key = ?, expire_date = ?";
            PreparedStatement statement = conn.prepareStatement(id);
            // INSERT si session n'existe pas
            statement.setInt(1, idUser);
            statement.setString(2, key);
            statement.setString(3, dateExpire);
            // UPDATE si session existe
            statement.setString(4, key);
            statement.setString(5, dateExpire);
            
            statement.executeUpdate();
        }
        
        return key;
    }
    
    public static boolean checkSession(String key) throws SQLException, ClassNotFoundException {
        Connection conn = DBStatic.getConnection(null);
        
        String sql = "SELECT * FROM session WHERE session_key = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, key);
        ResultSet result = statement.executeQuery();
        
        if (result.next()) {
            Date expireDate = result.getDate("expire_date");
            java.util.Date now = new java.util.Date();
            
            return !expireDate.before(now);
        }
        
        return false;
    }

    public static void deleteSession(String key) throws SQLException, ClassNotFoundException {
        Connection conn = DBStatic.getConnection(null);

        String sql = "UPDATE session SET session_key = null, expire_date = null WHERE session_key = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, key);
        statement.executeUpdate();
    }
    
    public static void insertUser(String username, String password, String lastname, String firstname, String email)
        throws SQLException, ClassNotFoundException {
        
        Connection conn = DBStatic.getConnection(null);
        
        Calendar calendar = Calendar.getInstance();
        Date dateSub = new Date(calendar.getTime().getTime());
        
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
