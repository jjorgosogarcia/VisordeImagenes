package com.example.sadarik.visordeimagenes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.io.File;


public class Fotos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
        ImageView iv=(ImageView)findViewById(R.id.ivPrincipal);

        String direccion = getIntent().getExtras().getString("direccion");
        File fichero = new File(direccion);
        Picasso.with(this).load(fichero).into(iv);
    }
}
