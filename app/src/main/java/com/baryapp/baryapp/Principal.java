package com.baryapp.baryapp;
 import android.app.Activity;
 import android.content.Intent;
 import android.os.Build;
 import android.os.Bundle;
 import android.view.View;
 import android.view.Window;
 import android.view.WindowManager;
 import android.widget.ImageView;

 import com.facebook.login.widget.LoginButton;

/**
 * Created by Andres on 23/11/2015.
 */
public class Principal extends Activity implements View.OnClickListener {
    private ImageView btCercaMi;
    private ImageView btEncuentro;
    private ImageView btPromo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Window window = getWindow();
        //Cambia el color de la status bar en versiones de Android superiores a 20
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
        btCercaMi = (ImageView)findViewById(R.id.btCercaDeMi);
        btEncuentro = (ImageView)findViewById(R.id.btEncuentro);
        btPromo = (ImageView)findViewById(R.id.btPromoYapp);
        btCercaMi.setOnClickListener(this);
        btEncuentro.setOnClickListener(this);
        btPromo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btCercaDeMi:
                finish();
                Intent cercaMi = new Intent(Principal.this, MapsActivity.class);
                startActivity(cercaMi);
            case R.id.btEncuentro:
                finish();
                Intent pEncuentro = new Intent(Principal.this, MapsActivity.class);
                startActivity(pEncuentro);
                break;
            case R.id.btPromoYapp:
                finish();
                Intent promoYapp = new Intent(Principal.this, MapsActivity.class);
                startActivity(promoYapp);
                break;
        }

    }
}