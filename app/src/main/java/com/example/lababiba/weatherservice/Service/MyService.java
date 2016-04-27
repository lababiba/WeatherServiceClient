package com.example.lababiba.weatherservice.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.lababiba.weatherservice.Controller.SharedPrefencesController;
import com.example.lababiba.weatherservice.Controller.WebClient;

/**
 * Created by lababiba on 14.04.16.
 */
public class MyService extends Service {
    String city;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        city = intent.getStringExtra("city");
        ServiceTask();
        return super.onStartCommand(intent, flags, startId);
    }

    private void ServiceTask() {
        final Context context = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(city!=null) {
                        WebClient webClient = new WebClient();
                        SharedPrefencesController.getInstance().init(context);
                        SharedPrefencesController.getInstance().putString("Weather",
                                webClient.getWeather(city)[0]);
                    }


                    try {
                     Thread.sleep(3600000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }).start();


    }
}
