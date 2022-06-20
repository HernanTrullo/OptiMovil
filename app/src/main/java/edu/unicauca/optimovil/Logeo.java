package edu.unicauca.optimovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class Logeo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

        // Se los elementos de la ventana con el script
        EditText txtUser = findViewById(R.id.txt_email);
        EditText txtPass = findViewById(R.id.txt_contrasena);

        Intent intent = getIntent();
    }


}