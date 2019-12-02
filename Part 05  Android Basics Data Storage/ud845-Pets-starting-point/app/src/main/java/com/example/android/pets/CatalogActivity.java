/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetDBHelper;
import com.example.android.pets.data.PetsCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.android.pets.data.PetContract.PetEntry;
/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView listView;
    private PetsCursorAdapter adapter;

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = new String[] {PetEntry.COLUMN_ID,
                PetEntry.COLUMN_NAME,
                PetEntry.COLUMN_BREED,
                PetEntry.COLUMN_GENDER,
                PetEntry.COLUMN_WEIGHT};

        return new CursorLoader(this,
                PetEntry.CONTENT_PATH,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        listView = findViewById(R.id.pets_list);
        View emptyList = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyList);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        adapter = new PetsCursorAdapter(this, null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                Uri uri = ContentUris.withAppendedId(PetEntry.CONTENT_PATH, id);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
    }


//    private void displayDatabaseInfo() {
//        // Perform this raw SQL query "SELECT * FROM pets"
//        // to get a Cursor that contains all rows from the pets table.
//        String[] projection = {PetEntry.COLUMN_ID,
//                PetEntry.COLUMN_NAME,
//                PetEntry.COLUMN_BREED,
//                PetEntry.COLUMN_GENDER,
//                PetEntry.COLUMN_WEIGHT};
//        //String selection = PetEntry.COLUMN_GENDER + "=?";
//        //String[] selectionArgs = new String[] {String.valueOf(PetEntry.MALE)};
//        Cursor cursor = getContentResolver().query(PetEntry.CONTENT_PATH, projection, null, null, null);
//        adapter = new PetsCursorAdapter(this, cursor);
//            // Display the number of rows in the Cursor (which reflects the number of rows in the
//            // pets table in the database).
////            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
////            displayView.setText("Number of rows in pets database table: " + cursor.getCount() + "\n \n");
////            displayView.append(PetEntry.COLUMN_ID + " - "
////                    + PetEntry.COLUMN_NAME + " - "
////                    + PetEntry.COLUMN_BREED + " - "
////                    + PetEntry.COLUMN_GENDER + " - "
////                    + PetEntry.COLUMN_WEIGHT + "\n");
//
//            listView.setAdapter(adapter);
//
//            int idIndex = cursor.getColumnIndex(PetEntry.COLUMN_ID);
//            int nameIndex = cursor.getColumnIndex(PetEntry.COLUMN_NAME);
//            int breedIndex = cursor.getColumnIndex(PetEntry.COLUMN_BREED);
//            int genderIndex = cursor.getColumnIndex(PetEntry.COLUMN_GENDER);
//            int weightIndex = cursor.getColumnIndex(PetEntry.COLUMN_WEIGHT);
//
////            while (cursor.moveToNext()) {
////                int id = cursor.getInt(idIndex);
////                String name = cursor.getString(nameIndex);
////                String breed = cursor.getString(breedIndex);
////                int gender = cursor.getInt(genderIndex);
////                int weight = cursor.getInt(weightIndex);
////
////                displayView.append(id + " - "
////                        + name + " - "
////                        + breed + " - "
////                        + gender + " - "
////                        + weight + "\n \n");
////
//            // Always close the cursor when you're done reading from it. This releases all its
//            // resources and makes it invalid.
//            //cursor.close();
//
//    }

    private void insertDummyData() {
        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_NAME, "toto");
        values.put(PetContract.PetEntry.COLUMN_BREED, "terrier");
        values.put(PetContract.PetEntry.COLUMN_GENDER, 2);
        values.put(PetContract.PetEntry.COLUMN_WEIGHT, 15);
        Uri uri = getContentResolver().insert(PetEntry.CONTENT_PATH, values);
        if (uri != null) {
            Toast.makeText(this, getString(R.string.pet_saved_successfully), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.pet_failed_to_save), Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_all_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deleteAllPets();
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

    /**
     * Perform the deletion of the pet in the database.
     */
    private void deleteAllPets() {
        int deleted = 0;
            deleted = getContentResolver().delete(PetEntry.CONTENT_PATH, null, null);

        if (deleted > 0) {
            Toast.makeText(this, getString(R.string.editor_delete_all_pet_successful), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.editor_delete_all_pet_failed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertDummyData();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
