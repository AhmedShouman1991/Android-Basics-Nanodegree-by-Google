package com.example.myinventory;

import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.myinventory.data.InventoryContract;
import com.example.myinventory.data.InventoryContract.ProductsEntry;
import com.example.myinventory.data.ProductsCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductsListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ListView listView;
    ProductsCursorAdapter productsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.products_list);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        productsAdapter = new ProductsCursorAdapter(this, null);
        listView.setAdapter(productsAdapter);
        FloatingActionButton floatingActionButton = findViewById(R.id.products_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsListActivity.this, ProductsActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newIntent = new Intent(ProductsListActivity.this, ProductsActivity.class);
                newIntent.setData(ContentUris.withAppendedId(InventoryContract.ProductsEntry.PRODUCTS_CONTENT_URI, id));
                startActivity(newIntent);
            }
        });
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.products_list_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_all_entries:
                showDeleteConfirmationDialog();
                return true;
            case R.id.action_suppliers_list:
                Intent intent = new Intent(ProductsListActivity.this, SuppliersListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_all_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAllProducts();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteAllProducts() {
        int rows = getContentResolver().delete(ProductsEntry.PRODUCTS_CONTENT_URI, null, null);
        if (rows > 0) {
            Toast.makeText(this, getString(R.string.delete_all_products_successfully), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.delete_all_products_failed), Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = new String[]{ProductsEntry.PRODUCT_ID,
                ProductsEntry.PRODUCT_NAME,
                ProductsEntry.PRODUCT_QUANTITY,
                ProductsEntry.PRODUCT_PRICE,
                ProductsEntry.PRODUCT_SUPPLIER};
        return new CursorLoader(this,
                ProductsEntry.PRODUCTS_CONTENT_URI,
                projection,
                null,
                null,
                null);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        productsAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        productsAdapter.swapCursor(null);
    }
}
