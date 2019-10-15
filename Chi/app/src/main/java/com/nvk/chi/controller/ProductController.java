package com.nvk.chi.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nvk.chi.database.DBHelper;
import com.nvk.chi.model.Category;
import com.nvk.chi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private DBHelper dbHelper;
    SQLiteDatabase db;

    private static final String TABLE_PRODUCT = "tbProduct";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUATITY = "quatity";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SUM = "sum";
    private static final String COLUMN_CAT_ID = "cat_id";
    private static final String COLUMN_STATUS = "status";

    public ProductController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public Boolean InsertProduct(Product product){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,product.getName());
        contentValues.put(COLUMN_QUATITY,product.getQuatity());
        contentValues.put(COLUMN_PRICE,product.getPrice());
        contentValues.put(COLUMN_SUM,product.getSum());
        contentValues.put(COLUMN_CAT_ID,product.getCat_id());
        long result = db.insert(TABLE_PRODUCT,null,contentValues);
        db.close();
        return result > 0 ? true: false;
    }

    public List<Product> getProByCatID(int cat_id){
        db = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<Product>();
        String select = "SELECT * FROM "+ TABLE_PRODUCT +" WHERE " +
                COLUMN_STATUS + " = 0 AND "+COLUMN_CAT_ID +" = " + cat_id;
        Cursor cursor = db.rawQuery(select,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Product product = new Product();
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int quatity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUATITY)));
                double price = Double.parseDouble( cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)));


                product.setName(name);
                product.setQuatity(quatity);
                product.setPrice(price);
                productList.add(product);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return productList;
    }



}
