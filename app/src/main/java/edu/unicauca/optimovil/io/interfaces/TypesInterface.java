package edu.unicauca.optimovil.io.interfaces;

import java.util.List;

import edu.unicauca.optimovil.io.response.Response;
import edu.unicauca.optimovil.io.response.Type;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TypesInterface {

    @GET("types")
    Call<Response<List<Type>>> getTypes(@Query("name") String name);
}
