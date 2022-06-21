package edu.unicauca.optimovil.Actividades;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.Actividades.Clases.Producto;
import edu.unicauca.optimovil.ListaMeGustaDB.ListaMeGusta;
import edu.unicauca.optimovil.ListaMeGustaDB.ListaMeGustaDataBaseAccesor;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaProductosMeGusta extends AppCompatActivity {

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    ArrayList<String> ListaProductosMeGusta = new ArrayList<String>();
    ArrayList<ListaMeGusta> ListaProductosMeGustadb = new ArrayList<ListaMeGusta>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_productos_megusta);
        Intent intent = getIntent();
        String message = intent.getStringExtra("Mensaje");
        ListaProductosMeGustadb = new ArrayList<ListaMeGusta>();
        ListaProductosMeGusta = (ArrayList<String>) getLastCustomNonConfigurationInstance();
        if (ListaProductosMeGusta == null)
            ListaProductosMeGusta = new ArrayList<String>();
        getTasks();
        // Capture the layout's TextView and set the string as its text
        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn_prod, BotonesFragment.class, null)
                .commit();
    }
    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<ListaMeGusta>> {

            @Override
            protected List<ListaMeGusta> doInBackground(Void... voids) {
                List<ListaMeGusta> taskList = ListaMeGustaDataBaseAccesor
                        .getInstance(getApplication()).listaMeGustaDAO().loadAllItems();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<ListaMeGusta> tasks) {
                super.onPostExecute(tasks);
                ListaProductosMeGusta.clear();
                ListaProductosMeGustadb.clear();
                for (int i = 0; i < tasks.size(); i++) {
                    ListaProductosMeGusta.add(tasks.get(i).getTask());
                    ListaProductosMeGustadb.add(tasks.get(i));
                }
                llenarProductos(ListaProductosMeGusta);
                AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProductosMeGusta.this);
                recyclerProductos.setAdapter(adapter);
            }
        }
        GetTasks getTasks = new GetTasks();
        getTasks.execute();
    }
    private void llenarProductos(ArrayList<String> Lista) {
        //Log.i("Tag", "CantProductosMG:"+Lista);
        for (int i = 0; i < Lista.size(); i++) {
            listaProductos.add(new Producto("Montura azúl","Esta montura es nueva", Integer.parseInt(Lista.get(i))));
        }
    }

}