package com.example.uplannerapk;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class RegistrarTarea extends AppCompatActivity {
    private TextInputLayout textTarea;
    private TextInputLayout textDescripcion;
    private TextView mDisplayDate;
    FloatingActionButton btn;
    private static final String TAG = "RegistrarTarea";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private final String CARPETA_RAIZ = "misImagenes/";
    private final String RUTA_IMAGEN = CARPETA_RAIZ+"misFotos";
    private final int COD_SELECCIONA = 10;
    private final int COD_FOTO = 20;
    private final String pathQ="";
    private Spinner comboCursos;
    private String cursoAB;
    private ArrayList<String> listaImagenes;
    private ArrayList<DatosCursos> cursosBD;
    private ArrayList<String> listaCursos;
    private ImageView imagenA;
    int contador = 0;
    private boolean fechaActivada=false;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tarea);
        listaImagenes = new ArrayList<String>();
        //listaImagenes.add("");listaImagenes.add("");listaImagenes.add("");listaImagenes.add("");
        textTarea = findViewById(R.id.text_tarea);
        textDescripcion = findViewById(R.id.text_descripcion);
        btn=(FloatingActionButton)findViewById(R.id.fab2);
        comboCursos = (Spinner)findViewById(R.id.spinnerCursos);
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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarTarea()){
                    return;
                }
                if (!fechaActivada){
                    Toast.makeText(getApplication(),"Ingrese la fecha del evento.",Toast.LENGTH_SHORT).show();
                    return;
                }
                BaseDeDatos rata = new BaseDeDatos(getApplicationContext());
                cursosBD = rata.mostrarCursos();
                String t=textTarea.getEditText().getText().toString().trim();
                String f=mDisplayDate.getText().toString().trim();
                String d=textDescripcion.getEditText().getText().toString().trim();
                rata.agregarTareas(t,d,f,cursoAB,"","","","");
                Toast.makeText(getApplication(),"Tarea agregada correctamente",Toast.LENGTH_SHORT).show();
                Intent btn = new Intent(RegistrarTarea.this,MainActivity.class);
                startActivity(btn);
            }
        });

        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrarTarea.this,
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
                fechaActivada = true;
            }
        };
    }

    private void agregarElementosCombo(){
        listaCursos = new ArrayList<String>();
        listaCursos.add("Seleccione un curso");
        for (int i=0;i<cursosBD.size();i++){
            listaCursos.add(cursosBD.get(i).getNombreCurso());
        }
    }

    private boolean validarTarea(){
        String inputTarea = textTarea.getEditText().getText().toString().trim();
        if (inputTarea.isEmpty()){
            textTarea.setError("Ingrese un valor");
            return false;
        }
        else{
            textTarea.setError(null);
            return true;
        }
    }

    public void onClickImagen (View view){
        cargarImagen();
    }

    private void cargarImagen(){
        if (contador==0){
            imagenA = (ImageView) findViewById(R.id.idImagen1);
        }
        if (contador==1){
            imagenA = (ImageView) findViewById(R.id.idImagen2);
        }
        if (contador==2){
            imagenA = (ImageView) findViewById(R.id.idImagen3);
        }
        if (contador==3){
            imagenA = (ImageView) findViewById(R.id.idImagen4);
            contador=0;
        }
        final CharSequence[] opciones = {"Tomar foto","Cargar imagen","Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(RegistrarTarea.this);
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
                    //listaImagenes.set(contador,miPath.toString());
                    //String url = miPath.toString();
                    listaImagenes.add(miPath.toString());
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

    private void AbrirCamara(){
        File fileImagen = new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";
        if (isCreada==false){
            isCreada=fileImagen.mkdirs();
        }
        if (isCreada==true){
            nombreImagen=(System.currentTimeMillis()/1000)+".jpg";
        }
        String pathQ= Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;
        File imagen = new File(pathQ);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imagen));
        startActivityForResult(intent,20);
    }

}
