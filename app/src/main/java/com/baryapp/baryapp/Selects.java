package com.baryapp.baryapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.baryapp.baryapp.fragments.BarODiscoteca;


public class Selects extends Activity {

    ImageView prev;
    ImageView next;
    Fragment imagenes;

    TextView textoSup;
    //Se usara para cambiar las fuentes de los EditText
    Typeface fuente;

    //Variables para el cambio de las imagenes
    boolean primeraVista;
    boolean segundaVista;
    boolean terceraVista;
    boolean cuartaVista;
    boolean quintaVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selects);
        Window window = getWindow();
        //Cambia el color de la status bar en versiones de Android superiores a 20
        if (Build.VERSION.SDK_INT >= 21){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
        prev = (ImageView)findViewById(R.id.prev);
        next = (ImageView)findViewById(R.id.next);
        textoSup = (TextView)findViewById(R.id.texto_sup);
        imagenes = new BarODiscoteca();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentSelects, imagenes);
        fragmentTransaction.commit();

        primeraVista = true;
        segundaVista = false;
        terceraVista = false;
        cuartaVista = false;
        quintaVista = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar_odisco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
