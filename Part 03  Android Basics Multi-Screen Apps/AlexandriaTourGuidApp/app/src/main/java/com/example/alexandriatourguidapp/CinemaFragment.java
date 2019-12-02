package com.example.alexandriatourguidapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaFragment extends AbstractFragment {


    public CinemaFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.initializeArrayList();
        super.addItem(new Item(R.drawable.san_stefano,
                getString(R.string.san_stifano),
                getString(R.string.cinema),
                getString(R.string.san_opening),
                getString(R.string.san_add)));

        super.addItem(new Item(R.drawable.vox,
                getString(R.string.vox),
                getString(R.string.cinema),
                getString(R.string.vox_opening),
                getString(R.string.vox_add)));

        super.addItem(new Item(R.drawable.green,
                getString(R.string.green),
                getString(R.string.cinema),
                getString(R.string.gin_opening),
                getString(R.string.green_add)));

        setRootView(inflater.inflate(R.layout.activity_items_list, container, false));
        super.setItemsList();
        return super.getRootView();
    }

}
