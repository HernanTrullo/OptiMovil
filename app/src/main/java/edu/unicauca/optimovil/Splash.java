package edu.unicauca.optimovil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import edu.unicauca.optimovil.Actividades.VentanaLogin;
import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.BaseDatosCLiente.Cliente;
import edu.unicauca.optimovil.BaseDatosCLiente.ClienteStrings;
import edu.unicauca.optimovil.BaseDatosCLiente.DbCLienteHelper;
import edu.unicauca.optimovil.BaseDatosCLiente.DbCategoriesHelper;
import edu.unicauca.optimovil.BaseDatosCLiente.DbClientsHelper;
import edu.unicauca.optimovil.BaseDatosCLiente.DbProductsHelper;
import edu.unicauca.optimovil.BaseDatosCLiente.DbTypesHelper;
import edu.unicauca.optimovil.io.autencticacion_api.Keys;
import edu.unicauca.optimovil.io.response.Category;
import edu.unicauca.optimovil.io.response.Client;
import edu.unicauca.optimovil.io.response.Product;
import edu.unicauca.optimovil.io.response.Response;
import edu.unicauca.optimovil.io.response.Token;
import edu.unicauca.optimovil.io.response.Type;
import edu.unicauca.optimovil.io.services.ServicioApiCategories;
import edu.unicauca.optimovil.io.services.ServicioApiClients;
import edu.unicauca.optimovil.io.services.ServicioApiProducts;
import edu.unicauca.optimovil.io.services.ServicioApiToken;
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
            getToken();

        }catch (Exception e){
            Toast.makeText(Splash.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void getTypes() {
        Call<Response<List<Type>>> get_types = ServicioApiTypes.getAppServicio().getTypes("");
            get_types.enqueue(new Callback<Response<List<Type>>>() {
                @Override
                public void onResponse(Call<Response<List<Type>>> call, retrofit2.Response<Response<List<Type>>> response) {
                    if(response.isSuccessful()){
                        Response<List<Type>> listTypes = response.body();
                        if(listTypes != null && listTypes.getStatus() == 1){
                            Gson gson = new Gson();
                            for (Type type: listTypes.getData()) {
                                Log.i("TYPE", "type : "+gson.toJson(type));
                            }
                            DbTypesHelper dbTypesHelper = new DbTypesHelper(Splash.this);
                            dbTypesHelper.insertTypes(listTypes.getData());

                        }
                    }
                }

                @Override
                public void onFailure(Call<Response<List<Type>>> call, Throwable t) {

                }
            });
    }

    public void getToken() throws Exception{

        Call<Response<Token>> call_token = ServicioApiToken.getAppServicio().obtenrerTokenAceso(Keys.secret);
        try {
            call_token.enqueue(new Callback<Response<Token>>() {
                @Override
                public void onResponse(Call<Response<Token>> call, retrofit2.Response<Response<Token>> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null) {
                            if(response.body().getStatus().equals(1)){
                                Keys.beaber_token=response.body().getData().getAccessToken();
                                getTypes();
                                getCategories();
                                getProducts();
                                DbClientsHelper dbClientsHelper = new DbClientsHelper(Splash.this);
                                Client cliente = dbClientsHelper.getFirstClient();
                                    Intent intent = new Intent(Splash.this, VentanaPrincipal.class);
                                    intent.putExtra(VentanaPrincipal.EXTRA_MENSAJE_PRINCIP, cliente != null);
                                    startActivity(intent);
                                Log.i("TOKEN_ACCESO", Keys.beaber_token);
                                finish();
                            }else{
                                Log.e("TOKEN_ACCESO", "No se obtuvo algun tokken");
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Response<Token>> call, Throwable t) {
                    Toast.makeText(Splash.this, "A ocurrido un error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    (new Timer()).schedule(null, Toast.LENGTH_LONG+1);
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TOKEN",e.getMessage());
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private void getProducts() {
        Call<Response<List<Product>>> call_products = ServicioApiProducts.getAppServicio().getProducts();
        call_products.enqueue(new Callback<Response<List<Product>>>() {
            @Override
            public void onResponse(Call<Response<List<Product>>> call, retrofit2.Response<Response<List<Product>>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Response<List<Product>> listProducts = response.body();
                        if(listProducts.getStatus() == 1){
                            Gson gson = new Gson();
                            for (Product product: listProducts.getData()) {
                                Log.i("Product", "product : "+gson.toJson(product));
                            }
                            DbProductsHelper dbProductsHelper = new DbProductsHelper(Splash.this);
                            dbProductsHelper.insertProducts(listProducts.getData());
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<Response<List<Product>>> call, Throwable t) {

            }
        });
    }

    private void getCategories() {
        Call<Response<List<Category>>> call_categories = ServicioApiCategories.getAppServicio().getTypes("");
        call_categories.enqueue(new Callback<Response<List<Category>>>() {
            @Override
            public void onResponse(Call<Response<List<Category>>> call, retrofit2.Response<Response<List<Category>>> response) {
                if(response.isSuccessful()){
                    Response<List<Category>> listCategories = response.body();
                    if(listCategories != null && listCategories.getStatus() == 1){
                        Gson gson = new Gson();
                        for (Category category: listCategories.getData()) {
                            Log.i("Category", "category : "+gson.toJson(category));
                        }
                        DbCategoriesHelper dbCategoriesHelper = new DbCategoriesHelper(Splash.this);
                        dbCategoriesHelper.insertCategories(listCategories.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Category>>> call, Throwable t) {

            }
        });
    }
}