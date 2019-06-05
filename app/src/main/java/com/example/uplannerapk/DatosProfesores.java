package com.example.uplannerapk;

public class DatosProfesores {
    private String profesor;
    private String correo;
    private String telefono;
    private String consultas;
    private String observaciones;

    public DatosProfesores(String profesor, String correo, String telefono, String consultas, String observaciones) {
        this.profesor = profesor;
        this.correo = correo;
        this.telefono = telefono;
        this.consultas = consultas;
        this.observaciones = observaciones;
    }

    public String getConsultas() {
        return consultas;
    }

    public void setConsultas(String consultas) {
        this.consultas = consultas;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
