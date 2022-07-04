package edu.unicauca.optimovil.Actividades;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.unicauca.optimovil.Actividades.BD.CarritoCompraHelper;
import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.Actividades.Clases.Producto;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaCarrito extends AppCompatActivity {

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    CarritoCompraHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_carrito);
        conn = new CarritoCompraHelper(this);
        Cursor cursor = conn.GetProductosCarrito();
        ArrayList<String> Lista= new ArrayList<String>();
        while (cursor.moveToNext())
        {
            Lista.add(cursor.getString(1));
        }
        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        LlenarProductos(Lista);
        AdaptadorProducto adapter = new AdaptadorProducto(listaProductos, VentanaCarrito.this);
        recyclerProductos.setAdapter(adapter);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn_prod, BotonesFragment.class, null)
                .commit();

    }

    private void LlenarProductos(ArrayList<String> lista)
    {
        for (int i = 0; i < lista.size(); i++) {
            listaProductos.add(new Producto("Montura azúl","Esta montura es nueva", Integer.parseInt(lista.get(i)),i+1));
        }
    }


}