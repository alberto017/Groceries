package com.example.sanchez.groceries;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Creamos instancia de firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        SystemClock.sleep(3000);
        Intent Acceso = new Intent(SplashScreen.this,Register.class);
        startActivity(Acceso);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser == null){
            Intent registerIntent = new Intent(SplashScreen.this,Register.class);
            startActivity(registerIntent);
            finish();
        }else{
            Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
        }//else

    }
}
