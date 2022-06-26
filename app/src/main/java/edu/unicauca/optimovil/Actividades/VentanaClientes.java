package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import edu.unicauca.optimovil.Logeo;
import edu.unicauca.optimovil.R;

public class VentanaClientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_clientes);

        Intent intent = getIntent();
        String message = intent.getStringExtra(VentanaPrincipal.EXTRA_MENSAJE);


        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.lb_vc_titulo);
        textView.setText(R.string.lb_cliente);

        TextView textViewNombreCliente = findViewById(R.id.nombre_cliente);
        textViewNombreCliente.setText(message);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, VentanaPrincipal.class);
        intent.putExtra("Cliente", "-1");
        startActivity(intent);
    }
}