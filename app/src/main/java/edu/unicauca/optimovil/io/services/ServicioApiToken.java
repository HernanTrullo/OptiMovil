package edu.unicauca.optimovil.io.services;

import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.interfaces.GetTokenAccessInterface;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class ServicioApiToken extends Keys {
    private static GetTokenAccessInterface APP_SERVICIO;
    public static GetTokenAccessInterface getAppServicio(){
        if(APP_SERVICIO == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(urlApi)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APP_SERVICIO = retrofit.create(GetTokenAccessInterface.class);
        }
        return APP_SERVICIO;
    }

}
