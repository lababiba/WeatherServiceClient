package com.example.lababiba.weatherservice.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lababiba.weatherservice.Controller.SharedPrefencesController;
import com.example.lababiba.weatherservice.Controller.WebClient;
import com.example.lababiba.weatherservice.R;
import com.example.lababiba.weatherservice.Service.MyService;




public class MainActivity extends Activity implements View.OnClickListener {

    Button btnStart;
    EditText editText;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnStart = (Button) findViewById(R.id.start);
        editText = (EditText) findViewById(R.id.editText);
        SharedPrefencesController.getInstance().init(this);
        setContentView(R.layout.activity_main);
        btnStart.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

            city = editText.getText().toString();
            Log.e("city",city);
            WebClient.c = this.getApplicationContext();
            Intent intent = new Intent(this,MyService.class);
            intent.putExtra(city,"city");
            startService(intent);
            new getWeatherTask().execute(city);

        }
}

    class getWeatherTask extends AsyncTask<String, Void, String[]>
    {

        @Override
        protected String[] doInBackground(String... params) {
            final WebClient webClient = new WebClient();
            return webClient.getWeather(params[0]);
        }


    }

