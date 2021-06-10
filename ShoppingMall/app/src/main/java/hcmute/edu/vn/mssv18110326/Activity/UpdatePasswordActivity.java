package hcmute.edu.vn.mssv18110326.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.R;

public class UpdatePasswordActivity extends AppCompatActivity {

    Button btnReturn;
    EditText txtNewPassword;
    EditText txtConfirmPassword;
    Button btnUpdate;

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);

        btnReturn = (Button)findViewById(R.id.btnReturn);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);

        db = new DatabaseManager(this);

        final String names = GetSessionUser();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNewPassword.getText().toString().equals(txtConfirmPassword.getText().toString()))
                {
                    db.UpdatePassword(names,txtNewPassword.getText().toString());
                    Toast.makeText(getApplicationContext(),"Cập nhật thành công!",Toast.LENGTH_LONG).show();
                    openHome();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Nhập lại mật khẩu!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private String GetSessionUser() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }

    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}