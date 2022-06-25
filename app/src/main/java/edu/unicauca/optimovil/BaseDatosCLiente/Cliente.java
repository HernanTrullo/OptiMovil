package edu.unicauca.optimovil.BaseDatosCLiente;

public class Cliente {
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apelledo2;
    private String correo;
    private String contra;
    private int id;
    private int foto;

    public Cliente(String nombre1, String nombre2, String apellido1, String apelledo2, String correo, String contra, int id, int foto) {
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apelledo2 = apelledo2;
        this.correo = correo;
        this.contra = contra;
        this.id = id;
        this.foto = foto;
    }

    public Cliente() {

    }

    public Cliente(int foto) {
        this.foto = foto;
    }

    public String getNombre1() {
        return nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApelledo2() {
        return apelledo2;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContra() {
        return contra;
    }

    public int getId() {
        return id;
    }

    public int getFoto() {
        return foto;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApelledo2(String apelledo2) {
        this.apelledo2 = apelledo2;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
