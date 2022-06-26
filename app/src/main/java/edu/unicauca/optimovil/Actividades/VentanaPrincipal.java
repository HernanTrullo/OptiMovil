package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import edu.unicauca.optimovil.Logeo;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaPrincipal extends AppCompatActivity {
    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO";
    private String esLog = "";

    private ImageButton  btiCliente,btiAccesorios, btiNinos, btiColecciones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_principal);
        // Se capura si el usuario ya esta logeado

        Intent intentPrincipal = getIntent();
        esLog = intentPrincipal.getStringExtra("Cliente");

        // Inicialización de los elementos de la actividad
        btiCliente = findViewById(R.id.bti_cliente);
        btiColecciones = findViewById(R.id.bti_colecciones);
        btiAccesorios = findViewById(R.id.bti_kids);
        btiNinos = findViewById(R.id.bti_linea_hombre);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_buttons, BotonesFragment.class, null)
                .commit();

        // Se agrega el listener de cada uno de los botones
        btiCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (esLog.equals("1")){
                    Intent intent = new Intent(VentanaPrincipal.this, Logeo.class);
                    String mensaje = "Carlos Perez Gallardo";
                    intent.putExtra(EXTRA_MENSAJE, mensaje);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(VentanaPrincipal.this, VentanaClientes.class);
                    String mensaje = "Carlos Perez Gallardo";
                    intent.putExtra(EXTRA_MENSAJE, mensaje);
                    startActivity(intent);
                }


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