package com.example.uplannerapk;

public class DatosCursos {
    private String nombreCurso;
    private String horario;
    private String creditos;
    private String periodo;
    private String profesor;

    public DatosCursos(String nombreCurso, String horario, String creditos, String periodo, String profesor) {
        this.nombreCurso = nombreCurso;
        this.horario = horario;
        this.creditos = creditos;
        this.periodo = periodo;
        this.profesor = profesor;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
