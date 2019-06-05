package com.example.uplannerapk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<DatosTareas> listaDatos;
    private View.OnClickListener listener;
    private static final String TAG = "AdaptadorDatos";

    public AdaptadorDatos(ArrayList<DatosTareas> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder (ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.call_fragment,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    /*public ArrayList<DatosTareas> getListaDatos(){
        return listaDatos;
    }*/

    @Override
    public void onBindViewHolder(ViewHolderDatos  holder, int position){
        holder.datoTarea.setText(listaDatos.get(position).getTarea());
        holder.datoFecha.setText(listaDatos.get(position).getFecha());
        holder.datoDescripcion.setText(listaDatos.get(position).getInfo());
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

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView datoTarea,datoFecha,datoDescripcion;

        public ViewHolderDatos(View itemView)
        {
            super(itemView);
            datoTarea=itemView.findViewById(R.id.idTarea);
            datoFecha=itemView.findViewById(R.id.idFecha);
            datoDescripcion=itemView.findViewById(R.id.idDescripcion);
        }

    }
}
