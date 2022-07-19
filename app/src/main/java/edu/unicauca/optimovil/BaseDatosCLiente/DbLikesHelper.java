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

import java.util.ArrayList;
import java.util.List;

import edu.unicauca.optimovil.io.response.Like;

public class DbLikesHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "likes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_CLIENT_ID = "client_id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";

    public DbLikesHelper(@Nullable Context context) {
        super(context, NAME_DATA_BASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_ID + " INTEGER, " +
                COLUMN_CLIENT_ID + " INTEGER, " +
                COLUMN_PRODUCT_NAME + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Insertar un nuevo like
    public long insertLike(Like like) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, like.getId());
        values.put(COLUMN_PRODUCT_ID, like.getProductId());
        values.put(COLUMN_CLIENT_ID, like.getClientId());
        values.put(COLUMN_PRODUCT_NAME, like.getProductName());
        return db.insert(TABLE_NAME, null, values);
    }

    //Obtener todos los likes de un cliente id
    @SuppressLint("Range")
    public List<Like> getLikesByClientId(int clientId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CLIENT_ID + " = " + clientId;
        Cursor cursor = db.rawQuery(query, null);
        List<Like> likes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Like like = new Like();
                like.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                like.setProductId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                like.setClientId(cursor.getInt(cursor.getColumnIndex(COLUMN_CLIENT_ID)));
                like.setProductName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                likes.add(like);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return likes;
    }

    // remover un like por id
    public void removeLike(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // limpiar la tabla de likes e insertar los likes nuevos
    public void cleanAndInsert(List<Like> likes) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        for (Like like : likes) {
            insertLike(like);
        }
        db.close();
    }

}
