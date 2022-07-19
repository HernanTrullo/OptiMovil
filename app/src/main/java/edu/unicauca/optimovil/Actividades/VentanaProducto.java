package edu.unicauca.optimovil.Actividades;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.Actividades.Clases.Producto;

import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.Splash;
import edu.unicauca.optimovil.fragments.BotonesFragment;

import edu.unicauca.optimovil.fragments.BotonesFragment;
import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.response.Product;
import edu.unicauca.optimovil.io.response.Response;
import edu.unicauca.optimovil.io.response.Token;
import edu.unicauca.optimovil.io.services.ServicioApiProducts;
import edu.unicauca.optimovil.io.services.ServicioApiToken;
import retrofit2.Call;
import retrofit2.Callback;

//import edu.unicauca.optimovil.Splash;


public class VentanaProducto extends AppCompatActivity {

    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO.INFORMACION";

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    ImageButton ibtn_carritoCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_producto);
        ibtn_carritoCompra = findViewById(R.id.CarritoDeCompra);
        Intent intent = getIntent();

        boolean esLogeado = intent.getBooleanExtra(VentanaPrincipal.EXTRA_MENSAJE_PRO, false);

        // Capture the layout's TextView and set the string as its text

        //TextView textView = findViewById(R.id.lb_vp_titulo);



        String message = intent.getStringExtra("Mensaje");
        ibtn_carritoCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(VentanaProducto.this,VentanaCarrito.class);
                startActivity(intent1);
            }
        });
        // Capture the layout's TextView and set the string as its text
        listaProductos = new ArrayList<Producto>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        llenarProductos(message);
        AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProducto.this);
        recyclerProductos.setAdapter(adapter);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn_prod, BotonesFragment.class, null)
                .commit();



        //loadTasks();

    }


    private void llenarProductos(String pantallaReferencia)
    {

        switch (pantallaReferencia) {
            case "Colecciones":
                getToken(14);
                break;
            case "Niños":
                getToken(4);
                break;
            case "Mujeres":
                getToken(2);
                break;
            case "Hombres":
                getToken(1);
                break;
            default:
                getToken(2);
                break;
        }
    }
    public void getToken(int catergoria){

        Call<Response<Token>> call_token = ServicioApiToken.getAppServicio().obtenrerTokenAceso(Keys.secret);
        try {
            call_token.enqueue(new Callback<Response<Token>>() {
                @Override
                public void onResponse(Call<Response<Token>> call, retrofit2.Response<Response<Token>> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null) {
                            if(response.body().getStatus().equals(1)){
                                Keys.beaber_token=response.body().getData().getAccessToken();
                                getProducts(catergoria);
                                Log.i("TOKEN_ACCESO", Keys.beaber_token);
                            }else{
                                Log.e("TOKEN_ACCESO", "No se obtuvo algun tokken");
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Response<Token>> call, Throwable t) {
                    Toast.makeText(VentanaProducto.this, "A ocurrido un error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    (new Timer()).schedule(null, Toast.LENGTH_LONG+1);
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TOKEN",e.getMessage());
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private void getProducts(int catergoria) {
        Call<Response<List<Product>>> call_products = ServicioApiProducts.getAppServicio().getProductsFilters(catergoria);
        call_products.enqueue(new Callback<Response<List<Product>>>() {
            @Override
            public void onResponse(Call<Response<List<Product>>> call, retrofit2.Response<Response<List<Product>>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Response<List<Product>> listProducts = response.body();
                        if(listProducts.getStatus() == 1){
                            Gson gson = new Gson();
                            for (Product product: listProducts.getData()) {
                                listaProductos.add(new Producto(product.getName(), product.getDescription(), product.getImagePath(), product.getId()));
                            }

                            AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProducto.this);
                            recyclerProductos.setAdapter(adapter);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Response<List<Product>>> call, Throwable t) {

            }
        });
    }
}