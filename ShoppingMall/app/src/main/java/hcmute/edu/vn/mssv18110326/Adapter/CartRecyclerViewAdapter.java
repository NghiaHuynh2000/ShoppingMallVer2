package hcmute.edu.vn.mssv18110326.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.Activity.CartFragment;
import hcmute.edu.vn.mssv18110326.Activity.DetailsActivity;
import hcmute.edu.vn.mssv18110326.Activity.MainActivity;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Cart;
import hcmute.edu.vn.mssv18110326.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;


public class CartRecyclerViewAdapter  extends  RecyclerView.Adapter<CartRecyclerViewAdapter.myViewHolder> {
    Context mContex;
    private ArrayList<Cart> cart;
    Button home,payment;
    Context context;
    DatabaseManager db=new DatabaseManager(context);

    public CartRecyclerViewAdapter(Context mContex, ArrayList<Cart> cart) {
        this.mContex = mContex;
        this.cart = cart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;


//        View v;
//        Context context = parent.getContext();
//        v = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
//
//        return new CartRecyclerViewAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {

        final int id = cart.get(position).getId();
        final String price = "Giá: " +cart.get(position).getPrice() + "VND";
        holder.name.setText(cart.get(position).getName());

        holder.price.setText(price);
        holder.color.setText("Màu sắc: "+cart.get(position).getColor());
        holder.size.setText("Kích cỡ: "+cart.get(position).getSize());

        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.cart_main.get(position).getQty() - 1!=0) {
                    MainActivity.cart_main.get(position).setQty(MainActivity.cart_main.get(position).getQty() - 1);
                    notifyDataSetChanged();
//                    DetailsActivity detailsActivity=new DetailsActivity();
//                    detailsActivity.Restart();
                }
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cart_main.get(position).setQty(MainActivity.cart_main.get(position).getQty() + 1);
                notifyDataSetChanged();
//                holder.total.
//                DetailsActivity detailsActivity=new DetailsActivity();
//                detailsActivity.Restart();
            }
        });
        String img = cart.get(position).getImage();
        int resourceId = mContex.getResources().getIdentifier(img, "drawable", mContex.getPackageName() );
        holder.imageView.setImageResource( resourceId );
        String qty = String.valueOf(cart.get(position).getQty());
     holder.solong.setText(qty);
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public TextView color;
        public TextView size;
        public TextView solong;
        public ImageView imageView;
        public Button btnSub;
        public Button btnAdd;
        public TextView total;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.txtTenSanPham);
            price = (TextView)itemView.findViewById(R.id.txtGiaSanPham);
            imageView= (ImageView)itemView.findViewById((R.id.imgAvatar));
            color=(TextView)itemView.findViewById(R.id.tvColor);
            size=(TextView)itemView.findViewById(R.id.tvSize);
            solong = (TextView)itemView.findViewById(R.id.txtsoluong);
            btnSub=(Button)itemView.findViewById(R.id.btnSub);
            btnAdd=(Button)itemView.findViewById(R.id.btnAdd);
            total=(TextView)itemView.findViewById(R.id.total);
        }
        public ImageView getImage(){ return this.imageView;}
    }

}