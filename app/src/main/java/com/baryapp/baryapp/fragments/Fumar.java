package com.baryapp.baryapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baryapp.baryapp.Inicio;
import com.baryapp.baryapp.Principal;
import com.baryapp.baryapp.R;

public class Fumar extends Fragment implements View.OnClickListener{

    ImageView noFumar;
    ImageView fumar;

    public Fumar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fumar, container, false);
        noFumar = (ImageView)v.findViewById(R.id.noFumar);
        fumar = (ImageView)v.findViewById(R.id.fumar);
        noFumar.setOnClickListener(this);
        fumar.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
        Intent principal = new Intent(getActivity().getBaseContext(), Principal.class);
        startActivity(principal);
    }
}
