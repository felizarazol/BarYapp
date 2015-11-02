package com.baryapp.baryapp.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baryapp.baryapp.R;

public class Precios extends Fragment implements View.OnClickListener{

    ImageView next;

    public Precios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_precios, container, false);
        next = (ImageView)v.findViewById(R.id.next);
        next.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentSelects, new Fumar());
        ft.commit();
    }
}
