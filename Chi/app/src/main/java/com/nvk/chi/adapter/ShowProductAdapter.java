package com.nvk.chi.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.chi.R;
import com.nvk.chi.activity.ShowProductActivity;
import com.nvk.chi.model.Product;
import android.content.Context;
import java.util.List;
import android.widget.TextView;

public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.ShowProductHolder> {
    private Context context;
    private List<Product> productList;
    private ShowProductActivity showProductActivity;


    public ShowProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.showProductActivity = (ShowProductActivity) context;
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
        product.setSum(product.getPrice() * product.getQuatity());
        holder.tvPrice.setText(product.getSum()+"");

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ShowProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private final ShowProductAdapter adapter;
        private TextView tvName,tvPrice,tvQuatity;
        private CardView cvProduct;

        public ShowProductHolder(@NonNull View itemView,ShowProductAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuatity = itemView.findViewById(R.id.tvQuatity);
            cvProduct = itemView.findViewById(R.id.cvProduct);
            cvProduct.setOnClickListener(this);
            cvProduct.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showProductActivity.UpdateDataCategory(productList.get(getAdapterPosition()).getName(),productList.get(getAdapterPosition()).getCat_id());
        }

        @Override
        public boolean onLongClick(View v) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("Bạn Có Muốn Xóa ?");
            dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showProductActivity.DeleteProduct(productList.get(getAdapterPosition()).getId());
                    dialog.dismiss();
                }
            });

            dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            dialog.create().show();

            return true;
        }
    }
}
