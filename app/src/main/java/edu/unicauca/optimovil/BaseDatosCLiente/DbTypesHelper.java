package edu.unicauca.optimovil.BaseDatosCLiente;

import static edu.unicauca.optimovil.io.autencticacion_api.Keys.DATABASE_VERSION;
import static edu.unicauca.optimovil.io.autencticacion_api.Keys.NAME_DATA_BASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.unicauca.optimovil.io.response.Type;

public class DbTypesHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "types";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public DbTypesHelper(@Nullable Context context) {
        super(context, NAME_DATA_BASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // insertar un nuevo registro type
    public long insertType(Type type){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,type.getId());
        values.put(COLUMN_NAME, type.getName());
        return db.insert(TABLE_NAME, null, values);
    }

    // insertar lista de types
    public void insertTypes(List<Type> types){
        for(Type type : types){
            insertType(type);
        }
    }

    // obtener un tipo por id
    public Type getTypeById(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            Type type = new Type();
            type.setId(cursor.getInt(0));
            type.setName(cursor.getString(1));
            return type;
        }
        return null;
    }
    // obtener todos los tipos
    public List<Type> getAllTypes(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            List<Type> types = new ArrayList<>();
            do{
                Type type = new Type();
                type.setId(cursor.getInt(0));
                type.setName(cursor.getString(1));
                types.add(type);
            }while(cursor.moveToNext());
            return types;
        }
        return null;
    }

    // Limpiar la tabla de tipos e insertar los tipos nuevos
    public void cleanAndInsertTypes(List<Type> types){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        insertTypes(types);
    }
}
