package com.example.uplannerapk;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editar_Profesor extends AppCompatActivity {
    private EditText textProfesor,textTelefono,textCorreo,textConsulta,textObservaciones;
    private String dato1;
    private FloatingActionButton btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__profesor);

        textProfesor = (EditText)findViewById(R.id.idProfe);
        textTelefono = (EditText)findViewById(R.id.idTele);
        textCorreo = (EditText) findViewById(R.id.idCorreo);
        textConsulta = (EditText) findViewById(R.id.idConsulta);
        textObservaciones = (EditText) findViewById(R.id.idObservaciones);

        Bundle bundle = getIntent().getExtras();

        dato1 = bundle.getString("profe").toString();
        String dato2 = bundle.getString("telefono").toString();
        String dato3 = bundle.getString("correo").toString();
        String dato4 = bundle.getString("consultas").toString();
        String dato5 = bundle.getString("observaciones").toString();

        textProfesor.setText(dato1);
        textTelefono.setText(dato2);
        textCorreo.setText(dato3);
        textConsulta.setText(dato4);
        textObservaciones.setText(dato5);

        btn1=(FloatingActionButton)findViewById(R.id.fabAddP);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vacio()){
                    Toast.makeText(getApplication(),"No puede dejar espacios vacíos",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                    String inputTarea1 = textProfesor.getText().toString().trim();
                    String inputTarea2 = textTelefono.getText().toString().trim();
                    String inputTarea3 = textCorreo.getText().toString().trim();
                    String inputTarea4 = textConsulta.getText().toString().trim();
                    String inputTarea5 = textObservaciones.getText().toString().trim();
                    rata.eliminarProfes(dato1);
                    rata.agregarProfesor(inputTarea1,inputTarea3,inputTarea2,inputTarea4,inputTarea5);
                    Toast.makeText(getApplication(),"Profesor Actualizado",Toast.LENGTH_SHORT).show();
                    Intent btn = new Intent(Editar_Profesor.this,MainActivity.class);
                    startActivity(btn);
                }
            }
        });

        btn2=(FloatingActionButton)findViewById(R.id.fabDeleteP);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                rata.eliminarProfes(dato1);
                Toast.makeText(getApplication(),"Se ha eliminado el profesor "+dato1,Toast.LENGTH_SHORT).show();
                Intent btn = new Intent(Editar_Profesor.this,MainActivity.class);
                startActivity(btn);
            }
        });

    }

    public boolean vacio(){
        String inputTarea1 = textProfesor.getText().toString().trim();
        String inputTarea2 = textTelefono.getText().toString().trim();
        String inputTarea3 = textCorreo.getText().toString().trim();
        String inputTarea4 = textConsulta.getText().toString().trim();
        if (inputTarea1.isEmpty() || inputTarea2.isEmpty() || inputTarea3.isEmpty() || inputTarea4.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

}
