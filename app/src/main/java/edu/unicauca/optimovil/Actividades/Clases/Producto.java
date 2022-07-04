package edu.unicauca.optimovil.Actividades.Clases;

public class Producto {
    private String nombre;
    private String descripcion;
    private int foto;

    public Producto(String nombre, String descripcion, int foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
    }
    public Producto(){

    }

    public Producto(int imagen_prueba) {
        this.foto = imagen_prueba;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }


    public Producto getTask() {
        return this;
    }
}
