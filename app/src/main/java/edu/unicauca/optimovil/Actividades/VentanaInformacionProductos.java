package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.R;

public class VentanaInformacionProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_informacion_productos);
        Intent intent = getIntent();
        String message = intent.getStringExtra(AdaptadorProducto.EXTRA_MENSAJE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.lb_vp_titulo_producto);
        textView.setText(message);
    }
}