package com.baryapp.baryapp.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baryapp.baryapp.R;
import com.baryapp.baryapp.Selects;

public class BarODiscoteca extends Fragment implements View.OnClickListener{

    ImageView primeraImagen;
    ImageView segundaImagen;

    public BarODiscoteca() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_bar_odiscoteca, container, false);
        primeraImagen = (ImageView)v.findViewById(R.id.bar);
        segundaImagen = (ImageView)v.findViewById(R.id.discoteca);
        primeraImagen.setOnClickListener(this);
        segundaImagen.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentSelects, new Bebida());
        ft.commit();
    }


}
