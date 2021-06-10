package hcmute.edu.vn.mssv18110326.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.edu.vn.mssv18110326.Adapter.CartRecyclerViewAdapter;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Cart;
import hcmute.edu.vn.mssv18110326.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartFragment extends Fragment {
    private RecyclerView myRecyclerView;
    LinearLayout linearLayout,empty,lv1,btn;
    Context context;
    private ArrayList<Cart> ListCart;
    DatabaseManager db = new DatabaseManager(context);
    Button home,payment;
    TextView txtTotal;
    public static int Total = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_cart, container, false);
        context = container.getContext();

        linearLayout = view.findViewById(R.id.ly2);
        lv1 = view.findViewById(R.id.ly1);
        btn = view.findViewById(R.id.btn);
        empty = view.findViewById(R.id.empty);

        if(MainActivity.cart_main.size()>0){
            empty.setVisibility(View.GONE);

        }else {
            linearLayout.setVisibility(View.GONE);
            lv1.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);

        }
        txtTotal = (TextView)view.findViewById(R.id.total);
        Total=0;
        for (int i = 0; i< MainActivity.cart_main.size();i++){
            Total += MainActivity.cart_main.get(i).getPrice()*MainActivity.cart_main.get(i).getQty();
        }
        DecimalFormat decimalFormat = new DecimalFormat(" ###,###,###");
        txtTotal.setText(decimalFormat.format(Total) + " VND");


        payment = (Button)view.findViewById(R.id.payment) ;
        payment.setOnClickListener(Next);
        ListCart = new ArrayList<>();
        myRecyclerView = (RecyclerView)  view.findViewById(R.id.RecyclerCart);
        myRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        final CartRecyclerViewAdapter recyclerViewAdapter = new CartRecyclerViewAdapter(getActivity(),MainActivity.cart_main);
        myRecyclerView.setAdapter(recyclerViewAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        myRecyclerView.addItemDecoration(itemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Total = (int) (Total - MainActivity.cart_main.get(position).getPrice());
                DecimalFormat decimalFormat = new DecimalFormat(" ###,###,###");
                txtTotal.setText(decimalFormat.format(Total) + " VND");
                MainActivity.cart_main.remove(position);
                if(MainActivity.cart_main.size()>0){
                    empty.setVisibility(View.GONE);

                }else {
                    linearLayout.setVisibility(View.GONE);
                    lv1.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);

                }
                recyclerViewAdapter.notifyDataSetChanged();


            }
        });
        itemTouchHelper.attachToRecyclerView(myRecyclerView);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private View.OnClickListener homeClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            db.DeleteCart();
//            Toast.makeText(CartActivity.this,"DELETE",Toast.LENGTH_SHORT).show();

        }
    };
    private  View.OnClickListener  Next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(MainActivity.cart_main.size()<=0){
                Toast.makeText(context,"Cart Is Empty!",Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(context, PaymentActivity.class);
            startActivity(intent);
        }
    };


}
