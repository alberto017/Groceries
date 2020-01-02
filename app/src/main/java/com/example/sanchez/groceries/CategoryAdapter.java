package com.example.sanchez.groceries;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/*

 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    //Declaramos una lista que contendra todas los metodos y variables de la clase CategoryModel
    private List<CategoryModel> categoryModelList;

    //Constructor
    public CategoryAdapter(List<CategoryModel> categoryModelList){
        this.categoryModelList = categoryModelList;
    }


    //Enlazamos con nuesro xml las variables
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    //Enlazamos
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        String icon = categoryModelList.get(position).getCategoryIconLink();
        String name = categoryModelList.get(position).getCategoryName();
        holder.setCategory(name,position);
        }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
        }

        private void setCategoryIcon(){
            //Hacer categoriaID
        }

        //Evento click en categorias
        private void setCategory(final String name, final int position){
            categoryName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position != 0){
                        Intent categoryIntent =  new Intent(itemView.getContext(),CategoryActivity.class);
                        categoryIntent.putExtra("CategoryName",name);
                        itemView.getContext().startActivity(categoryIntent);
                    }

                }
            });
        }
    }
}
