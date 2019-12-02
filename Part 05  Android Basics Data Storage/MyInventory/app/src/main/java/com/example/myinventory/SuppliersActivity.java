package com.example.myinventory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.myinventory.data.InventoryContract;

public class SuppliersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private EditText employeeName;
    private EditText comName;
    private EditText phoneNumber;
    private EditText email;
    private Uri supplierUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        Intent intent = getIntent();
        supplierUri = intent.getData();
        if (supplierUri == null) {
            getSupportActionBar().setTitle("Add Supplier");
        } else {
            getSupportActionBar().setTitle("Edit Supplier");
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        employeeName = findViewById(R.id.com_edit_employee_name);
        comName = findViewById(R.id.com_edit_company_name);
        phoneNumber = findViewById(R.id.com_edit_phone);
        email = findViewById(R.id.com_edit_email);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = new String[]{InventoryContract.SuppliersEntry.SUPPLIER_ID,
                InventoryContract.SuppliersEntry.COMPANY_NAME,
                InventoryContract.SuppliersEntry.SUPPLIER_NAME,
                InventoryContract.SuppliersEntry.COMPANY_PHONE_NUM,
                InventoryContract.SuppliersEntry.COMPANY_EMAIL};
        return new CursorLoader(this,
                this.supplierUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            employeeName.setText(data.getString(data.getColumnIndex(InventoryContract.SuppliersEntry.SUPPLIER_NAME)));
            comName.setText(data.getString(data.getColumnIndex(InventoryContract.SuppliersEntry.COMPANY_NAME)));
            phoneNumber.setText(String.valueOf(data.getInt(data.getColumnIndex(InventoryContract.SuppliersEntry.COMPANY_PHONE_NUM))));
            email.setText(data.getString(data.getColumnIndex(InventoryContract.SuppliersEntry.COMPANY_EMAIL)));
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        employeeName.setText(" ");
        comName.setText(" ");
        phoneNumber.setText(0);
        email.setText(" ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.supplier_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.supplierUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_s_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_s_save:
                saveProduct();
                return true;
            case R.id.action_s_delete:
                deleteProduct();
                return true;
            case android.R.id.home:
                return true;
            default:
                return false;
        }
    }

    private void saveProduct() {
        String employee = employeeName.getEditableText().toString().trim();
        String company = comName.getEditableText().toString();
        int phone = 111;
        String phoneString = phoneNumber.getEditableText().toString();
        if (!phoneString.isEmpty()){
            phone = Integer.parseInt(phoneString);
        }
        String comEmail= email.getText().toString();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.SuppliersEntry.SUPPLIER_NAME, employee);
        values.put(InventoryContract.SuppliersEntry.COMPANY_NAME, company);
        values.put(InventoryContract.SuppliersEntry.COMPANY_PHONE_NUM, phone);
        values.put(InventoryContract.SuppliersEntry.COMPANY_EMAIL, comEmail);
        Uri uri = null;
        if (this.supplierUri == null) {
            try {
                uri = getContentResolver().insert(InventoryContract.SuppliersEntry.SUPPLIERS_CONTENT_URI, values);
            } catch (IllegalArgumentException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if (uri != null) {
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                Log.e("tag", "msg = " + employeeName + ", " + comName + ", " + email + ", " + phoneNumber);
                finish();
            }
        } else {
            int updated = 0;
            try {
                updated = getContentResolver().update(this.supplierUri, values, null, null);
            } catch (IllegalArgumentException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if (updated > 0) {
                Toast.makeText(this, "supplier updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    private void deleteProduct() {
        int deleted = 0;
        try {
            deleted = getContentResolver().delete(this.supplierUri, null, null);
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (deleted > 0) {
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "delete failed", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


}
