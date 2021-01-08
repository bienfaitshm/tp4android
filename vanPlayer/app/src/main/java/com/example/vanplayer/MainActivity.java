package com.example.vanplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonPlay;
    private Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonPlay = (Button) this.findViewById(R.id.button_play);
        this.buttonStop = (Button) this.findViewById(R.id.button_stop);

        this.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSong();
            }
        });

        this.buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSong();
            }
        });
    }

    // This method is called when users click on the Play button.
    public void playSong()  {
        // Create Intent object for PlaySongService.
        Intent myIntent = new Intent(MainActivity.this, PlaySongService.class);

        // Call startService with Intent parameter.
        this.startService(myIntent);
    }

    // This method is called when users click on the Stop button.
    public void stopSong( )  {

        // Create Intent object
        Intent myIntent = new Intent(MainActivity.this, PlaySongService.class);
        this.stopService(myIntent);
    }

}