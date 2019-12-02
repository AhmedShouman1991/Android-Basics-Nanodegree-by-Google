package com.example.android.pets.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.android.pets.R;

public class PetsCursorAdapter extends CursorAdapter {
    public PetsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.name);
        textView.setText(cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.COLUMN_NAME)));

        textView = view.findViewById(R.id.summary);
        String breed = cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.COLUMN_BREED));
        if (breed.isEmpty()) {
            breed = "Unknown Breed";
        }
        textView.setText(breed);
    }
}
