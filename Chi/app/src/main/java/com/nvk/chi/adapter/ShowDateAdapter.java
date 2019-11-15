package com.nvk.chi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.chi.R;
import com.nvk.chi.activity.ShowCategoryActivity;
import com.nvk.chi.controller.CategoryController;
import com.nvk.chi.model.Category;

import java.util.List;

public class ShowDateAdapter extends RecyclerView.Adapter<ShowDateAdapter.ShowHolder> {
    private Context context;
    private List<Category> categoryList;
    private ShowCategoryActivity showCategoryActivity;
    private CategoryController categoryController;

    public ShowDateAdapter(Context context, List<Category> categoryList,CategoryController categoryController) {
        this.context = context;
        this.categoryList = categoryList;
        this.showCategoryActivity =(ShowCategoryActivity) context;
        this.categoryController = categoryController;
    }

    @NonNull
    @Override
    public ShowDateAdapter.ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_showdate,parent,false);
        return new ShowDateAdapter.ShowHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowDateAdapter.ShowHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tvDate.setText(category.getCatName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class  ShowHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private final TextView tvDate;
        private final ShowDateAdapter adapter;

        public ShowHolder(@NonNull View itemView,ShowDateAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.tvDate.setOnClickListener(this);
            this.tvDate.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showCategoryActivity.OpenShowProduct(getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_category,null,false);
            final EditText edtDate = view.findViewById(R.id.edtDate);
            edtDate.setText(categoryList.get(getLayoutPosition()).getCatName());
            Button btnOK = view.findViewById(R.id.btnOK);
            builder.setView(view);
            final AlertDialog dialog = builder.create();

            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String date = edtDate.getText().toString();
                    if (date.isEmpty()){
                        Toast.makeText(context,"Hãy Nhập Đầy Đủ",Toast.LENGTH_SHORT).show();
                    }else{
                        Category category = new Category();
                        category.setId(categoryList.get(getLayoutPosition()).getId());
                        category.setCatName(date);
                        categoryController.UpdateCategory(category);
                        categoryList.clear();
                        categoryList.addAll( categoryController.getAllCat());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
            return true;
        }
    }
}
