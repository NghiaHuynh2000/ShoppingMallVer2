package hcmute.edu.vn.mssv18110326.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {
    Context mContex;
    int pricee;
    ArrayList<Product> products;

    public RecyclerViewAdapter(Context mContex, ArrayList<Product> products) {
        this.mContex = mContex;
        this.products = products;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContex).inflate(R.layout.item_view,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        final int id = products.get(position).getId();
        pricee= Integer.parseInt(products.get(position).getPrice());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String price = "Giá: " +decimalFormat.format(pricee) +" VND";
        holder.name.setText(products.get(position).getName());
//        holder.color.setText("Xám");
//        holder.size.setText("S");
//        Picasso.with(mContex)
//                .load( products.get(position).getImage())
//                .into(holder.imageView);
   /*     String img = products.get(position).getImage();
        int resourceId = mContex.getResources().getIdentifier(img, "drawable", mContex.getPackageName() );

        holder.imageView.setImageResource( resourceId );*/
        Bitmap bitmap= BitmapFactory.decodeByteArray(products.get(position).getImage(),0,products.get(position).getImage().length);
        holder.imageView.setImageBitmap(bitmap);

        holder.price.setText(price);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContex, products.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContex, DetailsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("ID", id);
                bundle.putString("Name",products.get(position).getName());
                intent.putExtras(bundle);


                mContex.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public  static  class myViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView price;
        private ImageView imageView;
        private ImageButton imageButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txtTenSanPham);
            price = (TextView)itemView.findViewById(R.id.txtGiaSanPham);

            imageView= (ImageView)itemView.findViewById((R.id.imgAvatar));

        }
        public ImageView getImage(){ return this.imageView;}
    }

}
