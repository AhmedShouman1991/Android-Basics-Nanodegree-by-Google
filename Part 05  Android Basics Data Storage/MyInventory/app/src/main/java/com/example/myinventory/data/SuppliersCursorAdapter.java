package com.example.myinventory.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.myinventory.R;

public class SuppliersCursorAdapter extends CursorAdapter {
    public SuppliersCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.suppliers_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.supplier_item_company);
        textView.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.SuppliersEntry.COMPANY_NAME)));

        textView = view.findViewById(R.id.supplier_item_name);
        textView.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.SuppliersEntry.SUPPLIER_NAME)));
    }
}
