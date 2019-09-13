package com.example.splash_table;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;




public class HomeActivity extends AppCompatActivity {

    public String[] tiqueteAvion= new String[5];
    public List tiquete_de_avion = new ArrayList();
    private Pedido orden = new Pedido();


    /*
     * Nombre
     * Apellido
     * Cedula
     * Hora de salida
     * lugar de destino
     * */
    public List <Pedido> pizza = new ArrayList<Pedido>();
    public  List<Tickete> ticketes = new ArrayList<>();



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
        startActivityForResult(ventanaPizza,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data.getParcelableExtra("orden") != null){
            Pedido ordenPizza = data.getParcelableExtra("orden");
            pizza.add(ordenPizza);
            Log.d("Test", pizza.toString());
        }else if (requestCode == 2 && resultCode == RESULT_OK && data.getParcelableExtra("tickete") != null) {
            Tickete ticketeAvion = data.getParcelableExtra("tickete");
            ticketes.add(ticketeAvion);
            Log.d("TestAvion",ticketes.toString());

            }
        }


    /***
     * Metodo para lanzar el activity de avion
     * @param v
     */
    public void onClickAvionVentana(View v){
        Intent ventatanAvion = new Intent(this,Avion.class);
        startActivityForResult(ventatanAvion,2);
    }





}//Fin de la progra
