package edu.unicauca.optimovil.io.interfaces;

import edu.unicauca.optimovil.io.response.Client;
import edu.unicauca.optimovil.io.response.Like;
import edu.unicauca.optimovil.io.response.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClientsInterface {

    @GET("get_client")
    Call<Response<Client>> getClient(@Query("id") int id);

    @GET("likes")
    Call<Response<Client>> getLikesClient(@Query("clients_id") int clients_id);

    @POST("login2")
    Call<Response<Client>> loginClient(@Body Client client);

    @POST("remove_product_likes")
    Call<Response<Object>> removeProductLike(@Body Like like);

    @POST("add_product_likes")
    Call<Response<Object>> addProductLike(@Body Like like);

    @POST("register_client")
    Call<Response<Client>> registerClient(@Body Client client);


}
