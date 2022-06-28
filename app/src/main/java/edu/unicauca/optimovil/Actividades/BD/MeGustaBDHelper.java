package edu.unicauca.optimovil.Actividades.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class MeGustaBDHelper extends SQLiteOpenHelper {

    public static final String DataBase_Me_Gusta = "megusta.db";
    public static final String Tabla_Me_Gusta = "tabla_megusta";
    public static final String Campo_ID = "ID";
    public static final String Campo_foto = "foto";
    public MeGustaBDHelper(@Nullable Context context) {
        super(context, DataBase_Me_Gusta, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ Tabla_Me_Gusta+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,foto TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Me_Gusta);
        onCreate(sqLiteDatabase);
    }
    public void insertarProductoMeGusta(String fotoID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Campo_foto,fotoID);
        db.insert(Tabla_Me_Gusta,null,contentValues);
    }
    public Cursor GetProductosMeGusta()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultado = db.rawQuery("SELECT * FROM "+Tabla_Me_Gusta,null);
        return resultado;
    }
    public void BorrarProductoMeGusta(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Tabla_Me_Gusta,"foto =?",new String[] {id});
    }

}
