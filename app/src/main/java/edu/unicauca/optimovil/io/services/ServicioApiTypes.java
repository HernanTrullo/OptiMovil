package edu.unicauca.optimovil.io.services;

import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.interfaces.GetTokenAccessInterface;
import edu.unicauca.optimovil.io.interfaces.TypesInterface;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiTypes extends Servicio {
    private static TypesInterface APP_SERVICIO;
    public static TypesInterface getAppServicio(){
        if(APP_SERVICIO == null){
            APP_SERVICIO = getAppRetrofit().create(TypesInterface.class);
        }
        return APP_SERVICIO;
    }
}
