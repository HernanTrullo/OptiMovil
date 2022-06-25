package edu.unicauca.optimovil.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.unicauca.optimovil.BaseDatosCLiente.DbCLienteHelper;
import edu.unicauca.optimovil.Logeo;
import edu.unicauca.optimovil.R;
import edu.unicauca.optimovil.Splash;

public class VentanaClientes extends AppCompatActivity {
    boolean esLogeado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_clientes);

        Intent intent = getIntent();
        esLogeado = intent.getBooleanExtra(VentanaPrincipal.EXTRA_MENSAJE_CLIENTES, false);


        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.lb_vc_titulo);
        textView.setText(R.string.lb_cliente);

        TextView textViewNombreCliente = findViewById(R.id.nombre_cliente);
        textViewNombreCliente.setText("nombre 1");

        // Botón de log out
        Button btnLogOut = findViewById(R.id.btn_logout);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    DbCLienteHelper dbCLienteHelper = new DbCLienteHelper(VentanaClientes.this);
                    SQLiteDatabase db = dbCLienteHelper.getWritableDatabase();

                    Toast.makeText(VentanaClientes.this, R.string.txt_sesion_cerrada, Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, VentanaPrincipal.class);
        intent.putExtra(VentanaPrincipal.EXTRA_MENSAJE_PRINCIP, esLogeado);
        startActivity(intent);
    }
}