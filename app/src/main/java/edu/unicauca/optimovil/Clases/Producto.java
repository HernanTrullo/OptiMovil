package edu.unicauca.optimovil.Clases;

public class Producto {
    private String nombre;
    private String description;
    private int foto;

    public Producto(String nombre, String description, int foto) {
        this.nombre = nombre;
        this.description = description;
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescription() {
        return description;
    }

    public int getFoto() {
        return foto;
    }
}
