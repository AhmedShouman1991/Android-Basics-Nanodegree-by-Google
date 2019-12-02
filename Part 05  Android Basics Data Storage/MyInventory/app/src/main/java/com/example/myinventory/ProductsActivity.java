package com.example.myinventory;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.myinventory.data.InventoryContract.ProductsEntry;
import com.example.myinventory.data.InventoryContract.SuppliersEntry;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ProductsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Uri productUri = null;
    private EditText mProductName;
    private EditText mProductQuantity;
    private EditText mProductPrice;
    private Spinner mSuppliers;
    private ImageView mSupplierImg;
    private byte[] imgData;
    private SimpleCursorAdapter suppliersAdapter;
    private int quantity;
    private String employeeName;
    private String comName;
    private int phoneNumber;
    private String email;
    private int comPositionOnSpinner;
    private boolean isPaused = false;
    private boolean mProductHasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mProductHasChanged = true;
            return false;
        }
    };
    private String[] onPausedViewsValues = new String[4];
    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream inputStream = null;
        try {
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    try {
                        if (data != null) {
                            inputStream = getContentResolver().openInputStream(data.getData());
                            bitmap = bitmapAfterResize(BitmapFactory.decodeStream(inputStream));
                            imgData = getBitmapAsByteArray(bitmap);
                            //mSupplierImg.setImageBitmap(bitmap);
                            Log.e("intent", "called");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        Bitmap bitmapAfter = bitmapAfterResize(bitmap);
        bitmapAfter.compress(Bitmap.CompressFormat.PNG, 0, byteArray);
        return byteArray.toByteArray();
    }

    private Bitmap bitmapAfterResize(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = 600;
            height = (int) (width / bitmapRatio);
        } else {
            height = 600;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        Log.e("created", "___________________________________________");
        Intent intent = getIntent();
        productUri = intent.getData();
        if (productUri != null) {
            getSupportActionBar().setTitle("Edit Product");
            getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            getSupportActionBar().setTitle("Add Product");
        }

        mProductName = findViewById(R.id.product_name);
        mProductQuantity = findViewById(R.id.product_quantity);
        mProductPrice = findViewById(R.id.product_price);
        mSuppliers = findViewById(R.id.spinner_supplier);

        mProductName.setOnTouchListener(mTouchListener);
        mProductQuantity.setOnTouchListener(mTouchListener);
        mProductPrice.setOnTouchListener(mTouchListener);
        mSuppliers.setOnTouchListener(mTouchListener);

        suppliersAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, null,
                new String[]{SuppliersEntry.COMPANY_NAME}, new int[]{android.R.id.text1}, 0);
        suppliersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSuppliers.setAdapter(suppliersAdapter);

        mSuppliers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = suppliersAdapter.getCursor();
                cursor.moveToPosition(position);
                employeeName = cursor.getString(cursor.getColumnIndex(SuppliersEntry.SUPPLIER_NAME));
                comName = cursor.getString(cursor.getColumnIndex(SuppliersEntry.COMPANY_NAME));
                phoneNumber = cursor.getInt(cursor.getColumnIndex(SuppliersEntry.COMPANY_PHONE_NUM));
                email = cursor.getString(cursor.getColumnIndex(SuppliersEntry.COMPANY_EMAIL));
                comPositionOnSpinner = position;
                Toast.makeText(ProductsActivity.this, employeeName + " " + comName + " " + phoneNumber + " " + email, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSuppliers.setSelection(0);
            }
        });

        mSupplierImg = findViewById(R.id.product_img);
        mSupplierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1, 1);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume ", "called");
        if (isPaused) {
            mProductName.setText(onPausedViewsValues[0]);
            mProductQuantity.setText(onPausedViewsValues[1]);
            mProductPrice.setText(onPausedViewsValues[2]);
            mSuppliers.setSelection(Integer.parseInt(onPausedViewsValues[3]));
        }
        if (bitmap != null) {
            mSupplierImg.setImageBitmap(bitmap);
            Log.e("onResume ", "bitmap set");
        }
        isPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause ", "called");
        isPaused = true;
        onPausedViewsValues[0] = mProductName.getEditableText().toString();
        onPausedViewsValues[1] = mProductQuantity.getEditableText().toString();
        onPausedViewsValues[2] = mProductPrice.getEditableText().toString();
        onPausedViewsValues[3] = String.valueOf(comPositionOnSpinner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.productUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveProduct();
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(ProductsActivity.this);

                return true;
            default:
                return false;
        }
    }
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void saveProduct() {
        String productName = mProductName.getEditableText().toString().trim();
        String quantityString = mProductQuantity.getEditableText().toString();
        if (quantityString.isEmpty()) {
            quantity = 0;
        } else {
            quantity = Integer.parseInt(quantityString);
        }

        String priceString = mProductPrice.getEditableText().toString();
        int price = 0;
        if (!priceString.isEmpty()) {
            price = Integer.parseInt(priceString);
        }
        String supplierName = comName;
        int comPosition = comPositionOnSpinner;
        ContentValues values = new ContentValues();
        values.put(ProductsEntry.PRODUCT_NAME, productName);
        values.put(ProductsEntry.PRODUCT_PRICE, price);
        values.put(ProductsEntry.PRODUCT_QUANTITY, quantity);
        values.put(ProductsEntry.PRODUCT_SUPPLIER, supplierName);
        values.put(ProductsEntry.PRODUCT_SUPPLIER_NUMBER_IN_LIST, comPosition);
        if (imgData != null) {
            values.put(ProductsEntry.PRODUCT_IMG, imgData);
        }
        Uri uri = null;
        if (this.productUri == null) {
            try {
                uri = getContentResolver().insert(ProductsEntry.PRODUCTS_CONTENT_URI, values);
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
                updated = getContentResolver().update(this.productUri, values, null, null);
            } catch (IllegalArgumentException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if (updated > 0) {
                Toast.makeText(this, "Product Updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    private void deleteProduct() {
        int deleted = 0;
        try {
            deleted = getContentResolver().delete(this.productUri, null, null);
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

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection;
        switch (id) {
            case 0:
                projection = new String[]{ProductsEntry.PRODUCT_ID,
                        ProductsEntry.PRODUCT_NAME,
                        ProductsEntry.PRODUCT_PRICE,
                        ProductsEntry.PRODUCT_QUANTITY,
                        ProductsEntry.PRODUCT_SUPPLIER,
                        ProductsEntry.PRODUCT_SUPPLIER_NUMBER_IN_LIST,
                        ProductsEntry.PRODUCT_IMG};
                return new CursorLoader(this,
                        this.productUri,
                        projection,
                        null,
                        null,
                        null);
            case 1:
                projection = new String[]{SuppliersEntry.SUPPLIER_ID,
                        SuppliersEntry.COMPANY_NAME,
                        SuppliersEntry.SUPPLIER_NAME,
                        SuppliersEntry.COMPANY_EMAIL,
                        SuppliersEntry.COMPANY_PHONE_NUM};
                return new CursorLoader(this,
                        SuppliersEntry.SUPPLIERS_CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);
            case 2:

            default:
                throw new IllegalArgumentException("number not valid");
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 0:
                if (data.moveToFirst()) {
                    mProductName.setText(data.getString(data.getColumnIndex(ProductsEntry.PRODUCT_NAME)));
                    mProductQuantity.setText(String.valueOf(data.getInt(data.getColumnIndex(ProductsEntry.PRODUCT_QUANTITY))));
                    mProductPrice.setText(String.valueOf(data.getInt(data.getColumnIndex(ProductsEntry.PRODUCT_PRICE))));
                    //String company = data.getString(data.getColumnIndex(ProductsEntry.PRODUCT_SUPPLIER));
                    int comPositionInSpinner = data.getInt(data.getColumnIndex(ProductsEntry.PRODUCT_SUPPLIER_NUMBER_IN_LIST));
                    mSuppliers.setSelection(comPositionInSpinner);
                    byte[] imgByte = data.getBlob(data.getColumnIndex(ProductsEntry.PRODUCT_IMG));
                    if (imgByte != null) {
                        Bitmap productImg = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                        mSupplierImg.setImageBitmap(productImg);
                    }
                }
                Log.e("Loader background", " called");
                break;
            case 1:
                suppliersAdapter.swapCursor(data);
                //Log.e("tag", "case 1 started");
                break;
        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        switch (loader.getId()) {
            case 0:
                mProductName.setText(" ");
                mProductQuantity.setText(0);
                mProductPrice.setText(0);
                mSuppliers.setSelection(0);
                break;
            default:
                suppliersAdapter.swapCursor(null);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }



    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}

