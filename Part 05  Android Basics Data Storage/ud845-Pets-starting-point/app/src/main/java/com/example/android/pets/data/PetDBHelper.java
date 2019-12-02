package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.android.pets.data.PetContract.PetEntry;

public class PetDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pets.db";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + PetEntry.TABLE_NAME + " (" +
             PetEntry.COLUMN_ID + " INTEGER PRIMARY kEY AUTOINCREMENT, " +
             PetEntry.COLUMN_NAME +" TEXT NOT NULL, " +
             PetEntry.COLUMN_BREED +" TEXT, " +
             PetEntry.COLUMN_GENDER +" INTEGER NOT NULL, " +
             PetEntry.COLUMN_WEIGHT +" INTEGER NOT NULL DEFAULT 0) ";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +  PetEntry.TABLE_NAME;


    public PetDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
