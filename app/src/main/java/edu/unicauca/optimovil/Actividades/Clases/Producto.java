package edu.unicauca.optimovil.Actividades.Clases;

public class Producto {
    private String nombre;
    private String descripcion;
    private int foto;
    private int IDProducto;

    public Producto(String nombre, String descripcion, int foto, int ID) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.IDProducto = ID;
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

    public int getIDProducto() {
        return IDProducto;
    }
    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }
}
