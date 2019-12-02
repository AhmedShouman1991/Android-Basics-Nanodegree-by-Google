package com.example.alexandriatourguidapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbstractFragment extends Fragment {

    private ArrayList<Item> items;
    public ItemAdapter itemAdapter;
    private View rootView;
    private ListView list;

    public AbstractFragment() {
        initializeArrayList();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public void initializeArrayList() {
        items = new ArrayList<>();
    }

    public void setList(ListView list) {
        this.list = list;
    }

    public View getRootView() {
        return rootView;
    }

    public ListView getList() {
        return list;
    }

    public ItemAdapter getItemAdapter() {
        return new ItemAdapter(getActivity(), items);

    }

    public void setItemsList() {
        setList((ListView) getRootView().findViewById(R.id.list));

        getList().setAdapter(getItemAdapter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRootView(inflater.inflate(R.layout.activity_items_list, container, false));

        setItemsList();

        return getRootView();
    }

}
