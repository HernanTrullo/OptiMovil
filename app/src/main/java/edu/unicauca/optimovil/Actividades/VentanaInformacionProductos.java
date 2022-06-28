package edu.unicauca.optimovil.Actividades;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Objects;

import edu.unicauca.optimovil.Actividades.BDMeGusta.MeGustaBDHelper;
import edu.unicauca.optimovil.Actividades.Clases.Producto;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.fragments.BotonesFragment;

public class VentanaInformacionProductos extends AppCompatActivity{

    MeGustaBDHelper conn;
    ImageView iv_Producto;
    Button btn_Me_gusta;
    int resId;
    String resIDStr;
    boolean estaEnLista;
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
        conn = new MeGustaBDHelper(this);
        resIDStr = String.valueOf(resId);
        Consultar();
        btn_Me_gusta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Consultar();
                if(estaEnLista==false)
                {
                    conn.insertarProducto(resIDStr);
                    btn_Me_gusta.setText(R.string.btn_no_me_gusta);
                }
                if(estaEnLista==true)
                {
                    conn.BorrarProducto(resIDStr);
                    btn_Me_gusta.setText(R.string.btn_me_gusta);
                }

            }
        });
    }

    private void Consultar()
    {
        Cursor cursor = conn.GetProductosMeGusta();
        ArrayList<String> Lista= new ArrayList<String>();
        while (cursor.moveToNext())
        {
            Lista.add(cursor.getString(1));
        }
        for (int i = 0; i < Lista.size(); i++) {
            if(Lista.get(i).equals(resIDStr))
            {
                estaEnLista = true;
                btn_Me_gusta.setText(R.string.btn_no_me_gusta);
                i = Lista.size();
            }
            else
            {
                btn_Me_gusta.setText(R.string.btn_me_gusta);
                estaEnLista = false;
            }
        }
    }

    //private void getTasks() {
     /*   class GetTasks extends AsyncTask<Void, Void, List<ListaMeGusta>> {
        private OnTaskCompleted listener;

        public GetTasks(OnTaskCompleted listener){
            this.listener=listener;
        }
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
                listener.response(ListaProductosMeGusta);
                //System.out.println(ListaProductosMeGusta);
            }
        }
        //GetTasks getTasks = new GetTasks();
        //getTasks.execute();
        //System.out.println(ListaProductosMeGusta);

        //return ListaProductosMeGusta;

    //}
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
                //getTasks();
            }
        }

        SaveTask saveTask = new SaveTask();
        saveTask.execute();
    }*/

}