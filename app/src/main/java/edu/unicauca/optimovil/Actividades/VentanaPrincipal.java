package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import edu.unicauca.optimovil.Logeo;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;
import edu.unicauca.optimovil.io.autencticacion_api.Keys;

public class VentanaPrincipal extends AppCompatActivity {
    public static final String EXTRA_MENSAJE_PRO = "edu.unicauca.optimovil.PRODUCTO";
    public static final String EXTRA_MENSAJE_PRINCIP = "edu.unicauca.optimovil.VENTANAPRINCIPAL";
    public static final String EXTRA_MENSAJE_LOG = "edu.unicauca.optimovil.LOGEO";
    public static final String EXTRA_MENSAJE_CLIENTES = "edu.unicauca.optimovil.VENTANAPRINCIPAL";

    private boolean esLog = false;

    private ImageButton  btiCliente,btiAccesorios, btiNinos, btiColecciones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_principal);
        // Se capura si el usuario ya esta logeado

        Log.d("TOKKEN_NUEVO: ",Keys.beaber_token);
        Intent intentPrincipal = getIntent();
        esLog = intentPrincipal.getBooleanExtra(EXTRA_MENSAJE_PRINCIP, false);

        // Inicialización de los elementos de la actividad
        btiCliente = findViewById(R.id.bti_cliente);
        btiColecciones = findViewById(R.id.bti_colecciones);
        btiAccesorios = findViewById(R.id.bti_kids);
        btiNinos = findViewById(R.id.bti_linea_hombre);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_buttons, BotonesFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();

        // Se agrega el listener de cada uno de los botones
        btiCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!esLog){
                    Intent intent = new Intent(VentanaPrincipal.this, Logeo.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(VentanaPrincipal.this, VentanaClientes.class);
                    intent.putExtra(EXTRA_MENSAJE_CLIENTES, true);
                    startActivity(intent);
                }


            }
        });
        btiNinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Niños";
                intent.putExtra(EXTRA_MENSAJE_PRO, mensaje);
                startActivity(intent);
            }
        });
        btiColecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Colecciones";
                intent.putExtra(EXTRA_MENSAJE_PRO, mensaje);
                startActivity(intent);
            }
        });
        btiAccesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaPrincipal.this, VentanaProducto.class);
                String mensaje = "Accesorios";
                intent.putExtra(EXTRA_MENSAJE_PRO, mensaje);
                startActivity(intent);
            }
        });
    }
}