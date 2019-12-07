package com.example.sanchez.groceries;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    //Declaro la variable para etiqueta y frameLayout
    private TextView lblEnter;
    private FrameLayout flSignUpFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //Enlazo la variable con el componente con ayuda del view
        lblEnter = view.findViewById(R.id.lblEnter);
        flSignUpFragment = getActivity().findViewById(R.id.flRegister);
        return view;
    }//onCreateView


    //Evento para interactuar con el fragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Evento de etiqueta
        lblEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }//onClick
        });
    }//onViewCreated

    //Reemplazo fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(flSignUpFragment.getId(), fragment);
        fragmentTransaction.commit();
    }//setFragment
}
