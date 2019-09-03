package com.example.splash_table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class PizzaTable extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
   private Pedido orden = new Pedido();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_table);
        radioGroup = findViewById(R.id.radioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        setTitle("Pizza");




    }


    public void onclik_Pizza_data( View v){
        //se recogen los datos de la ventana
        String name = ((EditText)findViewById(R.id.nombre_pizza)).getText().toString();
        String apellido = ((EditText)findViewById(R.id.apellido_pizza)).getText().toString();
        String opcionPizza="Tipo de Pizza "+radioButton.getText();
        //se crea una lista con los datos recogidos
        orden.setName(name);
        orden.setApellido(apellido);
        orden.setTamanno(opcionPizza);

        //devolver los datos al activity principal.
        Intent result_pizza = new Intent();
        result_pizza.putExtra("orden", orden);

        setResult(RESULT_OK,result_pizza);
        finish();


    }
//temporizador de uso
    //si llega a 0 cerrar la ventana y no guardar los datos.

}
