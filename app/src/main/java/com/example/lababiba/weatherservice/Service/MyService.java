package com.example.lababiba.weatherservice.Service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lababiba.weatherservice.Controller.SharedPresencesController;
import com.example.lababiba.weatherservice.Controller.WebClient;

/**
 * Created by lababiba on 14.04.16.
 */
public class MyService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceTask();
        return super.onStartCommand(intent, flags, startId);
    }

    private void ServiceTask() {
        final Context context = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                        final WebClient webClient = WebClient.getInstance();

                        SharedPresencesController.getInstance().init(context);
                        SharedPresencesController.getInstance().putString("currWeatherResponse",
                                webClient.getWeather(SharedPresencesController.getInstance().getString("currCity"))[0]);

                        Intent intent = new Intent();
                        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                        context.sendBroadcast(intent);

                    Log.e("Service Running", "Thread Sleep");
                    try {
                     Thread.sleep(3600000);
                    } catch (InterruptedException e) {
                        Log.e("Thread","Thread HALT");
                    }


                }
            }
        }).start();


    }
}
