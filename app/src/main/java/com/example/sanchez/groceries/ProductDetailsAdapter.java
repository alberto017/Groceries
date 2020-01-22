package com.example.sanchez.groceries;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTals;

    public ProductDetailsAdapter(FragmentManager fm,int totalTabs) {
        super(fm);
        this.totalTals = totalTabs;
    }//ProductDetailsAdapter

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ProductDescriptionFragment productDescriptionFragment_1 = new ProductDescriptionFragment();
                return productDescriptionFragment_1;
            case 1:
                ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                return productSpecificationFragment;
            case 2:
                ProductDescriptionFragment productDescriptionFragment_2 = new ProductDescriptionFragment();
                return productDescriptionFragment_2;
                default:
                    return null;
        }//switch
    }//getItem

    @Override
    public int getCount() {
        return totalTals;
    }//getCount
}
