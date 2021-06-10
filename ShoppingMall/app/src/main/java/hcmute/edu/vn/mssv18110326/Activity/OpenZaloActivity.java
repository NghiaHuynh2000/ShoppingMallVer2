package hcmute.edu.vn.mssv18110326.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110326.Api.CreateOrder;

import org.json.JSONObject;

import hcmute.edu.vn.mssv18110326.R;
import vn.zalopay.listeners.PayOrderListener;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;

public class OpenZaloActivity extends AppCompatActivity {
    TextView lblZpTransToken, txtToken;
    Button btnCreateOrder, btnPay;
    EditText txtAmount;
    private String getName;
    private String getEmail;
    private int getPhone;
    private String getAddress;
    private String currentDateTimeString;
    int Total;
    String names;
    int id_user;
    int stt;
    String lblZpTransToken1,txtToken1;
    private void BindView() {
        txtToken = findViewById(R.id.txtToken);
        lblZpTransToken = findViewById(R.id.lblZpTransToken);
        btnCreateOrder = findViewById(R.id.btnCreateOrder);
        txtAmount = findViewById(R.id.txtAmount);
        btnPay = findViewById(R.id.btnPay);

    }

    private void IsLoading() {
        lblZpTransToken.setVisibility(View.INVISIBLE);
        txtToken.setVisibility(View.INVISIBLE);
        btnPay.setVisibility(View.INVISIBLE);
    }

    private void IsDone() {
        lblZpTransToken.setVisibility(View.VISIBLE);
        txtToken.setVisibility(View.VISIBLE);
        btnPay.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_zalo);
        BindView();
        Bundle bundle = getIntent().getExtras();
        getName = bundle.getString("name");
        getEmail = bundle.getString("email");
        getAddress = bundle.getString("address");
        getPhone = bundle.getInt("phone");
        stt = bundle.getInt("id");
        btnCreateOrder.setVisibility(View.VISIBLE);
        lblZpTransToken.setVisibility(View.INVISIBLE);
        txtToken.setVisibility(View.INVISIBLE);
        StrictMode.ThreadPolicy policy = new
        StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        for (int i = 0; i< MainActivity.cart_main.size(); i++){
            Total += MainActivity.cart_main.get(i).getPrice();;

        }
        // ZaloPay SDK Init
        ZaloPaySDK.init(553, Environment.SANDBOX);
        // bind components with ids

        // handle CreateOrder
        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                CreateOrder orderApi = new CreateOrder();

                try {
                    JSONObject data = orderApi.createOrder(String.valueOf(Total));
                    Log.d("Amount", String.valueOf(Total));
                    lblZpTransToken.setVisibility(View.VISIBLE);
                    String code = data.getString("returncode");
                    Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();

                    if (code.equals("1")) {
                        lblZpTransToken.setText("zptranstoken");
                        txtToken.setText(data.getString("zptranstoken"));
                        txtToken1 = data.getString("zptranstoken");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                CreateTrans();
                String token = txtToken1;
                Log.d("Amount", token);
                ZaloPaySDK.getInstance().payOrder(OpenZaloActivity.this, token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(final String transactionId, final String transToken) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(OpenZaloActivity.this)
                                        .setTitle("Payment Success")
                                        .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                        .setMessage(String.format("Payment success for order: %s", transToken))
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })

                                        .setNegativeButton("Cancel", null).show();
                            }

                        });

                        Intent intent = new Intent(getApplicationContext(), DoneActivity.class);
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

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayErrorCode, String zpTransToken) {
                        if (zaloPayErrorCode == ZaloPayError.ZALOPAY_NOT_INSTALLED) {
                            final Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    new AlertDialog.Builder(OpenZaloActivity.this)
                                            .setTitle("Error Payment")
                                            .setMessage("ZaloPay App not install on this Device.")
                                            .setPositiveButton("Open Market", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    ZaloPaySDK.getInstance().navigateToStore(OpenZaloActivity.this);
                                                }
                                            })
                                            .setNegativeButton("Back", null).show();

                                }
                            }, 500);
                            Log.d("CODE_NOT_INSTALL", "onError: <br> <b> <i> ZaloPay App not install on this Device. </i> </b>");
                        } else {
                            Log.d("CODE_PAY_ERROR", "onError: On onPaymentError with paymentErrorCode: " + zaloPayErrorCode.toString() + " - zpTransToken: " + zpTransToken);
                            new AlertDialog.Builder(OpenZaloActivity.this)
                                    .setTitle("Payment Fail")
                                    .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayErrorCode.toString(), zpTransToken))
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setNegativeButton("Cancel", null).show();
                        }
                    }
                    @Override
                    public void onPaymentCanceled(String zpTransToken) {
                        new AlertDialog.Builder(OpenZaloActivity.this)
                                .setTitle("User Cancel Payment")
                                .setMessage(String.format("zpTransToken: %s \n", zpTransToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }
                });
            }
        });
    }
    private  void CreateTrans(){
        CreateOrder orderApi = new CreateOrder();

        try {
            JSONObject data = orderApi.createOrder(String.valueOf(Total));
            Log.d("Amount", String.valueOf(Total));

            String code = data.getString("returncode");
            Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();

            if (code.equals("1")) {
                lblZpTransToken1 ="zptranstoken";
                txtToken1 = data.getString("zptranstoken");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ZaloPaySDK.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}
