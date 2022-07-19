package edu.unicauca.optimovil.io.autencticacion_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keys {

    public static final String urlApi = "https://optimovilapi.herokuapp.com/api/";
    @SerializedName("secret")
    @Expose
    public static String secret = "abf7755c8b07eee3c1e3bd578e2e5ec8";
    public static String beaber_token = "";
    public static final String NAME_DATA_BASE = "optimovil.db";
    public static final int DATABASE_VERSION = 2;

    public static String getSecret_key_app() {
        return secret;
    }

    public static void setSecret_key_app(String secret_key_app) {
        Keys.secret = secret_key_app;
    }

    public Keys() {
        this.secret = "abf7755c8b07eee3c1e3bd578e2e5ec8";
    }
}
