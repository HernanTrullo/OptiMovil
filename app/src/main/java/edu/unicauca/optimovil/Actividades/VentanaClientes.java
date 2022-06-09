package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
        textView.setText(message);
    }
}