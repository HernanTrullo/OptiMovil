package edu.unicauca.optimovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import edu.unicauca.optimovil.Actividades.VentanaPrincipal;

public class Splash extends Activity {

    private ProgressBar spinner;
    private TextView NombreEmpresa;
    private TextView lema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        NombreEmpresa = findViewById(R.id.Nombre_aplicacion);
        lema = findViewById(R.id.Lema_aplicacion);
        NombreEmpresa.setText("Optivisión del norte");
        lema.setText(R.string.lema_app);
        lema.setEnabled(true);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, VentanaPrincipal.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 5000);
    }
}