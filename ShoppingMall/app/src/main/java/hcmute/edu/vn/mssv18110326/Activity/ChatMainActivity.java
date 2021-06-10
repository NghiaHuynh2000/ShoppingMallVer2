package hcmute.edu.vn.mssv18110326.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import hcmute.edu.vn.mssv18110326.R;

public class ChatMainActivity extends AppCompatActivity {

    static final String APP_ID = "88318";
    static final String AUTH_KEY = "FWhqDr3azgWM2cM";
    static final String AUTH_SECRET = "83Wv3qVBcdq-tq3";
    static final String ACCOUNT_KEY = "rrSyeA8Xx48Xa8cw36Q7";

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);

        initializeFramework();

        btnLogin = findViewById(R.id.main_btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QBUser qbUser = new QBUser("user", "vuaphaan123");

                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getBaseContext(), "Connect Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ChatMainActivity.this, ChatDialogActivity.class);
                        intent.putExtra("user", "user");
                        intent.putExtra("password", "vuaphaan123");
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(QBResponseException error) {
                        Toast.makeText(getBaseContext(), ""+ error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initializeFramework(){
        QBSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }
}