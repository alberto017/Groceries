package com.example.sanchez.groceries;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.nio.file.Watchable;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    //Declaro los objetos
    private TextView lblEnter;
    private FrameLayout flSignUpFragment;
    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;
    private ProgressBar progressBar;
    private ImageButton close;
    private Button btnCreateUser;
    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //Enlazo la variable con el componente con ayuda del view
        lblEnter = view.findViewById(R.id.lblEnter);
        flSignUpFragment = getActivity().findViewById(R.id.flRegister);
        email = view.findViewById(R.id.txtEmail);
        fullName = view.findViewById(R.id.txtName);
        password = view.findViewById(R.id.txtPassword);
        confirmPassword = view.findViewById(R.id.txtRepeatPassword);
        close = view.findViewById(R.id.imgClose);
        progressBar = view.findViewById(R.id.pgbSignUp);
        btnCreateUser = view.findViewById(R.id.btnCreateUser);

        //Creamos una instancia
        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }//onCreateView


    //Evento para interactuar con el fragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Evento de etiqueta
        lblEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }//onClick
        });

        //Evento de Boton
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }//onViewCreated


    //Reemplazo fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(flSignUpFragment.getId(), fragment);
        fragmentTransaction.commit();
    }//setFragment


    //Validacion de campos
    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(fullName.getText())){
                if(!TextUtils.isEmpty(password.getText()) && password.length() > 8){
                    if(!TextUtils.isEmpty(confirmPassword.getText())){
                        btnCreateUser.setEnabled(true);
                        btnCreateUser.setTextColor(Color.rgb(255,255,255));
                    }else{
                        btnCreateUser.setEnabled(false);
                        btnCreateUser.setTextColor(Color.rgb(50,255,255));
                    }//4to
                }else{
                    btnCreateUser.setEnabled(false);
                    btnCreateUser.setTextColor(Color.rgb(50,255,255));
                }//3ro
            }else{
                btnCreateUser.setEnabled(true);
                btnCreateUser.setTextColor(Color.rgb(50,255,255));
            }//2do
        }else{
            btnCreateUser.setEnabled(false);
            btnCreateUser.setTextColor(Color.rgb(50,255,255));
        }//1ero
    }//checkInputs


    //Validacion de Correo-Email
    private void checkEmailAndPassword(){
        if(email.toString().matches(emailPattern)){
            if(password.getText().toString().equals(confirmPassword.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);
                btnCreateUser.setEnabled(false);
                btnCreateUser.setTextColor(Color.rgb(50,255,255));

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                    startActivity(mainIntent);
                                    getActivity().finish();
                                }else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    btnCreateUser.setEnabled(false);
                                    btnCreateUser.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                }//else
                            }//onComplete
                        });
            }else{
                confirmPassword.setError("¡Caracteres no validos!");
            }//2d0
        }else{
            email.setError("¡Email no valido!");

        }//1ro
    }//checkEmailAndPasoword

}//SignUpFragment
