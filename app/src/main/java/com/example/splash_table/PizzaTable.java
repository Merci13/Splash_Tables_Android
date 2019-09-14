package com.example.splash_table;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

    /***
     * Metodo para recoger los datos de los textedit junto de los del radio button
     * despues los retorna al homeactivity para su procesamiento
     * @param v
     */
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
//Crear un servicio que:
    //Verfique si los edtitext estan en uso
    // Si no estan en uso iniciar un temporizador
    // iniciar el temporizador apenas se entre en la ventana
    //cerrar el activity si el temporizador llega a su limite.


}
