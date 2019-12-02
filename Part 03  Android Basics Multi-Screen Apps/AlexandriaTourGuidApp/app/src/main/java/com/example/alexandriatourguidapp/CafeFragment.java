package com.example.alexandriatourguidapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CafeFragment extends AbstractFragment {


    public CafeFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.initializeArrayList();
        super.addItem(new Item(R.drawable.brazilian_coffee, getString(R.string.brazilian),
                getString(R.string.braz_type),
                getString(R.string.braz_opening),
                getString(R.string.braz_add)));

        super.addItem(new Item(R.drawable.trianon,
                getString(R.string.trianon),
                getString(R.string.tria_type) ,
                getString(R.string.tri_opening),
                getString(R.string.tria_add)));

        super.addItem(new Item(R.drawable.passage,
                getString(R.string.l_p),
                getString(R.string.tria_type)
                ,getString(R.string.l_p_opening),
                getString(R.string.l_p_add)));

        setRootView(inflater.inflate(R.layout.activity_items_list, container, false));
        super.setItemsList();

        return super.getRootView();
    }

}
