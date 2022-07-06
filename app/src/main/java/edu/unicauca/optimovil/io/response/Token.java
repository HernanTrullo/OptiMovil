package edu.unicauca.optimovil.io.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {
    @Expose
    @SerializedName("access_token")
    private String accessToken;
    @Expose
    @SerializedName("expiry")
    private int expiry;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }
}
