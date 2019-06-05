package com.example.uplannerapk;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Editar_Cursos extends AppCompatActivity {
    private EditText textCurso,textHorario,textCeditos,textPeriodo;
    private String dato1;
    private FloatingActionButton btn1,btn2;
    private Spinner comboBoxProfesor;
    private ArrayList<String> listaProfesores;
    private ArrayList<DatosProfesores> profesBD;
    private String profeAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__cursos);

        textCurso = (EditText)findViewById(R.id.idCurso);
        textHorario = (EditText)findViewById(R.id.idHorario);
        textCeditos = (EditText) findViewById(R.id.idCreditos);
        textPeriodo = (EditText) findViewById(R.id.idPeriodo);
        Bundle bundle = getIntent().getExtras();

        comboBoxProfesor = findViewById(R.id.spinnerP);
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

        dato1 = bundle.getString("curso").toString();
        String dato2 = bundle.getString("horario").toString();
        String dato3 = bundle.getString("credito").toString();
        String dato4 = bundle.getString("periodo").toString();

        textCurso.setText(dato1);
        textHorario.setText(dato2);
        textCeditos.setText(dato3);
        textPeriodo.setText(dato4);

        btn1=(FloatingActionButton)findViewById(R.id.fabAddC);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vacio()){
                    Toast.makeText(getApplication(),"No puede dejar espacios vacíos",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                    rata.eliminarCursos(dato1);
                    String inputTarea1 = textCurso.getText().toString().trim();
                    String inputTarea2 = textHorario.getText().toString().trim();
                    String inputTarea3 = textCeditos.getText().toString().trim();
                    String inputTarea4 = textPeriodo.getText().toString().trim();
                    rata.agregarCursos(inputTarea1,inputTarea2,inputTarea3,inputTarea4,profeAB);
                    //rata.agregarCursos(inputTarea1,inputTarea2,inputTarea3,inputTarea4);
                    Toast.makeText(getApplication(),"Curso Actualizado",Toast.LENGTH_SHORT).show();
                    Intent btn = new Intent(Editar_Cursos.this,MainActivity.class);
                    startActivity(btn);
                }
            }
        });

        btn2=(FloatingActionButton)findViewById(R.id.fabDeleteC);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                rata.eliminarCursos(dato1);
                Toast.makeText(getApplication(),"Tarea Eliminada",Toast.LENGTH_SHORT).show();
                Intent btn = new Intent(Editar_Cursos.this,MainActivity.class);
                startActivity(btn);
            }
        });
    }

    private void agregarElementosCombo(){
        listaProfesores = new ArrayList<String>();
        listaProfesores.add("Cambiar profesor");
        for (int i=0;i<profesBD.size();i++){
            listaProfesores.add(profesBD.get(i).getProfesor());
        }
    }

    public boolean vacio(){
        String inputTarea1 = textCurso.getText().toString().trim();
        String inputTarea2 = textHorario.getText().toString().trim();
        String inputTarea3 = textCeditos.getText().toString().trim();
        String inputTarea4 = textPeriodo.getText().toString().trim();
        if (inputTarea1.isEmpty() || inputTarea2.isEmpty() || inputTarea3.isEmpty() || inputTarea4.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

}
