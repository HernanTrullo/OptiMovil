package edu.unicauca.optimovil.Actividades;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;

import edu.unicauca.optimovil.Actividades.BD.CarritoCompraHelper;
import edu.unicauca.optimovil.Actividades.BD.MeGustaBDHelper;
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

public class VentanaInformacionProductos extends AppCompatActivity{

    ImageView iv_Producto;
    TextView descripcion;
    TextView Nombre;
    TextView PrecioP;
    MeGustaBDHelper conn;
    CarritoCompraHelper conncc;
    Button btn_Me_gusta;
    Button btn_Carrito;
    ImageButton ibtn_Carrito;
    String resId;
    String resIDStr;
    boolean estaEnLista,estaEnCarrito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_informacion_productos);
        Bundle bundle = getIntent().getExtras();
        iv_Producto = findViewById(R.id.imagen_del_producto);
        btn_Me_gusta = findViewById(R.id.Me_Gusta);
        btn_Carrito = findViewById(R.id.Comprar);
        ibtn_Carrito = findViewById(R.id.CarritoCompra);
        Nombre = findViewById(R.id.NombreProductoInfo);
        PrecioP = findViewById(R.id.PrecioP);
        descripcion = findViewById(R.id.Informacion_Producto);
        if (bundle != null) {
            resId = bundle.getString("Producto");
            Log.i("a","b "+resId);
            getToken(resId);
            //iv_Producto.setImageResource(resId);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn, BotonesFragment.class, null)
                .commit();
        conn = new MeGustaBDHelper(this);
        conncc = new CarritoCompraHelper(this);
        //resIDStr = String.valueOf(resId);
        ConsultarMeGusta();
        ConsultarCarrito();
        ibtn_Carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(VentanaInformacionProductos.this,VentanaCarrito.class);
                startActivity(intent1);
            }
        });
        btn_Me_gusta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConsultarMeGusta();
                if(estaEnLista==false)
                {
                    conn.insertarProductoMeGusta(resId);
                    btn_Me_gusta.setText(R.string.btn_no_me_gusta);
                }
                if(estaEnLista==true)
                {
                    conn.BorrarProductoMeGusta(resId);
                    btn_Me_gusta.setText(R.string.btn_me_gusta);
                }
            }
        });
        btn_Carrito.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConsultarCarrito();
                if(estaEnCarrito==false)
                {
                    conncc.insertarProductoaCarrito(resId);
                    btn_Carrito.setText(R.string.btn_no_comprar);
                }
                if(estaEnCarrito==true)
                {
                    conncc.BorrarProductoCarrito(resId);
                    btn_Carrito.setText(R.string.btn_comprar);
                }

            }
        });
    }

    private void ConsultarMeGusta()
    {
        Cursor cursor = conn.GetProductoMeGusta(resId);
        if(cursor.moveToFirst())
        {
            String productoLike = cursor.getString(0);
            estaEnLista = true;
            btn_Me_gusta.setText(R.string.btn_no_me_gusta);
        }
        else
        {
            btn_Me_gusta.setText(R.string.btn_me_gusta);
            estaEnLista = false;
        }
    }
    private void ConsultarCarrito()
    {
        Cursor cursor = conncc.GetProductoCarrito(resId);
        if(cursor.moveToFirst())
        {
            String productoLike = cursor.getString(0);
            estaEnCarrito = true;
            btn_Carrito.setText(R.string.btn_no_comprar);
        }
        else
        {
            btn_Carrito.setText(R.string.btn_comprar);
            estaEnCarrito = false;
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
                    Toast.makeText(VentanaInformacionProductos.this, "A ocurrido un error: "+t.getMessage(), Toast.LENGTH_LONG).show();
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
                                Log.i("Producto","El id es "+product.getId());
                                Picasso.get().load(product.getImagePath()).into(iv_Producto);
                                Nombre.setText(product.getName());
                                descripcion.setText(product.getDescription());
                                PrecioP.setText(String.valueOf(product.getPrice()));
                            }
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