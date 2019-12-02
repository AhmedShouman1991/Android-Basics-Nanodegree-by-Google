package com.example.myinventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myinventory.data.InventoryContract.ProductsEntry;
import androidx.annotation.Nullable;

public class ProductsDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "productsDb";

    private static final String CRATE_TABLE = "CREATE TABLE " + ProductsEntry.TABLE_NAME + " (" +
            ProductsEntry.PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ProductsEntry.PRODUCT_NAME + " TEXT Not Null, " +
            ProductsEntry.PRODUCT_PRICE + " INTEGER NOT NULL DEFAULT 0, " +
            ProductsEntry.PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, " +
            ProductsEntry.PRODUCT_SUPPLIER + " TEXT NOT NULL, " +
            ProductsEntry.PRODUCT_SUPPLIER_NUMBER_IN_LIST + " INTEGER NOT NULL, " +
            ProductsEntry.PRODUCT_IMG + " BLOB)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + ProductsEntry.TABLE_NAME;

    public ProductsDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRATE_TABLE);
        Log.e("tag", CRATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
