package com.example.sanchez.groceries;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class Register extends AppCompatActivity {

    private FrameLayout flRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Enlazo la variable con el componente
        flRegister = findViewById(R.id.flRegister);
        //Llamo metodo de asignacion
        setFragment(new SignInFragment());
    }

    //Asignacion de fragment signInFragment en Register
    private void setFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(flRegister.getId(),fragment);
        fragmentTransaction.commit();
    }//setFragment
}
