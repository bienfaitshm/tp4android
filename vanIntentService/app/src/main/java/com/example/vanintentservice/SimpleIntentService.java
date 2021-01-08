package com.example.vanintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

public class SimpleIntentService extends IntentService {

    public static volatile boolean shouldStop = false;

    public static final String ACTION_1 ="MY_ACTION_1";

    public static final String PARAM_PERCENT = "Commencer";

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // Create Intent object (to broadcast).
        Intent broadcastIntent = new Intent();

        // Set Action name for this Intent.
        // A Intent can perform many different actions.
        broadcastIntent.setAction(SimpleIntentService.ACTION_1);

        // Loop 100 times broadcast of Intent.
        for (int i = 100; i >= 0; i--) {

            // Set data
            // (Percent of work)
            broadcastIntent.putExtra(PARAM_PERCENT, i);

            // Send broadcast
            sendBroadcast(broadcastIntent);

            // Sleep 100 Milliseconds.
            SystemClock.sleep(100);

            if(shouldStop) {
                stopSelf();
                return;
            }
        }

    }
}