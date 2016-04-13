package com.tweet.services.authentification;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.tweet.services.ServicesTools;
import com.tweet.services.tools.AuthentificationTools;

public class LogoutService {
	public static JSONObject logoutUser(String key) {
		if (key == null) {
			return ServicesTools.error("Wrong arguments.", ServicesTools.WRONG_ARG_ERROR);
		}

		try {
			if (AuthentificationTools.checkSession(key)) {
				AuthentificationTools.deleteSession(key);
			} else {
                return ServicesTools.error("The session does not exist.", ServicesTools.WRONG_ARG_ERROR);
            }

            JSONObject response = new JSONObject();
            return response;
        } catch (SQLException e) {
            return(ServicesTools.error("SQL Problem : " + e.getMessage(), ServicesTools.SQL_EXCEPTION));
        } catch (ClassNotFoundException e) {
            return ServicesTools.error(e.getMessage(), ServicesTools.CLASS_NOT_FOUND_EXCEPTION);
        }
	}
}