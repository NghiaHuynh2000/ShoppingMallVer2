package hcmute.edu.vn.mssv18110326.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.edu.vn.mssv18110326.Adapter.OrderBill;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;

public class Order_Bill_Activity extends Fragment {

    private RecyclerView myRecyclerView;
    Context context;
    private ArrayList<Bill> ProductList;
    DatabaseManager db ;
    String names;
    int id_user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_bill_order, container, false);
        context = container.getContext();
        db = new DatabaseManager(context);
        myRecyclerView =  view.findViewById(R.id.hdd_recyclerview);
        OrderBill recyclerViewAdapter = new OrderBill(context,ProductList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        myRecyclerView.addItemDecoration(itemDecoration);
        recyclerViewAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductList = new ArrayList<>();
        names = GetSessionUser();
        Cursor cursor1 = new DatabaseManager(getContext()).Profile_User(names);
        
        if(cursor1.getCount()!=0){
            if(cursor1.moveToFirst()){
                id_user = cursor1.getInt(0);
            }
        }
        Log.d("test1",String.valueOf(id_user));


       Cursor cursor = new DatabaseManager(getContext()).GetBills(id_user);

        if (cursor != null & cursor.moveToFirst()){
            do {

                Bill bill = new Bill (cursor.getInt(0),cursor.getString(5), cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getInt(3), cursor.getInt(7), cursor.getInt(6),cursor.getInt(8));

                ProductList.add(bill);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

    }
    public String GetSessionUser(){

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }



}
