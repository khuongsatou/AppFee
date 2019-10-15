package com.nvk.chi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nvk.chi.R;
import com.nvk.chi.controller.ProductController;
import com.nvk.chi.database.DBHelper;
import com.nvk.chi.model.Product;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.nvk.chi.activity.ShowProductActivity.KEY_CAT_ID;

public class InsertProductActivity extends AppCompatActivity {
    private EditText edtName,edtPrice;
    private Button btnOK,btnCancel;
    private Spinner snQuatity;

    private DBHelper helper;
    public ProductController productController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);


        Radiation();
        CreateDatabase();
        CreateAdapterQuality();
        CreateInsertProduct();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void CreateInsertProduct() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                int quatity = Integer.parseInt(snQuatity.getSelectedItem().toString());
                if (name.equals("") || price.equals("")){
                    Toast.makeText(getApplicationContext(),"Bạn phải nhập dữ liệu",Toast.LENGTH_SHORT).show();
                    return;
                }
                Double price_checked = Double.parseDouble(price);
                Product product = new Product();
                product.setName(name);
                product.setPrice(price_checked);
                product.setQuatity(quatity);
                product.setCat_id(getIntent().getIntExtra(KEY_CAT_ID,-1));
                Boolean result = productController.InsertProduct(product);
                if (result){
                    Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });



    }

    private void CreateAdapterQuality() {
        ArrayList<Integer> quatityList = new ArrayList<>();
        quatityList.add(1);
        quatityList.add(2);
        quatityList.add(3);
        quatityList.add(4);
        quatityList.add(5);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,quatityList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        snQuatity.setAdapter(adapter);
        snQuatity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InsertProductActivity.this,snQuatity.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void CreateDatabase() {
        helper = new DBHelper(this);
        try {
            helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productController = new ProductController(this);
    }

    private void Radiation() {
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);
        snQuatity = findViewById(R.id.snQuatity);
    }
}
