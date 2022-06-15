package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import edu.unicauca.optimovil.R;

public class VentanaProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_producto);

        //
        Intent intent = getIntent();
        String message = intent.getStringExtra(VentanaPrincipal.EXTRA_MENSAJE);

        // Capture the layout's TextView and set the string as its text
        TextView textViewCliente = findViewById(R.id.lb_vp_titulo);
        textViewCliente.setText("CLientes");
    }

}