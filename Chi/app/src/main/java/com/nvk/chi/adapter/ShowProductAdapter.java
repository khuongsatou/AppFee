package com.nvk.chi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.chi.R;
import com.nvk.chi.model.Product;
import android.content.Context;
import java.util.List;
import android.widget.TextView;

public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.ShowProductHolder> {
    private Context context;
    private List<Product> productList;

    public ShowProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ShowProductAdapter.ShowProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_showproduct,parent,false);
        return new ShowProductHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowProductHolder holder, int position) {

        Product product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvQuatity.setText(product.getQuatity()+"");
        holder.tvPrice.setText((product.getPrice()*product.getQuatity())+"");

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ShowProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ShowProductAdapter adapter;
        public TextView tvName,tvPrice,tvQuatity;

        public ShowProductHolder(@NonNull View itemView,ShowProductAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuatity = itemView.findViewById(R.id.tvQuatity);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
