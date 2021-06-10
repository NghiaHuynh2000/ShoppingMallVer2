package hcmute.edu.vn.mssv18110326.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.edu.vn.mssv18110326.Activity.DetailsActivity;
import hcmute.edu.vn.mssv18110326.Model.Product;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.myViewHolder> {

    Context mContex;
    ArrayList<Product> allProducts;
    int pricee;

    public ProductAdapter(Context mContex, ArrayList<Product> allProducts) {
        this.mContex = mContex;
        this.allProducts = allProducts;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContex).inflate(R.layout.item_view,parent,false);
        return new ProductAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        final int id = allProducts.get(position).getId();
        String price = "Giá: " + allProducts.get(position).getPrice()+"VND";
        pricee = Integer.parseInt(allProducts.get(position).getPrice());
        holder.Name.setText(allProducts.get(position).getName());
        holder.Price.setText(price);
        String img = allProducts.get(position).getImage();
        int resourceId = mContex.getResources().getIdentifier(img, "drawable", mContex.getPackageName() );
        holder.imgView.setImageResource( resourceId );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContex, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", id);
                bundle.putString("Name",allProducts.get(position).getName());
                intent.putExtras(bundle);

                mContex.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        private TextView Name;
        private TextView Price;
        private ImageButton imgButton;
        private ImageView imgView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = (TextView)itemView.findViewById(R.id.txtTenSanPham);
            Price = (TextView)itemView.findViewById(R.id.txtGiaSanPham);
            imgView= (ImageView)itemView.findViewById((R.id.imgAvatar));

        }
    }
}
