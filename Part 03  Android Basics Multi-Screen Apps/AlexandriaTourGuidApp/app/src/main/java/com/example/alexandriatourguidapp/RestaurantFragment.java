package com.example.alexandriatourguidapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends AbstractFragment {


    public RestaurantFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.initializeArrayList();
        super.addItem(new Item(R.drawable.gad,
                getString(R.string.gad),
                getString(R.string.gad_type)
                , getString(R.string.gad_opening),
                getString(R.string.gad_add)));

        super.addItem(new Item(R.drawable.elfallah,
                getString(R.string.elfallah),
                getString(R.string.fal_type),
                getString(R.string.fal_opening),
                getString(R.string.fal_add)));

        super.addItem(new Item(R.drawable.sushi_fusion,
                getString(R.string.ginger),
                getString(R.string.gin_type),
                getString(R.string.gin_opening),
                getString(R.string.ging_add)));

        setRootView(inflater.inflate(R.layout.activity_items_list, container, false));
        super.setItemsList();

        return super.getRootView();
    }

}
