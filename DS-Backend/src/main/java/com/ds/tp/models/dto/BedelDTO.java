package com.ds.tp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BedelDTO {

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("contrasenia")
    private String contrasenia;

    @JsonProperty("confContrasenia")
    private String confContrasenia;

    @JsonProperty("email")
    private String email;

    @JsonProperty("turno")
    private int turno;

    // Getters y Setters

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getConfContrasenia() {
        return confContrasenia;
    }

    public void setConfContrasenia(String confContrasenia) {
        this.confContrasenia = confContrasenia;
    }
}