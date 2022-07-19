package edu.unicauca.optimovil.BaseDatosCLiente;

import static edu.unicauca.optimovil.io.autencticacion_api.Keys.DATABASE_VERSION;
import static edu.unicauca.optimovil.io.autencticacion_api.Keys.NAME_DATA_BASE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import edu.unicauca.optimovil.io.response.Client;

public class DbClientsHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "clients";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SECOND_NAME = "second_name";
    public static final String COLUMN_SECOND_LAST_NAME = "second_last_name";
    public static final String COLUMN_FIRST_LAST_NAME = "first_last_nam";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_PASSWORD = "password";

    public DbClientsHelper(@Nullable Context context) {
        super(context, NAME_DATA_BASE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SECOND_NAME + " TEXT, " +
                COLUMN_SECOND_LAST_NAME + " TEXT, " +
                COLUMN_FIRST_LAST_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_PASSWORD + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // insertar un nuevo registro cliente
    public long insertClient(Client client){
        SQLiteDatabase db = getWritableDatabase();
        //eliminar registros anteriores
        db.delete(TABLE_NAME, null, null);

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,client.getId());
        values.put(COLUMN_NAME, client.getName());
        values.put(COLUMN_SECOND_NAME, client.getSecondName());
        values.put(COLUMN_SECOND_LAST_NAME, client.getSecondLastName());
        values.put(COLUMN_FIRST_LAST_NAME, client.getFirstLastNam());
        values.put(COLUMN_EMAIL, client.getEmail());
        values.put(COLUMN_PHONE_NUMBER, client.getPhoneNumber());
        values.put(COLUMN_PASSWORD, client.getPassword());

        return db.insert(TABLE_NAME, null, values);
    }

    // obtener cliente por id
    @SuppressLint("Range")
    public Client getClienteById(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" = "+id;
        String [] types = new String[7];
        Client client = new Client();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            client.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            client.setSecondName(cursor.getString(cursor.getColumnIndex(COLUMN_SECOND_NAME)));
            client.setSecondLastName(cursor.getString(cursor.getColumnIndex(COLUMN_SECOND_LAST_NAME)));
            client.setFirstLastNam(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_LAST_NAME)));
            client.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            client.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
        }
        cursor.close();
        return client;
    }

    // obtener el primer cliente
    @SuppressLint("Range")
    public Client getFirstClient(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" LIMIT 1";
        Client client = null;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            client = new Client();
            client.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            client.setSecondName(cursor.getString(cursor.getColumnIndex(COLUMN_SECOND_NAME)));
            client.setSecondLastName(cursor.getString(cursor.getColumnIndex(COLUMN_SECOND_LAST_NAME)));
            client.setFirstLastNam(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_LAST_NAME)));
            client.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            client.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
        }
        cursor.close();
        return client;
    }
}
