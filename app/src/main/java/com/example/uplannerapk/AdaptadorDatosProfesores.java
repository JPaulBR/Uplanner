package com.example.uplannerapk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorDatosProfesores extends RecyclerView.Adapter<AdaptadorDatosProfesores.ViewHolderDatos3> implements View.OnClickListener  {
    private ArrayList<DatosProfesores> listaDatos;
    private View.OnClickListener listener;
    private static final String TAG = "AdaptadorDatosProfesores";

    public AdaptadorDatosProfesores(ArrayList<DatosProfesores> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @Override
    public AdaptadorDatosProfesores.ViewHolderDatos3 onCreateViewHolder (ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_profesor,null,false);
        view.setOnClickListener(this);
        return new AdaptadorDatosProfesores.ViewHolderDatos3(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorDatosProfesores.ViewHolderDatos3 holder, int position){
        holder.datoProfesor.setText(listaDatos.get(position).getProfesor());
        holder.datoCorreo.setText(listaDatos.get(position).getCorreo());
        holder.datoTelefono.setText(listaDatos.get(position).getTelefono());
    }

    @Override
    public int getItemCount(){
        return listaDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view){
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos3 extends RecyclerView.ViewHolder{
        TextView datoProfesor,datoCorreo,datoTelefono;

        public ViewHolderDatos3(View itemView)
        {
            super(itemView);
            datoProfesor=itemView.findViewById(R.id.idProfesor);
            datoCorreo=itemView.findViewById(R.id.idCorreo);
            datoTelefono=itemView.findViewById(R.id.idTelef);
        }

    }

}
