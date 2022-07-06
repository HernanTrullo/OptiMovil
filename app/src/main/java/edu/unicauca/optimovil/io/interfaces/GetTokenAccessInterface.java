package edu.unicauca.optimovil.io.interfaces;

import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.response.Response;
import edu.unicauca.optimovil.io.response.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetTokenAccessInterface {


    @Headers("Accept: application/vnd.github.v3.full+json")
    @FormUrlEncoded
    @POST("get-token")
    Call<Response<Token>> obtenrerTokenAceso(@Field("secret") String apiKey);
}
