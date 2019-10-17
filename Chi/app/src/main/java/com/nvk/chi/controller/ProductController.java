package com.nvk.chi.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nvk.chi.database.DBHelper;
import com.nvk.chi.model.Category;
import com.nvk.chi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private static final String TABLE_PRODUCT = "tbProduct";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUATITY = "quatity";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_CAT_ID = "cat_id";
    private static final String COLUMN_STATUS = "status";

    public ProductController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void InsertProduct(Product product){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,product.getName());
        contentValues.put(COLUMN_QUATITY,product.getQuatity());
        contentValues.put(COLUMN_PRICE,product.getPrice());
        contentValues.put(COLUMN_CAT_ID,product.getCat_id());
        db.insert(TABLE_PRODUCT,null,contentValues);
        db.close();
    }

    public void UpdateProduct(int id ,Product product){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,product.getName());
        contentValues.put(COLUMN_QUATITY,product.getQuatity());
        contentValues.put(COLUMN_PRICE,product.getPrice());
        db.update(TABLE_PRODUCT,contentValues,COLUMN_ID + "= ?" ,new String[]{(String.valueOf(id))});
        db.close();
    }

    public void DeleteProduct(int id){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATUS,1);
        long result = db.update(TABLE_PRODUCT,contentValues,COLUMN_ID + "= ?" ,new String[]{(String.valueOf(id))});
        db.close();
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
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int quatity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUATITY)));
                double price = Double.parseDouble( cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)));

                product.setId(id);
                product.setName(name);
                product.setQuatity(quatity);
                product.setPrice(price);
                product.setCat_id(cat_id);
                productList.add(product);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return productList;
    }

    public int getProByID(String name,int cat_id){
        db = dbHelper.getReadableDatabase();
        int id = 0;
        String select = "SELECT id FROM "+ TABLE_PRODUCT +" WHERE " +
                COLUMN_STATUS + " = 0 AND " +
                COLUMN_NAME + " = '" + name + "' AND "+
                COLUMN_CAT_ID + " = " +cat_id ;
        Cursor cursor = db.rawQuery(select,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        }
        cursor.close();
        db.close();
        return id;
    }

    public Product getOneProduct(int id){
        db = dbHelper.getReadableDatabase();
        Product product = new Product();
        String select = "SELECT * FROM "+ TABLE_PRODUCT +" WHERE " +
                COLUMN_STATUS + " = 0 AND " +
                COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(select,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            int quatity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUATITY)));
            double price = Double.parseDouble( cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)));
            product.setName(name);
            product.setQuatity(quatity);
            product.setPrice(price);
        }
        cursor.close();
        db.close();
        return product;
    }


}
