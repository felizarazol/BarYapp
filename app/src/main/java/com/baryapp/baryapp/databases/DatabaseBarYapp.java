package com.baryapp.baryapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by estma_000 on 23/11/2015.
 */
public class DatabaseBarYapp extends SQLiteOpenHelper{

    private static final String NAME_DATABASE = "DatabaseBarYapp3.db";
    private static final int VERSION_DATABASE = 1;
    Context context;
    DatabaseBarYapp bdAyuda;
    SQLiteDatabase db;

    public DatabaseBarYapp(Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS profile_user(" +
                    "id_user_facebook TEXT NOT NULL PRIMARY KEY, " +
                    "nombre TEXT NOT NULL, " +
                    "bar_o_discoteca TEXT, " +
                    "bebida TEXT, " +
                    "fumar TEXT, " +
                    "musica TEXT, " +
                    "precio TEXT);");
            Log.e("CreateApp", "CREATE TABLE");
        }
        catch (Exception e){
            Log.e("ErrorBD", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Metodos para manejar la BD
    public void abrir(){
        bdAyuda = new DatabaseBarYapp(context);
        db = bdAyuda.getWritableDatabase();
    }

    public void cerrar(){
        db.close();
    }

    public long insertUsuario(String id_user_facebook, String nombre, String bar_o_discoteca,
                              String bebida, String fumar, String musica, String precio){
        ContentValues valores = new ContentValues();
        valores.put("id_user_facebook", id_user_facebook);
        valores.put("nombre", nombre);
        valores.put("bar_o_discoteca", bar_o_discoteca);
        valores.put("bebida", bebida);
        valores.put("fumar", fumar);
        valores.put("musica", musica);
        valores.put("precio", precio);

        return db.insert("profile_user", null, valores);
    }

    /*public long updateBarODiscoteca(){

    }

    public long updateBebida(){

    }

    public long updateFumar(){

    }

    public long updateMusica(){

    }

    public long updatePrecios(){

    }*/

}
