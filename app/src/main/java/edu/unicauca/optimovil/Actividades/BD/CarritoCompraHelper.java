package edu.unicauca.optimovil.Actividades.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CarritoCompraHelper extends SQLiteOpenHelper {

    public static final String DataBase_Carrito = "carrito.db";
    public static final String Tabla_Carrito = "tabla_carrito";
    public static final String Campo_ID = "ID";
    public static final String Campo_foto = "foto";
    public CarritoCompraHelper(@Nullable Context context) {
        super(context, DataBase_Carrito, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ Tabla_Carrito+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,foto TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Carrito);
        onCreate(sqLiteDatabase);
    }
    public void insertarProductoaCarrito(String fotoID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Campo_foto,fotoID);
        db.insert(Tabla_Carrito,null,contentValues);
    }
    public Cursor GetProductosCarrito()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultado = db.rawQuery("SELECT * FROM "+Tabla_Carrito,null);
        return resultado;
    }
    public void BorrarProductoCarrito(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Tabla_Carrito,"foto =?",new String[] {id});
    }

}
