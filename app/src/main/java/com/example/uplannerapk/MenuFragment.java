package com.example.uplannerapk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuFragment extends Fragment{
    ArrayList<DatosTareas> listaDatos;
    RecyclerView recycler;
    FloatingActionButton btn;
    public ImageView mDeleteImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_menu, container, false);
        btn=(FloatingActionButton) vista.findViewById(R.id.fab1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(getActivity(),RegistrarTarea.class);
                startActivity(btn);
            }
        });
        BaseDeDatos rata = new BaseDeDatos(getContext());
        listaDatos = rata.mostrarTareas();

        recycler = (RecyclerView) vista.findViewById(R.id.recylerId);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorDatos adapter = new AdaptadorDatos(listaDatos);
        recycler.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Editar_Tareas.class);
                String tareilla = listaDatos.get(recycler.getChildAdapterPosition(v)).getTarea().toString();
                String descripc = listaDatos.get(recycler.getChildAdapterPosition(v)).getInfo().toString();
                String fechaE = listaDatos.get(recycler.getChildAdapterPosition(v)).getFecha().toString();
                intent.putExtra("tarea",tareilla);
                intent.putExtra("descrip",descripc);
                intent.putExtra("fecha",fechaE);
                startActivity(intent);
            }
        });

        return vista;
    }

}
