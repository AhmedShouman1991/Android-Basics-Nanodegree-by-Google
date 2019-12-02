package com.example.myinventory.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.myinventory.R;
import com.example.myinventory.data.InventoryContract.ProductsEntry;

public class ProductsCursorAdapter extends CursorAdapter {
    private Cursor cursor;
    public ProductsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.item_name);
        textView.setText(cursor.getString(cursor.getColumnIndex(ProductsEntry.PRODUCT_NAME)));

        textView = view.findViewById(R.id.item_quantity);
        String quantity = cursor.getInt(cursor.getColumnIndex(ProductsEntry.PRODUCT_QUANTITY)) + " pcs.";
        textView.setText(quantity);

        textView = view.findViewById(R.id.item_price);
        String price = cursor.getInt(cursor.getColumnIndex(ProductsEntry.PRODUCT_PRICE)) + "EP for each.";
        textView.setText(price);

        Button sellOne = view.findViewById(R.id.sell_one);
        this.cursor = cursor;
        sellOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private int decreaseQuantity(Context context) {
        int quantity = this.cursor.getInt(this.cursor.getColumnIndex(ProductsEntry.PRODUCT_QUANTITY));
        if (quantity > 0) {
            quantity--;
        } else {
            Toast.makeText(context, "No products in the Inventory", Toast.LENGTH_SHORT).show();
        }
        return quantity;
    }
}
