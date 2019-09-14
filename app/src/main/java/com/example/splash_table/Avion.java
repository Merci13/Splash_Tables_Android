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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Avion extends AppCompatActivity{

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText editTextNumeroCedula;
    private EditText  editTextNombre;
    private EditText editTextApellido;
    private Tickete tickete = new Tickete();
    private myTexttwatcher myTexttwatcher;
    private int segundosSinUso = 15;//define el tiempo en el que no se usa la aplicacion inicia
                                    // el conteo apenas se abre el activity

    private Handler mainHandler = new Handler();
    ExampleThread thread;
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
        editTextNombre = findViewById(R.id.nombre_tickete);
        editTextApellido = findViewById(R.id.apellido_tickete);
        editTextNumeroCedula = findViewById(R.id.numeroDeCedula);
        myTexttwatcher = new myTexttwatcher();
        editTextNombre.addTextChangedListener(myTexttwatcher);
        editTextApellido.addTextChangedListener(myTexttwatcher);
        editTextNumeroCedula.addTextChangedListener(myTexttwatcher);



        thread = new ExampleThread(segundosSinUso);
        thread.start();

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




       class ExampleThread extends Thread{
        int segundosSinUso;
        ExampleThread(int segundosSinUso){
            this.segundosSinUso = segundosSinUso;
        }
         int conteo = 0;
        @Override
           public  void run(){

            while(conteo != segundosSinUso+1){
                //llamar a un metodo para asegurarse de que la IU esta en uso
                try {
                    Thread.sleep(1000);
                    String impresionSegundos = "segundos: "+ conteo;
                    Log.d("Tracer",impresionSegundos);
                    if(conteo == segundosSinUso){
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                    finish();//cierra el activity y se devuelve a la ventana home
                            }
                        });
                    }
                        conteo++;





                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }



        }
       }



            /**
             * clase modificado que implementa el TextWatcher
             * */

         private class myTexttwatcher implements  TextWatcher {
             private boolean enUso;

             myTexttwatcher(){

             }
             myTexttwatcher(boolean enUso){
                 this.enUso = enUso;
             }


             /*                                *
              * Inicia el hilo cuando se deja de usar
              *
              * */
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                thread.conteo = 0;
             }


             /**
              * Metodo que vigila los cambios que se estan realizando en el momento
              */

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 //cambiar el buleano enUso a true, porque se estan usando y detener el temporizador
                 thread.conteo = 0;
             }

             /*                                *
              * Inicia el hilo cuando se deja de usar
              *
              * */
             @Override
             public void afterTextChanged(Editable editable) {
                 thread.conteo = 0;
             }

             public boolean isEnUso() {
                 return enUso;
             }

             public void setEnUso(boolean enUso) {
                 this.enUso = enUso;
             }

             @Override
             public String toString() {
                 return "myTexttwatcher{" +
                         "enUso=" + enUso +
                         '}';
             }
         }








}//fin de la progra

