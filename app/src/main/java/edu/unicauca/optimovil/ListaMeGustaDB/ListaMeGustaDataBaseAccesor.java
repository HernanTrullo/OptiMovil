package edu.unicauca.optimovil.ListaMeGustaDB;

import android.content.Context;

import androidx.room.Room;

public class ListaMeGustaDataBaseAccesor {
    private static ListaMeGustaDataBase ListaMeGustaDatabaseInstance;
    //Constant about the name assigned to SQLite database
    private static final String MeGusta_DB_NAME = "megusta_db";

    private ListaMeGustaDataBaseAccesor() {
    }

    public static ListaMeGustaDataBase getInstance(Context context) {
        if (ListaMeGustaDatabaseInstance == null) {
// Create or open a new SQLite database, and return it as a Room Database instance.
            ListaMeGustaDatabaseInstance = Room.databaseBuilder(context,
                    ListaMeGustaDataBase.class, MeGusta_DB_NAME).build();
        }
        return ListaMeGustaDatabaseInstance;
    }
}