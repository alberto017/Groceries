package com.example.sanchez.groceries;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SystemClock.sleep(3000);
        Intent Acceso = new Intent(splashScreen.this,Register.class);
        startActivity(Acceso);
        finish();
    }
}
