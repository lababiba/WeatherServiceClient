package com.example.lababiba.weatherservice.Widget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.lababiba.weatherservice.Controller.SharedPresencesController;
import com.example.lababiba.weatherservice.R;
import com.example.lababiba.weatherservice.Utils.TextCorrectionUtils;

public class MyWidget extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

         SharedPresencesController.getInstance().init(context);
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        //Создаем строку с текущей датой и временем


        final String response = SharedPresencesController.getInstance().getString("currWeatherResponse");
        Log.e("parse response", "parse");
        remoteViews.setTextViewText(R.id.textView,SharedPresencesController.getInstance().getString("currCity"));
        remoteViews.setTextViewText(R.id.textView2, TextCorrectionUtils.getTemp(response));
        remoteViews.setTextViewText(R.id.textView3, TextCorrectionUtils.getWeather(response));
        remoteViews.setImageViewResource(R.id.imageButton, getPicture(TextCorrectionUtils.getWeather(response)));

        for (int i : appWidgetIds)
            appWidgetManager.updateAppWidget(i, remoteViews);


        super.onUpdate(context, appWidgetManager, appWidgetIds);


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Widget", "Catch intent");
        //Ловим наш Broadcast
        final String action = intent.getAction();
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {
            AppWidgetManager gm = AppWidgetManager.getInstance(context);
            int[] ids = gm.getAppWidgetIds(new ComponentName(context, MyWidget.class));

            this.onUpdate(context, gm, ids);
        }

        super.onReceive(context, intent);
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



}