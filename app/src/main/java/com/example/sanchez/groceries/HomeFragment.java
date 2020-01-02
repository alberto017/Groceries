package com.example.sanchez.groceries;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;

    private ViewPager vpBannerSlider;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    private TextView txtTitleHorizontalScrollItem;
    private Button btnViewAllHorizontalScrollItem;
    private RecyclerView rvHorizontalScrollItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // RecyclerView
        rvCategory = view.findViewById(R.id.rvCategory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCategory.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Frutas"));
        categoryModelList.add(new CategoryModel("link","Verduras"));
        categoryModelList.add(new CategoryModel("link","Semillas"));
        categoryModelList.add(new CategoryModel("link","Carnes"));
        categoryModelList.add(new CategoryModel("link","Lacteos"));
        categoryModelList.add(new CategoryModel("link","Desechables"));
        categoryModelList.add(new CategoryModel("link","Licores"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        rvCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        // Banner
        vpBannerSlider = view.findViewById(R.id.banner_slider_view_pager);
        sliderModelList = new ArrayList<SliderModel>();
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

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        vpBannerSlider.setAdapter(sliderAdapter);
        vpBannerSlider.setClipToPadding(false);
        vpBannerSlider.setPageMargin(20);

        vpBannerSlider.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };

        vpBannerSlider.addOnPageChangeListener(onPageChangeListener);
        startBannerSlideShow();
        vpBannerSlider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerShow();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });



        /*
                   Horizontal product layout
        */

        txtTitleHorizontalScrollItem = view.findViewById(R.id.txtTitleHorizontalScroll);
        btnViewAllHorizontalScrollItem = view.findViewById(R.id.btnViewAllHorizontarScroll);
        rvHorizontalScrollItem = view.findViewById(R.id.rvHorizontalScroll);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sugar,"Azucar Morena 1Kg.","Zulka","$28.00"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvHorizontalScrollItem.setLayoutManager(linearLayoutManager);
        rvHorizontalScrollItem.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();


        /*
                  Grid Product Layout
        */

        TextView txtTitleGridProduct = view.findViewById(R.id.txtTitleGridProduct);
        Button btnAceptarGridProduct = view.findViewById(R.id.btnAceptarGridProduct);
        GridView gvListaGridProduct = view.findViewById(R.id.gvListaGridProduct);

        gvListaGridProduct.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));


        return view;
    }

    private void pageLooper(){
        if(currentPage == sliderModelList.size() -2){
            currentPage = 2;
            vpBannerSlider.setCurrentItem(currentPage,false);
        }
        if(currentPage == 1){
            currentPage = sliderModelList.size() -3;
            vpBannerSlider.setCurrentItem(currentPage,false);
        }
    }


    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                vpBannerSlider.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopBannerShow(){
        timer.cancel();
    }


}

