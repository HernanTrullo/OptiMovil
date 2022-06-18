package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaInformacionProductos extends AppCompatActivity {

    ImageView iv_Producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_informacion_productos);
        Bundle bundle = getIntent().getExtras();
        iv_Producto = findViewById(R.id.imagen_del_producto);
        if (bundle != null) {
            int resId = bundle.getInt("Imagen");
            iv_Producto.setImageResource(resId);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn, BotonesFragment.class, null)
                .commit();
    }
}