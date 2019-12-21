package com.example.sanchez.groceries;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {

    private EditText txtResetPassword;
    private ProgressBar pgbResetPassword;
    private ViewGroup vgEmailContainer;
    private ImageView imgEmail;
    private TextView lblNotice;
    private Button btnResetPassword;
    private FrameLayout flResetPassword;
    private String emailPattern = "[a-zA-z0-9._-]+@[a-z]+.[a-z]+";

    FirebaseAuth firebaseAuth;


    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        txtResetPassword= view.findViewById(R.id.txtResetPassword);
        vgEmailContainer= view.findViewById(R.id.vgEmailContainer);
        imgEmail= view.findViewById(R.id.imgEmail);
        lblNotice= view.findViewById(R.id.lblNotice);
        pgbResetPassword= view.findViewById(R.id.pgbResetPassword);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        flResetPassword = getActivity().findViewById(R.id.flResetPassword);

        //Instanciamos la conexion con el autenticador
        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtResetPassword.addTextChangedListener(new TextWatcher() {
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


        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(vgEmailContainer);
                imgEmail.setVisibility(View.VISIBLE);
                pgbResetPassword.setVisibility(View.VISIBLE);
                btnResetPassword.setEnabled(false);
                btnResetPassword.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.sendPasswordResetEmail(txtResetPassword.getText() .toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0,imgEmail.getWidth()/2,imgEmail.getHeight()/2);
                                    scaleAnimation.setDuration(100);
                                    scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                                    scaleAnimation.setRepeatCount(1);
                                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {

                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            pgbResetPassword.setVisibility(View.GONE);
                                            lblNotice.setText("Coreo enviado satisfactoriamente!");
                                            lblNotice.setTextColor(getResources().getColor(R.color.successGreen));
                                            TransitionManager.beginDelayedTransition(vgEmailContainer);
                                            lblNotice.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                            imgEmail.setImageResource(R.drawable.email);
                                        }
                                    });
                                    imgEmail.startAnimation(scaleAnimation);
                                }else{
                                    String error = task.getException().getMessage();
                                    pgbResetPassword.setVisibility(View.GONE);
                                    lblNotice.setText(error);
                                    lblNotice.setTextColor(getResources().getColor(R.color.successFail));
                                    TransitionManager.beginDelayedTransition(vgEmailContainer);
                                    lblNotice.setVisibility(View.VISIBLE);
                                    //Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                }//else
                                btnResetPassword.setEnabled(true);
                                btnResetPassword.setTextColor(Color.argb(255,255,255,255));
                            }//else
                        });
            }//onClick
        });
    }//onViewCreated

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slideout_from_left,R.anim.slide_from_right);
        fragmentTransaction.replace(flResetPassword.getId(),fragment);
        fragmentTransaction.commit();
    }//setFragment

    private void checkInputs(){
        if(TextUtils.isEmpty(txtResetPassword.getText())){
            btnResetPassword.setEnabled(false);
            btnResetPassword.setTextColor(Color.argb(50,255,255,255));
        }else{
            btnResetPassword.setEnabled(true);
            btnResetPassword.setTextColor(Color.argb(255,255,255,255));
        }//1er

    }//checkInputs

    private void SignIn(){
        Intent register = new Intent(getActivity(),Register.class);
        startActivity(register);
        getActivity().finish();
    }
}
