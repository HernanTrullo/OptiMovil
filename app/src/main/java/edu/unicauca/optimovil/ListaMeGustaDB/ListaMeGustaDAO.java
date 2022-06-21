package edu.unicauca.optimovil.ListaMeGustaDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListaMeGustaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertListaProductos(List<ListaMeGusta> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertListaProducto(ListaMeGusta item);

    @Update
    public void updateListaProducto(ListaMeGusta item);

    @Delete
    public void deleteListaProducto(ListaMeGusta item);

    @Query("SELECT * FROM ListaMeGusta ORDER BY timestamp DESC")
    public List<ListaMeGusta> loadAllItems();

    /*@Query("SELECT id FROM ListaMeGusta ORDER BY timestamp DESC")
    public ListaMeGusta load();*/

}