package com.example.sanchez.groceries;

import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.Viewholder> {

    private List<MyOrderItemModel> myOrderItemModelList;

    //Constructor
    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    //Cargo mi item al recyclerView
    @NonNull
    @Override
    public MyOrderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item,parent,false);
        return new Viewholder(view);
    }

    //Obtenemos valores del modelo
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.Viewholder holder, int position) {
        int resorce = myOrderItemModelList.get(position).getProductImage();
        String title = myOrderItemModelList.get(position).getProductTitle();
        String deliveredDate = myOrderItemModelList.get(position).getDeliveryStatus();

        holder.setData(resorce,title,deliveredDate);
    }

    //Retorno tamano del arreglo
    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    //Clase que extendemos del adapter
    public class Viewholder extends RecyclerView.ViewHolder {

        //Declaro los controles pertenecientes al item
        private ImageView imgProductOrder;
        private ImageView imgOrderIndicator;
        private TextView txtProductTitleOrder;
        private TextView txtDeliveredOrder;

        public Viewholder(View itemView) {
            super(itemView);
            imgProductOrder = itemView.findViewById(R.id.imgProductOrder);
            imgOrderIndicator = itemView.findViewById(R.id.imgOrderIndicator);
            txtProductTitleOrder = itemView.findViewById(R.id.txtProductTitleOrder);
            txtDeliveredOrder = itemView.findViewById(R.id.txtDeliveredOrder);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setData(int resource, String title, String deliveredDate){
            imgProductOrder.setImageResource(resource);
            txtProductTitleOrder.setText(title);
            if(deliveredDate.equals("Descontinuado")){
                imgOrderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successFail)));
            }else{
                imgOrderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successGreen)));
            }
            txtDeliveredOrder.setText(deliveredDate);
        }//setData
    }//viewHolder
}
