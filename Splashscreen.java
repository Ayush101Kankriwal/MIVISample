package com.mivi.mivilogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splashscreen extends Activity {
    int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen_activity);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                handler.removeCallbacksAndMessages(null);
                Intent i = new Intent(Splashscreen.this, LoginScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra(Constants.NOTIFICATION_TYPE, finalNotification_type);
//                i.putExtra(Constants.ACTION_PARAMS, finalActionParams);
                startActivity(i);
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}
