package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.Adapter.RecyclerViewAdapter;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Product;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;

public class PhuKienFragment extends Fragment {
    private RecyclerView myRecyclerView;
    Context context;
    private ArrayList<Product> ProductList;

    DatabaseManager  db = new DatabaseManager(context);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_phu_kien, container, false);
        context = container.getContext();

        myRecyclerView =  view.findViewById(R.id.ao_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,ProductList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        myRecyclerView.addItemDecoration(itemDecoration);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductList = new ArrayList<>();

        Cursor cursor = new DatabaseManager(getContext()).GetPhuKien();

        if (cursor != null & cursor.moveToFirst()){
            do {
                Product product = new Product(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getBlob(3), cursor.getInt(4), cursor.getString(5));

                ProductList.add(product);
            }
            while (cursor.moveToNext());
        }
        cursor.close();


    }



}
