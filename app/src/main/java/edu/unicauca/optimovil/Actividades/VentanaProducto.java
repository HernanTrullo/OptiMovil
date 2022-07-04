package edu.unicauca.optimovil.Actividades;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import edu.unicauca.optimovil.fragments.BotonesFragment;

import edu.unicauca.optimovil.fragments.BotonesFragment;

//import edu.unicauca.optimovil.Splash;


public class VentanaProducto extends AppCompatActivity {

    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO.INFORMACION";

    ArrayList<Producto> listaProductos;
    RecyclerView recyclerProductos;
    ImageButton ibtn_carritoCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_producto);
        ibtn_carritoCompra = findViewById(R.id.CarritoDeCompra);
        Intent intent = getIntent();

        boolean esLogeado = intent.getBooleanExtra(VentanaPrincipal.EXTRA_MENSAJE_PRO, false);

        // Capture the layout's TextView and set the string as its text

        //TextView textView = findViewById(R.id.lb_vp_titulo);



        String message = intent.getStringExtra("Mensaje");
        ibtn_carritoCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(VentanaProducto.this,VentanaCarrito.class);
                startActivity(intent1);
            }
        });
        // Capture the layout's TextView and set the string as its text
        listaProductos = new ArrayList<Producto>();
        recyclerProductos = findViewById(R.id.RecyclerViewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        llenarProductos(message);
        AdaptadorProducto adapter = new AdaptadorProducto(listaProductos,VentanaProducto.this);
        recyclerProductos.setAdapter(adapter);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_btn_prod, BotonesFragment.class, null)
                .commit();



        //loadTasks();

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

    @Override
    protected void onResume() {
        super.onResume();
        //loadTasks();

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