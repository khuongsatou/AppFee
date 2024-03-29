package com.nvk.chi.controller;

import com.nvk.chi.database.DBHelper;
import com.nvk.chi.model.Category;
import com.nvk.chi.model.Product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.ArrayList;

public class CategoryController {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    private static final String TABLE_CATEGORY = "tbCategory";
    private static final String COLUMN_CATNAME = "CatName";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SUM = "sum";
    private static final String COLUMN_STATUS = "Status";




    public CategoryController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<Category> getAllCat(){
        db = dbHelper.getReadableDatabase();
        List<Category> categoryList = new ArrayList<Category>();
        String select = "SELECT * FROM "+ TABLE_CATEGORY +" WHERE " + COLUMN_STATUS + " = 0 ";
        Cursor cursor = db.rawQuery(select,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Category category = new Category();
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String catName = cursor.getString(cursor.getColumnIndex(COLUMN_CATNAME));
                category.setId(id);
                category.setCatName(catName);
                categoryList.add(category);

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return categoryList;
    }


    public void InsertCategory(Category category){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATNAME,category.getCatName());
        db.insert(TABLE_CATEGORY,null,contentValues);
        db.close();
    }

    public void UpdateCategory(Category category){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATNAME,category.getCatName());
        db.update(TABLE_CATEGORY,contentValues,COLUMN_ID + "= ?" ,new String[]{(String.valueOf(category.getId()))});
        db.close();
    }



}
