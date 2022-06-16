package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
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
        TextView textView = findViewById(R.id.lb_vp_titulo);
        textView.setText(message);
        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        llenarProductos();
        AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProducto.this);
        recyclerProductos.setAdapter(adapter);
    }


    private void llenarProductos()
    {
        listaProductos.add(new Producto("Montura azúl","Esta montura es nueva",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura roja","Esta montura es reparada",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura amarilla","Esta montura es nueva",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura verde","Esta montura es de vieja colección",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura plateada","Esta montura es nueva",R.drawable.imagen_prueba));

        TextView textViewCliente = findViewById(R.id.lb_vp_titulo);
        textViewCliente.setText("CLientes");

    }

}