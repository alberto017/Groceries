package com.example.sanchez.groceries;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }

    //Declaro la variable para etiqueta y frameLayout
    private TextView lblCreate;
    private FrameLayout flSignInFragment;
    private Button btnEnter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        //Enlazo la variable con el componente con ayuda del view
        lblCreate = view.findViewById(R.id.lblCreate);
        btnEnter = view.findViewById(R.id.btnEnter);
        flSignInFragment = getActivity().findViewById(R.id.flRegister);
        return view;
    }

    //Evento para interactuar con el fragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Evento de etiqueta
        lblCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }//onClick
        });


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"¡Te quiero muchote!",Toast.LENGTH_LONG).show();
            }//onClick
        });
    }//onViewCreated


    //Reemplazo fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(flSignInFragment.getId(),fragment);
        fragmentTransaction.commit();

    }
}
