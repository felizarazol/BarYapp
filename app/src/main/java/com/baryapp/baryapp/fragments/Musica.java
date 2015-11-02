package com.baryapp.baryapp.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baryapp.baryapp.R;

public class Musica extends Fragment implements View.OnClickListener{

    ImageView rock;
    ImageView jazz;
    ImageView salsa;
    ImageView electronica;

    public Musica() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_musica, container, false);
        rock = (ImageView)v.findViewById(R.id.rock);
        jazz = (ImageView)v.findViewById(R.id.jazz);
        salsa = (ImageView)v.findViewById(R.id.salsa);
        electronica = (ImageView)v.findViewById(R.id.electronica);
        rock.setOnClickListener(this);
        jazz.setOnClickListener(this);
        salsa.setOnClickListener(this);
        electronica.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentSelects, new Precios());
        ft.commit();
    }
}
