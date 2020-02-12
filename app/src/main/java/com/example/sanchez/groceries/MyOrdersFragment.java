package com.example.sanchez.groceries;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView rvMyOrders;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        //Enlazo el RecyclerView a una cariable
        rvMyOrders = view.findViewById(R.id.rvMyOrders);

        //Declaro el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyOrders.setLayoutManager(layoutManager);

        //Cargamos datos al modelo
        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.sugar,"Azucar Morena","Disponible"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.sugar,"Azucar Morena","Descontinuado"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.sugar,"Azucar Morena","Disponible"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.sugar,"Azucar Morena","Descontinuado"));

        //Asignamos la lista al adapter
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        rvMyOrders.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;

    }

}
