package com.example.sanchez.groceries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView rvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvCategory = findViewById(R.id.rvCategory);

        /*
              Banner
         */

        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.cart,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.cart,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.cart,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.banner,"#077AE4"));


        /*
              Horizontal Product Layout
         */

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvCategory.setLayoutManager(linearLayoutManager);
        rvCategory.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();


        TextView txtTitleGridProduct = findViewById(R.id.txtTitleGridProduct);
        Button btnAceptarGridProduct = findViewById(R.id.btnAceptarGridProduct);
        GridView gvListaGridProduct = findViewById(R.id.gvListaGridProduct);

        gvListaGridProduct.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {
            //Codigo search
            return true;
        }else if(id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
