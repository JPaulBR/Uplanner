package com.example.uplannerapk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {
    private static final String AGENDA_BD = "agenda1.bd";
    private static final int VERSION_BD=1;
    private static final String TABLA_RECORD = "CREATE TABLE RECORD(NOMBRE TEXT PRIMARY KEY,PATH TEXT,DATE TEXT)";
    private static final String TABLA_TAREAS = "CREATE TABLE TAREAS(TAREA TEXT PRIMARY KEY,FECHA TEXT,DESCRIPCION TEXT,CURSO TEXT,IMAGEN1 TEXT, IMAGEN2 TEXT, IMAGEN3 TEXT, IMAGEN4 TEXT )";
    private static final String TABLA_CURSOS = "CREATE TABLE CURSOS(CURSO TEXT PRIMARY KEY,HORARIO TEXT,CREDITO TEXT,PERIODO TEXT, PROFESOR TEXT)";
    private static final String TABLA_PROFESOR = "CREATE TABLE PROFESOR(PROFESOR TEXT PRIMARY KEY,TELEFONO TEXT,CORREO TEXT,CONSULTAS TEXT,OBSERVACIONES TEXT)";
    private static final String TABLA_MAPAS = "CREATE TABLE MAPA(LATITUD TEXT PRIMARY KEY,LONGITUD TEXT)";

    public BaseDeDatos(Context context) {
        super(context, AGENDA_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLA_TAREAS);
        sqLiteDatabase.execSQL(TABLA_CURSOS);
        sqLiteDatabase.execSQL(TABLA_PROFESOR);
        sqLiteDatabase.execSQL(TABLA_RECORD);
        sqLiteDatabase.execSQL(TABLA_MAPAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_TAREAS);
        sqLiteDatabase.execSQL(TABLA_TAREAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_CURSOS);
        sqLiteDatabase.execSQL(TABLA_CURSOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_PROFESOR);
        sqLiteDatabase.execSQL(TABLA_PROFESOR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_RECORD);
        sqLiteDatabase.execSQL(TABLA_RECORD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_MAPAS);
        sqLiteDatabase.execSQL(TABLA_MAPAS);
    }

    public void agregarTareas(String tarea,String fecha,String descripcion,String curso,String imagen1, String imagen2, String imagen3, String imagen4){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO TAREAS VALUES ('"+tarea+"','"+fecha+"','"+descripcion+"','"+curso+"','"+imagen1+"','"+imagen2+"','"+imagen3+"','"+imagen4+"')");
            bd.close();
        }
    }

    public ArrayList<DatosTareas> mostrarTareas(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM TAREAS",null);
        ArrayList<DatosTareas> tareasBD = new ArrayList<DatosTareas>();
        if (cursor.moveToFirst()){
            do{
                tareasBD.add(new DatosTareas(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7)));
            }while (cursor.moveToNext());
        }
        return tareasBD;
    }

    public void eliminarTareas(String id){
        SQLiteDatabase bd = getWritableDatabase();
        String[] parametro = {id};
        bd.delete("TAREAS","TAREA=?",parametro);
        bd.close();
    }

    public void agregarCursos(String curso,String horario,String creditos,String periodo,String profe){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO CURSOS VALUES ('"+curso+"','"+horario+"','"+creditos+"','"+periodo+"','"+profe+"')");
            bd.close();
        }
    }

    public ArrayList<DatosCursos> mostrarCursos(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CURSOS",null);
        ArrayList<DatosCursos> cursosBD = new ArrayList<DatosCursos>();
        if (cursor.moveToFirst()){
            do{
                cursosBD.add(new DatosCursos(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return cursosBD;
    }

    public void eliminarCursos(String id){
        SQLiteDatabase bd = getWritableDatabase();
        String[] parametro = {id};
        bd.delete("CURSOS","CURSO=?",parametro);
        bd.close();
    }

    public void agregarProfesor(String profe,String telefono,String correo,String consultas,String observaciones){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO PROFESOR VALUES ('"+profe+"','"+telefono+"','"+correo+"','"+consultas+"','"+observaciones+"')");
            bd.close();
        }
    }

    public ArrayList<DatosProfesores> mostrarProfes(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM PROFESOR",null);
        ArrayList<DatosProfesores> cursosBD = new ArrayList<DatosProfesores>();
        if (cursor.moveToFirst()){
            do{
                cursosBD.add(new DatosProfesores(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return cursosBD;
    }

    public void eliminarProfes(String id){
        SQLiteDatabase bd = getWritableDatabase();
        String[] parametro = {id};
        bd.delete("PROFESOR","PROFESOR=?",parametro);
        bd.close();
    }

    public void agregarGrabacion(String nombre,String grabacion,String fecha){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO RECORD VALUES ('"+nombre+"','"+grabacion+"','"+fecha+"')");
            bd.close();
        }
    }

    public ArrayList<DatosGrabaciones> mostrarGrabaciones(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM RECORD",null);
        ArrayList<DatosGrabaciones> cursosBD = new ArrayList<DatosGrabaciones>();
        if (cursor.moveToFirst()){
            do{
                cursosBD.add(new DatosGrabaciones(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return cursosBD;
    }

    public void agregarDireccion(String lat,String longi){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO MAPA VALUES ('"+lat+"','"+longi+"')");
            bd.close();
        }
    }

    public void eliminarDireccion(){
        SQLiteDatabase bd = getWritableDatabase();
        bd.delete("MAPA",null,null);
        bd.close();
    }

    public ArrayList<DatosMapas> mostrarDirecciones(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM MAPA",null);
        ArrayList<DatosMapas> cursosBD = new ArrayList<DatosMapas>();
        if (cursor.moveToFirst()){
            do{
                cursosBD.add(new DatosMapas(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return cursosBD;
    }

}
