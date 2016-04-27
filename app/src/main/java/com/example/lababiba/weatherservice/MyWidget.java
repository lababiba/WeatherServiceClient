package com.example.lababiba.weatherservice;


import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.lababiba.weatherservice.Controller.SharedPrefencesController;
import com.example.lababiba.weatherservice.Controller.WebClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWidget extends AppWidgetProvider {
    public int counter = 1;
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

            SharedPrefencesController.getInstance().init(context);



        //вызываем переопределенный метод родительского класса
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        if( !SharedPrefencesController.getInstance().getString("Weather").equals(null)){
            String weather = SharedPrefencesController.getInstance().getString("Weather");
            Intent intent = new Intent();
            intent.setAction(ACTION_WIDGET_RECEIVER);
            intent.putExtra("Weather",weather);

            context.sendBroadcast(intent);


        }
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        //Создаем строку с текущей датой и временем
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        String now = dateFormat.format(date);
        //обновляем данные в TextView виджета
        remoteViews.setTextViewText(R.id.textView2,getTemperature() );
        remoteViews.setTextViewText(R.id.textView3,getWeatherInstance() );
        remoteViews.setImageViewResource(R.id.imageButton, getPicture(getWeatherInstance()));

        Intent update = new Intent(context, MyWidget.class);
        update.setAction("Update");

        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, R.id.imageButton, update, 0);
        remoteViews.setOnClickPendingIntent(R.id.imageButton, actionPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);


    }

        private int getPicture(String weather) {

            switch (weather) {

                case "Ясно":
                    return R.drawable.weather_sunny;

                case "Легкий дождь":
                    return R.drawable.weather_rainy;

                case "Небольшой дождь":
                    return R.drawable.weather_rainy;

                case "Дождь":
                    return R.drawable.weather_pouring;

                case "Туман":
                    return R.drawable.weather_fog;

                case "Небольшая облачность":
                    return R.drawable.weather_partlycloudy;

                case "Переменная облачность":
                    return R.drawable.weather_partlycloudy;

                case "Облачно":
                    return R.drawable.weather_cloudy;

                case "Небольшой снег":
                    return R.drawable.weather_snowy;

                case "Рваные облака":
                    return R.drawable.weather_partlycloudy;


            }
            return 0;
        }


public String getTemperature (){
    String weath=new String();
    String weathInst;
    weathInst = SharedPrefencesController.getInstance().getString("Weather");
    boolean firstmatch = true;
    for(int i=0;i<weathInst.length();i++)
    {
        if(weathInst.charAt(i)=='=')
        {
            if(!firstmatch) {

                firstmatch = true;
                continue;
            }

            for(int j=i+1;i<weathInst.length();j++)
            {

                if(weathInst.charAt(j)==';') break;
                weath+=weathInst.charAt(j);


            }
            return weath;
        }

    }
    return weath;
}

    public String getWeatherInstance () {
        String weath=new String();
        String weathInst;
        weathInst = SharedPrefencesController.getInstance().getString("Weather");
        boolean firstmatch = false;
        for(int i=0;i<weathInst.length();i++)
        {
            if(weathInst.charAt(i)=='=')
            {
                if(!firstmatch) {

                    firstmatch = true;
                    continue;
                }

                for(int j=i+1;i<weathInst.length();j++)
                {

                    if(weathInst.charAt(j)==';') break;
                    weath+=weathInst.charAt(j);


                }
                return weath;
            }

        }
        return weath;
    }







    public class getWeatherTask extends AsyncTask<Object,Void,String[]>{
        private Context context ;
        @Override
        protected String[] doInBackground(Object... params) {
            WebClient web = new WebClient();
            String[] s = web.getWeather((String)params[0]);
            context = (Context)params[1];
            return s;
        }

        @Override
        protected void onPostExecute(String[] strings) {

            Toast.makeText(context, strings[0],Toast.LENGTH_LONG).show();
            //remoteViews.setTextViewText(R.id.textView3, now);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);

        Log.d("kkk", "onReceive");





        //Ловим наш Broadcast
        final String action = intent.getAction();
        if (ACTION_WIDGET_RECEIVER.equals(action)) {
            Toast.makeText(context,intent.getStringExtra("Weather"),Toast.LENGTH_LONG).show();

            AppWidgetManager gm = AppWidgetManager.getInstance(context);
            int[] ids = gm.getAppWidgetIds(new ComponentName(context, MyWidget.class));
            this.onUpdate(context, gm, ids);
        }

        if(action.equals("Update")){
            AppWidgetManager gm = AppWidgetManager.getInstance(context);
            int[] ids = gm.getAppWidgetIds(new ComponentName(context, MyWidget.class));
            Toast.makeText(context,intent.getStringExtra("Weather"),Toast.LENGTH_LONG).show();
            this.onUpdate(context, gm, ids);

        }
    }
}