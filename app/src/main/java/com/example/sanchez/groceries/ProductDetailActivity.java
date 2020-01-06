package com.example.sanchez.groceries;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private ViewPager vpProductImages;
    private TabLayout tlIndicator;
    private static  boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton btnAddWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Quitar titulo de ventana principal
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vpProductImages = findViewById(R.id.vpProductImages);
        tlIndicator = findViewById(R.id.tlIndicator);

        btnAddWishlist = findViewById(R.id.btnAddWishlist);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.sugar);
        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        vpProductImages.setAdapter(productImagesAdapter);

        tlIndicator.setupWithViewPager(vpProductImages,true);

        btnAddWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ALREADY_ADDED_TO_WISHLIST){
                    ALREADY_ADDED_TO_WISHLIST = false;
                    btnAddWishlist.setBackgroundTintList((ColorStateList.valueOf(Color.parseColor("#9e9e9e"))));
                }else{
                    ALREADY_ADDED_TO_WISHLIST = true;
                    btnAddWishlist.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            //Search
            finish();
        }else if(id == R.id.main_search_icon){
            //Cart
            return true;
        }else if(id == R.id.main_cart_icon){
            //Cart
            return true;
        }//else

        return super.onOptionsItemSelected(item);
    }


}
