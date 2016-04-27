package com.example.lababiba.weatherservice.Models;

/**
 * Created by lababiba on 14.04.16.
 */
public class ModelWeather {
    int temp;
    String city;
    String weather;

    public ModelWeather(String city, int temp, String weather) {
        this.city = city;
        this.temp = temp;
        this.weather = weather;
    }
}
