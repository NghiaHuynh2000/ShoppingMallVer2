package hcmute.edu.vn.mssv18110326.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.Activity.BillDetaiilsActivity;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;

public class OrderBill extends RecyclerView.Adapter<OrderBill.myViewHolder>{

    Context mContex;
    ArrayList<Bill> products;
    int pricee;
    public OrderBill(Context mContex, ArrayList<Bill> products) {
        this.mContex = mContex;
        this.products = products;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View v;
        v = LayoutInflater.from(mContex).inflate(R.layout.item_bill,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        final int id = products.get(position).getId();
        String price = String.valueOf(products.get(position).getTotal());
        holder.idd.setText("Hóa đơn ID: " +"#FASTSHOP"+id);


        holder.total.setText("Tổng: " + price + "VND");
        holder.date.setText("Ngày: "+products.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContex, BillDetaiilsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", id);
                intent.putExtras(bundle);
                mContex.startActivity(intent);
            }
        });

//       holder.imageView.setImageResource(R.drawable.cod);
//            if(products.get(position).getStt() == 1){
//                holder.imageView.setImageResource(R.drawable.cod);
//            }
//
//            if(products.get(position).getStt() == 2){
//                holder.imageView.setImageResource(R.drawable.logozalo);
//            }



    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView idd;
        private TextView total;
        private ImageView imageView;
        private TextView date;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            idd = (TextView)itemView.findViewById(R.id.idbill);
            total = (TextView)itemView.findViewById(R.id.total);
            date= (TextView)itemView.findViewById((R.id.date));
            itemView= (ImageView)itemView.findViewById((R.id.imgAvatar));

        }
    }
}
