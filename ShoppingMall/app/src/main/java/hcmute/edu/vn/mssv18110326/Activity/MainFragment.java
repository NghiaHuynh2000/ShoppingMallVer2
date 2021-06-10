package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.Adapter.ProductAdapter;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.Model.Cart;
import hcmute.edu.vn.mssv18110326.Model.Product;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    public static ArrayList<Cart> cart_fragment;
    public static Bill Bill_fragment;

    RecyclerView prodItemRecycler;
    Context context;
    ArrayList<Product> productsList;
    EditText edtSearch;
    Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = container.getContext();
        if(cart_fragment!= null){

        }else{
            cart_fragment = new ArrayList<>();
            cart_fragment = MainActivity.cart_main;
        }

        prodItemRecycler = (RecyclerView) view.findViewById(R.id.product_recycler);

        edtSearch=(EditText) view.findViewById(R.id.edtSearch);
        btnSearch=(Button)view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edtSearch.getText().toString();
                productsList = new ArrayList<>();
                Cursor cursor = new DatabaseManager(getContext()).getSearchProducts(search);
                if (cursor != null && cursor.moveToFirst()) {
                    do{
                        Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6));
                        productsList.add(product);
                    }while(cursor.moveToNext());
                }
                cursor.close();

                ProductAdapter productAdapter = new ProductAdapter(context, productsList);
                prodItemRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                prodItemRecycler.setAdapter(productAdapter);

            }
        });

        ProductAdapter productAdapter = new ProductAdapter(context, productsList);
        prodItemRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        prodItemRecycler.setAdapter(productAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productsList = new ArrayList<>();
        Cursor cursor = new DatabaseManager(getContext()).getAllProducts();
        if (cursor != null && cursor.moveToFirst()) {
            do{
                Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6));
                productsList.add(product);
            }while(cursor.moveToNext());
        }

        cursor.close();
    }

}
