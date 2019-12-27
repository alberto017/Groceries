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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.sanchez.groceries.Register.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }

    //Declaro la variable para etiqueta y frameLayout

    private FrameLayout flSignInFragment;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView lblForgot;
    private TextView lblCreateUser;
    private ProgressBar pgbSignIn;
    private Button btnEnter;
    private ImageView imgClose;

    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-z0-9._-]+@[a-z]+.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        //Enlazo la variable con el componente con ayuda del view
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPassword = view.findViewById(R.id.txtPassword);
        lblForgot = view.findViewById(R.id.lblForgot);
        lblCreateUser = view.findViewById(R.id.lblCreateUser);
        pgbSignIn = view.findViewById(R.id.pgbSignIn);
        btnEnter = view.findViewById(R.id.btnEnter);
        imgClose = view.findViewById(R.id.imgClose);
        flSignInFragment = getActivity().findViewById(R.id.flRegister);

        //Creamos una instancia de firebase
        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }//onCreate

    //Evento para interactuar con el fragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //
        txtEmail.addTextChangedListener(new TextWatcher() {
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

        //
        txtPassword.addTextChangedListener(new TextWatcher() {
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
        lblCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }//onClick
        });

        //
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntent();
            }
        });

        //
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }//onClick
        });

        lblForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment = true;
                setFragment(new ResetPasswordFragment());
            }
        });
    }//onViewCreated


    private void checkInputs(){
        if(!TextUtils.isEmpty(txtEmail.getText())){
            if(!TextUtils.isEmpty(txtPassword.getText())){
                btnEnter.setEnabled(true);
                btnEnter.setTextColor(Color.rgb(255,255,255));
            }else{
                btnEnter.setEnabled(true);
                btnEnter.setTextColor(Color.argb(50,255,255,255));
            }//else
        }else{
            btnEnter.setEnabled(true);
            btnEnter.setTextColor(Color.argb(50,255,255,255));
        }//else
    }//checkInputs


    private void checkEmailAndPassword(){
        if(txtEmail.getText().toString().matches(emailPattern)){
            if(txtPassword.length() >= 8){
                pgbSignIn.setVisibility(View.VISIBLE);
                btnEnter.setEnabled(false);
                btnEnter.setTextColor(Color.argb(50,255,255,255));
                firebaseAuth.signInWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    mainIntent();
                                }else{
                                    pgbSignIn.setVisibility(View.INVISIBLE);
                                    btnEnter.setEnabled(true);
                                    btnEnter.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(),"Datos incorrectos",Toast.LENGTH_SHORT).show();
                                }//else
                            }
                        });
            }else{
                Toast.makeText(getActivity(),"Datos incorrectos",Toast.LENGTH_SHORT).show();
            }//else
        }else{
            Toast.makeText(getActivity(),"Datos incorrectos",Toast.LENGTH_SHORT).show();
        }//else
    }//checkEmailAndPassword(


    //Reemplazo fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(flSignInFragment.getId(),fragment);
        fragmentTransaction.commit();
    }


    //Intent principal
    private void mainIntent(){
        Intent mainIntent = new Intent(getActivity(),MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }
}
