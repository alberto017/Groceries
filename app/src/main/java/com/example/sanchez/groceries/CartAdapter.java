package com.example.sanchez.groceries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    //Creamos una lista en base a nuestra clase modelo
    private List<CartItemModel> cartItemModelList;

    //Constructor
    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    //Asigno las vistas y las relaciono con nuestras clases declaradas particularmente
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new CartItemViewholder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new CartTotalAmountViewholder(cartTotalView);
            default:
                return null;
        }//switch
    }

    //En este metodo se interactua directamente con los componentes
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (cartItemModelList.get(position).getType()) {
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String cuttedPrice = cartItemModelList.get(position).getCuttedPrice();

                ((CartItemViewholder) holder).setItemDetails(resource, title, productPrice, cuttedPrice);
                break;

            case CartItemModel.TOTAL_AMOUNT:
                String totalItems = cartItemModelList.get(position).getTotalItems();
                String totalItemsPrice = cartItemModelList.get(position).getTotalItemPrice();
                String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                String totalAmount = cartItemModelList.get(position).getTotalAmount();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();

                ((CartTotalAmountViewholder) holder).setTotalAmount(totalItems, totalItemsPrice, deliveryPrice, totalAmount, savedAmount);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    //Clases relacionadas a CartItem y CartTotalAmount
    class CartItemViewholder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView productQuantity;
        private Button btnRemoveItem;

        public CartItemViewholder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imgProductItem);
            productTitle = itemView.findViewById(R.id.txtProductTitleOrder);
            productPrice = itemView.findViewById(R.id.txtProductPrice);
            cuttedPrice = itemView.findViewById(R.id.txtCuttedPrice);
            productQuantity = itemView.findViewById(R.id.txtProductQuantity);
            btnRemoveItem = itemView.findViewById(R.id.btnRemoveItem);
            /*
            btnRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(this,"Hola",Toast.LENGTH_LONG);
                }
            });
            */
        }

        private void setItemDetails(int resource, String title, String productPriceText, String cuttedPriceText) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);
            //productQuantity.setText(productQuantityText);
        }
    }


    class CartTotalAmountViewholder extends RecyclerView.ViewHolder {

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public CartTotalAmountViewholder(View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.lblTotalItems);
            totalItemPrice = itemView.findViewById(R.id.lblTotalItemsPrice);
            deliveryPrice = itemView.findViewById(R.id.lblDeliveryPrice);
            totalAmount = itemView.findViewById(R.id.lblTotalAmount);
            savedAmount = itemView.findViewById(R.id.lblSavedAmount);
        }

        private void setTotalAmount(String totalItemText, String totalItemPriceText, String deliveryPriceText, String totalAmountText, String savedAmountText) {
            totalItems.setText(totalItemText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}
