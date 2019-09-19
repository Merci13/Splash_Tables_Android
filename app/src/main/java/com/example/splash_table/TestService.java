package com.example.splash_table;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.example.splash_table.App.CHANNEL_ID;


public class TestService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
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
       int contador = Integer.parseInt(intent.getStringExtra("Contador"));

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

        // inicia el contador
        int conteo = 0;
        while(conteo != contador+1){
            //llamar a un metodo para asegurarse de que la IU esta en uso
            try {
                Thread.sleep(1000);
                String impresionSegundos = "segundos: "+ conteo;
                Log.d("Tracer",impresionSegundos);
                if(conteo == contador){
                    //cierra el activity y detener el service

                    //detiene el service
                    stopSelf();

                }
                conteo++;

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }



        return  START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
