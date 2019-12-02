package com.example.myinventory.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myinventory.data.InventoryContract.SuppliersEntry;
import androidx.annotation.Nullable;

public class SuppliersDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "suppliersDb";

    private static final String CRATE_TABLE = "CREATE TABLE " + SuppliersEntry.TABLE_NAME + " (" +
            SuppliersEntry.SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SuppliersEntry.SUPPLIER_NAME+ " TEXT NOT NULL, " +
            SuppliersEntry.COMPANY_NAME + " TEXT NOT NULL, " +
            SuppliersEntry.COMPANY_PHONE_NUM + " INTEGER NOT NULL, " +
            SuppliersEntry.COMPANY_EMAIL + " Text)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + SuppliersEntry.TABLE_NAME;

    public SuppliersDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRATE_TABLE);
        ContentValues values = new ContentValues();
        values.put(SuppliersEntry.SUPPLIER_NAME, "no one");
        values.put(SuppliersEntry.COMPANY_NAME, "UNKNOWN");
        values.put(SuppliersEntry.COMPANY_PHONE_NUM, 111);
        values.put(SuppliersEntry.COMPANY_EMAIL, "noEmail");
        db.insert(SuppliersEntry.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
