package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

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
        btiCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaClientes.class);
                String mensaje = "Carlos Perez Gallardo";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                startActivity(intent);
            }
        });
        btiNinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Niños";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                startActivity(intent);
            }
        });
        btiColecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Colecciones";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                startActivity(intent);
            }
        });
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