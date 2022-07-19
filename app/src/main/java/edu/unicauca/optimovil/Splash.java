package edu.unicauca.optimovil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.BaseDatosCLiente.Cliente;
import edu.unicauca.optimovil.BaseDatosCLiente.ClienteStrings;
import edu.unicauca.optimovil.BaseDatosCLiente.DbCLienteHelper;
import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.response.Category;
import edu.unicauca.optimovil.io.response.Client;
import edu.unicauca.optimovil.io.response.Product;
import edu.unicauca.optimovil.io.response.Response;
import edu.unicauca.optimovil.io.response.Type;
import edu.unicauca.optimovil.io.services.ServicioApiCategories;
import edu.unicauca.optimovil.io.services.ServicioApiClients;
import edu.unicauca.optimovil.io.services.ServicioApiProducts;
import edu.unicauca.optimovil.io.services.ServicioApiToken;
import edu.unicauca.optimovil.io.response.Token;
import edu.unicauca.optimovil.io.services.ServicioApiTypes;
import retrofit2.Call;
import retrofit2.Callback;

public class Splash extends Activity {

    private ProgressBar spinner;
    private DbCLienteHelper dbCLienteHelper;
    private ClienteStrings cS;
    private TextView NombreEmpresa;
    private TextView lema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        spinner = (ProgressBar)findViewById(R.id.progressBar);


        boolean esLogeado = false;
        String log = "";
        try{
            dbCLienteHelper = new DbCLienteHelper(Splash.this);
            SQLiteDatabase db = dbCLienteHelper.getWritableDatabase();
            // Create a new map of values, where column names are the keys
                    /*String [] types = {"Hernan", "Dario", "Trullo", "Muñoz", "trullodario@gmail.com","1007587458", "no" };
                    String [] types2 = {"Hernan", "Dario", "Trullo", "Muñoz", "trullodario@unicauca.edu.co","1007587458", "no" };
                    dbCLienteHelper.insertCliente(db, types);
                    dbCLienteHelper.insertCliente(db, types2);*/
        NombreEmpresa = findViewById(R.id.Nombre_aplicacion);
        lema = findViewById(R.id.Lema_aplicacion);
        NombreEmpresa.setText("Optivisión del norte");
        lema.setText(R.string.lema_app);
        lema.setEnabled(true);

            // Revisar si el usuario estpa logeado
            Cursor cursor = dbCLienteHelper.isLogeado(db);
            Cliente cliente = new Cliente(R.drawable.imagen_prueba);

            while (cursor.moveToNext()){
                log = cursor.getString(cursor.getColumnIndexOrThrow(cS.LOGEADO));
            }
            cursor.close();

            if (Objects.equals(log, "si")){
                Toast.makeText(Splash.this, "es logeado", Toast.LENGTH_SHORT).show();
                esLogeado = true;
            }

        }catch (Exception e){
            Toast.makeText(Splash.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        boolean finalEsLogeado = esLogeado;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // incio: Crear base de datos
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TOKEN",e.getMessage());
                }
                Intent intent = new Intent(Splash.this, VentanaPrincipal.class);
                intent.putExtra(VentanaPrincipal.EXTRA_MENSAJE_PRINCIP, finalEsLogeado);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 5000);

    }
}