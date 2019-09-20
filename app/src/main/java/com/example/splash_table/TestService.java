package com.example.splash_table;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.example.splash_table.App.CHANNEL_ID;


public class TestService extends Service {
    private int contador = 15;

    @Override
    public void onCreate() {
        super.onCreate();
        //registro el broadcast para que pueda ser utilizado por la clase
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiverTestService, new IntentFilter("RESET_COUNTER"));

        Intent notificatioIntent = new Intent(this,PizzaTable.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,notificatioIntent,0);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("TestService")
                .setContentText("Temporizador de uso")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
    }

    /***
     * Metodo que inicia el service
     * @param intent el intent donde se manda la informacion
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //recoge el contador por medio del intent
        contador = intent.getIntExtra("Contador", 0);
        handler.postDelayed(validator, 1000);


        return  START_NOT_STICKY;

    }

    Handler handler = new Handler();

    private Runnable validator = new Runnable() {
        @Override
        public void run() {
            contador--;
            Log.d("CONTADOR", "contador: "+contador);
            if (contador == 0){
                handler.removeCallbacksAndMessages(null);
                Intent in = new Intent();
                in.putExtra("finish",true);
                in.setAction("FINISH");
                //sendBroadcast(in);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(in);
            } else {
                handler.postDelayed(this, 1000);
            }

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /***
     * BoradcastReceiver que recibe una peticion para volver a iniciar el contador
     */
    private BroadcastReceiver broadcastReceiverTestService = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int newCounter = intent.getIntExtra("counter", 0);  //get the type of message from MyGcmListenerService 1 - lock or 0 -Unlock
            contador = newCounter;
        }
    };
}

