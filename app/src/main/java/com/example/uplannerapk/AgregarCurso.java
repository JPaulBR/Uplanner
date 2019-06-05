package com.example.uplannerapk;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AgregarCurso extends AppCompatActivity {
    private FloatingActionButton btn;
    private TextInputLayout textCurso;
    private TextInputLayout textHorario;
    private TextInputLayout textCreditos;
    private TextInputLayout textPeriodo;
    private Spinner comboBoxProfesor;
    private ArrayList<String> listaProfesores;
    private ArrayList<DatosProfesores> profesBD;
    private String profeAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_curso);
        textCurso = findViewById(R.id.idCurso);
        textHorario = findViewById(R.id.idHorario);
        textCreditos = findViewById(R.id.idCreditos);
        textPeriodo = findViewById(R.id.idPeriodo);
        comboBoxProfesor = findViewById(R.id.spinnerProfesor);
        BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
        profesBD = rata.mostrarProfes();
        agregarElementosCombo();
        ArrayAdapter<CharSequence> asapRocky = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listaProfesores);
        comboBoxProfesor.setAdapter(asapRocky);
        comboBoxProfesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profeAB=listaProfesores.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn=(FloatingActionButton)findViewById(R.id.fabAddCurso);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarCurso() || !validarHorario() || !validarCreditos() || !validarPeriodo()){
                    return;
                }
                BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                String n=textCurso.getEditText().getText().toString().trim();
                String h=textHorario.getEditText().getText().toString().trim();
                String c=textCreditos.getEditText().getText().toString().trim();
                String p=textPeriodo.getEditText().getText().toString().trim();
                rata.agregarCursos(n,h,c,p,profeAB);
                Toast.makeText(getApplication(),"Curso agregado correctamente",Toast.LENGTH_SHORT).show();
                Intent btn = new Intent(AgregarCurso.this,MainActivity.class);
                startActivity(btn);
            }
        });
    }

    private void agregarElementosCombo(){
        listaProfesores = new ArrayList<String>();
        listaProfesores.add("Seleccione un profesor");
        for (int i=0;i<profesBD.size();i++){
            listaProfesores.add(profesBD.get(i).getProfesor());
        }
    }

    private boolean validarCurso(){
        String inputTarea = textCurso.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textCurso.setError("Ingrese un valor");
            return false;
        }
        else{
            textCurso.setError(null);
            return true;
        }
    }

    private boolean validarHorario(){
        String inputTarea = textHorario.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textHorario.setError("Ingrese un valor");
            return false;
        }
        else{
            textHorario.setError(null);
            return true;
        }
    }

    private boolean validarCreditos(){
        String inputTarea = textCreditos.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textCreditos.setError("Ingrese un valor");
            return false;
        }
        else{
            textCreditos.setError(null);
            return true;
        }
    }

    private boolean validarPeriodo(){
        String inputTarea = textPeriodo.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textPeriodo.setError("Ingrese un valor");
            return false;
        }
        else{
            textPeriodo.setError(null);
            return true;
        }
    }

}
