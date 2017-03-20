package com.codepath.nytimessearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int secondsDelayed = 4;
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, SearchActivity.class));
            finish();
        }, secondsDelayed * 1000);
    }
}
