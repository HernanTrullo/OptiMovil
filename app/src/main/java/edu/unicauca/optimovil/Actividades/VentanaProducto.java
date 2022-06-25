package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.unicauca.optimovil.Actividades.Clases.AdaptadorProducto;
import edu.unicauca.optimovil.Actividades.Clases.Producto;

import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.Splash;

public class VentanaProducto extends AppCompatActivity {

    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO.INFORMACION";

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_producto);

        Intent intent = getIntent();
        boolean esLogeado = intent.getBooleanExtra(VentanaPrincipal.EXTRA_MENSAJE_PRO, false);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.lb_vp_titulo);


        listaProductos = new ArrayList<>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        llenarProductos();
        AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProducto.this);
        recyclerProductos.setAdapter(adapter);



        //loadTasks();
    }


    private void llenarProductos()
    {
        listaProductos.add(new Producto("Montura azúl","Esta montura es nueva",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura morada","Esta montura es nueva",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura roja","Esta montura es reparada",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura amarilla","Esta montura es nueva",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura verde","Esta montura es de vieja colección",R.drawable.imagen_prueba));
        listaProductos.add(new Producto("Montura plateada","Esta montura es nueva",R.drawable.imagen_prueba));

        TextView textViewCliente = findViewById(R.id.lb_vp_titulo);
        textViewCliente.setText("CLientes");

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();

    }

    private void loadTasks() {

        class LoadTasks extends AsyncTask<Void, Void, Void> {

            private static final String TAG = "ToDo";
            private ArrayList<Producto> jsonTodoItems;

            @Override
            protected Void doInBackground(Void... voids) {

                ArrayList<String> result = new ArrayList<>(0);
                String myFeed = getApplication().getString(R.string.url);
                try {
                    URL url = new URL(myFeed);
                    // Create a new HTTP URL connection
                    URLConnection connection = url.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        InputStream in = httpConnection.getInputStream();
                        jsonTodoItems = new ArrayList<>();
                        //Parse the answer in JSON format
                        parseJSON(in);
                    }
                    httpConnection.disconnect();
                } catch (MalformedURLException e) {

                    Log.e(TAG, "Malformed URL Exception.", e);

                } catch (IOException e) {
                    Log.e(TAG, "IO Exception.", e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //Update the tasks list after downloading the content from Internet
                listaProductos .clear();
                for (int i = 0; i < jsonTodoItems.size(); i++) {
                    listaProductos.add(jsonTodoItems.get(i).getTask());
                }
                AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,getBaseContext());
                recyclerProductos.setAdapter(adapter);
            }

            //Method to parse the tasks.json file available in th server
            private void parseJSON(InputStream in) throws IOException {
               // Create a new Json Reader to parse the input.
                JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                try {
                    //JSON file starts with an array
                    reader.beginArray();
                    while (reader.hasNext()) {
                        Producto item = new Producto();
                        //Parse a specific object inside the array
                        reader.beginObject();
                        while (reader.hasNext()) {
                            String value = reader.nextName();
                            //It gets the property value and store it on the correct property of ToDoItem object
                            switch (value) {
                                case "nombre":
                                    item.setNombre(reader.nextString());
                                    break;
                                case "id":
                                    item.setFoto(R.drawable.imagen_prueba);
                                    break;
                                case "descripcion":
                                    item.setDescripcion(reader.nextString());
                                default:
                                    reader.skipValue();
                                    break;
                            }
                        }
                        reader.endObject();
                        jsonTodoItems.add(item);
                    }
                    reader.endArray();
                } finally {
                    reader.close();
                }
            }

        }

        LoadTasks loadTasks = new LoadTasks();
        loadTasks.execute();
    }

}