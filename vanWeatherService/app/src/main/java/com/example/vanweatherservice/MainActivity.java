package com.example.vanweatherservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean binded = false;
    private WeatherService weatherService;

    private TextView textViewWeather;
    private EditText editTextLocation;
    private Button buttonWeather;

    ServiceConnection weatherServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WeatherService.LocalWeatherBinder binder = (WeatherService.LocalWeatherBinder) service;
            weatherService = binder.getService();
            binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binded = false;
        }
    };

    // When the Activity creating its interface.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.textViewWeather = (TextView) this.findViewById(R.id.textView_weather);
        this.editTextLocation = (EditText) this.findViewById(R.id.editText_location);
        this.buttonWeather = (Button) this.findViewById(R.id.button_weather);

        this.buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeather();
            }
        });
    }

    // When Activity starting.
    @Override
    protected void onStart() {
        super.onStart();

        // Create Intent object for WeatherService.
        Intent intent = new Intent(this, WeatherService.class);

        // Call bindService(..) method to bind service with UI.
        this.bindService(intent, weatherServiceConnection, Context.BIND_AUTO_CREATE);
    }

    // Activity stop
    @Override
    protected void onStop() {
        super.onStop();
        if (binded) {
            // Unbind Service
            this.unbindService(weatherServiceConnection);
            binded = false;
        }
    }

    // When user click on 'Show weather' button.
    public void showWeather()  {
        String location = this.editTextLocation.getText().toString();

        String weather= this.weatherService.getWeatherToday(location);

        this.textViewWeather.setText(weather);
    }

}