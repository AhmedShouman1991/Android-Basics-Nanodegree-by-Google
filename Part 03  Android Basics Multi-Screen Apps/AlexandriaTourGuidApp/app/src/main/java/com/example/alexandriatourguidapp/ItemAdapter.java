package com.example.alexandriatourguidapp;

//import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private ImageView itemImg;
    private TextView itemName;
    private TextView itemType;
    private TextView itemHours;
    private TextView itemLocation;

    public ItemAdapter(@NonNull Context context, @NonNull List<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.img_item_layout, parent, false);
        }
        Item item = getItem(position);

        itemImg = listItem.findViewById(R.id.item_img);
        itemImg.setImageResource(item.getnImgId());


        itemName = listItem.findViewById(R.id.item_name);
        itemName.setText(item.getnName());

        itemType = listItem.findViewById(R.id.type_input);
        itemType.setText(item.getnType());

        itemHours = listItem.findViewById(R.id.o_hours_input);
        itemHours.setText(item.getnOpeningHours());

        itemLocation = listItem.findViewById(R.id.location_input);
        itemLocation. setText(item.getnLocation());


        return listItem;
    }
}
