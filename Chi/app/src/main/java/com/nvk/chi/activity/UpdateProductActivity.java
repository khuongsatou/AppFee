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

import java.util.ArrayList;

import static com.nvk.chi.activity.ShowProductActivity.KEY_CAT_ID;
import static com.nvk.chi.activity.ShowProductActivity.KEY_CAT_NAME;

public class UpdateProductActivity extends AppCompatActivity {
    private EditText edtName,edtPrice;
    private Button btnOK,btnCancel;
    private Spinner snQuatity;
    public ProductController productController;
    public int id_product = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Radiation();
        CreateDatabase();
        CreateAdapterQuality();
        LoadDataProduct();
        CreateUpdateProduct();
    }

    private void LoadDataProduct() {
        Intent intent = getIntent();
        int cat_id = intent.getIntExtra(KEY_CAT_ID,-1);
        String name = intent.getStringExtra(KEY_CAT_NAME);
        this.id_product = productController.getProByID(name,cat_id);
        Product product = productController.getOneProduct(this.id_product);
        edtName.setText(product.getName());
        edtPrice.setText(product.getPrice()+"");
        snQuatity.setSelection(product.getQuatity() -1);
    }

    private void CreateUpdateProduct() {
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
                Double price_checked = 0.0;
                try{
                     price_checked=Double.parseDouble(price);
                }catch (Exception err){
                    Toast.makeText(getApplicationContext(),"Bạn Nhập giá Không Hợp lệ",Toast.LENGTH_SHORT).show();
                }


                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price_checked);
                    product.setQuatity(quatity);
                    product.setCat_id(getIntent().getIntExtra(KEY_CAT_ID,-1));
                    productController.UpdateProduct( UpdateProductActivity.this.id_product,product);
                    setResult(RESULT_OK);
                    finish();



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

    }



    private void CreateDatabase() {
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
