package edu.unicauca.optimovil.io.services;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.Splash;
import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.response.Token;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicio extends Keys {
    private static Retrofit APP_RETROFIT;
    private static Activity activity = new Activity();

    public static Retrofit getAppRetrofit() {
        if(Keys.beaber_token.equals("")){
            getToken();
        }
        if (APP_RETROFIT == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "Bearer " + Keys.beaber_token)
                            .header("Accept", "application/vnd.github.v3.full+json")
                            .header("Content-Type", "application/vnd.github.v3.full+json")
                            .build();
                    return chain.proceed(request);
                }
            });
            OkHttpClient client = httpClient.build();
            Log.i("BEARER: ", Keys.beaber_token);
            APP_RETROFIT = new Retrofit.Builder()
                    .baseUrl(urlApi)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return APP_RETROFIT;
    }

    private static void getToken(){
            Call<edu.unicauca.optimovil.io.response.Response<Token>> call_token = ServicioApiToken.getAppServicio().obtenrerTokenAceso(Keys.secret);
            try {
                call_token.enqueue(new Callback<edu.unicauca.optimovil.io.response.Response<Token>>() {
                    @Override
                    public void onResponse(Call<edu.unicauca.optimovil.io.response.Response<Token>> call, retrofit2.Response<edu.unicauca.optimovil.io.response.Response<Token>> response) {
                        if(response.isSuccessful()){
                            if (response.body() != null) {
                                if(response.body().getStatus().equals(1)){
                                    Keys.beaber_token=response.body().getData().getAccessToken();
                                    Log.i("TOKEN_ACCESO1", Keys.beaber_token);
                                }else{
                                    Log.e("TOKEN_ACCESO", "No se obtuvo algun tokken");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<edu.unicauca.optimovil.io.response.Response<Token>> call, Throwable t) {
                        Toast.makeText(activity.getApplicationContext(), "Error al obtener la informción.!",Toast.LENGTH_LONG).show();
                        Log.e("ERROR_TOKEN", "No se obtuvo algun tokken" );
                    }
                });

            } catch (Exception e){
                e.printStackTrace();
                Log.d("ERROR",e.getMessage());
            }
    }
}
