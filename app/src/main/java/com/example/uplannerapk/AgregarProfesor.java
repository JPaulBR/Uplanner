package com.example.uplannerapk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class AgregarProfesor extends Fragment {
    private FloatingActionButton btn;
    ArrayList<DatosProfesores> listaDatos;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_agregar_profesor, container, false);
        btn=(FloatingActionButton) vista.findViewById(R.id.fabPP);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(getActivity(),AdicionarProfesor.class);
                startActivity(btn);
            }
        });
        BaseDeDatos rata = new BaseDeDatos(getContext());
        listaDatos = rata.mostrarProfes();

        recycler = (RecyclerView) vista.findViewById(R.id.recylerIdProfes);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorDatosProfesores adapter = new AdaptadorDatosProfesores(listaDatos);
        recycler.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Editar_Profesor.class);
                String profe = listaDatos.get(recycler.getChildAdapterPosition(v)).getProfesor().toString();
                String telefono = listaDatos.get(recycler.getChildAdapterPosition(v)).getTelefono().toString();
                String correo = listaDatos.get(recycler.getChildAdapterPosition(v)).getCorreo().toString();
                String consultas = listaDatos.get(recycler.getChildAdapterPosition(v)).getConsultas().toString();
                String observaciones = listaDatos.get(recycler.getChildAdapterPosition(v)).getObservaciones().toString();
                intent.putExtra("profe",profe);
                intent.putExtra("telefono",telefono);
                intent.putExtra("correo",correo);
                intent.putExtra("consultas",consultas);
                intent.putExtra("observaciones",observaciones);
                startActivity(intent);
            }
        });
        return vista;
    }
}
