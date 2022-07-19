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
                listaProductos.add(new Producto("Montura azúl", "Esta montura es nueva", R.drawable.montura_azul, 1));
                listaProductos.add(new Producto("Montura morada", "Esta montura es nueva", R.drawable.montura_morada, 2));
                listaProductos.add(new Producto("Montura roja", "Esta montura es reparada", R.drawable.montura_roja, 3));
                listaProductos.add(new Producto("Montura amarilla", "Esta montura es nueva", R.drawable.montura_amarilla, 4));
                listaProductos.add(new Producto("Montura verde", "Esta montura es de vieja colección", R.drawable.montura_verde, 5));
                listaProductos.add(new Producto("Montura plateada", "Esta montura es nueva", R.drawable.montura_plateada, 6));
                break;
            case "Niños":
                listaProductos.add(new Producto("Montura Hello Kitty", "Esta montura es nueva", R.drawable.montura_hello_kitty, 7));
                listaProductos.add(new Producto("Montura de silicona morada", "Esta montura es nueva", R.drawable.montura_silicina_morada, 8));
                listaProductos.add(new Producto("Montura de oso", "Esta montura es reparada", R.drawable.montura_oso, 9));
                listaProductos.add(new Producto("Montura sencilla", "Esta montura es nueva", R.drawable.montura_sencilla, 10));
                break;
            case "Clientes":
                getToken();
                break;
            case "Mujeres":
                getToken();
                break;
            case "Hombres":
                listaProductos.add(new Producto("Montura sencilla negra", "Esta montura es nueva", R.drawable.montura_sencilla_hombre, 22));
                listaProductos.add(new Producto("Montura plateada redonda", "Esta montura es nueva", R.drawable.montura_plateada_redonda, 23));
                listaProductos.add(new Producto("Montura negra de marco completo", "Esta montura es reparada", R.drawable.montura_negra_completa, 24));
                listaProductos.add(new Producto("Montura amarilla cuadrada", "Esta montura es nueva", R.drawable.montura_amarilla_cuadrada, 25));
                break;
            default:
                listaProductos.add(new Producto("Montura azúl", "Esta montura es nueva", R.drawable.montura_azul, 1));
                listaProductos.add(new Producto("Montura morada", "Esta montura es nueva", R.drawable.montura_morada, 2));
                listaProductos.add(new Producto("Montura roja", "Esta montura es reparada", R.drawable.montura_roja, 3));
                listaProductos.add(new Producto("Montura amarilla", "Esta montura es nueva", R.drawable.montura_amarilla, 4));
                listaProductos.add(new Producto("Montura verde", "Esta montura es de vieja colección", R.drawable.montura_verde, 5));
                listaProductos.add(new Producto("Montura plateada", "Esta montura es nueva", R.drawable.montura_plateada, 6));
                break;
        }
    }
    public void getToken(){

        Call<Response<Token>> call_token = ServicioApiToken.getAppServicio().obtenrerTokenAceso(Keys.secret);
        try {
            call_token.enqueue(new Callback<Response<Token>>() {
                @Override
                public void onResponse(Call<Response<Token>> call, retrofit2.Response<Response<Token>> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null) {
                            if(response.body().getStatus().equals(1)){
                                Keys.beaber_token=response.body().getData().getAccessToken();
                                getProducts();
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

    private void getProducts() {
        Call<Response<List<Product>>> call_products = ServicioApiProducts.getAppServicio().getProducts();
        call_products.enqueue(new Callback<Response<List<Product>>>() {
            @Override
            public void onResponse(Call<Response<List<Product>>> call, retrofit2.Response<Response<List<Product>>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Response<List<Product>> listProducts = response.body();
                        if(listProducts.getStatus() == 1){
                            Gson gson = new Gson();
                            for (Product product: listProducts.getData()) {
                                switch (product.getId())
                                {
                                    case 264:
                                        listaProductos.add(new Producto(product.getName(), product.getDescription(),R.drawable.estuche_clasico, product.getId()));
                                        break;
                                    case 124:
                                        listaProductos.add(new Producto(product.getName(), product.getDescription(),R.drawable.montura_amarilla, product.getId()));
                                        break;
                                    case 94:
                                        listaProductos.add(new Producto(product.getName(), product.getDescription(),R.drawable.montura_azul, product.getId()));
                                        break;
                                    case 1:
                                        listaProductos.add(new Producto(product.getName(), product.getDescription(),R.drawable.montura_roja, product.getId()));
                                        break;
                                    default:
                                        listaProductos.add(new Producto(product.getName(), product.getDescription(),R.drawable.montura_roja, product.getId()));
                                        break;
                                }
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

