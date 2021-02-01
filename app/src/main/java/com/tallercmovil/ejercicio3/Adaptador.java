package com.tallercmovil.ejercicio3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tallercmovil.ejercicio3.API.DatoAPI;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    Context context;



    ArrayList<DatoAPI.PokemonURL> datos;

    private static LayoutInflater inflater = null;


    public Adaptador(Context context, ArrayList<DatoAPI.PokemonURL> datos) {
        this.context = context;
        this.datos = datos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {return position+1;
        /*return datos.get(position).getId();*/
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint({"ViewHolder", "InflateParams"}) final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView tvNombre = vista.findViewById(R.id.tvNombre);

        tvNombre.setText(
                (position + 1) +".- "+ "  " +
                    datos.get(position).getName().substring(0,1).toUpperCase() +
                        datos.get(position).getName().substring(1).toLowerCase());
        //para tenerlo  con el numero del pokemon y con la primera letra en mayusculas del nombre




        return vista;

    }
}
