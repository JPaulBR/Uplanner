package com.example.uplannerapk;

public class DatosGrabaciones {
    private String nombre;
    private String path;
    private String fecha;

    public DatosGrabaciones(String nombre, String path, String fecha) {
        this.nombre = nombre;
        this.path = path;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
