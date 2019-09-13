package com.example.splash_table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Avion extends AppCompatActivity{

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText editTextNumeroCedula;
    private EditText  editTextNombre = findViewById(R.id.nombre_tickete);
    private EditText editTextApellido = findViewById(R.id.apellido_tickete);
    private Tickete tickete = new Tickete();
    private int segundosSinUso = 15;//define el tiempo en el que no se usa la aplicacion inicia
                                    // el conteo apenas se abre el activity

    private Handler mainHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avion);

        setTitle("Tickete Avion");

        //selecciono el radio grup de la vista
        radioGroup = findViewById(R.id.radioGroup2);
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
        String validarNumero = editTextNumeroCedula.getText().toString();
        int numeroCedula = Integer.parseInt(validarNumero.length() == 8 ? validarNumero : "0");
        String cantidadPersonas =""+radioButton.getText();
        if (name == "" || apelllido == "" || name == null || apelllido == null || numeroCedula == 0){

            mostrarMensaje("Favor introducir los datos solicitados");

        }else{
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

    /***
     * Muestra un mesaje del tipo Toast
     * @param mensaje String, mensaje que se desea mostrar
     */
    public void mostrarMensaje(String mensaje){

           Toast toast = Toast.makeText(getApplicationContext(),
                   mensaje,Toast.LENGTH_SHORT);
           toast.getView().setBackgroundColor(Color.rgb(199,233,158));

           toast.show();
       }

       public void empiezaConteoAtras(View v){
       /* for (int i = 0; i<segundosSinUso; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }*/

       ExampleThread thread = new ExampleThread(segundosSinUso);
       thread.start();

       }

       class ExampleThread extends Thread{
        int segundosSinUso;
        ExampleThread(int segundosSinUso){
            this.segundosSinUso = segundosSinUso;
        }
        @Override
           public  void run(){

            boolean used = true;
            for (int i = 0; i<segundosSinUso; i++){
                //llamar a un metodo para asegurarse de que la IU esta en uso
                used = enUso();
                if (used){
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
       }

       public boolean enUso(){
          boolean enUso = true;//variable a devolver

           /*
           * se comprueban cambios en los editText
           * */
           editTextNombre.addTextChangedListener(editTextCambios);
           editTextApellido.addTextChangedListener(editTextCambios);
           editTextNumeroCedula.addTextChangedListener(editTextCambios);



        return enUso;
       }
       private  TextWatcher editTextCambios = new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }


           /**
            * Metodo que vigila los cambios que se estan realizando en el momento
            *
            * */

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //cambiar el buleano enUso a true, porque se estan usando y detener el temporizador
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       };










}//fin de la progra

