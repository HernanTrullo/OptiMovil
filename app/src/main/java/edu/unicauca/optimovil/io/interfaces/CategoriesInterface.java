package edu.unicauca.optimovil.io.interfaces;

import java.util.List;

import edu.unicauca.optimovil.io.response.Category;
import edu.unicauca.optimovil.io.response.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoriesInterface {

    @GET("categories")
    Call<Response<List<Category>>> getTypes(@Query("name") String name);
}
