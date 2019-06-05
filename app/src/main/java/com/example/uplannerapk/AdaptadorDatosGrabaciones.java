package com.example.uplannerapk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorDatosGrabaciones extends RecyclerView.Adapter<AdaptadorDatosGrabaciones.ViewHolderDatos4> implements View.OnClickListener{

    private ArrayList<DatosGrabaciones> listaDatos;
    private View.OnClickListener listener;
    private static final String TAG = "AdaptadorDatosGrabaciones";

    public AdaptadorDatosGrabaciones(ArrayList<DatosGrabaciones> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @Override
    public AdaptadorDatosGrabaciones.ViewHolderDatos4 onCreateViewHolder (ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design_grabacion,null,false);
        view.setOnClickListener(this);
        return new AdaptadorDatosGrabaciones.ViewHolderDatos4(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorDatosGrabaciones.ViewHolderDatos4 holder, int position){
        holder.datoNombreG.setText(listaDatos.get(position).getNombre());
        holder.datoFechaG.setText(listaDatos.get(position).getFecha());
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

    public class ViewHolderDatos4 extends RecyclerView.ViewHolder{
        TextView datoNombreG,datoFechaG;

        public ViewHolderDatos4(View itemView)
        {
            super(itemView);
            datoNombreG=itemView.findViewById(R.id.idGrabacion);
            datoFechaG=itemView.findViewById(R.id.idFecha);
        }
    }

}
