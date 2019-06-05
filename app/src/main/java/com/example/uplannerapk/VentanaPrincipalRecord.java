package com.example.uplannerapk;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class VentanaPrincipalRecord extends Fragment {
    private FloatingActionButton btn_recorder;
    ArrayList<DatosGrabaciones> listaDatos;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_ventana_principal_record, container, false);
        btn_recorder = (FloatingActionButton) vista.findViewById(R.id.fabR);
        btn_recorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(getActivity(),Grabador.class);
                startActivity(btn);
            }
        });
        BaseDeDatos rata = new BaseDeDatos(getContext());
        listaDatos = rata.mostrarGrabaciones();

        recycler = (RecyclerView) vista.findViewById(R.id.recylerIdGraba);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        AdaptadorDatosGrabaciones adapter = new AdaptadorDatosGrabaciones(listaDatos);
        recycler.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(listaDatos.get(recycler.getChildAdapterPosition(v)).getPath());
                    mediaPlayer.prepare();
                } catch (IOException e){
                }
                mediaPlayer.start();
                Toast.makeText(getContext(), "Reproduciendo audio", Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }



}
