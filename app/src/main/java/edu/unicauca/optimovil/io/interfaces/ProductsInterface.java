package edu.unicauca.optimovil.io.interfaces;

import java.util.List;

import edu.unicauca.optimovil.io.response.Product;
import edu.unicauca.optimovil.io.response.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductsInterface {
//    id=&name=&description=&image=&image_path=&stock=&price=&category_id=&type_id=
    @GET("products")
    Call<Response<List<Product>>> getProducts();

    @GET("products")
    Call<Response<List<Product>>> getProductsFilters( @Query("category_id") int categoryId );

    @GET("products")
    Call<Response<List<Product>>> getProductsFiltersID( @Query("id") int id);
}
