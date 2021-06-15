package hcmute.edu.vn.mssv18110326.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


import hcmute.edu.vn.mssv18110326.Adapter.BillDetailsAdapter;
import hcmute.edu.vn.mssv18110326.DAO.CartDAO;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.BillDetails;
import hcmute.edu.vn.mssv18110326.R;


import java.util.ArrayList;

public class BillDetaiilsActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    int id_user;
    Context context;
    private ArrayList<BillDetails> BillDetailsList;
    DatabaseManager db = new DatabaseManager(this);
    CartDAO cartDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_acitivity);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int id = bundle.getInt("ID",0);
        myRecyclerView =  (RecyclerView)findViewById(R.id.detail_bill);
        BillDetailsList = new ArrayList<>();

        Cursor cursor = new DatabaseManager(getApplicationContext()).GetDetailsBill(id);

        cartDAO = new CartDAO(db);

        if (cursor != null & cursor.moveToFirst()){
            do {
                BillDetails billDetails = new BillDetails(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getBlob(5), cartDAO.Get_NameColor(cursor.getInt(6)),cartDAO.Get_NameSize(cursor.getInt(7)));
                BillDetailsList.add(billDetails);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        BillDetailsAdapter recyclerViewAdapter = new BillDetailsAdapter(getApplicationContext(),BillDetailsList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

    }

}