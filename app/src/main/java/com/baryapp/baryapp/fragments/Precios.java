package com.baryapp.baryapp.fragments;

import android.app.FragmentTransaction;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baryapp.baryapp.R;

public class Precios extends Fragment implements View.OnClickListener{

    ImageView next;
    ImageView bajoP;
    ImageView medioP;
    ImageView altoP;

    public Precios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_precios, container, false);
        next = (ImageView)v.findViewById(R.id.next);
        bajoP=(ImageView)v.findViewById(R.id.btBajo);
        medioP=(ImageView)v.findViewById(R.id.btMedio);
        altoP=(ImageView)v.findViewById(R.id.btAlto);
        next.setOnClickListener(this);
        bajoP.setOnClickListener(this);
        medioP.setOnClickListener(this);
        altoP.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentSelects, new Fumar());
        ft.commit();
    }
}
