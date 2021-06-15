package hcmute.edu.vn.mssv18110326.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110326.DAO.UsersDAO;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Users;
import hcmute.edu.vn.mssv18110326.R;


public class RegisterAcitivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView login;
    private EditText Remail;
    private EditText Rname;
    private EditText Rphone;
    private EditText Rpassword;

    DatabaseManager db = new DatabaseManager(RegisterAcitivity.this);
    private UsersDAO user = new UsersDAO(db);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        login = (TextView)findViewById(R.id.login);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        Remail = (EditText)findViewById(R.id.email);
        Rphone=(EditText)findViewById(R.id.phone);
        Rpassword = (EditText)findViewById(R.id.password);
        Rname = (EditText)findViewById(R.id.name);
        db.AddProduct();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Rname.getText().toString().equals("")){
                    Toast.makeText(RegisterAcitivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Remail.getText().toString().equals("")){
                    Toast.makeText(RegisterAcitivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Rphone.getText().toString().equals("")){
                    Toast.makeText(RegisterAcitivity.this, "Please enter Phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Rpassword.getText().toString().equals("")){
                    Toast.makeText(RegisterAcitivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean checkemail = db.checkmail(Remail.getText().toString());
                if(checkemail == true){
                    Users users = CreateUser();
                    user.addUsers(users);
                    Toast.makeText(RegisterAcitivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(RegisterAcitivity.this, "Email is exist", Toast.LENGTH_SHORT).show();


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });
    }
    public void openActivity1(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private Users CreateUser(){
        String name = Rname.getText().toString();
        String phone=Rphone.getText().toString();
        String password = Rpassword.getText().toString();
        String email = Remail.getText().toString();
        Users users = new Users(name,phone,email,password);
        return users;
    }
}
