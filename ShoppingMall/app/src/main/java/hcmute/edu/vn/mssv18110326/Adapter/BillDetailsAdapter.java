package hcmute.edu.vn.mssv18110326.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.DAO.CartDAO;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.BillDetails;
import hcmute.edu.vn.mssv18110326.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BillDetailsAdapter extends RecyclerView.Adapter<BillDetailsAdapter.myViewHolder> {
    Context mContex;
    ArrayList<BillDetails> billDetails;
    CartDAO cartDAO;

    public BillDetailsAdapter(Context mContex, ArrayList<BillDetails> billDetails) {
        this.mContex = mContex;
        this.billDetails = billDetails;
        DatabaseManager db= new DatabaseManager(mContex);
        cartDAO = new CartDAO(db);
    }

    @NonNull
    @Override
    public BillDetailsAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContex).inflate(R.layout.item_bill_details,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BillDetailsAdapter.myViewHolder holder, int position) {
      //  final int id = billDetails.get(position).getId();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int quantity = billDetails.get(position).getQuantity();
        int price = Integer.parseInt(billDetails.get(position).getPrice());
        int total = quantity * price;
        holder.tvColor.setText("Màu sắc: "+billDetails.get(position).getColor());
        holder.tvSize.setText("Kích cỡ: "+billDetails.get(position).getSize());
        holder.txtTenSanPham.setText(billDetails.get(position).getName_product());
        holder.txtsoluong.setText(String.valueOf(billDetails.get(position).getQuantity()));
        holder.txtGiaSanPham.setText("Tổng tiền: " +decimalFormat.format(total) + " VND");
/*        String img = billDetails.get(position).getImage();
        int resourceId = mContex.getResources().getIdentifier(img, "drawable", mContex.getPackageName() );
        holder.imageView.setImageResource( resourceId );*/
        Bitmap bitmap= BitmapFactory.decodeByteArray(billDetails.get(position).getImage(),0,billDetails.get(position).getImage().length);
        holder.imgAvatar.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return billDetails.size()  ;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAvatar;
        private TextView txtTenSanPham;
        private TextView tvColor;
        private TextView tvSize;
        private TextView txtsoluong;
        private TextView txtGiaSanPham;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = (ImageView)itemView.findViewById(R.id.imgAvatar);
            txtTenSanPham=(TextView)itemView.findViewById(R.id.txtTenSanPham);
            tvColor = (TextView)itemView.findViewById(R.id.tvColor);
            tvSize= (TextView)itemView.findViewById(R.id.tvSize);
            txtsoluong= (TextView)itemView.findViewById(R.id.txtsoluong);
            txtGiaSanPham = (TextView) itemView.findViewById(R.id.txtGiaSanPham);


        }
    }
}
