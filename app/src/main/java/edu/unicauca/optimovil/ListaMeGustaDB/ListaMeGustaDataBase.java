package edu.unicauca.optimovil.ListaMeGustaDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ListaMeGusta.class}, version = 1)
public abstract class ListaMeGustaDataBase extends RoomDatabase {
    public abstract ListaMeGustaDAO listaMeGustaDAO();
}

