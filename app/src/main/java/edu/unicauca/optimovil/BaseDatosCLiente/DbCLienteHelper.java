package edu.unicauca.optimovil.BaseDatosCLiente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbCLienteHelper extends SQLiteOpenHelper {
    private static final int DATA_BASE_VERSION = 1;
    private static final String NAME_DATA_BASE = "cliente.db";
    private static final String TABLE_CLIENTE = "t_cliente";


    private ClienteStrings cS;
    private String CREATE_TABLE_CLIENTE = "CREATE TABLE "+ TABLE_CLIENTE + "("+
                                    cS.ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                    cS.NOMBRE1 + " TEXT NOT NULL,"+
                                    cS.NOMBRE2 + " TEXT,"+
                                    cS.APELLIDO1 + " TEXT NOT NULL,"+
                                    cS.APELLIDO2 + " TEXT,"+
                                    cS.CORREO + " TEXT NOT NULL,"+
                                    cS.CONTRA + " TEXT NOT NULL," +
                                    cS.LOGEADO + " TEXT NOT NULL);";

    private String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_CLIENTE;

    public DbCLienteHelper(@Nullable Context context) {
        super(context, NAME_DATA_BASE, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqL) {
        sqL.execSQL(CREATE_TABLE_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqL, int i, int i1) {
        sqL.execSQL(DROP_TABLE);
        onCreate(sqL);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertCliente(SQLiteDatabase db, String [] types){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(cS.NOMBRE1,types[0]);
        values.put(cS.NOMBRE2,types[1]);
        values.put(cS.APELLIDO1,types[2]);
        values.put(cS.APELLIDO2,types[3]);
        values.put(cS.CORREO,types[4]);
        values.put(cS.CONTRA,types[5]);
        values.put(cS.LOGEADO,types[6]);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_CLIENTE, null, values);
        return newRowId;
    }

    public Cursor isLogeado(SQLiteDatabase db){
        String[] projection = {
                cS.LOGEADO
        };
        // Filter results WHERE "logeado" = 'si'
        String selection = cS.LOGEADO + " = ?";
        String[] selectionArgs = { "si" }; // Indica que el usuario está logeado
        String sortOrder =
                cS.ID + " DESC";

        Cursor cursor = db.query(
               TABLE_CLIENTE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }
    public int clienteLogout(SQLiteDatabase db){
        // New value for one column
        String value = "no";
        ContentValues values = new ContentValues();
        values.put(cS.LOGEADO, value);

        // Which row to update, based on the title
        String selection = cS.LOGEADO + " = ?";
        String[] selectionArgs = { "si" };

        int count = db.update(
                TABLE_CLIENTE,
                values,
                selection,
                selectionArgs);
        return count;
    }
    public int clienteLogin(SQLiteDatabase db, String correo){
        // New value for one column
        String value = "si";
        ContentValues values = new ContentValues();
        values.put(cS.LOGEADO, value);

        // Which row to update, based on the title
        String selection = cS.CORREO + " = ?";
        String[] selectionArgs = { correo };

        int count = db.update(
                TABLE_CLIENTE,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public Cursor isContraCorreoVal (SQLiteDatabase db, String contra, String correo){
        String[] projection = {
                cS.CONTRA,
                cS.CORREO
        };
        // Filter results WHERE "logeado" = 'si'
        String selection = cS.CONTRA + " = ? AND " + cS.CORREO + " = ?";
        String[] selectionArgs = { contra , correo}; // Se compara la contraseña y correo
        String sortOrder =
                cS.ID + " DESC";

        Cursor cursor = db.query(
                TABLE_CLIENTE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }

}
