package com.example.sanchez.groceries;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView rvCartItems;

//Enlazamos muestros controles
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        rvCartItems = view.findViewById(R.id.rvCartItems);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCartItems.setLayoutManager(linearLayoutManager);

        //Insertamos elementos al modelo
        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.sugar,"Azucar de caña","$19.00 mxn","$18.00 mxn",1));
        cartItemModelList.add(new CartItemModel(0,R.drawable.sugar,"Azucar de caña","$19.00 mxn","$18.00 mxn",1));
        cartItemModelList.add(new CartItemModel(0,R.drawable.sugar,"Azucar de caña","$19.00 mxn","$18.00 mxn",1));

        cartItemModelList.add(new CartItemModel(1,"Costo (3 elementos)","$56.00 mxn","Gratuito","$56.00 mxn","$56.00 mxn"));

        //Instanciamos la lista al adatador
        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        ////Asignamos elementos al recyclerView
        rvCartItems.setAdapter(cartAdapter);

        cartAdapter.notifyDataSetChanged();

        return view;
    }

}
