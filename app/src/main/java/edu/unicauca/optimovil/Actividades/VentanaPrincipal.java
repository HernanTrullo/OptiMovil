package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.unicauca.optimovil.Clases.Producto;
import edu.unicauca.optimovil.R;

public class VentanaPrincipal extends AppCompatActivity {
    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO";
    private ImageButton  btiCliente,btiAccesorios, btiNinos, btiColecciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_principal);

        // Inicialización de los elementos de la actividad
        btiCliente = findViewById(R.id.bti_cliente);
        btiColecciones = findViewById(R.id.bti_colecciones);
        btiAccesorios = findViewById(R.id.bti_accesorios);
        btiNinos = findViewById(R.id.bti_ninos);

        // Se agrega el listener de cada uno de los botones
        // Botón de cliente
        btiCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(VentanaPrincipal.this, VentanaClientes.class);
                String mensaje = "Cliente";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                startActivity(intent);
            }
        });
        // Botón que lleva a la ventana para niños
        btiNinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Niños";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                startActivity(intent);
            }
        });
        // Boton que lleva a la ventana de colecicones
        btiColecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Colecciones";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                startActivity(intent);
            }
        });
        // Botón que lleva a la ventana de accesorios
        btiAccesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Accesorios";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                startActivity(intent);
            }
        });
    }

}