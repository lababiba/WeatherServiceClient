package com.example.lababiba.weatherservice.Controller;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.lababiba.weatherservice.Interfaces.IWebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by lababiba on 14.04.16.
 */
public class WebClient implements IWebService {
    private static final String NAMESPACE = "http://Interfaces.Controller/";
    private static String URL="http://178.136.218.195:8080/weather?wsdl";
    private static final String METHOD_NAME = "getWeather";
    private static final String SOAP_ACTION =  "getWeather";

    public static Context c;


    private final SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);

        return envelope;
    }






    @Override
    public String[] getWeather(String city) {
        {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


            PropertyInfo propInfo = new PropertyInfo();
            propInfo.name = "arg0";
            propInfo.type = PropertyInfo.STRING_CLASS;
            request.addProperty("arg0", city);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);


                Log.e("casc",envelope.getResult().toString());
                return new String[]{envelope.getResponse().toString()};
            } catch (Exception soapFault) {
                soapFault.printStackTrace();
            }



        }
        return null;
    }
    @Override
    public String[] getCites() {
        return new String[0];
    }



    public static class getWeatherTask extends AsyncTask<String,Void,String[]>
    {


        @Override
        protected String[] doInBackground(String... params) {
           String[] weather = new WebClient().getWeather(params[0]);

           return weather;
        }

    }
}
