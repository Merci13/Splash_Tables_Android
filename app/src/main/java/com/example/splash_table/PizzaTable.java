package com.example.splash_table;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PizzaTable extends AppCompatActivity {
    public final int CONSTANTE_CONTEO = 15;

    RadioGroup radioGroup;
    RadioButton radioButton;
    private Pedido orden = new Pedido();
    EditText nombrePizza;
    EditText apellidoPizza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_table);
        radioGroup = findViewById(R.id.radioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        setTitle("Pizza");
        startService();
        nombrePizza = findViewById(R.id.nombre_pizza);
        apellidoPizza = findViewById(R.id.apellido_pizza);

        //agregar los TextWatcher
        nombrePizza.addTextChangedListener(new myTexttwatcher());
        apellidoPizza.addTextChangedListener(new myTexttwatcher());

    }

    /***
     * Metodo para recoger los datos de los textedit junto de los del radio button
     * despues los retorna al homeactivity para su procesamiento
     * @param v
     */
    public void onclik_Pizza_data(View v) {
        //se recogen los datos de la ventana
        String name = ((EditText) findViewById(R.id.nombre_pizza)).getText().toString();
        String apellido = ((EditText) findViewById(R.id.apellido_pizza)).getText().toString();
        String opcionPizza = "Tipo de Pizza " + radioButton.getText();
        //se crea una lista con los datos recogidos
        orden.setName(name);
        orden.setApellido(apellido);
        orden.setTamanno(opcionPizza);

        //devolver los datos al activity principal.
        Intent result_pizza = new Intent();
        result_pizza.putExtra("orden", orden);

        setResult(RESULT_OK, result_pizza);
        finish();


    }


    //Crear un servicio que:
    //Verfique si los edtitext estan en uso
    // Si no estan en uso iniciar un temporizador
    // iniciar el temporizador apenas se entre en la ventana
    //cerrar el activity si el temporizador llega a su limite.

    public void startService() {
        //llama al servicio para que inicie el conteo hacia atras desde la constante
        // que esta arriba
        Intent serviceIntent = new Intent(this, TestService.class);
        serviceIntent.putExtra("Contador", CONSTANTE_CONTEO);
        startService(serviceIntent);


    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, TestService.class);
        stopService(serviceIntent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        startService();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("FINISH"));
    }

    @Override
    protected void onStop() {
        stopService();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean finish = intent.getBooleanExtra("finish", false);  //get the type of message from MyGcmListenerService 1 - lock or 0 -Unlock

            if (finish)
                finish();
        }
    };


    //--------------Metodos para el textWatcher----------------//

    private class myTexttwatcher implements TextWatcher {
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


        }


        /**
         * Metodo que vigila los cambios que se estan realizando en el momento
         */

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //cambiar el buleano enUso a true, porque se estan usando y detener el temporizador
            Intent in = new Intent();
            in.putExtra("counter",15);
            in.setAction("RESET_COUNTER");
            //sendBroadcast(in);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(in);
        }

        /*                                *
         * Inicia el hilo cuando se deja de usar
         *
         * */
        @Override
        public void afterTextChanged(Editable editable) {

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


}
