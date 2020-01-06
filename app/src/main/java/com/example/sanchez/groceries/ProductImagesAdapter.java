package com.example.sanchez.groceries;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ProductImagesAdapter extends PagerAdapter {

    private List<Integer> productImages;

    public ProductImagesAdapter(List<Integer> productImages) {
        this.productImages = productImages;
    }

    //Declaro la imagen y la agrego al contenedor
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView prodductImage = new ImageView(container.getContext());
        prodductImage.setImageResource(productImages.get(position));
        container.addView(prodductImage,0);
        container.addView(prodductImage,1);
        return prodductImage;
    }

    //Metodo para eliminar imagen
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }//destroyItem

    //Regresa la cantidad de imagenes
    @Override
    public int getCount() {
        return productImages.size();
    }//getCount

    //Regreso el objeto de imagen
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }//isViewFromObject
}
