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
                listaProductos.add(new Producto("Montura azúl","Esta montura es nueva",R.drawable.montura_azul,1));
                listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.montura_morada,2));
                listaProductos.add(new Producto("Montura roja","Esta montura es reparada",R.drawable.montura_roja,3));
                listaProductos.add(new Producto("Montura amarilla","Esta montura es nueva",R.drawable.montura_amarilla,4));
                listaProductos.add(new Producto("Montura verde","Esta montura es de vieja colección",R.drawable.montura_verde,5));
                listaProductos.add(new Producto("Montura plateada","Esta montura es nueva",R.drawable.montura_plateada,6));
                break;
            case "Niños":
                listaProductos.add(new Producto("Montura Hello Kitty","Esta montura es nueva",R.drawable.montura_hello_kitty,7));
                listaProductos.add(new Producto("Montura de silicona morada","Esta montura es nueva",R.drawable.montura_silicina_morada,8));
                listaProductos.add(new Producto("Montura de oso","Esta montura es reparada",R.drawable.montura_oso,9));
                listaProductos.add(new Producto("Montura sencilla","Esta montura es nueva",R.drawable.montura_sencilla,10));
                break;
            case "Clientes":
                listaProductos.add(new Producto("Retenedores de silicona","Esta montura es nueva",R.drawable.retenedores_silicona,11));
                listaProductos.add(new Producto("Sujetador para gafas","Esta montura es nueva",R.drawable.sujetador,12));
                listaProductos.add(new Producto("Estuche plegable","Esta montura es reparada",R.drawable.estuche_plegable,13));
                listaProductos.add(new Producto("Estuche clásico","Esta montura es nueva",R.drawable.estuche_clasico,14));
                listaProductos.add(new Producto("Liquido de limpieza para lentes","Esta montura es de vieja colección",R.drawable.limpiador,15));
                listaProductos.add(new Producto("Goma decorativa para niño","Esta montura es nueva",R.drawable.goma_decorativa,16));
                listaProductos.add(new Producto("Cadena dorada para gafas","Esta montura es nueva",R.drawable.cadena_dorada,17));
                break;
            case "Mujeres":
                listaProductos.add(new Producto("Montura roja","Esta montura es nueva",R.drawable.montura_roja,3));
                listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.montura_morada,2));
                listaProductos.add(new Producto("Montura plateada","Esta montura es reparada",R.drawable.montura_plateada,6));
                listaProductos.add(new Producto("Montura vintage","Esta montura es nueva",R.drawable.montura_vintage,18));
                listaProductos.add(new Producto("Montura transparente redonda","Esta montura es de vieja colección",R.drawable.montura_transparente_redonda,19));
                listaProductos.add(new Producto("Montura rosada redonda","Esta montura es nueva",R.drawable.montura_rosa_redonda,20));
                listaProductos.add(new Producto("Montura dorada redonda","Esta montura es nueva",R.drawable.montura_dorada,21));
                break;
            case "Hombres":
                listaProductos.add(new Producto("Montura sencilla negra","Esta montura es nueva",R.drawable.montura_sencilla_hombre,22));
                listaProductos.add(new Producto("Montura plateada redonda","Esta montura es nueva",R.drawable.montura_plateada_redonda,23));
                listaProductos.add(new Producto("Montura negra de marco completo","Esta montura es reparada",R.drawable.montura_negra_completa,24));
                listaProductos.add(new Producto("Montura amarilla cuadrada","Esta montura es nueva",R.drawable.montura_amarilla_cuadrada,25));
                break;
            default:
                listaProductos.add(new Producto("Montura azúl","Esta montura es nueva",R.drawable.montura_azul,1));
                listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.montura_morada,2));
                listaProductos.add(new Producto("Montura roja","Esta montura es reparada",R.drawable.montura_roja,3));
                listaProductos.add(new Producto("Montura amarilla","Esta montura es nueva",R.drawable.montura_amarilla,4));
                listaProductos.add(new Producto("Montura verde","Esta montura es de vieja colección",R.drawable.montura_verde,5));
                listaProductos.add(new Producto("Montura plateada","Esta montura es nueva",R.drawable.montura_plateada,6));
                break;
        }



    }

}