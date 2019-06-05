package com.example.uplannerapk;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdicionarProfesor extends AppCompatActivity {
    private FloatingActionButton btn;
    private TextInputLayout textProfesor;
    private TextInputLayout textTelefono;
    private TextInputLayout textCorreo;
    private TextInputLayout textConsultas;
    private TextInputLayout textObservaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_profesor);

        textProfesor = findViewById(R.id.idProfe);
        textTelefono = findViewById(R.id.idTelefono);
        textCorreo = findViewById(R.id.idCorreo);
        textConsultas = findViewById(R.id.idConsultas);
        textObservaciones = findViewById(R.id.idObservaciones);
        btn=(FloatingActionButton)findViewById(R.id.fabAddProfe);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarProfesor() || !validarTelefono() || !validarCorreo()){
                    return;
                }
                BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                String input1 = textProfesor.getEditText().getText().toString().trim();
                String input2 = textTelefono.getEditText().getText().toString().trim();
                String input3 = textCorreo.getEditText().getText().toString().trim();
                String input4 = textConsultas.getEditText().getText().toString().trim();
                String input5 = textObservaciones.getEditText().getText().toString().trim();
                rata.agregarProfesor(input1,input3,input2,input4,input5);
                Toast.makeText(getApplication(),"Profesor agregado correctamente",Toast.LENGTH_SHORT).show();
                Intent btn = new Intent(AdicionarProfesor.this,MainActivity.class);
                startActivity(btn);
            }
        });
    }

    private boolean validarProfesor(){
        String inputTarea = textProfesor.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textProfesor.setError("Ingrese un valor");
            return false;
        }
        else{
            textProfesor.setError(null);
            return true;
        }
    }

    private boolean validarTelefono(){
        String inputTarea = textTelefono.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textTelefono.setError("Ingrese un valor");
            return false;
        }
        else{
            textTelefono.setError(null);
            return true;
        }
    }

    private boolean validarCorreo(){
        String inputTarea = textCorreo.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textCorreo.setError("Ingrese un valor");
            return false;
        }
        else{
            textCorreo.setError(null);
            return true;
        }
    }

}
