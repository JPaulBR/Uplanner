package com.example.uplannerapk;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Editar_Tareas extends AppCompatActivity {
    private EditText textTarea,textDescripcion,textCurso;
    private TextView mDisplayDate;
    private static final String TAG = "Editar_Tareas";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FloatingActionButton btn1,btn2;
    private String dato1;
    private Spinner comboCursos;
    private int contador = 0;
    private final String pathQ="";
    private String cursoAB;
    private ArrayList<String> listaImagenes;
    private ArrayList<DatosCursos> cursosBD;
    private ArrayList<String> listaCursos;
    private ImageView imagenA,imagenB,imagenC,imagenD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__tareas);
        textTarea = (EditText)findViewById(R.id.text_tareaE);
        textDescripcion = (EditText)findViewById(R.id.text_descripcionE);
        comboCursos = (Spinner)findViewById(R.id.spinnerCursosE);
        mDisplayDate = (TextView) findViewById(R.id.tvDateE);
        Bundle bundle = getIntent().getExtras();

        BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
        cursosBD = rata.mostrarCursos();
        agregarElementosCombo();
        ArrayAdapter<CharSequence> asapRocky = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listaCursos);
        comboCursos.setAdapter(asapRocky);
        comboCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cursoAB=listaCursos.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dato1 = bundle.getString("tarea").toString();
        String dato2 = bundle.getString("descrip").toString();
        String dato3 = bundle.getString("fecha").toString();

        listaImagenes = new ArrayList<String>();
        textTarea.setText(dato1);
        textDescripcion.setText(dato2);
        mDisplayDate.setText(dato3);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Editar_Tareas.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " +day+ "/" +month+ "/" + year);

                String date = day+"/"+ month +"/"+ year;
                mDisplayDate.setText(date);
            }
        };

        btn1=(FloatingActionButton)findViewById(R.id.fabAddE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vacio()){
                    Toast.makeText(getApplication(),"Ingrese una tarea",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                    String t=textTarea.getText().toString().trim();
                    String f=mDisplayDate.getText().toString().trim();
                    String d=textDescripcion.getText().toString().trim();
                    rata.eliminarTareas(dato1);
                    rata.agregarTareas(t,d,f,cursoAB,"","","","");
                    Toast.makeText(getApplication(),"Tarea Actualizada",Toast.LENGTH_SHORT).show();
                    Intent btn = new Intent(Editar_Tareas.this,MainActivity.class);
                    startActivity(btn);
                }
            }
        });

        btn2=(FloatingActionButton)findViewById(R.id.fabDeleteE);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                rata.eliminarTareas(dato1);
                Toast.makeText(getApplication(),"Tarea Eliminada",Toast.LENGTH_SHORT).show();
                Intent btn = new Intent(Editar_Tareas.this,MainActivity.class);
                startActivity(btn);
            }
        });

    }

    private void agregarElementosCombo(){
        listaCursos = new ArrayList<String>();
        listaCursos.add("Cambiar el curso");
        for (int i=0;i<cursosBD.size();i++){
            listaCursos.add(cursosBD.get(i).getNombreCurso());
        }
    }

    public boolean vacio(){
        String inputTarea = textTarea.getText().toString().trim();
        if(inputTarea.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public void onClickImagen (View view){
        cargarImagen();
    }

    private void cargarImagen(){
        if (contador==0){
            imagenA = (ImageView) findViewById(R.id.idImagen1E);
        }
        if (contador==1){
            imagenA = (ImageView) findViewById(R.id.idImagen2E);
        }
        if (contador==2){
            imagenA = (ImageView) findViewById(R.id.idImagen3E);
        }
        if (contador==3){
            imagenA = (ImageView) findViewById(R.id.idImagen4E);
        }
        final CharSequence[] opciones = {"Tomar foto","Cargar imagen","Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(Editar_Tareas.this);
        alertOpciones.setTitle("Seleccione una opción ");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (opciones[which].equals("Tomar foto")) {
                    Toast.makeText(getApplication(),"Opción disponible para algunos teléfonos",Toast.LENGTH_SHORT).show();
                    //AbrirCamara();
                }
                else{
                    if (opciones[which].equals("Cargar imagen")){
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"seleccione la aplicación"),10);
                    }
                    else{
                        dialog.dismiss();
                    }
                }
            }
        });
        alertOpciones.show();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==RESULT_OK){
            contador++;
            switch (requestCode){
                case 10:
                    Uri miPath = data.getData();
                    imagenA.setImageURI(miPath);
                    //String url = miPath.toString();
                    //listaImagenes.add(url);
                    break;
                case 20:
                    MediaScannerConnection.scanFile(this, new String[]{pathQ}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento","Path"+pathQ);
                        }
                    });
                    Bitmap bitmap = BitmapFactory.decodeFile(pathQ);
                    imagenA.setImageBitmap(bitmap);
                    break;
            }
        }
    }
}
