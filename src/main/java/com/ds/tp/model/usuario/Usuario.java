package com.ds.tp.model.usuario;

public abstract class Usuario {
    //atributos
    protected long id;
    protected String usuario;
    protected String nombre;
    protected String apellido;
    protected String contrasenia;

    //getter/setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseniaa() {
        return contrasenia;
    }

    public void setContraseniaa(String contraseña) {
        this.contrasenia = contraseña;
    }



}
