package com.example.igiagante.thegarden.core;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.example.igiagante.thegarden.core.domain.entity.User;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.inject.Singleton;

/**
 * @author Ignacio Giagante, on 2/8/16.
 */

@Singleton
public class Session {

    private User user;
    private String token;
    private Date tokenExpiration;

    public Session() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        setTokenExpirationDateFromStringCodeBase64();
    }

    public Date getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Date tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public boolean checkIfTokenIsExpired() {
        if (tokenExpiration != null) {
            return new Date().after(tokenExpiration);
        }
        return false;
    }

    public void cleanSession() {
        user = new User();
        token = "";
        tokenExpiration = null;
    }

    /**
     * Extract expiration date from token
     */
    private void setTokenExpirationDateFromStringCodeBase64() {

        if (!TextUtils.isEmpty(token)) {
            String[] jwtParts = token.split("\\.");
            if (jwtParts.length > 1 && jwtParts[1] != null) {
                byte[] data = Base64.decode(jwtParts[1], Base64.DEFAULT);
                try {

                    String text = new String(data, "UTF-8");
                    Claims claims = new Gson().fromJson(text, Claims.class);

                    long milliseconds = Integer.parseInt(claims.getExp()) * 1000L;
                    this.tokenExpiration = new Date(milliseconds);

                    this.getUser().setId(claims.getSub());

                } catch (UnsupportedEncodingException e) {
                    Log.d("Exception", "Something was wrong trying to parse token to extract expiration date");
                }
            }
        }
    }

    /**
     * Used to parse string data token into object Claims
     */
    private class Claims {
        private String sub;
        private String iat;
        private String exp;

        public String getSub() {
            return sub;
        }

        public String getExp() {
            return exp;
        }
    }
}
