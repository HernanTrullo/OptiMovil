package edu.unicauca.optimovil.Actividades;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import edu.unicauca.optimovil.Actividades.BD.MeGustaBDHelper;
import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.Actividades.Clases.Producto;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;
import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.response.Product;
import edu.unicauca.optimovil.io.response.Response;
import edu.unicauca.optimovil.io.response.Token;
import edu.unicauca.optimovil.io.services.ServicioApiProducts;
import edu.unicauca.optimovil.io.services.ServicioApiToken;
import retrofit2.Call;
import retrofit2.Callback;

public class VentanaProductosMeGusta extends AppCompatActivity {

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    MeGustaBDHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_productos_megusta);
        Intent intent = getIntent();
        String message = intent.getStringExtra("Mensaje");
        conn = new MeGustaBDHelper(this);
        Cursor cursor = conn.GetProductosMeGusta();
        ArrayList<String> Lista= new ArrayList<String>();
        while (cursor.moveToNext())
        {
            Lista.add(cursor.getString(1));
        }
        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        LlenarProductos(Lista);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn_prod, BotonesFragment.class, null)
                .commit();

    }

    private void LlenarProductos(ArrayList<String> lista)
    {
        for (int i = 0; i < lista.size(); i++) {
            getToken(lista.get(i));
        }
    }
    public void getToken(String name){

        Call<Response<Token>> call_token = ServicioApiToken.getAppServicio().obtenrerTokenAceso(Keys.secret);
        try {
            call_token.enqueue(new Callback<Response<Token>>() {
                @Override
                public void onResponse(Call<Response<Token>> call, retrofit2.Response<Response<Token>> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null) {
                            if(response.body().getStatus().equals(1)){
                                Keys.beaber_token=response.body().getData().getAccessToken();
                                getProduct(name);
                                Log.i("TOKEN_ACCESO", Keys.beaber_token);
                            }else{
                                Log.e("TOKEN_ACCESO", "No se obtuvo algun tokken");
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Response<Token>> call, Throwable t) {
                    Toast.makeText(VentanaProductosMeGusta.this, "A ocurrido un error: "+t.getMessage(), Toast.LENGTH_LONG).show();
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

    private void getProduct(String name) {
        Call<Response<List<Product>>> call_products = ServicioApiProducts.getAppServicio().getProductsByID(name);
        call_products.enqueue(new Callback<Response<List<Product>>>() {
            @Override
            public void onResponse(Call<Response<List<Product>>> call, retrofit2.Response<Response<List<Product>>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Response<List<Product>> listProducts = response.body();
                        if(listProducts.getStatus() == 1){
                            for (Product product: listProducts.getData()) {
                                listaProductos.add(new Producto(product.getName(), product.getDescription(), product.getImagePath(), product.getId()));
                            }
                            AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProductosMeGusta.this);
                            recyclerProductos.setAdapter(adapter);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Response<List<Product>>> call, Throwable t) {
                Log.i("Producto","Error");
            }
        });
    }


}