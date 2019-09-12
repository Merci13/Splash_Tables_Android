package com.example.splash_table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Avion extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText editTextNumeroCedula;
    private Tickete tickete = new Tickete();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avion);

        setTitle("Tickete Avion");

        //selecciono el radio grup de la vista
        radioGroup = findViewById(R.id.radioGroup);
        //selecciono el radio id de que esta seleccionado en la vista
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        editTextNumeroCedula = findViewById(R.id.numeroDeCedula);

    }

    //se recogen los datos de la vista

    /***
     * Metodo para recoger los datos de la vista
     * @param v
     */
       public void ticketeData(View v){
        String name = ((EditText)findViewById(R.id.nombre_tickete)).getText().toString();
        String apelllido = ((EditText)findViewById(R.id.apellido_tickete)).getText().toString();
        int numeroCedula = Integer.parseInt(editTextNumeroCedula.getText().toString());

        String cantidadPersonas =""+radioButton.getText();
        //se crea el tickete con la informacion brindada
        tickete.setName(name);
        tickete.setApellido(apelllido);
        tickete.setCantidadPersonas(cantidadPersonas);
        tickete.setNumeroCedula(numeroCedula);

        //devolver los datos al activity principal
           Intent result_avion = new Intent();
           result_avion.putExtra("tickete",tickete);
           setResult(RESULT_OK,result_avion);
           finish();
       }



}
