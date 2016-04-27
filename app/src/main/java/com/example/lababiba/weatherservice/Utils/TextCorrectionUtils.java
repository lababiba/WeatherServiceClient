package com.example.lababiba.weatherservice.Utils;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getCites(final String responce){
        List<String> list = new ArrayList<>();
        int iterator = 0;
        while (iterator<responce.length()-1)
        {
            if(responce.charAt(iterator)=='='){
                String result = "";
                int j=iterator+1;
                for(;j<responce.length();j++){
                    if(responce.charAt(j)==';') break;
                    result+=responce.charAt(j);
                }
                list.add(result);
                iterator = j;
            }


            iterator++ ;
        }



        return list;
    }
}
