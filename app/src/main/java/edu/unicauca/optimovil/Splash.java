package edu.unicauca.optimovil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.BaseDatosCLiente.Cliente;
import edu.unicauca.optimovil.BaseDatosCLiente.ClienteStrings;
import edu.unicauca.optimovil.BaseDatosCLiente.DbCLienteHelper;

public class Splash extends Activity {

    private ProgressBar spinner;
    private DbCLienteHelper dbCLienteHelper;
    private ClienteStrings cS;

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