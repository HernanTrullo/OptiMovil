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

import edu.unicauca.optimovil.io.response.Category;

public class DbCategoriesHelper extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "categories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public DbCategoriesHelper(@Nullable Context context) {
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

    // insertar un nuevo registro categoria
    public long insertCategory(Category category){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,category.getId());
        values.put(COLUMN_NAME, category.getName());
        return db.insert(TABLE_NAME, null, values);
    }

    // insertar lista de categorias
    public void insertCategories(List<Category> categories){
        for(Category category : categories){
            insertCategory(category);
        }
    }

    // obtener una categoria por id
    public Category getCategoryById(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            return category;
        }
        return null;
    }

    // obtener todas las categorias
    public List<Category> getAllCategories() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            List<Category> categories = new ArrayList<>();
            do {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));
                categories.add(category);
            } while (cursor.moveToNext());
            return categories;
        }
        return null;
    }

    // limpiar la tabla de categorias e insertar las categorias nuevas
    public void clearAndInsertCategories(List<Category> categories){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        insertCategories(categories);
    }
}
