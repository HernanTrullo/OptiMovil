package edu.unicauca.optimovil.Actividades;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import edu.unicauca.optimovil.ListaMeGustaDB.ListaMeGusta;
import edu.unicauca.optimovil.ListaMeGustaDB.ListaMeGustaDataBaseAccesor;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaInformacionProductos extends AppCompatActivity {

    ImageView iv_Producto;
    Button btn_Me_gusta;
    ArrayList<String> ListaProductosMeGusta = new ArrayList<String>();
    ArrayList<ListaMeGusta> ListaProductosMeGustadb = new ArrayList<ListaMeGusta>();
    int resId;
    String resIDStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_informacion_productos);
        Bundle bundle = getIntent().getExtras();
        iv_Producto = findViewById(R.id.imagen_del_producto);
        btn_Me_gusta = findViewById(R.id.Me_Gusta);
        if (bundle != null) {
            resId = bundle.getInt("Imagen");
            iv_Producto.setImageResource(resId);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn, BotonesFragment.class, null)
                .commit();

        //ListaProductosMeGustadb = new ArrayList<ListaMeGusta>();
        //ListaProductosMeGusta = (ArrayList<String>) getLastCustomNonConfigurationInstance();
        /*if (ListaProductosMeGusta == null)
            ListaProductosMeGusta = new ArrayList<String>();*/
        List<String> ListaLocal = getTasks();
        Log.i("Tag", "CantProductosMG:"+ListaLocal);

        btn_Me_gusta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resIDStr = String.valueOf(resId);
                ListaMeGusta task = new ListaMeGusta(resIDStr);
                /*for (int i = 0; i < ListaProductosMeGusta.size(); i++) {
                }*/
                saveTask(task);
                //Log.i("Tag", "CantProductosMG:"+task);
            }
        });
    }
    private List<String>  getTasks() {
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
                    //System.out.println(tasks.get(i).getTask());
                }
                //System.out.println(ListaProductosMeGusta);
            }
        }
        GetTasks getTasks = new GetTasks();
        getTasks.execute();
        System.out.println(ListaProductosMeGusta);
        return ListaProductosMeGusta;

    }
    private void saveTask(final ListaMeGusta task) {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                ListaMeGustaDataBaseAccesor.getInstance(getApplication()).listaMeGustaDAO().insertListaProducto(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getTasks();
            }
        }

        SaveTask saveTask = new SaveTask();
        saveTask.execute();
    }
}