package edu.unicauca.optimovil.ListaMeGustaDB;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListaMeGusta {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String task;
    private long timestamp;

    public ListaMeGusta(String _task) {
        this(_task, java.lang.System.currentTimeMillis());
    }

    public ListaMeGusta(String task, long timestamp) {
        this.task = task;
        this.timestamp = timestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }


    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public long getTimestamp() {
        return java.lang.System.currentTimeMillis();
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}