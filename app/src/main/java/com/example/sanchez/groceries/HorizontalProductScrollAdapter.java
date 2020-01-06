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

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {


    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal__scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder holder, int position) {
        int resource = horizontalProductScrollModelList.get(position).getProductImage();
        String title = horizontalProductScrollModelList.get(position).getProductTitle();
        String description = horizontalProductScrollModelList.get(position).getProductDescription();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();

        holder.setProductImage(resource);
        holder.setProductTitle(title);
        holder.setProductDescription(description);
        holder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {
        return horizontalProductScrollModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imgHorizontalScrollItem);
            productTitle = itemView.findViewById(R.id.txtTitleHorizontalScrollItem);
            productDescription = itemView.findViewById(R.id.txtDescHorizontalScrollItem);
            productPrice = itemView.findViewById(R.id.txtPriceHorizontalScrollItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }

        private void setProductImage(int resource){
            productImage.setImageResource(resource);
        }

        private void setProductTitle(String title){
            productTitle.setText(title);
        }

        private void setProductDescription(String description){
            productDescription.setText(description);
        }

        private void setProductPrice(String price){
            productPrice.setText(price);
        }


    }
}
