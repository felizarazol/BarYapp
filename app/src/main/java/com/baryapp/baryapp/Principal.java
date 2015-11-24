package com.baryapp.baryapp;
 import android.app.Activity;
 import android.content.Intent;
 import android.os.Build;
 import android.os.Bundle;
 import android.view.View;
 import android.view.Window;
 import android.view.WindowManager;

/**
 * Created by Andres on 23/11/2015.
 */
public class Principal extends Activity implements View.OnClickListener {
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registrate:
                finish();
                Intent registro = new Intent(Principal.this, Registro.class);
                startActivity(registro);
            case R.id.btn_entrar:
                finish();
                Intent entrar = new Intent(Principal.this, MapsActivity.class);
                startActivity(entrar);
                break;
        }

    }
}