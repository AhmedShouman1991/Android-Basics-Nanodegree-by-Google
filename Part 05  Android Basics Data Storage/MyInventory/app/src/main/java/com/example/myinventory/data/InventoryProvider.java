package com.example.myinventory.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myinventory.data.InventoryContract.ProductsEntry;
import com.example.myinventory.data.InventoryContract.SuppliersEntry;

public class InventoryProvider extends ContentProvider {
    public static final String LOG_TAG = InventoryProvider.class.getSimpleName();
    public final static int PRODUCT = 100;
    public final static int PRODUCT_ID = 101;
    public final static int SUPPLIER = 200;
    public final static int SUPPLIER_ID = 201;

    private ProductsDBHelper productsDB;
    private SuppliersDbHelper suppliersDb;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_PRODUCTS, PRODUCT);
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_PRODUCTS + "/#", PRODUCT_ID);
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_SUPPLIERS, SUPPLIER);
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_SUPPLIERS +"/#", SUPPLIER_ID);
    }
    @Override
    public boolean onCreate() {
        productsDB = new ProductsDBHelper(getContext());
        suppliersDb = new SuppliersDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase products = productsDB.getReadableDatabase();
        SQLiteDatabase suppliers = suppliersDb.getReadableDatabase();
        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                cursor = products.query(ProductsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID:
                selection = ProductsEntry.PRODUCT_ID +"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = products.query(ProductsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SUPPLIER:
                cursor = suppliers.query(SuppliersEntry.TABLE_NAME ,projection, selection, selectionArgs,null, null, sortOrder);
                break;
            case SUPPLIER_ID:
                selection = SuppliersEntry.SUPPLIER_ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = suppliers.query(SuppliersEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
                default:
                    break;
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                    return ProductsEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return ProductsEntry.CONTENT_ITEM_TYPE;
            case SUPPLIER:
                return SuppliersEntry.CONTENT_LIST_TYPE;
            case SUPPLIER_ID:
                return SuppliersEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + uriMatcher.match(uri));
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                return insertNewProduct(uri, values);
            case SUPPLIER:
                return insertNewSupplier(uri, values);
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + uriMatcher.match(uri));
        }
    }

    private Uri insertNewProduct(Uri uri, ContentValues values) {
        SQLiteDatabase products = productsDB.getWritableDatabase();

        String productName = values.getAsString(ProductsEntry.PRODUCT_NAME);
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product required valid Name");
        }
        Integer productQuantity = values.getAsInteger(ProductsEntry.PRODUCT_QUANTITY);
        if (productQuantity == null || productQuantity < 0) {
            throw new IllegalArgumentException("Product required valid Quantity");
        }
        Integer productPrice = values.getAsInteger(ProductsEntry.PRODUCT_PRICE);
        if (productPrice == null || productPrice < 0) {
            throw new IllegalArgumentException("product required valid Price");
        }

        long id = products.insert(ProductsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertNewSupplier(Uri uri, ContentValues values) {
        SQLiteDatabase suppliers = suppliersDb.getWritableDatabase();
        String name = values.getAsString(SuppliersEntry.SUPPLIER_NAME);
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Supplier required valid Name");
        }
        String comName = values.getAsString(SuppliersEntry.COMPANY_NAME);
        if (comName == null || comName.isEmpty()) {
            throw new IllegalArgumentException("Supplier required valid Company Name");
        }
        Integer phone = values.getAsInteger(SuppliersEntry.COMPANY_PHONE_NUM);
        if (phone == null) {
            throw new IllegalArgumentException("Supplier required valid phone number");
        }
        long id = suppliers.insert(SuppliersEntry.TABLE_NAME, null, values);

        if (id == -1) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase products = productsDB.getWritableDatabase();
        SQLiteDatabase suppliers = suppliersDb.getWritableDatabase();
        int rows = 0;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                rows = products.delete(ProductsEntry.TABLE_NAME, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            case PRODUCT_ID:
                selection = ProductsEntry.PRODUCT_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rows = products.delete(ProductsEntry.TABLE_NAME, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            case SUPPLIER:
                rows = suppliers.delete(SuppliersEntry.TABLE_NAME, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            case SUPPLIER_ID:
                selection = SuppliersEntry.SUPPLIER_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rows = suppliers.delete(SuppliersEntry.TABLE_NAME, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rows = 0;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                rows = updateProduct(ProductsEntry.TABLE_NAME, values, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            case PRODUCT_ID:
                selection = ProductsEntry.PRODUCT_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rows = updateProduct(ProductsEntry.TABLE_NAME, values, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            case SUPPLIER:
                rows = updateSupplier(SuppliersEntry.TABLE_NAME, values, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            case SUPPLIER_ID:
                selection = SuppliersEntry.SUPPLIER_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rows = updateSupplier(SuppliersEntry.TABLE_NAME, values, selection, selectionArgs);
                if (rows != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rows;
            default:
                return 0;
        }
    }

    private int updateProduct(String tableName,ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase products = productsDB.getWritableDatabase();
        if (values.containsKey(ProductsEntry.PRODUCT_NAME)) {
            String productName = values.getAsString(ProductsEntry.PRODUCT_NAME);
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("Product need valid name");
            }
        }
        if (values.containsKey(ProductsEntry.PRODUCT_QUANTITY)) {
            Integer quantity = values.getAsInteger(ProductsEntry.PRODUCT_QUANTITY);
            if (quantity == null || quantity < 0) {
                throw new IllegalArgumentException("Product need valid Quantity");
            }
        }
        if (values.containsKey(ProductsEntry.PRODUCT_PRICE)) {
            Integer price = values.getAsInteger(ProductsEntry.PRODUCT_PRICE);
            if (price == null || price < 0) {
                throw new IllegalArgumentException("Product need valid Price");
            }
        }
        if (values.size() == 0) {
            return 0;
        }
        return products.update(tableName, values, selection, selectionArgs);
    }

    private int updateSupplier(String tableName,ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase suppliers = suppliersDb.getWritableDatabase();
        if (values.containsKey(SuppliersEntry.SUPPLIER_NAME)) {
            String supplierName = values.getAsString(ProductsEntry.PRODUCT_NAME);
            if (supplierName == null || supplierName.isEmpty()) {
                throw new IllegalArgumentException("Supplier need valid name");
            }
        }
        if (values.containsKey(SuppliersEntry.COMPANY_NAME)) {
            String comName = values.getAsString(SuppliersEntry.COMPANY_NAME);
            if (comName == null || comName.isEmpty()) {
                throw new IllegalArgumentException("Supplier need valid Company Name");
            }
        }
        if (values.containsKey(SuppliersEntry.COMPANY_PHONE_NUM)) {
            Integer phone = values.getAsInteger(SuppliersEntry.COMPANY_PHONE_NUM);
            if (phone == null ) {
                throw new IllegalArgumentException("Supplier need valid Phone Number");
            }
        }
        if (values.size() == 0) {
            return 0;
        }
        return suppliers.update(tableName, values, selection, selectionArgs);
    }

}
