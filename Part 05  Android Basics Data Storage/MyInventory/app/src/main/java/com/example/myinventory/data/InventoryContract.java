package com.example.myinventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.security.PublicKey;


public final class InventoryContract implements BaseColumns {

    public static final String CONTENT_AUTHORITY = "com.example.myinventory";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCTS = "products";
    public static final String PATH_SUPPLIERS = "suppliers";

    public static final class ProductsEntry {

        public final static String TABLE_NAME = "products";
        public final static String PRODUCT_ID = BaseColumns._ID;
        public final static String PRODUCT_NAME = "name";
        public final static String PRODUCT_QUANTITY = "quantity";
        public final static String PRODUCT_PRICE = "price";
        public final static String PRODUCT_SUPPLIER = "supplier";
        public static final String PRODUCT_SUPPLIER_NUMBER_IN_LIST = "comNumber";
        public final static String PRODUCT_IMG = "img";

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        public static final Uri PRODUCTS_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);
    }

    public static final class SuppliersEntry {
        public final static String TABLE_NAME = "suppliers";

        public final static String SUPPLIER_ID = BaseColumns._ID;
        public final static String SUPPLIER_NAME = "name";
        public final static String COMPANY_NAME = "com";
        public static final String COMPANY_PHONE_NUM = "number";
        public final static String COMPANY_EMAIL = "email";

        public static final Uri SUPPLIERS_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SUPPLIERS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUPPLIERS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUPPLIERS;

    }
}
