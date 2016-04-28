package com.example.lababiba.weatherservice.Controller;


import android.util.Log;

import com.example.lababiba.weatherservice.Interfaces.IWebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by lababiba on 14.04.16.
 */
public class WebClient implements IWebService {
    private static volatile WebClient instance;

    public static WebClient getInstance() {
        WebClient localInstance = instance;
        if (localInstance == null) {
            synchronized (WebClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new WebClient();
                }
            }
        }
        return localInstance;
    }

    private WebClient(){}

    private static final String NAMESPACE = "http://Interfaces.Controller/";
    private static String URL="http://192.168.0.102:8080/weather?wsdl";
    private static final String METHOD_NAME_WEATHER = "getWeather";
    private static final String SOAP_ACTION_WEATHER =  "getWeather";
    private static final String METHOD_NAME_CITIES = "getCites";
    private static final String SOAP_ACTION_CITIES = "getCites";









    @Override
    public String[] getWeather(final String city) {
        {
            final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_WEATHER);
            final PropertyInfo propInfo = new PropertyInfo();
            propInfo.name = "arg0";
            propInfo.type = PropertyInfo.STRING_CLASS;
            request.addProperty("arg0", city);
            final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            final HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(SOAP_ACTION_WEATHER, envelope);
                envelope.setOutputSoapObject(request);

                Log.e("getWeather",envelope.getResponse().toString());
                return new String[]{envelope.getResponse().toString()};
            } catch (Exception soapFault) {
                soapFault.printStackTrace();
            }



        }
        return null;
    }

    @Override
    public String[] getCites() {
        {
            final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_CITIES);
            final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            final HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(SOAP_ACTION_CITIES, envelope);
                envelope.setOutputSoapObject(request);

                Log.e("getCities",envelope.getResult().toString());
                return new String[]{envelope.getResponse().toString()};
            } catch (Exception soapFault) {
                soapFault.printStackTrace();
            }



        }
        return null;
    }


}
