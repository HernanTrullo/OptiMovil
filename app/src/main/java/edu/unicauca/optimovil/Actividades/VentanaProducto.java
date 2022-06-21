package edu.unicauca.optimovil.Actividades;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.Actividades.Clases.Producto;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaProducto extends AppCompatActivity {

    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO.INFORMACION";

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_producto);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Mensaje");

        // Capture the layout's TextView and set the string as its text
        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        llenarProductos(message);
        AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProducto.this);
        recyclerProductos.setAdapter(adapter);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn_prod, BotonesFragment.class, null)
                .commit();
    }


    private void llenarProductos(String pantallaReferencia)
    {

        switch (pantallaReferencia)
        {
            case "Colecciones":
                listaProductos.add(new Producto("Montura azúl","Esta montura es nueva",R.drawable.montura_azul));
                listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.montura_morada));
                listaProductos.add(new Producto("Montura roja","Esta montura es reparada",R.drawable.montura_roja));
                listaProductos.add(new Producto("Montura amarilla","Esta montura es nueva",R.drawable.montura_amarilla));
                listaProductos.add(new Producto("Montura verde","Esta montura es de vieja colección",R.drawable.montura_verde));
                listaProductos.add(new Producto("Montura plateada","Esta montura es nueva",R.drawable.montura_plateada));
                break;
            case "Niños":
                listaProductos.add(new Producto("Montura Hello Kitty","Esta montura es nueva",R.drawable.montura_hello_kitty));
                listaProductos.add(new Producto("Montura de silicona morada","Esta montura es nueva",R.drawable.montura_silicina_morada));
                listaProductos.add(new Producto("Montura de oso","Esta montura es reparada",R.drawable.montura_oso));
                listaProductos.add(new Producto("Montura sencilla","Esta montura es nueva",R.drawable.montura_sencilla));
                break;
            case "Clientes":
                listaProductos.add(new Producto("Retenedores de silicona","Esta montura es nueva",R.drawable.retenedores_silicona));
                listaProductos.add(new Producto("Sujetador para gafas","Esta montura es nueva",R.drawable.sujetador));
                listaProductos.add(new Producto("Estuche plegable","Esta montura es reparada",R.drawable.estuche_plegable));
                listaProductos.add(new Producto("Estuche clásico","Esta montura es nueva",R.drawable.estuche_clasico));
                listaProductos.add(new Producto("Liquido de limpieza para lentes","Esta montura es de vieja colección",R.drawable.limpiador));
                listaProductos.add(new Producto("Goma decorativa para niño","Esta montura es nueva",R.drawable.goma_decorativa));
                listaProductos.add(new Producto("Cadena dorada para gafas","Esta montura es nueva",R.drawable.cadena_dorada));
                break;
            case "Mujeres":
                listaProductos.add(new Producto("Montura roja","Esta montura es nueva",R.drawable.montura_roja));
                listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.montura_morada));
                listaProductos.add(new Producto("Montura plateada","Esta montura es reparada",R.drawable.montura_plateada));
                listaProductos.add(new Producto("Montura vintage","Esta montura es nueva",R.drawable.montura_vintage));
                listaProductos.add(new Producto("Montura transparente redonda","Esta montura es de vieja colección",R.drawable.montura_transparente_redonda));
                listaProductos.add(new Producto("Montura rosada redonda","Esta montura es nueva",R.drawable.montura_rosa_redonda));
                listaProductos.add(new Producto("Montura dorada redonda","Esta montura es nueva",R.drawable.montura_dorada));
                break;
            case "Hombres":
                listaProductos.add(new Producto("Montura sencilla negra","Esta montura es nueva",R.drawable.montura_sencilla_hombre));
                listaProductos.add(new Producto("Montura plateada redonda","Esta montura es nueva",R.drawable.montura_plateada_redonda));
                listaProductos.add(new Producto("Montura negra de marco completo","Esta montura es reparada",R.drawable.montura_negra_completa));
                listaProductos.add(new Producto("Montura amarilla cuadrada","Esta montura es nueva",R.drawable.montura_amarilla_cuadrada));
                break;
            default:
                listaProductos.add(new Producto("Montura azúl","Esta montura es nueva",R.drawable.montura_azul));
                listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.montura_morada));
                listaProductos.add(new Producto("Montura roja","Esta montura es reparada",R.drawable.montura_roja));
                listaProductos.add(new Producto("Montura amarilla","Esta montura es nueva",R.drawable.montura_amarilla));
                listaProductos.add(new Producto("Montura verde","Esta montura es de vieja colección",R.drawable.montura_verde));
                listaProductos.add(new Producto("Montura plateada","Esta montura es nueva",R.drawable.montura_plateada));
                break;
        }



    }

}