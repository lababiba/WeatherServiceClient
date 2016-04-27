package com.example.lababiba.weatherservice.Utils;

/**
 * Created by lexa on 27.04.16.
 */
public class TextCorrectionUtils {

    public static String getTemp(final String responce){
        String weath = "";

        for(int i=0;i<responce.length();i++)
        {
            if(responce.charAt(i)=='=')
            {
                for(int j=i+1;i<responce.length();j++)
                {

                    if(responce.charAt(j)==';') break;
                    weath+=responce.charAt(j);
                }
                return weath;
            }

        }
        return weath;
    }

    public static String getWeather(final String responce){
        String weath="";
        boolean firstMatch = false;
        for(int i=0;i<responce.length();i++)
        {
            if(responce.charAt(i)=='=')
            {
                if(!firstMatch) {
                    firstMatch = true;
                    continue;
                }

                for(int j=i+1;i<responce.length();j++)
                {

                    if(responce.charAt(j)==';') break;
                    weath+=responce.charAt(j);


                }
                return weath;
            }

        }
        return weath;
    }
}
