package hcmute.edu.vn.mssv18110326.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.Adapter.CartRecyclerViewAdapter;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.Model.Details;

import java.text.DecimalFormat;
import java.util.Date;

import hcmute.edu.vn.mssv18110326.R;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPaySDK;


public class PaymentActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private EditText name, email, phone, address;
    private Button btnNext;
    private String getName;
    private String getEmail;
    private int getPhone;
    private String getAddress;
    private String currentDateTimeString;
    int Total=0;
    String names;
    int id_user;
    String lblZpTransToken,txtToken;
    RadioButton cod,zalo;
    public static TextView txvTotalPrice;
    DatabaseManager db = new DatabaseManager(PaymentActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_bill);
        txvTotalPrice=(TextView)findViewById(R.id.txvTotalPrice);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ZaloPaySDK.init(553, Environment.SANDBOX);

        names = GetSessionUser();
        Cursor cursor = db.Profile_User(names);
        if(cursor.getCount()!=0){
            if(cursor.moveToFirst()){
                id_user = cursor.getInt(0);
            }
        }

        for (int i = 0; i< MainActivity.cart_main.size();i++){
            Total += MainActivity.cart_main.get(i).getPrice()*MainActivity.cart_main.get(i).getQty();
        }

        DecimalFormat decimalFormat = new DecimalFormat(" ###,###,###");
        txvTotalPrice.setText(decimalFormat.format(Total) + " VND");

        myRecyclerView = (RecyclerView)  findViewById(R.id.RecyclerCart);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(PaymentActivity.this));
        CartRecyclerViewAdapter recyclerViewAdapter = new CartRecyclerViewAdapter(PaymentActivity.this,MainActivity.cart_main);
        myRecyclerView.setAdapter(recyclerViewAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        myRecyclerView.addItemDecoration(itemDecoration);
        recyclerViewAdapter.notifyDataSetChanged();
        name = (EditText)findViewById(R.id.txtName);
        email = (EditText)findViewById(R.id.editTextEmail);
        phone = (EditText)findViewById(R.id.txtPhone);
        address = (EditText)findViewById(R.id.txtAddress);
        btnNext = (Button)findViewById(R.id.btnNext);
        cod = (RadioButton)findViewById(R.id.Cod);
        zalo = (RadioButton)findViewById(R.id.Zalo);
        btnNext.setOnClickListener(Next);
    }
    private final View.OnClickListener Next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(name.getText().toString().equals("")){
                String error = "Please enter Name";
                Toast.makeText(PaymentActivity.this,error, Toast.LENGTH_SHORT).show();
                return;
            }
            if(email.getText().toString().equals("")){
                String error ="Please enter Email";
                Toast.makeText(PaymentActivity.this,error, Toast.LENGTH_SHORT).show();
                return;
            }
            if(phone.getText().toString().equals("")){
                String error = "Please enter phone";
                Toast.makeText(PaymentActivity.this,error, Toast.LENGTH_SHORT).show();
                return;
            }
            if(address.getText().toString().equals("")){
                String error ="Please enter address";
                Toast.makeText(PaymentActivity.this,error, Toast.LENGTH_SHORT).show();
                return;
            }

            if(cod.isChecked() == true){
                Intent intent = new Intent(getApplicationContext(),DoneActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",getName);
                bundle.putString("email",getEmail);
                bundle.putString("address",getAddress);
                bundle.putInt("phone",getPhone);
                bundle.putString("date",currentDateTimeString);
                bundle.putInt("id",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(zalo.isChecked() == true){
                getName = name.getText().toString();
                getEmail = email.getText().toString();
                getPhone = Integer.parseInt(phone.getText().toString());
                getAddress = address.getText().toString();
                currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

                Intent intent = new Intent(getApplicationContext(),OpenZaloActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",getName);
                bundle.putString("email",getEmail);
                bundle.putString("address",getAddress);
                bundle.putInt("phone",getPhone);
                bundle.putString("date",currentDateTimeString);
                bundle.putInt("id",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }






        }
    };


    private void InsertBill(int id){
        int id_bill ;
        Bill bill = CreateBill(id);
        db.AddBill(bill);
        Cursor cursor = db.GetMaxIdBill();

        if(cursor.getCount()!=0){
            if(cursor.moveToFirst()){
                id_bill= cursor.getInt(0);
                for (int i = 0; i< MainActivity.cart_main.size();i++){

                    Details details = new Details(id_bill,MainActivity.cart_main.get(i).getId(),MainActivity.cart_main.get(i).getColor(),MainActivity.cart_main.get(i).getSize(),MainActivity.cart_main.get(i).getQty());
                    db.AddDetails(details);
                }
                db.UpdateTotal(id_bill,Total);
            }
        }
    }

    private  Bill CreateBill(int id){

        getName = name.getText().toString();
        getEmail = email.getText().toString();
        getPhone = Integer.parseInt(phone.getText().toString());
        getAddress = address.getText().toString();
        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        Bill bill = new Bill(null,getName,currentDateTimeString,getAddress,getEmail,getPhone,id_user,0,id);
        return bill;
    }
    public String GetSessionUser(){

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }

}