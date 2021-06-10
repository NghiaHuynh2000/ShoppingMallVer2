package hcmute.edu.vn.mssv18110326.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.R;

public class EditProfileActivity extends AppCompatActivity {

    Button btnReturn;
    EditText txtName;
    EditText txtPhone;
    EditText txtEmail;
    EditText txtAddress;
    Button btnUpdate;

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        txtName = (EditText) findViewById(R.id.txtName);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAddress = (EditText) findViewById(R.id.txtAddress);

        btnReturn = (Button)findViewById(R.id.btnReturn);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);

        db = new DatabaseManager(this);

        final String names = GetSessionUser();

        Cursor cursor = db.Profile_User(names);
        if(cursor.getCount()!=0){
            if(cursor.moveToFirst()){
                txtName.setText(cursor.getString(1));
                txtEmail.setText(cursor.getString(2));
                txtPhone.setText(cursor.getString(3));
                txtAddress.setText(cursor.getString(5));
            }
        }

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.UpdateProfile(txtName.getText().toString(),txtPhone.getText().toString(),txtEmail.getText().toString(),txtAddress.getText().toString(),names);
                if(!(txtEmail.getText().toString().equals(""))) {
                    UpdateUserEmail(txtEmail.getText().toString());
                }
                Toast.makeText(getApplicationContext(),"Cập nhật thành công!",Toast.LENGTH_LONG).show();
                openHome();
            }
        });
    }

    public String GetSessionUser(){

        SharedPreferences sharedPreferences = this.getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }

    public void UpdateUserEmail(String personEmail){
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_check", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_email", personEmail);
        editor.apply();

    }

    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}