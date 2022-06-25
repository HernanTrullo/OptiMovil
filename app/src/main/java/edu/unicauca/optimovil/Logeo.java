package edu.unicauca.optimovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import edu.unicauca.optimovil.Actividades.VentanaClientes;
import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.Actividades.VentanaProducto;
import edu.unicauca.optimovil.BaseDatosCLiente.ClienteStrings;
import edu.unicauca.optimovil.BaseDatosCLiente.DbCLienteHelper;

public class Logeo extends AppCompatActivity {
    ClienteStrings cS;
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
                    // Se llama  a la funcion para que actualize el estado de la base datos a logeado
                    logeoBD(txtUser.getText().toString());
                    // Se crea el intent que llevara a la ventana de cleintes
                    Intent intent = new Intent(Logeo.this, VentanaClientes.class);
                    intent.putExtra(VentanaPrincipal.EXTRA_MENSAJE_CLIENTES, true);

                    // Mostrara mensaje de aviso
                    Toast.makeText( Logeo.this, R.string.txt_bienvenida , Toast.LENGTH_SHORT).show();

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
        boolean isValido = false;

        try{
           DbCLienteHelper dbCLienteHelper = new DbCLienteHelper(Logeo.this);
           SQLiteDatabase db = dbCLienteHelper.getWritableDatabase();
           Cursor cursorPass = dbCLienteHelper.isContraCorreoVal(db, pass, user);

            while (cursorPass.moveToNext()){
                isValido = true;
            }
            cursorPass.close();

       }catch (Exception e){

       }
       return isValido;
    }

    private void logeoBD(String correo){
        try{
            DbCLienteHelper dbCLienteHelper = new DbCLienteHelper(Logeo.this);
            SQLiteDatabase db = dbCLienteHelper.getWritableDatabase();
            dbCLienteHelper.clienteLogin(db,correo);
        }catch (Exception e){

        }
    }
    private void destruirActivity(){
        finish();
    }

}