package hcmute.edu.vn.mssv18110326.Adapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import hcmute.edu.vn.mssv18110326.Activity.CartFragment;
import hcmute.edu.vn.mssv18110326.Activity.LoginActivity;
import hcmute.edu.vn.mssv18110326.Activity.MainActivity;
import hcmute.edu.vn.mssv18110326.Activity.MainFragment;
import hcmute.edu.vn.mssv18110326.Activity.Order_Bill_Activity;
import hcmute.edu.vn.mssv18110326.Activity.ProfileActivity;
import hcmute.edu.vn.mssv18110326.Activity.ShopFragment;

public class viewpagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    public viewpagerAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                return new MainFragment();
            case 1:
                return new ShopFragment();
            case 2:
                return new CartFragment();
            case 3:
                return new Order_Bill_Activity();
            case 4:
                return new ProfileActivity();
            default:
                return new MainFragment();
        }
        //return  null;
    }

    @Override
    public int getCount()
    {
        return 5;
    }

    public String GetSessionUser(){

        SharedPreferences sharedPreferences = this.context.getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }

}