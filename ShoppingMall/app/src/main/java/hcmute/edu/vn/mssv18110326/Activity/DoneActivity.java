package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110326.DAO.CartDAO;
import hcmute.edu.vn.mssv18110326.DAO.ProductDAO;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.Model.Details;
import hcmute.edu.vn.mssv18110326.R;

import java.util.Date;

public class DoneActivity extends AppCompatActivity {
    private String getName;
    private String getEmail;
    private int getPhone;
    private String getAddress;
    private String currentDateTimeString;
    int Total;
    String names;
    int stt;
    int id_user;

    DatabaseManager db = new DatabaseManager(DoneActivity.this);
    CartDAO cartDAO= new CartDAO(db);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        Bundle bundle = getIntent().getExtras();
        getName = bundle.getString("name");
        getEmail = bundle.getString("email");
        getAddress = bundle.getString("address");
        getPhone = bundle.getInt("phone");
        stt = bundle.getInt("id");
        names = GetSessionUser();
        Cursor cursor = db.Profile_User(names);
        if(cursor.getCount()!=0){
            if(cursor.moveToFirst()){
                id_user = cursor.getInt(0);
            }
        }
        for (int i = 0; i< MainActivity.cart_main.size(); i++){
            Total += MainActivity.cart_main.get(i).getPrice()*MainActivity.cart_main.get(i).getQty();
            cartDAO.DeleteCart(names,MainActivity.cart_main.get(i).getId(),MainActivity.cart_main.get(i).getColor(),MainActivity.cart_main.get(i).getSize());

        }
        InsertBill(stt);
        MainActivity.cart_main.clear();
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(3000);
                }catch (Exception e) {

                }
                finally
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);;
                    startActivity(intent);
                }
            }
        };
        bamgio.start();
    }

    private void InsertBill(int id){
        int id_bill ;
        Bill bill = CreateBill(id);
        db.AddBill(bill);
        Cursor cursor = db.GetMaxIdBill();

        if(cursor.getCount()!=0){
            if(cursor.moveToFirst()){
                id_bill= cursor.getInt(0);
                for (int i = 0; i< MainActivity.cart_main.size(); i++){

                    Details details = new Details(id_bill, MainActivity.cart_main.get(i).getId(), MainActivity.cart_main.get(i).getColor(),MainActivity.cart_main.get(i).getSize(),MainActivity.cart_main.get(i).getQty());
                    db.AddDetails(details);
                }
                db.UpdateTotal(id_bill,Total);
            }
        }
    }

    private Bill CreateBill(int id){
        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        Bill bill = new Bill(null,getName,currentDateTimeString,getAddress,getEmail,getPhone,id_user,0,id);
        return bill;
    }
    public String GetSessionUser(){

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }
    protected void onPause(){
        super.onPause();
        finish();
    }

}