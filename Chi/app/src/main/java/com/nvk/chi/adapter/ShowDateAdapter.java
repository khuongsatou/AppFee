package com.nvk.chi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.chi.R;
import com.nvk.chi.activity.ShowCategoryActivity;
import com.nvk.chi.model.Category;

import java.util.List;

public class ShowDateAdapter extends RecyclerView.Adapter<ShowDateAdapter.ShowHolder> {
    private Context context;
    private List<Category> categoryList;
    private ShowCategoryActivity showCategoryActivity;

    public ShowDateAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        this.showCategoryActivity =(ShowCategoryActivity) context;
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

    public class  ShowHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvDate;
        private final ShowDateAdapter adapter;

        public ShowHolder(@NonNull View itemView,ShowDateAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.tvDate.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showCategoryActivity.OpenShowProduct(getLayoutPosition());
        }
    }
}
