package com.example.splash_table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public String[] tiqueteAvion= new String[5];
    public List tiquete_de_avion = new ArrayList();
    /*
     * Nombre
     * Apellido
     * Cedula
     * Hora de salida
     * lugar de destino
     * */
    public List pizza = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     * Metodo para lanzar el activity pizza
     * @param v
     */
    public void onClickPizzaVentana(View v){
        Intent ventanaPizza= new Intent(this,PizzaTable.class);
        startActivity(ventanaPizza);



    }


    /**
    * Crear una nueva vista con una serie de inputs para que el usuario llene el pedido de pizza
     * debe tener
     * Nombre, Apellido, Direccion, numero de slices y mostrar el precio segun el tamanno de la
     * pizza
     * ademas, tiene que mostrarle una serie de sabores a escojer, ya se
     * Suprema, Jamon y hongos o Hauawana
    */


}
