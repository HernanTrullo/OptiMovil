package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import edu.unicauca.optimovil.Clases.Producto;
import edu.unicauca.optimovil.R;

public class VentanaProducto extends AppCompatActivity {
    private ArrayList<Producto> listaProducto;
    private RecyclerView reciclerProducto;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_producto);

        // Se inicializa el intent que es enviado desde la ventana anterior
        Intent intent = getIntent();
        String message = intent.getStringExtra(VentanaPrincipal.EXTRA_MENSAJE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.lb_vp_titulo);
        textView.setText(message);

        // Se inicializa el recyclerWiew y la clase usuario
        listaProducto = new ArrayList<>();
        reciclerProducto = findViewById(R.id.recycler_producto);
        reciclerProducto.setLayoutManager(new LinearLayoutManager(this));

        // Código para mostrar los productos en el recycler view

    }

}