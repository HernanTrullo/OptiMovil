package edu.unicauca.optimovil.Actividades;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import java.util.ArrayList;

import edu.unicauca.optimovil.Actividades.BD.CarritoCompraHelper;
import edu.unicauca.optimovil.Actividades.BD.MeGustaBDHelper;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaInformacionProductos extends AppCompatActivity{

    ImageView iv_Producto;
    MeGustaBDHelper conn;
    CarritoCompraHelper conncc;
    Button btn_Me_gusta;
    Button btn_Carrito;
    ImageButton ibtn_Carrito;
    int resId;
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
        if (bundle != null) {
            resId = bundle.getInt("Imagen");
            iv_Producto.setImageResource(resId);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn, BotonesFragment.class, null)
                .commit();
        conn = new MeGustaBDHelper(this);
        conncc = new CarritoCompraHelper(this);
        resIDStr = String.valueOf(resId);
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
                    conn.insertarProductoMeGusta(resIDStr);
                    btn_Me_gusta.setText(R.string.btn_no_me_gusta);
                }
                if(estaEnLista==true)
                {
                    conn.BorrarProductoMeGusta(resIDStr);
                    btn_Me_gusta.setText(R.string.btn_me_gusta);
                }
            }
        });
        btn_Carrito.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConsultarCarrito();
                if(estaEnCarrito==false)
                {
                    conncc.insertarProductoaCarrito(resIDStr);
                    btn_Carrito.setText(R.string.btn_no_comprar);
                }
                if(estaEnCarrito==true)
                {
                    conncc.BorrarProductoCarrito(resIDStr);
                    btn_Carrito.setText(R.string.btn_comprar);
                }

            }
        });
    }

    private void ConsultarMeGusta()
    {
        Cursor cursor = conn.GetProductoMeGusta(resIDStr);
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
        Cursor cursor = conncc.GetProductoCarrito(resIDStr);
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

    }