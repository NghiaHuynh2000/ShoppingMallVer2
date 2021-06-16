package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110326.DAO.CartDAO;
import hcmute.edu.vn.mssv18110326.DAO.UsersDAO;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Cart;
import hcmute.edu.vn.mssv18110326.Model.Users;
import hcmute.edu.vn.mssv18110326.R;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText email ;
    private EditText password ;
    TextView insert;
    private  Button sign_in_button;
    public String name;
    DatabaseManager db = new DatabaseManager(LoginActivity.this);
    CartDAO cartDAO=new CartDAO(db);
    private UsersDAO user = new UsersDAO(db);

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        password = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);
        Button btnlogin = findViewById(R.id.cirLoginButton);
        TextView dangkyuser = findViewById(R.id.dangkyuser);
        TextView forgot = findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forgot();

//                db.AddProduct();
//                Toast.makeText(getApplicationContext(),"Add product",Toast.LENGTH_LONG).show();
            }
        });

//

        dangkyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openActivity2();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = email.getText().toString();
                String pass = password.getText().toString();
                boolean check = user.login(name,pass);
                if(check == true) {
                    SharedPreferences sharedPreferences = getSharedPreferences("user_check", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("user_email", name);
                    editor.apply();
                    Login();
                    if(!(MainActivity.cart_main.isEmpty())){

                        ArrayList<Cart> cartCurrent=MainActivity.cart_main;

                        ArrayList<Cart> cartData=cartDAO.GetCart(name);



                        for(int i=0;i<cartCurrent.size();i++) {

                            boolean checkCart=false;

                            for (int position = 0; position < cartData.size(); position++) {

                                if (cartData.get(position).getId() == cartCurrent.get(i).getId()) {
                                        cartDAO.UpdateCart(name, cartCurrent.get(i).getId(), cartCurrent.get(i).getColor(), cartCurrent.get(i).getSize(), cartCurrent.get(i).getQty()+cartData.get(position).getQty());
                                        checkCart=true;
                                }
                            }
                            if(checkCart==false){
                                cartDAO.AddCart(name, cartCurrent.get(i).getId(), cartCurrent.get(i).getColor(), cartCurrent.get(i).getSize(), cartCurrent.get(i).getQty());
                            }

                        }

                    }
                    MainActivity.cart_main=cartDAO.GetCart(name);

                }
                else
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Set the dimensions of the sign-in button.
 /*       SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });*/
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {

                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();

                SharedPreferences sharedPreferences = getSharedPreferences("user_check", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user_email", personEmail);
                editor.apply();

                Users users = new Users(personName,personEmail,"0");
                user.addUsers(users);

                Toast.makeText(this, "Hello: " + personEmail, Toast.LENGTH_SHORT).show();
            }

            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Error:", e.toString());
        }
    }



    public void openActivity2(){
        Intent intent = new Intent(this, RegisterAcitivity.class);
        startActivity(intent);
    }
    public void Login(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void Forgot(){
        Intent intent = new Intent(this, ForgotActivity.class);
        startActivity(intent);
    }
//    protected void onPause(){
//        super.onPause();
//        finish();
//    }
}
