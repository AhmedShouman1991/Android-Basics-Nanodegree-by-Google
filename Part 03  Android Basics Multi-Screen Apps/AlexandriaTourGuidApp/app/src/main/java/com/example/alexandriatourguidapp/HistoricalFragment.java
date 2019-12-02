package com.example.alexandriatourguidapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HistoricalFragment extends AbstractFragment {


    public HistoricalFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.initializeArrayList();
        super.addItem(new Item(R.drawable.bib,
                getString(R.string.bib),
                getString(R.string.bib_type),
                getString(R.string.bib_opening),
                getString(R.string.bib_add)));

        super.addItem(new Item(R.drawable.alexandria_national_museum,
                getString(R.string.anm),
                getString(R.string.anm_type),
                getString(R.string.anm_opening),
                getString(R.string.anm_add)));

        super.addItem(new Item(R.drawable.citadel_of_qaitbay,
                getString(R.string.cof),
                getString(R.string.cof_type),
                getString(R.string.cof_opening),
                getString(R.string.cof_add)));


        setRootView(inflater.inflate(R.layout.activity_items_list, container, false));
        super.setItemsList();

        return super.getRootView();
    }

}
