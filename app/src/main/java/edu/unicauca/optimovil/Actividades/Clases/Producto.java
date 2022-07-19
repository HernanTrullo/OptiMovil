package edu.unicauca.optimovil.Actividades.Clases;

public class Producto {
    private String nombre;
    private String descripcion;
    private String foto;
    private int IDProducto;

    public Producto(String nombre, String descripcion, String foto, int ID) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.IDProducto = ID;
    }
    public Producto(){

    }

    public Producto(String imagen_prueba) {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public Producto getTask() {
        return this;
    }
    public int getIDProducto()
    {
        return IDProducto;
    }
    public void setIDProducto(int IDProducto)
        {
        this.IDProducto = IDProducto;
    }
}
