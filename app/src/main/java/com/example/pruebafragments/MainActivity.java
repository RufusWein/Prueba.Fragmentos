package com.example.pruebafragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.llamaFragmentoA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Evento","Frgamento A");
                cambiaFrame("A",new FragmentoA());
            }
        });

        findViewById(R.id.llamaFragmentoB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Evento","Frgamento B");
                cambiaFrame("B",new FragmentoB());
            }
        });
    }

    public void cambiaFrame(String nombre, Fragment fragmento){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int numFragmentos = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("Frame","Fragmentos Apilados = "+numFragmentos);
        if (numFragmentos>=1){ // Comprobamos la pila, y si existen más Fragments dentro entonces...
            // Comenta esta linea y compara el resultado sin comentar esta linea, cual es la conclusión?
            getSupportFragmentManager().popBackStack(); // Sacamos el de atrás de la pila (creo que era para evitar que cuando pulsaras el botón BACK volvieras a la vista anterior)

            ft.replace(R.id.contenedor, fragmento); // y reeplazamos
            ft.addToBackStack(nombre); // Le ponemos un nombre a ese Fragment

        } else {
            ft.add(R.id.contenedor, fragmento); // En caso de no existir ninguno simplemente añadimos
            ft.addToBackStack(nombre);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int numFragmentos = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("Frame","Haciendo Pop, fragmentos apilados = "+numFragmentos);

    }
}
