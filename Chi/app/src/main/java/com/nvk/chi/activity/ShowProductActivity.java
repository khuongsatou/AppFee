package com.nvk.chi.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nvk.chi.R;
import com.nvk.chi.adapter.ShowProductAdapter;
import com.nvk.chi.controller.ProductController;
import com.nvk.chi.model.Product;

import java.util.ArrayList;
import java.util.List;



public class ShowProductActivity extends AppCompatActivity {
    public static final String KEY_CAT_ID ="1234" ;
    private static final int REQUESTCODE = 123;
    public static final String KEY_CAT_NAME = "123";
    private RecyclerView rcvProduct;
    private FloatingActionButton fabAddProduct;

    private ShowProductAdapter adapter;
    private List<Product> productList;
    private ProductController productController;
    public static final String KEY_SHOW_PRODUCT = "123";
    private int position_cat_id = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        Radiation();
        GetData();
        CreateAdapter();
        InsertDataCategory();
    }

    private void InsertDataCategory() {
        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInsertProduct();
            }
        });
    }



    public void UpdateDataCategory(String name,int cat_id){
        Intent intent = new Intent(this,UpdateProductActivity.class);
        intent.putExtra(KEY_CAT_ID,cat_id);
        intent.putExtra(KEY_CAT_NAME,name);
        startActivityForResult(intent,REQUESTCODE);
    }

   public void DeleteProduct(int id){
        productController.DeleteProduct(id);
        productList.clear();
        productList.addAll(productController.getProByCatID(this.position_cat_id));
        adapter.notifyDataSetChanged();
   }


    private void ShowInsertProduct() {
        Intent intent = new Intent(this,InsertProductActivity.class);
        intent.putExtra(KEY_CAT_ID,this.position_cat_id);
        startActivityForResult(intent,REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE && resultCode == RESULT_OK){
            productList.clear();
            productList.addAll(productController.getProByCatID(this.position_cat_id));
            adapter.notifyDataSetChanged();
        }

    }

    private void GetData() {
        productController = new ProductController(this);
        productList = new ArrayList<>();
        this.position_cat_id = getIntent().getIntExtra(KEY_SHOW_PRODUCT,-1);
        productList.clear();
        productList.addAll(productController.getProByCatID(this.position_cat_id));
    }

    private void CreateAdapter() {
        rcvProduct.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvProduct.setLayoutManager(layoutManager);
        adapter = new ShowProductAdapter(this,productList);
        rcvProduct.setAdapter(adapter);
    }

    private void Radiation() {
        rcvProduct = findViewById(R.id.rcvProduct);
        fabAddProduct = findViewById(R.id.fabAddProduct);
    }
}
