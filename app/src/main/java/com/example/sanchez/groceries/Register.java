package com.example.sanchez.groceries;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

public class Register extends AppCompatActivity {

    private FrameLayout flRegister;
    public static boolean onResetPasswordFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Enlazo la variable con el componente
        flRegister = findViewById(R.id.flRegister);
        //Llamo metodo de asignacion
        setDefaultFragment(new SignInFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(onResetPasswordFragment){
                onResetPasswordFragment = false;
                setFragment(new SignUpFragment());
                return false;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    //Asignacion de fragment signInFragment en Register
    private void setDefaultFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(flRegister.getId(),fragment);
        fragmentTransaction.commit();
    }//setFragment

    //Asignacion de fragment signInFragment en Register
    private void setFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(flRegister.getId(),fragment);
        fragmentTransaction.commit();
    }//setFragment
}
