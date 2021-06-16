package hcmute.edu.vn.mssv18110326.Animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hcmute.edu.vn.mssv18110326.Activity.LoginActivity;
import hcmute.edu.vn.mssv18110326.Activity.MainActivity;
import hcmute.edu.vn.mssv18110326.R;

public class LoadAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_app);
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
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
    protected void onPause(){
        super.onPause();
        finish();
    }
}