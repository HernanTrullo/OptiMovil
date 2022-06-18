package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.Actividades.Clases.Producto;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaProducto extends AppCompatActivity {

    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO.INFORMACION";

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_producto);

        Intent intent = getIntent();
        String message = intent.getStringExtra(VentanaPrincipal.EXTRA_MENSAJE);

        // Capture the layout's TextView and set the string as its text
        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        llenarProductos();
        AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProducto.this);
        recyclerProductos.setAdapter(adapter);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn_prod, BotonesFragment.class, null)
                .commit();
    }


    private void llenarProductos()
    {
        listaProductos.add(new Producto("Montura azúl","Esta montura es nueva",R.drawable.montura_azul));
        listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.montura_morada));
        listaProductos.add(new Producto("Montura roja","Esta montura es reparada",R.drawable.montura_roja));
        listaProductos.add(new Producto("Montura amarilla","Esta montura es nueva",R.drawable.montura_amarilla));
        listaProductos.add(new Producto("Montura verde","Esta montura es de vieja colección",R.drawable.montura_verde));
        listaProductos.add(new Producto("Montura plateada","Esta montura es nueva",R.drawable.montura_plateada));


    }

}