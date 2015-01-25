package com.example.sadarik.visordeimagenes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;


public class Principal extends Activity {

    private GridView gvFotos;
    private ArrayList<String> direcciones;
    private Adaptador ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        iniciarComponentes();
    }

    public void iniciarComponentes(){
        direcciones=new ArrayList<String>();
        ad = new Adaptador(this,R.layout.item,direcciones);
        gvFotos = (GridView)findViewById(R.id.gridFotos);
        gvFotos.setAdapter(ad);
        registerForContextMenu(gvFotos);
        contentProvider();
        onClick();
    }

    public void onClick(){
        gvFotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(Principal.this, Fotos.class);
                intent.putExtra("direccion", direcciones.get(position));
                startActivity(intent);
            }
        });
    }

    public void contentProvider(){

        Uri uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] proyeccion = null;
        String condicion = null;
        String[] parametros = null;
        String orden = null;
        Cursor cursor=getContentResolver().query(
                uri,
                proyeccion,
                condicion,
                parametros,
                orden);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            direcciones.add(cursor.getString(1));
            cursor.moveToNext();
        }
    }

}
