package com.example.lababiba.weatherservice.Activities;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lababiba.weatherservice.Controller.SharedPresencesController;
import com.example.lababiba.weatherservice.R;
import com.example.lababiba.weatherservice.Service.MyService;




public class MainActivity extends Activity implements View.OnClickListener {

    private Button button = null;
    private EditText inputCity = null;
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


        inputCity = (EditText) findViewById(R.id.editText);


        button = (Button) findViewById(R.id.start);
        button.setClickable(true);
        button.setOnClickListener(this);
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onClick(View v) {

        final Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        final Intent intent = new Intent(this,MyService.class);
        startService(intent);
        SharedPresencesController.getInstance().putString("currCity", inputCity.getText().toString());
        this.setResult(RESULT_OK, resultValue);
        Log.e("Widget", " Widget configurated");
        this.finish();
    }
}




