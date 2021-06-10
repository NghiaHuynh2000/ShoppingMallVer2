package hcmute.edu.vn.mssv18110326.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.Model.BillDetails;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;

public class BillDetailsAdapter extends RecyclerView.Adapter<BillDetailsAdapter.myViewHolder> {
    Context mContex;
    ArrayList<BillDetails> billDetails;

    public BillDetailsAdapter(Context mContex, ArrayList<BillDetails> billDetails) {
        this.mContex = mContex;
        this.billDetails = billDetails;
    }

    @NonNull
    @Override
    public BillDetailsAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContex).inflate(R.layout.item_bill,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BillDetailsAdapter.myViewHolder holder, int position) {
      //  final int id = billDetails.get(position).getId();
        int quantity = billDetails.get(position).getQuantity();
        int price = Integer.parseInt(billDetails.get(position).getPrice());
        String total = String.valueOf(quantity * price);
        holder.idd.setText(billDetails.get(position).getName_product());
        holder.total.setText("Số lượng: "+billDetails.get(position).getQuantity()+"VND");
        holder.date.setText("Tổng: " +total);
        String img = billDetails.get(position).getImage();
        int resourceId = mContex.getResources().getIdentifier(img, "drawable", mContex.getPackageName() );
        holder.imageView.setImageResource( resourceId );
    }

    @Override
    public int getItemCount() {
        return billDetails.size()  ;
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
            imageView= (ImageView)itemView.findViewById((R.id.imgAvatar));


        }
    }
}
