package edu.unicauca.optimovil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import edu.unicauca.optimovil.Actividades.VentanaPrincipal;

public class Splash extends Activity {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        spinner = (ProgressBar)findViewById(R.id.progressBar);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, VentanaPrincipal.class);
                intent.putExtra("Cliente", "1");
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 5000);
    }
}