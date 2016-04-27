package com.example.lababiba.weatherservice.Activities;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lababiba.weatherservice.Controller.SharedPresencesController;
import com.example.lababiba.weatherservice.Controller.WebClient;
import com.example.lababiba.weatherservice.R;
import com.example.lababiba.weatherservice.Service.MyService;
import com.example.lababiba.weatherservice.Utils.TextCorrectionUtils;

import java.util.List;


public class MainActivity extends Activity {

    private ListView listView = null;
    private int    widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            SharedPresencesController.getInstance().init(this);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        listView = (ListView) findViewById(R.id.listView);
        new getCitiesTask().execute();
        super.onCreate(savedInstanceState);

    }




    public void onClick(String city) {

        final Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        final Intent intent = new Intent(this,MyService.class);
        startService(intent);
        Log.e("city", city);
        SharedPresencesController.getInstance().putString("currCity",city);
        this.setResult(RESULT_OK, resultValue);
        Log.e("Widget", " Widget configurated");

        this.finish();
    }



    private class getCitiesTask extends AsyncTask<Void,Void,List<String>>
    {

        @Override
        protected List<String> doInBackground(Void... params) {
           List<String> list = TextCorrectionUtils.getCites(WebClient.getInstance().getCites()[0]);
           for(int i=0;i<list.size();i++)
           {
           Log.e(i+"",list.get(i));
           }



            return list;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                    R.layout.list_layout, strings);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MainActivity.this.onClick((String)parent.getItemAtPosition(position));
                }
            });
        }
    }
}




