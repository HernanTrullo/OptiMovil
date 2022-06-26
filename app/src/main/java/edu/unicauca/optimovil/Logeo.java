package edu.unicauca.optimovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import edu.unicauca.optimovil.Actividades.VentanaClientes;
import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.Actividades.VentanaProducto;

public class Logeo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

        // Se los elementos de la ventana con el script
        EditText txtUser = findViewById(R.id.txt_email);
        EditText txtPass = findViewById(R.id.txt_contrasena);
        Button btnLogeo = findViewById(R.id.btn_login);

        btnLogeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (esValidaUserPass(txtUser.getText().toString(), txtPass.getText().toString())){

                    Intent intent = new Intent(Logeo.this, VentanaClientes.class);

                    startActivity(intent);
                    destruirActivity();
                }
                else{
                    Toast.makeText( Logeo.this, R.string.txt_mensaje_error_logeo, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean esValidaUserPass(String user, String pass) {
        if (user.contains("trullodario@gmail.com") && pass.contains("1007587458")){

            return true;
        }
        else {
            return false;
        }

    }
    private void destruirActivity(){
        finish();
    }

}