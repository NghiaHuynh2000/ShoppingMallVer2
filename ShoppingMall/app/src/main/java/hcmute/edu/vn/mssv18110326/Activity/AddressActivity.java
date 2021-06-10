package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.R;


import java.util.Date;

public class AddressActivity extends AppCompatActivity {
    private EditText name, email, phone, address;
    private Button btnNext, btnBack;
    private TextView error;
    private String getName;
    private String getEmail;
    private int getPhone;
    private String getAddress;
    private String currentDateTimeString;

    DatabaseManager db = new DatabaseManager(AddressActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        name = (EditText)findViewById(R.id.txtName);
        email = (EditText)findViewById(R.id.editTextEmail);
        phone = (EditText)findViewById(R.id.txtPhone);
        address = (EditText)findViewById(R.id.txtAddress);
        btnNext = (Button)findViewById(R.id.btnNext);
        error = (TextView)findViewById(R.id.error);
        btnNext.setOnClickListener(Next);
    }

    private final View.OnClickListener Next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(name.getText().toString().equals("")){
                error.setText("Vui lòng nhập tên của bạn");
                return;
            }
            if(email.getText().toString().equals("")){
                error.setText("Vui lòng nhập Email của bạn");
                return;
            }
            if(phone.getText().toString().equals("")){
                error.setText("Vui lòng nhập số điện thoại");
                return;
            }
            if(address.getText().toString().equals("")){
                error.setText("Vui lòng nhập địa chỉ");
                return;
            }
            int text ;
//            Bill bill = CreateBill();
//            db.AddBill(bill);
//            Cursor cursor = db.GetMaxIdBill();
//            int Total = 0;
//            if(cursor.getCount()!=0){
//                if(cursor.moveToFirst()){
//                    text= cursor.getInt(0);
//                    for (int i = 0; i< MainActivity.cart_main.size();i++){
//                        Total += MainActivity.cart_main.get(i).getPrice();;
//                        Details details = new Details(text,MainActivity.cart_main.get(i).getId(),MainActivity.cart_main.get(i).getQty());
//                        db.AddDetails(details);
//                    }
//                    db.UpdateTotal(text,Total);
//                }
//            }

            getName = name.getText().toString();
            getEmail = email.getText().toString();
            getPhone = Integer.parseInt(phone.getText().toString());
            getAddress = address.getText().toString();
            currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
            MainActivity.Bill_main = new Bill(null,getName,currentDateTimeString,getAddress,getEmail,getPhone,1,0,1);
            String id = MainActivity.Bill_main.getName();
           // Toast.makeText(AddressActivity.this,id,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
            startActivity(intent);

        }
    };
    private  Bill CreateBill(){
         getName = name.getText().toString();
         getEmail = email.getText().toString();
         getPhone = Integer.parseInt(phone.getText().toString());
         getAddress = address.getText().toString();
         currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        Bill bill = new Bill(null,getName,currentDateTimeString,getAddress,getEmail,getPhone,1,0,1);
        return bill;
    }
}