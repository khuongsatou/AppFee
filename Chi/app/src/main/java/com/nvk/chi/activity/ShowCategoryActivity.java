package com.nvk.chi.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nvk.chi.R;
import com.nvk.chi.adapter.ShowDateAdapter;
import com.nvk.chi.controller.CategoryController;
import com.nvk.chi.database.DBHelper;
import com.nvk.chi.model.Category;

import java.util.Collections;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;

public class ShowCategoryActivity extends AppCompatActivity {
    private RecyclerView rcvDate;
    private FloatingActionButton fabAddCategory;
    private ShowDateAdapter adapter;

    private DBHelper dbHelper;
    private CategoryController categoryController;

    private List<Category> categoryList;

    public static final String KEY_SHOW_PRODUCT = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);

        Radiation();
        CreateDatabase();
        GetDataCategory();
        CreateAdapter();
        InsertDataCategory();
    }

    private void InsertDataCategory() {
        fabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Bạn Vừa chon thêm category",Snackbar.LENGTH_LONG).setAction("Action",null).show();

                ShowDialogAddCategory();
            }
        });
    }

    private void ShowDialogAddCategory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_category,null,false);
        builder.setView(view);

        Button btnOK =view.findViewById(R.id.btnOK);
        final EditText edtDate = view.findViewById(R.id.edtDate);

        final AlertDialog dialog = builder.create();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String date = edtDate.getText().toString();
                if (date.equals("")){
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập",Toast.LENGTH_SHORT).show();
                    return;
                }

                Category category = new Category();
                category.setCatName(date);

                Boolean result = categoryController.InsertCategory(category);
                if (result){
                    categoryList.clear();
                    categoryList.addAll(categoryController.getAllCat());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void GetDataCategory() {
        categoryList= new ArrayList<Category>();
        categoryList.clear();
        categoryList.addAll(categoryController.getAllCat());
    }

    private void CreateDatabase() {
        dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        categoryController = new CategoryController(this);

    }

    private void CreateAdapter() {
        adapter = new ShowDateAdapter(this,categoryList);
        rcvDate.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvDate.setLayoutManager(layoutManager);
        rcvDate.setAdapter(adapter);

    }

    private void Radiation() {
        rcvDate = findViewById(R.id.rcvDate);
        fabAddCategory = findViewById(R.id.fabAddCategory);
    }

    public void OpenShowProduct(int position){
        Intent intent = new Intent(this,ShowProductActivity.class);
        intent.putExtra(KEY_SHOW_PRODUCT,(position+1));
        startActivity(intent);
        Toast.makeText(this,"Chọn "+(position+1),Toast.LENGTH_SHORT).show();
    }
}
