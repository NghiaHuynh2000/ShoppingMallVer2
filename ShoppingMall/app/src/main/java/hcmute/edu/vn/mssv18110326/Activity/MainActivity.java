package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import hcmute.edu.vn.mssv18110326.Adapter.viewpagerAdapter;
import hcmute.edu.vn.mssv18110326.DAO.CartDAO;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.Model.Cart;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewpager;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    public static ArrayList<Cart> cart_main;
    public static Bill Bill_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager= findViewById(R.id.viewpager1);
        bottomNavigationView = findViewById(R.id.bottom);
        viewpagerAdapter viewpagerAdapter = new viewpagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,this);
        viewpager.setAdapter(viewpagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.homepage).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.shop).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.cart).setChecked(true);
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.bill).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homepage:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.shop:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.cart:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.bill: {
                        String name= GetSessionUser();
                        if(name.isEmpty()){
                            openLoginWithBill();
                            break;
                        }
                        viewpager.setCurrentItem(3);
                        break;
                    }
                    case R.id.profile:
                    {
                        String name= GetSessionUser();
                        if(name.isEmpty()){
                            openLoginWithProfile();
                            break;
                        }
                        viewpager.setCurrentItem(4);
                        break;
                    }
                    default:
                        viewpager.setCurrentItem(0);
                        break;
                }
                return true;
            }
        });
        if(cart_main!= null){

        }else{
            cart_main = new ArrayList<>();
        }
    }



    ////////////////////////////////////////////////////////////////////////////
    public void Login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public String GetSessionUser(){

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }
    public void  Logout(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        SharedPreferences sharedPreferences = getSharedPreferences("user_check", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor =sharedPreferences.edit();
        editor.remove("user_email");
        editor.apply();
        finish();
    }

    public void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openLoginWithProfile(){
        Toast.makeText(this,"Đăng nhập để xem Thông tin cá nhân!", Toast.LENGTH_LONG).show();
        openLogin();
    }

    public void openLoginWithBill(){
        Toast.makeText(this,"Đăng nhập để xem Hóa đơn!", Toast.LENGTH_LONG).show();
        openLogin();
    }


}