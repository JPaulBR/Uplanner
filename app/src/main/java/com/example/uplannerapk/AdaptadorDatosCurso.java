package com.example.uplannerapk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorDatosCurso extends RecyclerView.Adapter<AdaptadorDatosCurso.ViewHolderDatos2> implements View.OnClickListener {
    private ArrayList<DatosCursos> listaDatos;
    private View.OnClickListener listener;
    private static final String TAG = "AdaptadorDatosCurso";

    public AdaptadorDatosCurso(ArrayList<DatosCursos> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @Override
    public AdaptadorDatosCurso.ViewHolderDatos2 onCreateViewHolder (ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pagina1_fragment_design,null,false);
        view.setOnClickListener(this);
        return new AdaptadorDatosCurso.ViewHolderDatos2(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorDatosCurso.ViewHolderDatos2 holder, int position){
        holder.datoCurso.setText(listaDatos.get(position).getNombreCurso());
        holder.datoHorario.setText(listaDatos.get(position).getHorario());
        holder.datoPeriodo.setText(listaDatos.get(position).getPeriodo());
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

    public class ViewHolderDatos2 extends RecyclerView.ViewHolder{
        TextView datoCurso,datoHorario,datoPeriodo;

        public ViewHolderDatos2(View itemView)
        {
            super(itemView);
            datoCurso=itemView.findViewById(R.id.idCurso);
            datoHorario=itemView.findViewById(R.id.idHorario);
            datoPeriodo=itemView.findViewById(R.id.idPeriodo);
        }

    }
}
