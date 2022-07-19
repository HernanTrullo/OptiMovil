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

import edu.unicauca.optimovil.io.response.Product;

public class DbProductsHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_IMAGE_PATH = "image_path";
    private static final String COLUMN_CATEGORY_ID = "category_id";
    private static final String COLUMN_CATEGORY_NAME = "category";
    private static final String COLUMN_TYPE_ID = "type_id";
    private static final String COLUMN_TYPE_NAME = "type";
    private static final String COLUMN_STOCK = "stock";

    public DbProductsHelper(@Nullable Context context) {
        super(context, NAME_DATA_BASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_IMAGE + " INTEGER, " +
                COLUMN_IMAGE_PATH + " TEXT, " +
                COLUMN_CATEGORY_ID + " INTEGER, " +
                COLUMN_CATEGORY_NAME + " TEXT, " +
                COLUMN_TYPE_ID + " INTEGER, " +
                COLUMN_TYPE_NAME + " TEXT, " +
                COLUMN_STOCK + " INTEGER);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // insertar un nuevo registro producto
    public long insertProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, product.getId());
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_IMAGE, product.getImage());
        values.put(COLUMN_IMAGE_PATH, product.getImagePath());
        values.put(COLUMN_CATEGORY_ID, product.getCategoryId());
        values.put(COLUMN_CATEGORY_NAME, product.getCategory());
        values.put(COLUMN_TYPE_ID, product.getTypeId());
        values.put(COLUMN_TYPE_NAME, product.getType());
        values.put(COLUMN_STOCK, product.getStock());
        return db.insert(TABLE_NAME, null, values);
    }

    // insertar una lista de productos
    public void insertProducts(List<Product> products) {
        for (Product product : products) {
            insertProduct(product);
        }
    }

    // obtener un producto por id
    @SuppressLint("Range")
    public Product getProductById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)));
            product.setImage(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)));
            product.setImagePath(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH)));
            product.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
            product.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
            product.setTypeId(cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE_ID)));
            product.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_NAME)));
            product.setStock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)));
            cursor.close();
            db.close();
            return product;
        }
        cursor.close();
        db.close();
        return null;
    }

    // obtener una List de productos por categoria id y/o tipo id y/o nombre y/o precio y/o stock
    @SuppressLint("Range")
    public List<Product> getProducts(int categoryId, int typeId, String name, int price, int stock) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        query += " WHERE 1=1 ";

        if (categoryId != 0) {
            query += " AND " + COLUMN_CATEGORY_ID + " = " + categoryId;
        }
        if (typeId != 0) {
            query += " AND " + COLUMN_TYPE_ID + " = " + typeId;
        }
        if (name != null && !name.isEmpty()) {
            query += " AND " + COLUMN_NAME + " LIKE '%" + name + "%'";
        }
        if (price != 0) {
            query += " AND " + COLUMN_PRICE + " = " + price;
        }
        if (stock != 0) {
            query += " AND " + COLUMN_STOCK + " = " + stock;
        }
        Cursor cursor = db.rawQuery(query, null);
        List<Product> products = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            product.setImage(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)));
            product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)));
            product.setImagePath(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH)));
            product.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
            product.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
            product.setTypeId(cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE_ID)));
            product.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_NAME)));
            product.setStock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)));
            products.add(product);
        }
        cursor.close();
        db.close();
        return products;
    }

    // limpiar la tabla de productos y insertar una lista de productos
    public void clearAndInsertProducts(List<Product> products) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        insertProducts(products);
    }

}
