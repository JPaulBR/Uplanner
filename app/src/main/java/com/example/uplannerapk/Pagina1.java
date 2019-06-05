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

public class Pagina1 extends Fragment {
    private FloatingActionButton btn;
    ArrayList<DatosCursos> listaDatos;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_pagina1, container, false);
        btn=(FloatingActionButton) vista.findViewById(R.id.fabAddCursoP);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(getActivity(),AgregarCurso.class);
                startActivity(btn);
            }
        });
        BaseDeDatos rata = new BaseDeDatos(getContext());
        listaDatos = rata.mostrarCursos();

        recycler = (RecyclerView) vista.findViewById(R.id.recylerIdCurso);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorDatosCurso adapter = new AdaptadorDatosCurso(listaDatos);
        recycler.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Editar_Cursos.class);
                String curso = listaDatos.get(recycler.getChildAdapterPosition(v)).getNombreCurso().toString();
                String horario = listaDatos.get(recycler.getChildAdapterPosition(v)).getHorario().toString();
                String credito = listaDatos.get(recycler.getChildAdapterPosition(v)).getCreditos().toString();
                String periodo = listaDatos.get(recycler.getChildAdapterPosition(v)).getPeriodo().toString();
                intent.putExtra("curso",curso);
                intent.putExtra("horario",horario);
                intent.putExtra("credito",credito);
                intent.putExtra("periodo",periodo);
                startActivity(intent);
            }
        });

        return vista;
    }
}
