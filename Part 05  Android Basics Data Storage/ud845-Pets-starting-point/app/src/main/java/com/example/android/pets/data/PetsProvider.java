package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.pets.data.PetContract.PetEntry;

public class PetsProvider extends ContentProvider {

    public static final String LOG_TAG = PetsProvider.class.getSimpleName();
    private PetDBHelper dbHelper;
    public static final int PETS = 100;
    public static final int PETS_ID = 101;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS, PETS);
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS +"/#", PETS_ID);
    }
    @Override
    public boolean onCreate() {
        dbHelper = new PetDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (match) {
            case PETS:
                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PETS_ID:
                selection = PetContract.PetEntry.COLUMN_ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
                default:
                    break;

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                return PetEntry.CONTENT_LIST_TYPE;
            case PETS_ID:
                return PetEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                return insertPets(uri, values);
                default:
                    throw new IllegalArgumentException("uri is not valid");
        }
    }
    private Uri insertPets(Uri uri, ContentValues values) {
       SQLiteDatabase db = dbHelper.getWritableDatabase();
        String name = values.getAsString(PetEntry.COLUMN_NAME);
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Pet requires a name");
        }

        // Check that the gender is valid
        Integer gender = values.getAsInteger(PetEntry.COLUMN_GENDER);
        if (gender == null || !PetEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("Pet requires valid gender");
        }

        // If the weight is provided, check that it's greater than or equal to 0 kg
        Integer weight = values.getAsInteger(PetEntry.COLUMN_WEIGHT);
        if (weight != null && weight < 0) {
            throw new IllegalArgumentException("Pet requires valid weight");
        }
       long idStatue = db.insert(PetContract.PetEntry.TABLE_NAME, null, values);
       if (idStatue == -1) {
           return null;
       }
       getContext().getContentResolver().notifyChange(uri, null);
       return ContentUris.withAppendedId(uri, idStatue);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        final int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                // Delete all rows that match the selection and selection args
                int rowsNum =  database.delete(PetEntry.TABLE_NAME, selection, selectionArgs);
                if (rowsNum != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsNum;
            case PETS_ID:
                // Delete a single row given by the ID in the URI
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                int nums = database.delete(PetEntry.TABLE_NAME, selection, selectionArgs);
                if (nums != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return nums;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = uriMatcher.match(uri);
        int rowsNum;
        switch (match) {
            case PETS:
                rowsNum = updatePet(uri, values, selection, selectionArgs);
                if (rowsNum != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsNum;
            case PETS_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsNum = updatePet(uri, values, selection, selectionArgs);
                if (rowsNum != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsNum;
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(values.containsKey(PetEntry.COLUMN_NAME)) {
            String name = values.getAsString(PetEntry.COLUMN_NAME);
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Pet requires a name");
            }
        }
        // Check that the gender is valid
        if (values.containsKey(PetEntry.COLUMN_GENDER)) {
            Integer gender = values.getAsInteger(PetEntry.COLUMN_GENDER);
            if (gender == null || !PetEntry.isValidGender(gender)) {
                throw new IllegalArgumentException("Pet requires valid gender");
            }
        }


        // If the weight is provided, check that it's greater than or equal to 0 kg
        if (values.containsKey(PetEntry.COLUMN_WEIGHT)) {
            Integer weight = values.getAsInteger(PetEntry.COLUMN_WEIGHT);
            if (weight != null && weight < 0) {
                throw new IllegalArgumentException("Pet requires valid weight");
            }
        }

        if (values.size() == 0) {
            return 0;
        }
       return db.update(PetEntry.TABLE_NAME, values, selection, selectionArgs);
    }
}
