package com.example.myinventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myinventory.data.InventoryContract;
import com.example.myinventory.data.SuppliersCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SuppliersListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView listView;
    private SuppliersCursorAdapter suppliersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_list);
        listView = findViewById(R.id.suppliers_list);
        View emptyView = findViewById(R.id.suppliers_empty_view);
        listView.setEmptyView(emptyView);
        getSupportLoaderManager().initLoader(0,null,this);
        suppliersAdapter = new SuppliersCursorAdapter(this, null);
        listView.setAdapter(suppliersAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SuppliersListActivity.this, SuppliersActivity.class);
                Uri uri = ContentUris.withAppendedId(InventoryContract.SuppliersEntry.SUPPLIERS_CONTENT_URI, id);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        FloatingActionButton floatingActionButton = findViewById(R.id.suppliers_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuppliersListActivity.this, SuppliersActivity.class);
                startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = new String[]{InventoryContract.SuppliersEntry.SUPPLIER_ID,
                InventoryContract.SuppliersEntry.SUPPLIER_NAME,
                InventoryContract.SuppliersEntry.COMPANY_NAME,
                InventoryContract.SuppliersEntry.COMPANY_PHONE_NUM,
                InventoryContract.SuppliersEntry.COMPANY_EMAIL};

        return new CursorLoader(this,
                InventoryContract.SuppliersEntry.SUPPLIERS_CONTENT_URI,
                projection,
                null,
               null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        suppliersAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        suppliersAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.suppliers_list_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_all_entries:
                showDeleteConfirmationDialog();
                return true;
            case R.id.action_suppliers_list:
                Intent intent = new Intent(SuppliersListActivity.this, SuppliersActivity.class);
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
        int rows = getContentResolver().delete(InventoryContract.SuppliersEntry.SUPPLIERS_CONTENT_URI, null, null);
        if (rows > 0) {
            Toast.makeText(this, getString(R.string.delete_all_products_successfully), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.delete_all_products_failed), Toast.LENGTH_SHORT).show();
        }
    }
}
